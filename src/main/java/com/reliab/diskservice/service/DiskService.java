package com.reliab.diskservice.service;

import com.reliab.diskservice.model.File;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public interface DiskService {
    List<File> getFiles() throws GeneralSecurityException, IOException;
}
