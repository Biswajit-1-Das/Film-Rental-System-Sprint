package com.frontend.dto;

import java.util.List;

public class MemberDTO {

    private int index;
    private String name;
    private String emoji;
    private String color;
    private String role;
    private List<String> tags;
    private List<EntityDTO> entities;

    public MemberDTO(int index, String name, String emoji, String color,
                     String role, List<String> tags, List<EntityDTO> entities) {
        this.index = index;
        this.name = name;
        this.emoji = emoji;
        this.color = color;
        this.role = role;
        this.tags = tags;
        this.entities = entities;
    }

    public int getIndex()                  { return index; }
    public String getName()                { return name; }
    public String getEmoji()               { return emoji; }
    public String getColor()               { return color; }
    public String getRole()                { return role; }
    public List<String> getTags()          { return tags; }
    public List<EntityDTO> getEntities()   { return entities; }
}