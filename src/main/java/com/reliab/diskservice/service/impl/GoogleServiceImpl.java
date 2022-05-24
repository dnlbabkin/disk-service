package com.reliab.diskservice.service.impl;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.reliab.diskservice.components.DriveQuickstart;
import com.reliab.diskservice.model.File;
import com.reliab.diskservice.model.Resources;
import com.reliab.diskservice.service.DiskService;
import com.reliab.diskservice.service.FlatResourcesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

@Service("google")
@RequiredArgsConstructor
public class GoogleServiceImpl implements DiskService, FlatResourcesService {

    private static final String APPLICATION_NAME = "disk-service";
    private static final GsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private final DriveQuickstart driveQuickstart;

    @Override
    public List<File> getFiles() {
        List<File> files = new ArrayList<>();
        File file = new File();

        file.setFileName("google");
        files.add(file);

        return files;
    }

    @Override
    public Resources getFlatResource() throws IOException, GeneralSecurityException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Drive service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, driveQuickstart.getCredential(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();

        com.google.api.services.drive.model.FileList result = service.files().list()
                .setPageSize(10)
                .setFields("nextPageToken, files(id, name)")
                .execute();
        List<com.google.api.services.drive.model.File> files = result.getFiles();
        Resources resources = new Resources();
        resources.setGoogleFiles(files);

        return resources;
    }
}
