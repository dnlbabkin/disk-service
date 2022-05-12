package com.reliab.diskservice.service;

import com.yandex.disk.rest.exceptions.ServerException;

import java.io.IOException;

public interface DownloadFileService {
    void downloadFile(String path) throws ServerException, IOException;
}
