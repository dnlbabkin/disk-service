package com.reliab.diskservice.service;

import com.yandex.disk.rest.exceptions.ServerIOException;
import com.yandex.disk.rest.json.ResourceList;

import java.io.IOException;

public interface FlatResourcesService {
    ResourceList getFlatResource() throws ServerIOException, IOException;
}
