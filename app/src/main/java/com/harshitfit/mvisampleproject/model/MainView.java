package com.harshitfit.mvisampleproject.model;


import com.harshitfit.mvisampleproject.view.MainViewState;
import com.hannesdorfmann.mosby3.mvp.MvpView;

import io.reactivex.Observable;

public interface MainView extends MvpView {
    Observable<Integer> getSuperheroIntent();

    void render(MainViewState viewState);

}
