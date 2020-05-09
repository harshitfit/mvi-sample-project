package com.harshitfit.mvisampleproject.view;

import com.harshitfit.mvisampleproject.pojo.SuperHero;

import java.util.List;

public class MainViewState {
    boolean isLoading;
    boolean isSuperheroCardShown;
    com.harshitfit.mvisampleproject.pojo.SuperHero SuperHero;
    Throwable error;

    public MainViewState(boolean isLoading, boolean isSuperheroCardShown, com.harshitfit.mvisampleproject.pojo.SuperHero superHero, Throwable error) {
        this.isLoading = isLoading;
        this.isSuperheroCardShown = isSuperheroCardShown;
        SuperHero = superHero;
        this.error = error;
    }
}
