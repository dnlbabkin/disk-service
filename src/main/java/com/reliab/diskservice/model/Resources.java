package com.reliab.diskservice.model;

import com.google.api.services.drive.model.File;
import com.yandex.disk.rest.json.Resource;
import lombok.Data;

import java.util.List;

@Data
public class Resources<T> {
    private List<T> fileList;

    //fixme удалить
    private List<Resource> yandexFiled;
    private List<File> googleFiles;
}
