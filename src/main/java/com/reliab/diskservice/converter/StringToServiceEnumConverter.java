package com.reliab.diskservice.converter;

import com.reliab.diskservice.enums.TypeOfService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToServiceEnumConverter implements Converter<String, TypeOfService> {

    @Override
    public TypeOfService convert(String source){
        return TypeOfService.valueOf(source.toUpperCase());
    }
}
