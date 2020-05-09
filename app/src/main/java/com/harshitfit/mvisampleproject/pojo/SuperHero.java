package com.harshitfit.mvisampleproject.pojo;

import com.harshitfit.mvisampleproject.model.PartialMainState;
import com.harshitfit.mvisampleproject.view.MainViewState;

public class SuperHero {
    String name;
    String image;
    String description;


    public SuperHero(String name, String image, String description) {
        this.name = name;
        this.image = image;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}