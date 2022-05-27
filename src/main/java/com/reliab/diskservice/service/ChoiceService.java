package com.reliab.diskservice.service;

import com.reliab.diskservice.enums.NameService;
import com.reliab.diskservice.model.Disk;
import com.reliab.diskservice.model.Path;
import com.reliab.diskservice.model.Resources;
import com.yandex.disk.rest.exceptions.ServerException;
import com.yandex.disk.rest.exceptions.ServerIOException;
import com.yandex.disk.rest.json.DiskInfo;
import com.yandex.disk.rest.json.Resource;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface ChoiceService {//fixme шаблон стратегия вместо этого класс
    Disk getFiles(NameService nameService) throws GeneralSecurityException, IOException;
    DiskInfo getInfo(NameService nameService) throws ServerIOException, IOException;
    void downloadFile(NameService nameService, Path path, String file) throws ServerException, IOException;
    Resources getFlatResource(NameService nameService) throws ServerIOException, IOException, GeneralSecurityException;
    Resource getResources(NameService nameService, String path) throws ServerIOException, IOException;
    void uploadFile(NameService nameService, Path path, String file) throws ServerException, IOException, GeneralSecurityException;
}
