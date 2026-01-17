package com.peerpay.model;

public class User {
    private Long id;
    private String name;
    private String language; 

    public User() {}
    public User(Long id, String name, String language) {
        this.id = id;
        this.name = name;
        this.language = language;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getLanguage() { return language; }
}
