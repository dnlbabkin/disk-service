package com.reliab.diskservice.service;

import com.yandex.disk.rest.exceptions.ServerIOException;
import com.yandex.disk.rest.json.Resource;

import java.io.IOException;

public interface ResourcesService {
    Resource getResources(String path) throws ServerIOException, IOException;
}
