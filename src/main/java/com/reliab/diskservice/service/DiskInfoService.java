package com.reliab.diskservice.service;

import com.yandex.disk.rest.exceptions.ServerIOException;
import com.yandex.disk.rest.json.DiskInfo;

import java.io.IOException;
import java.util.List;

public interface DiskInfoService {
    List<DiskInfo> getInfo() throws ServerIOException, IOException;
}
