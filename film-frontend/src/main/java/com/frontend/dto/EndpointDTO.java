package com.frontend.dto;

public class EndpointDTO {

    private String method;
    private String path;
    private String desc;

    public EndpointDTO(String method, String path, String desc) {
        this.method = method;
        this.path = path;
        this.desc = desc;
    }

    public String getMethod() { return method; }
    public String getPath() { return path; }
    public String getDesc() { return desc; }
}