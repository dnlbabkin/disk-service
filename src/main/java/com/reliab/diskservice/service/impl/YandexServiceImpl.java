package com.reliab.diskservice.service.impl;

import com.reliab.diskservice.model.Disk;
import com.reliab.diskservice.model.Path;
import com.reliab.diskservice.model.Resources;
import com.reliab.diskservice.properties.CredentialsProperties;
import com.reliab.diskservice.service.*;
import com.yandex.disk.rest.FileDownloadListener;
import com.yandex.disk.rest.ResourcesArgs;
import com.yandex.disk.rest.RestClient;
import com.yandex.disk.rest.exceptions.ServerException;
import com.yandex.disk.rest.exceptions.ServerIOException;
import com.yandex.disk.rest.json.DiskInfo;
import com.yandex.disk.rest.json.Link;
import com.yandex.disk.rest.json.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service("yandex")
@RequiredArgsConstructor
public class YandexServiceImpl implements DiskService {

    private final RestClient restClient;
    private final CredentialsProperties properties;

    @Override
    public Disk getFiles() {
        Disk file = new Disk();
        file.setDiskName("yandex");
        file.setAuthPage(properties.getYandexAuth() + properties.getClientId());

        return file;
    }

    @Override
    public DiskInfo getInfo() throws ServerIOException, IOException {

        return restClient.getDiskInfo();
    }

    @Override
    public Resources getFlatResource() throws ServerIOException, IOException {
        ResourcesArgs.Builder builder = new ResourcesArgs.Builder();
        Resources resources = new Resources();
        resources.setYandexFiled(restClient.getFlatResourceList(builder.build()).getItems());

        return resources;
    }


    @Override
    public Resource getResources(String path) throws ServerIOException, IOException {
        ResourcesArgs.Builder builder = new ResourcesArgs.Builder();

        return restClient.getResources(builder.setPath(path).build());
    }

    @Override
    public void downloadFile(Path path, String file) throws ServerException, IOException {
        restClient.downloadFile(file,
                new FileDownloadListener(new java.io.File(path.getPath() + file), null));
    }

    @Override
    public void uploadFile(Path path, String file) throws ServerException, IOException {
        Link link = restClient.getUploadLink(file, true);
        restClient.uploadFile(link, true, new java.io.File(path.getPath(), file), null);
    }


}
