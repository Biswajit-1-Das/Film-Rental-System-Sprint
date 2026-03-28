package com.frontend.dto;

import java.util.List;

public class EntityDTO {

    private String name;
    private List<EndpointDTO> endpoints;

    public EntityDTO(String name, List<EndpointDTO> endpoints) {
        this.name = name;
        this.endpoints = endpoints;
    }

    public String getName() { return name; }
    public List<EndpointDTO> getEndpoints() { return endpoints; }
}