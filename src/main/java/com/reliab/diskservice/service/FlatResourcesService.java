package com.reliab.diskservice.service;

import com.reliab.diskservice.enums.TypeOfService;
import com.yandex.disk.rest.exceptions.ServerIOException;
import com.yandex.disk.rest.json.ResourceList;

import java.io.IOException;

public interface FlatResourcesService {
    ResourceList getFlatResource() throws ServerIOException, IOException;
}
