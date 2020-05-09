package com.harshitfit.mvisampleproject.model;

import com.harshitfit.mvisampleproject.pojo.SuperHero;

import java.util.List;

public interface PartialMainState {
    final class Loading implements PartialMainState{}
    final class getListOfSuperheroes implements PartialMainState{
        public SuperHero mListOfSuperheroes;

        public getListOfSuperheroes(SuperHero mListOfSuperheroes) {
            this.mListOfSuperheroes = mListOfSuperheroes;
        }
    }
    final class Error implements PartialMainState{
        public Throwable error;

        public Error(Throwable error) {
            this.error = error;
        }
    }
}
