package com.reliab.diskservice.controller;

import com.reliab.diskservice.enums.TypeOfService;
import com.reliab.diskservice.model.Disk;
import com.reliab.diskservice.model.Path;
import com.reliab.diskservice.model.Resources;
import com.reliab.diskservice.service.impl.ChoiceServiceImpl;
import com.yandex.disk.rest.exceptions.ServerException;
import com.yandex.disk.rest.exceptions.ServerIOException;
import com.yandex.disk.rest.json.DiskInfo;
import com.yandex.disk.rest.json.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
@RequestMapping("disk-service")
@RequiredArgsConstructor
public class DiskServiceController {

    private final ChoiceServiceImpl choiceService;

    @GetMapping("/{typeOfService}")
    public Disk getFiles(@PathVariable("typeOfService") TypeOfService typeOfService)
            throws GeneralSecurityException, IOException {
        Disk files = choiceService.getFiles(typeOfService);

        return files;
    }

    @GetMapping("/{typeOfService}/get-disk-info")
    public DiskInfo getDiskInfo(@PathVariable("typeOfService") TypeOfService typeOfService)
            throws ServerIOException, IOException {
        DiskInfo diskInfo = choiceService.getInfo(typeOfService);

        return diskInfo;
    }

    @GetMapping("/{typeOfService}/get-flat-resources")
    public Resources getFlatResources(@PathVariable("typeOfService") TypeOfService typeOfService)
            throws ServerIOException, IOException, GeneralSecurityException {
        Resources resources = choiceService.getFlatResource(typeOfService);

        return resources;
    }

    @GetMapping("/{typeOfService}/get-resources/{path}")
    public Resource getResources(@PathVariable("typeOfService") TypeOfService typeOfService,
                                 @PathVariable("path") String path) throws ServerIOException, IOException {
        Resource resource = choiceService.getResources(typeOfService, path);

        return resource;
    }

    @PostMapping("/{typeOfService}/download/{file}")
    public String downloadFile(@PathVariable("typeOfService") TypeOfService typeOfService,
                               @PathVariable("file") String file,
                               @RequestBody Path path) throws ServerException, IOException {
        choiceService.downloadFile(typeOfService, path, file);

        return path.getPath() + file;
    }

    @PutMapping("/{typeOfService}/upload/{file}")
    public String uploadFile(@PathVariable("typeOfService") TypeOfService typeOfService,
                             @PathVariable("file") String file,
                             @RequestBody Path path) throws ServerException, IOException, GeneralSecurityException {
        choiceService.uploadFile(typeOfService, path, file);

        return path.getPath() + file;
    }
}
