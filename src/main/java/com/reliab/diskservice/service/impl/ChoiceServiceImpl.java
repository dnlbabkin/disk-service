package com.reliab.diskservice.service.impl;

import com.google.api.services.drive.model.FileList;
import com.reliab.diskservice.enums.TypeOfService;
import com.reliab.diskservice.model.File;
import com.reliab.diskservice.model.Path;
import com.reliab.diskservice.model.Resources;
import com.reliab.diskservice.service.*;
import com.yandex.disk.rest.exceptions.ServerException;
import com.yandex.disk.rest.exceptions.ServerIOException;
import com.yandex.disk.rest.json.DiskInfo;
import com.yandex.disk.rest.json.Resource;
import com.yandex.disk.rest.json.ResourceList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
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
    private final Map<String, UploadFileService> uploadFileServiceMap;


    @Override
    public List<File> getFiles(TypeOfService typeOfService) throws GeneralSecurityException, IOException {
        DiskService diskServices = diskServiceMap.get(typeOfService.getService());

        return diskServices.getFiles();
    }

    @Override
    public List<DiskInfo> getInfo(TypeOfService typeOfService) throws ServerIOException, IOException {
        DiskInfoService diskInfo = diskInfoMap.get(typeOfService.getService());

        return diskInfo.getInfo();
    }

    @Override
    public Resources getFlatResource(TypeOfService typeOfService) throws ServerIOException, IOException, GeneralSecurityException {
        FlatResourcesService patchResources = patchResourcesMap.get(typeOfService.getService());

        return patchResources.getFlatResource();
    }

    @Override
    public Resource getResources(TypeOfService typeOfService, String path) throws ServerIOException, IOException {
        ResourcesService resources = resourcesMap.get(typeOfService.getService());

        return resources.getResources(path);
    }

    @Override
    public void downloadFile(TypeOfService typeOfService, Path path, String file) throws ServerException, IOException {
        DownloadFileService downloadFileService = downloadFileServiceMap.get(typeOfService.getService());

        downloadFileService.downloadFile(path, file);
    }

    @Override
    public void uploadFile(TypeOfService typeOfService, Path path, String file) throws ServerException, IOException {
        UploadFileService uploadFileService = uploadFileServiceMap.get(typeOfService.getService());

        uploadFileService.uploadFile(path, file);
    }
}
