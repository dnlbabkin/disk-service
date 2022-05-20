package com.reliab.diskservice;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.reliab.diskservice.model.Path;
import com.reliab.diskservice.properties.ExternalProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties(ExternalProperties.class)
public class DiskServiceApplication {

	private static final String APPLICATION_NAME = "disk-service";
	private static final GsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

	public static void main(String[] args) throws GeneralSecurityException, IOException {
		SpringApplication.run(DiskServiceApplication.class, args);

		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		DriveQuickstart driveQuickstart = new DriveQuickstart();
		Drive service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, driveQuickstart.getCredential(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME)
				.build();

		FileList result = service.files().list()
				.setPageSize(10)
				.setFields("nextPageToken, files(id, name)")
				.execute();
		List<File> files = result.getFiles();

		if(files == null || files.isEmpty()) {
			System.out.println("No files found.");
		} else {
			System.out.println("Files:");
			for (File file : files) {
				System.out.printf("%s (%s)\n", file.getName(), file.getId());
			}
		}
	}

}
