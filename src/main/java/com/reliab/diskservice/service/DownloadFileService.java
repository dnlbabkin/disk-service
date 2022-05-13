package com.reliab.diskservice.service;

import com.reliab.diskservice.model.Path;
import com.yandex.disk.rest.exceptions.ServerException;

import java.io.IOException;

public interface DownloadFileService {
    void downloadFile(Path path, String file) throws ServerException, IOException;
}
