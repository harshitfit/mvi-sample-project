package com.harshitfit.mvisampleproject.view;

import com.harshitfit.mvisampleproject.Utils.DataSource;
import com.harshitfit.mvisampleproject.model.MainView;
import com.hannesdorfmann.mosby3.mvi.MviBasePresenter;
import com.harshitfit.mvisampleproject.model.PartialMainState;
import com.squareup.picasso.Picasso;

import java.util.Arrays;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter extends MviBasePresenter<MainView, MainViewState> {
    DataSource dataSource;

    public MainPresenter(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected void bindIntents() {
        Observable<? extends PartialMainState> gotData = intent(MainView::getSuperheroIntent)
                .switchMap(index -> dataSource.getListOfSuperheroes(index)
                        .map(superHeroName -> (PartialMainState) new PartialMainState.getListOfSuperheroes(superHeroName))
                        .startWith(new PartialMainState.Loading())
                        .onErrorReturn(error -> new PartialMainState.Error(error))
                        .subscribeOn(Schedulers.io()));

        MainViewState initState = new MainViewState(false,
                false,
               null,
                null);
        Observable<? extends PartialMainState> initIntent = gotData.observeOn(AndroidSchedulers.mainThread());

        subscribeViewState(initIntent.scan(initState, this::viewStateReducer), MainView::render);
    }

    MainViewState viewStateReducer(MainViewState oldState, PartialMainState changedState) {
        MainViewState newState = oldState;
        if (changedState instanceof PartialMainState.Loading) {
            newState.isLoading = true;
            newState.isSuperheroCardShown = false;
        }
        if (changedState instanceof PartialMainState.getListOfSuperheroes) {
            newState.isLoading = false;
            newState.isSuperheroCardShown = true;
            newState.SuperHero = ((PartialMainState.getListOfSuperheroes) changedState).mListOfSuperheroes;

        }
        if (changedState instanceof PartialMainState.Error) {
            newState.isLoading = false;
            newState.isSuperheroCardShown = false;
            newState.error = ((PartialMainState.Error) changedState).error;


        }
        return newState;
    }
}
