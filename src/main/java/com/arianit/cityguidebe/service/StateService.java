package com.arianit.cityguidebe.service;

import com.arianit.cityguidebe.dao.StateRepository;
import com.arianit.cityguidebe.dto.StateDto;
import com.arianit.cityguidebe.dto.request.StateRequest;
import com.arianit.cityguidebe.entity.State;
import com.arianit.cityguidebe.exception.ResourceNotFoundException;
import com.arianit.cityguidebe.mapper.StateMapper;
import com.arianit.cityguidebe.util.ReflectionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StateService {

    private final StateRepository stateRepository;
    private final StateMapper stateMapper;

    public StateDto create(StateRequest stateRequest) {
        State state = stateMapper.toEntity(stateRequest);
        return stateMapper.toDto(stateRepository.save(state));
    }

    public StateDto getById(Long id) {
        State stateInDb = stateRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(String.format(
                        "Entity with %s id dosent exist",id
                ))
        );
        return stateMapper.toDto(stateInDb);
    }

    public List<StateDto> getAll() {
        List<State> states = stateRepository.findAll();
        return states.stream()
                .map(stateMapper::toDto)
                .collect(Collectors.toList());

    }

    public StateDto update(Long id, Map<String, Object> fields){
        State stateInDb = stateRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException(String.format("State with id %s not found", id)));
        fields.forEach((key, value) ->{
            ReflectionUtil.setFieldValue(stateInDb, key, value);
        });
        return stateMapper.toDto(stateRepository.save(stateInDb));
    }


    public void deleteById (Long id){
        State stateInDb = stateRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(String.format(
                        "Entity with %s id dosent exist",id
                ))
        );
                stateRepository.deleteById(id);
    }

}
