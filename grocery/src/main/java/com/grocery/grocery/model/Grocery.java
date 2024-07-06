package com.grocery.grocery.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Grocery {

    @Id
    @GeneratedValue
    public Long id;
    public String name;
    public String img;

    public Grocery(String name, String img) {
        this.name = name;
        this.img = img;
    }

    public Grocery() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
