package com.reliab.diskservice.enums;

public enum NameService {
    YANDEX("yandex"),
    GOOGLE("google");

    private String service;

    NameService(String service) {
        this.service = service;
    }

    public String getService() {
        return service;
    }
}
