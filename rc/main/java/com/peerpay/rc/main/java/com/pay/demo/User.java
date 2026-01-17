package com.peerpay.model;

public class User {
    private Long id;
    private String name;
    private String language; // EN or ES

    public User() {}

    public User(Long id, String name, String language) {
        this.id = id;
        this.name = name;
        this.language = language;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getLanguage() { return language; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setLanguage(String language) { this.language = language; }
}
