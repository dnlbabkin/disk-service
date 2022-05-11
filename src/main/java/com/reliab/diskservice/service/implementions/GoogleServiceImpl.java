package com.reliab.diskservice.service.implementions;

import com.reliab.diskservice.model.File;
import com.reliab.diskservice.service.DiskService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("google")
public class GoogleServiceImpl implements DiskService {
    @Override
    public List<File> getFiles() {
        List<File> files = new ArrayList<>();
        File file = new File();

        file.setFileName("google");
        files.add(file);

        return files;
    }
}
