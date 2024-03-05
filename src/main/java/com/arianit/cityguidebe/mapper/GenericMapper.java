package com.arianit.cityguidebe.mapper;

public interface GenericMapper<E, D, R>{

    D toDto(E entity);

    E toEntity(R request);
}
