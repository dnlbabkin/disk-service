package com.reliab.diskservice.service;

import com.reliab.diskservice.model.Disk;
import com.reliab.diskservice.model.Path;
import com.reliab.diskservice.model.Resources;
import com.yandex.disk.rest.exceptions.ServerException;
import com.yandex.disk.rest.exceptions.ServerIOException;
import com.yandex.disk.rest.json.DiskInfo;
import com.yandex.disk.rest.json.Resource;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface DiskService {
    Disk getFiles() throws GeneralSecurityException, IOException;
    DiskInfo getInfo() throws ServerIOException, IOException;
    void downloadFile(Path path, String file) throws ServerException, IOException;
    Resources getFlatResource() throws ServerIOException, IOException, GeneralSecurityException;
    Resource getResources(String path) throws ServerIOException, IOException;
    void uploadFile(Path path, String file) throws ServerException, IOException, GeneralSecurityException;
}
