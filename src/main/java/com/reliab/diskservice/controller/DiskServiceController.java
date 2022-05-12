package com.reliab.diskservice.controller;

import com.reliab.diskservice.enums.TypeOfService;
import com.reliab.diskservice.model.File;
import com.reliab.diskservice.service.impl.ChoiceServiceImpl;
import com.yandex.disk.rest.exceptions.ServerException;
import com.yandex.disk.rest.exceptions.ServerIOException;
import com.yandex.disk.rest.json.DiskInfo;
import com.yandex.disk.rest.json.Link;
import com.yandex.disk.rest.json.Resource;
import com.yandex.disk.rest.json.ResourceList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("disk-service")
@RequiredArgsConstructor
public class DiskServiceController {

    private final ChoiceServiceImpl choiceService;

    @GetMapping("/{typeOfService}")
    public List<File> getFiles(@PathVariable("typeOfService") TypeOfService typeOfService){
        List<File> files = choiceService.getFiles(typeOfService);

        return files;
    }

    @GetMapping("/{typeOfService}/get-disk-info")
    public List<DiskInfo> getDiskInfo(@PathVariable("typeOfService") TypeOfService typeOfService) throws ServerIOException, IOException {
        List<DiskInfo> diskInfo = choiceService.getInfo(typeOfService);

        return diskInfo;
    }

    @GetMapping("/{typeOfService}/get-flat-resources")
    public ResourceList getFlatResources(@PathVariable("typeOfService") TypeOfService typeOfService) throws ServerIOException, IOException {
        ResourceList resources = choiceService.getFlatResource(typeOfService);

        return resources;
    }

    @GetMapping("/{typeOfService}/get-resources/{path}")
    public Resource getResources(@PathVariable("typeOfService") TypeOfService typeOfService,
                                 @PathVariable("path") String path) throws ServerIOException, IOException {
        Resource resource = choiceService.getResources(typeOfService, path);

        return resource;
    }

    @GetMapping("/{typeOfService}/download/{path}")
    public void downloadFile(@PathVariable("typeOfService") TypeOfService typeOfService,
                             @PathVariable("path") String path) throws ServerException, IOException {
        choiceService.downloadFile(typeOfService, path);
    }
}
