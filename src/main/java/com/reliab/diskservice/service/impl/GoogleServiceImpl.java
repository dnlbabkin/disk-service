package com.reliab.diskservice.service.impl;

import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.reliab.diskservice.model.Disk;
import com.reliab.diskservice.model.Path;
import com.reliab.diskservice.model.Resources;
import com.reliab.diskservice.properties.CredentialsProperties;
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

    private String fileId;


    private List<com.google.api.services.drive.model.File> getFileList() throws IOException {
        com.google.api.services.drive.model.FileList result = service.files().list()
                .setFields("nextPageToken, files(id, name)")
                .execute();
        List<com.google.api.services.drive.model.File> files = result.getFiles();

        return files;
    }

    private com.google.api.services.drive.model.File getFile(String file) {
        com.google.api.services.drive.model.File fileMetadata = new com.google.api.services.drive.model.File();
        fileMetadata.setName(file);

        return fileMetadata;
    }

    private String getFileId(String file) throws IOException {
        List<com.google.api.services.drive.model.File> files = getFileList();
        for (com.google.api.services.drive.model.File getFileId : files) {
            if(getFileId.getName().equals(file)){
                fileId = getFileId.getId();
            }
        }

        return fileId;
    }

    @Override
    public Disk getFiles() {
        Disk file = new Disk();
        file.setDiskName("google");
        file.setAuthPage(properties.getGoogleAuth());

        return file;
    }

    @Override
    public Resources getFlatResource() throws IOException {
        List<com.google.api.services.drive.model.File> files = getFileList();
        Resources resources = new Resources();
        resources.setGoogleFiles(files);

        return resources;
    }

    @Override
    public void uploadFile(Path path, String file) throws IOException {
        com.google.api.services.drive.model.File fileMetadata = getFile(file);
        java.io.File filePath = new java.io.File(path.getPath() + file);
        FileContent mediaContent = new FileContent(path.getType(), filePath);
        service.files().create(fileMetadata, mediaContent)
                .setFields("id")
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
        return null;
    }

    @Override
    public Resource getResources(String path) throws ServerIOException, IOException {
        return null;
    }
}
