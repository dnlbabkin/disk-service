package com.reliab.diskservice.converter;

import com.reliab.diskservice.enums.ServicesEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToServiceEnumConverter implements Converter<String, ServicesEnum> {

    @Override
    public ServicesEnum convert(String source){
        try {
            return ServicesEnum.valueOf(source);
        } catch (Exception e) {
            return ServicesEnum.UNKNOWN;
        }
    }
}
