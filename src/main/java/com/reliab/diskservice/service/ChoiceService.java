package com.reliab.diskservice.service;

import com.reliab.diskservice.enums.TypeOfService;
import com.reliab.diskservice.model.File;
import com.reliab.diskservice.model.Path;
import com.reliab.diskservice.model.Resources;
import com.yandex.disk.rest.exceptions.ServerException;
import com.yandex.disk.rest.exceptions.ServerIOException;
import com.yandex.disk.rest.json.DiskInfo;
import com.yandex.disk.rest.json.Resource;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public interface ChoiceService {
    List<File> getFiles(TypeOfService typeOfService) throws GeneralSecurityException, IOException;
    List<DiskInfo> getInfo(TypeOfService typeOfService) throws ServerIOException, IOException;
    void downloadFile(TypeOfService typeOfService, Path path, String file) throws ServerException, IOException;
    Resources getFlatResource(TypeOfService typeOfService) throws ServerIOException, IOException, GeneralSecurityException;
    Resource getResources(TypeOfService typeOfService, String path) throws ServerIOException, IOException;
    void uploadFile(TypeOfService typeOfService, Path path, String file) throws ServerException, IOException;
}
