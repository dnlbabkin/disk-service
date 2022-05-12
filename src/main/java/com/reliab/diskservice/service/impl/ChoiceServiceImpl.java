package com.reliab.diskservice.service.impl;

import com.reliab.diskservice.enums.TypeOfService;
import com.reliab.diskservice.model.File;
import com.reliab.diskservice.service.*;
import com.yandex.disk.rest.exceptions.ServerException;
import com.yandex.disk.rest.exceptions.ServerIOException;
import com.yandex.disk.rest.json.DiskInfo;
import com.yandex.disk.rest.json.Link;
import com.yandex.disk.rest.json.Resource;
import com.yandex.disk.rest.json.ResourceList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ChoiceServiceImpl implements ChoiceService {

    private final Map<String, DiskService> diskServiceMap;
    private final Map<String, DiskInfoService> diskInfoMap;
    private final Map<String, FlatResourcesService> patchResourcesMap;
    private final Map<String, ResourcesService> resourcesMap;
    private final Map<String, DownloadFileService> downloadFileServiceMap;



    @Override
    public List<File> getFiles(TypeOfService typeOfService) {
        DiskService diskServices = diskServiceMap.get(typeOfService.getService());

        return diskServices.getFiles();
    }

    @Override
    public List<DiskInfo> getInfo(TypeOfService typeOfService) throws ServerIOException, IOException {
        DiskInfoService diskInfo = diskInfoMap.get(typeOfService.getService());

        return diskInfo.getInfo();
    }

    @Override
    public ResourceList getFlatResource(TypeOfService typeOfService) throws ServerIOException, IOException {
        FlatResourcesService patchResources = patchResourcesMap.get(typeOfService.getService());

        return patchResources.getFlatResource();
    }

    @Override
    public Resource getResources(TypeOfService typeOfService, String path) throws ServerIOException, IOException {
        ResourcesService resources = resourcesMap.get(typeOfService.getService());

        return resources.getResources(path);
    }

    @Override
    public void downloadFile(TypeOfService typeOfService, String path) throws ServerException, IOException {
        DownloadFileService downloadFileService = downloadFileServiceMap.get(typeOfService.getService());

        downloadFileService.downloadFile(path);
    }
}
