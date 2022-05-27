package com.reliab.diskservice.components;

import com.reliab.diskservice.enums.NameService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EnumConverter implements Converter<String, NameService> {

    @Override
    public NameService convert(String source){
        return NameService.valueOf(source.toUpperCase());
    }
}
