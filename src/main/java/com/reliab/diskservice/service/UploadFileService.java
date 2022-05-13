package com.reliab.diskservice.service;

import com.reliab.diskservice.model.Path;
import com.yandex.disk.rest.exceptions.ServerException;
import com.yandex.disk.rest.exceptions.ServerIOException;
import com.yandex.disk.rest.exceptions.WrongMethodException;

import java.io.IOException;

public interface UploadFileService {
    void uploadFile(Path path, String file) throws ServerException, IOException;
}
