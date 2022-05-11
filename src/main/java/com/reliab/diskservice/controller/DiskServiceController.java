package com.reliab.diskservice.controller;

import com.reliab.diskservice.enums.ServicesEnum;
import com.reliab.diskservice.model.File;
import com.reliab.diskservice.service.ChoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("disk-service")
@RequiredArgsConstructor
public class DiskServiceController {

    private final ChoiceService choiceService;

    @GetMapping("/{servicesEnum}")
    public List<File> getFiles(@PathVariable("servicesEnum") ServicesEnum servicesEnum){
        List<File> files = choiceService.setService(servicesEnum);

        return files;
    }
}
