package com.reliab.diskservice.service;

import com.reliab.diskservice.model.Resources;
import com.yandex.disk.rest.exceptions.ServerIOException;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface FlatResourcesService {
    Resources getFlatResource() throws ServerIOException, IOException, GeneralSecurityException;
}
