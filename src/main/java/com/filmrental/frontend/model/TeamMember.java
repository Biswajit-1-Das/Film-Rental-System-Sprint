package com.filmrental.frontend.model;

public class TeamMember {

    private final String id;
    private final String name;
    private final String entities;
    private final String imageUrl;
    private final String accentColor;

    public TeamMember(String id, String name, String entities, String imageUrl, String accentColor) {
        this.id = id;
        this.name = name;
        this.entities = entities;
        this.imageUrl = imageUrl;
        this.accentColor = accentColor;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEntities() {
        return entities;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getAccentColor() {
        return accentColor;
    }
}
