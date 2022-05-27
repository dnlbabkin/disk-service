package com.reliab.diskservice.service.impl;

import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.reliab.diskservice.model.Disk;
import com.reliab.diskservice.model.Path;
import com.reliab.diskservice.model.Resources;
import com.reliab.diskservice.configuration.properties.CredentialsProperties;
import com.reliab.diskservice.service.DiskService;
import com.yandex.disk.rest.exceptions.ServerIOException;
import com.yandex.disk.rest.json.DiskInfo;
import com.yandex.disk.rest.json.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Service("google")
@RequiredArgsConstructor
public class GoogleServiceImpl implements DiskService {

    private final Drive service;
    private final CredentialsProperties properties;

    public String fileId; //fixme


    private List<File> getFileList() throws IOException {
        com.google.api.services.drive.model.FileList result = service.files().list()
                .setFields("nextPageToken, files(id, name)")
                .execute();

        return result.getFiles();
    }

    private File buildGoogleFile(String file) {
        return new File().setName(file);
    }

    private String getFileId(String file) throws IOException {
        return getFileList().stream()
                .filter(getFileId -> getFileId.getName().equals(file))
                .findFirst()
                .map(getFileId -> fileId = getFileId.getId()).orElse(null);
    }

    @Override
    public Disk getFiles() {
        Disk file = new Disk();
        file.setDiskName("google");
        file.setAuthPage(properties.getGoogleAuth());

        return file;
    }

    @Override
    public Resources getFlatFileList() throws IOException {
        Resources resources = new Resources();
        resources.setGoogleFiles(getFileList());
        return resources;
    }

    @Override
    public void uploadFile(Path path, String file) throws IOException {
        File fileMetadata = buildGoogleFile(file);
        java.io.File filePath = new java.io.File(path.getPath() + file);
        FileContent mediaContent = new FileContent(path.getMimeType(), filePath);
        service.files().create(fileMetadata, mediaContent)
                .setFields("id") //why?
                .execute();
    }

    @Override
    public void downloadFile(Path path, String file) throws IOException {
        String fileId = getFileId(file);
        OutputStream outputStream = new FileOutputStream(path.getPath() + file);
        service.files().get(fileId).executeMediaAndDownloadTo(outputStream);
    }

    @Override
    public DiskInfo getInfo() throws ServerIOException, IOException {
        throw new IllegalStateException();
    }

    @Override
    public Resource getResources(String path) throws ServerIOException, IOException {
        throw new IllegalStateException();
    }
}
