package com.example.Demo.Comman;

import org.modelmapper.ModelMapper;

public class EntityDTOConverter<F, T> {
    private ModelMapper modelMapper = new ModelMapper();
    private Class<T> toClass;

    public EntityDTOConverter(Class<T> toClass) {
        this.toClass = toClass;
    }

    public T convert(F from) {
        T to = this.modelMapper.map(from, this.toClass);
        return to;
    }
}
