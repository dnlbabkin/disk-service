package com.reliab.diskservice.service;

import com.reliab.diskservice.enums.ServicesEnum;
import com.reliab.diskservice.model.File;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ChoiceService {

    private final Map<String, DiskService> diskServiceMap;

    public List<File> setService(ServicesEnum servicesEnum) {
        DiskService diskServices = diskServiceMap.get(servicesEnum.getService());

        return diskServices.getFiles();
    }
}
