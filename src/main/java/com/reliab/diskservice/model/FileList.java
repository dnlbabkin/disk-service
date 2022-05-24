package com.reliab.diskservice.model;

import com.google.api.services.drive.model.File;
import lombok.Data;

import java.util.List;

@Data
public class FileList {
    private List<File> fileList;
}
