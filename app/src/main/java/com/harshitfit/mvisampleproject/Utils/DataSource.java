package com.harshitfit.mvisampleproject.Utils;

import com.harshitfit.mvisampleproject.pojo.SuperHero;

import java.util.List;

import io.reactivex.Observable;

public class DataSource {
    List<SuperHero> listOfSuperheroes;

    public DataSource(List<SuperHero> listOfSuperheroes) {
        this.listOfSuperheroes = listOfSuperheroes;
    }

    public Observable<SuperHero> getListOfSuperheroes(int index){
        return Observable.just(listOfSuperheroes.get(index));
    }
}
