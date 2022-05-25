package com.reliab.diskservice.service.impl;

import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.reliab.diskservice.model.File;
import com.reliab.diskservice.model.Path;
import com.reliab.diskservice.model.Resources;
import com.reliab.diskservice.service.DiskService;
import com.reliab.diskservice.service.FlatResourcesService;
import com.reliab.diskservice.service.UploadFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service("google")
@RequiredArgsConstructor
public class GoogleServiceImpl implements DiskService, FlatResourcesService, UploadFileService {

    private final Drive service;

    @Override
    public List<File> getFiles() {
        List<File> files = new ArrayList<>();
        File file = new File();

        file.setFileName("google");
        files.add(file);

        return files;
    }


    @Override
    public Resources getFlatResource() throws IOException {
        com.google.api.services.drive.model.FileList result = service.files().list()
                .setPageSize(10)
                .setFields("nextPageToken, files(id, name)")
                .execute();
        List<com.google.api.services.drive.model.File> files = result.getFiles();
        Resources resources = new Resources();
        resources.setGoogleFiles(files);

        return resources;
    }

    @Override
    public void uploadFile(Path path, String file) throws IOException {
        com.google.api.services.drive.model.File fileMetadata = new com.google.api.services.drive.model.File();
        fileMetadata.setName(file);
        java.io.File filePath = new java.io.File(path.getPath() + file);
        FileContent mediaContent = new FileContent(path.getType(), filePath);
        service.files().create(fileMetadata, mediaContent)
                .setFields("id")
                .execute();
    }
}
