package com.valunskii.libraryproject.beans;

public class Genre {

    public Genre() {

    }

    private long id;
    private String name;

    public Genre(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
