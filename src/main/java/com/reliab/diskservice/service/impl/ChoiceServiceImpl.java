package com.reliab.diskservice.service.impl;

import com.reliab.diskservice.enums.TypeOfService;
import com.reliab.diskservice.model.Disk;
import com.reliab.diskservice.model.Path;
import com.reliab.diskservice.model.Resources;
import com.reliab.diskservice.service.*;
import com.yandex.disk.rest.exceptions.ServerException;
import com.yandex.disk.rest.exceptions.ServerIOException;
import com.yandex.disk.rest.json.DiskInfo;
import com.yandex.disk.rest.json.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ChoiceServiceImpl implements ChoiceService {

    private final Map<String, DiskService> diskServiceMap;


    @Override
    public Disk getFiles(TypeOfService typeOfService) throws GeneralSecurityException, IOException {
        DiskService diskServices = diskServiceMap.get(typeOfService.getService());

        return diskServices.getFiles();
    }

    @Override
    public DiskInfo getInfo(TypeOfService typeOfService) throws ServerIOException, IOException {
        DiskService diskInfo = diskServiceMap.get(typeOfService.getService());

        return diskInfo.getInfo();
    }

    @Override
    public Resources getFlatResource(TypeOfService typeOfService) throws ServerIOException, IOException, GeneralSecurityException {
        DiskService patchResources = diskServiceMap.get(typeOfService.getService());

        return patchResources.getFlatResource();
    }

    @Override
    public Resource getResources(TypeOfService typeOfService, String path) throws ServerIOException, IOException {
        DiskService resources = diskServiceMap.get(typeOfService.getService());

        return resources.getResources(path);
    }

    @Override
    public void downloadFile(TypeOfService typeOfService, Path path, String file) throws ServerException, IOException {
        DiskService downloadFileService = diskServiceMap.get(typeOfService.getService());

        downloadFileService.downloadFile(path, file);
    }

    @Override
    public void uploadFile(TypeOfService typeOfService, Path path, String file) throws ServerException, IOException, GeneralSecurityException {
        DiskService uploadFileService = diskServiceMap.get(typeOfService.getService());

        uploadFileService.uploadFile(path, file);
    }
}
