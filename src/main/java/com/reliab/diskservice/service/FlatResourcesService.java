package com.reliab.diskservice.service;

import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.reliab.diskservice.model.Resources;
import com.yandex.disk.rest.exceptions.ServerIOException;
import com.yandex.disk.rest.json.ResourceList;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public interface FlatResourcesService {
    Resources getFlatResource() throws ServerIOException, IOException, GeneralSecurityException;
}
