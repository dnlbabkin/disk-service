package com.reliab.diskservice.service.impl;

import com.reliab.diskservice.enums.NameService;
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
public class ChoiceServiceImpl implements ChoiceService { //fixme шаблон стратегия вместо этого класс

    private final Map<NameService, DiskService> diskServiceMap;


    @Override
    public Disk getFiles(NameService typeOfService) throws GeneralSecurityException, IOException {
        DiskService diskServices = diskServiceMap.get(typeOfService);

        return diskServices.getFiles();
    }

    @Override
    public DiskInfo getInfo(NameService typeOfService) throws ServerIOException, IOException {
        DiskService diskInfo = diskServiceMap.get(typeOfService);

        return diskInfo.getInfo();
    }

    @Override
    public Resources getFlatResource(NameService typeOfService) throws ServerIOException, IOException, GeneralSecurityException {
        DiskService patchResources = diskServiceMap.get(typeOfService);

        return patchResources.getFlatFileList();
    }

    @Override
    public Resource getResources(NameService typeOfService, String path) throws ServerIOException, IOException {
        DiskService resources = diskServiceMap.get(typeOfService); //fixme

        return resources.getResources(path);
    }

    @Override
    public void downloadFile(NameService typeOfService, Path path, String file) throws ServerException, IOException {
        DiskService downloadFileService = diskServiceMap.get(typeOfService);

        downloadFileService.downloadFile(path, file);
    }

    @Override
    public void uploadFile(NameService typeOfService, Path path, String file) throws ServerException, IOException, GeneralSecurityException {
        DiskService uploadFileService = diskServiceMap.get(typeOfService);

        uploadFileService.uploadFile(path, file);
    }
}
