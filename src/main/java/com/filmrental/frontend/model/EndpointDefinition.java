package com.filmrental.frontend.model;

public class EndpointDefinition {

    private final String id;
    private final String entity;
    private final String method;
    private final String path;
    private final String title; // NEW FIELD for beautiful UI titles!

    public EndpointDefinition(String id, String entity, String method, String path, String title) {
        this.id = id;
        this.entity = entity;
        this.method = method;
        this.path = path;
        this.title = title;
    }

    public String getId() { return id; }
    public String getEntity() { return entity; }
    public String getMethod() { return method; }
    public String getPath() { return path; }
    public String getTitle() { return title; } // NEW GETTER
}