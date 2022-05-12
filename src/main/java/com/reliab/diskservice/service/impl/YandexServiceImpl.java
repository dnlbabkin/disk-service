package com.reliab.diskservice.service.impl;

import com.reliab.diskservice.model.File;
import com.reliab.diskservice.service.*;
import com.yandex.disk.rest.FileDownloadListener;
import com.yandex.disk.rest.ResourcesArgs;
import com.yandex.disk.rest.RestClient;
import com.yandex.disk.rest.exceptions.ServerException;
import com.yandex.disk.rest.exceptions.ServerIOException;
import com.yandex.disk.rest.json.DiskInfo;
import com.yandex.disk.rest.json.Resource;
import com.yandex.disk.rest.json.ResourceList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service("yandex")
@RequiredArgsConstructor
public class YandexServiceImpl implements DiskInfoService, DiskService, FlatResourcesService, ResourcesService, DownloadFileService {

    private final RestClient restClient;

    @Override
    public List<File> getFiles() {
        List<File> files = new ArrayList<>();
        File file = new File();

        file.setFileName("yandex");
        files.add(file);

        return files;
    }

    @Override
    public List<DiskInfo> getInfo() throws ServerIOException, IOException {
        List<DiskInfo> diskInfos = new ArrayList<>();

        diskInfos.add(restClient.getDiskInfo());

        return diskInfos;
    }

    @Override
    public ResourceList getFlatResource() throws ServerIOException, IOException {
        ResourcesArgs.Builder builder = new ResourcesArgs.Builder();

        return restClient.getFlatResourceList(builder.build());
    }

    @Override
    public Resource getResources(String path) throws ServerIOException, IOException {
        ResourcesArgs.Builder builder = new ResourcesArgs.Builder();

        return restClient.getResources(builder.setPath(path).build());
    }

    @Override
    public void downloadFile(String path) throws ServerException, IOException {
        restClient.downloadFile(path,
                new FileDownloadListener(new java.io.File(path), null));
    }
}
