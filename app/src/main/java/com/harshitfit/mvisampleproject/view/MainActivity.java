package com.harshitfit.mvisampleproject.view;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hannesdorfmann.mosby3.mvi.MviActivity;
import com.harshitfit.mvisampleproject.R;
import com.harshitfit.mvisampleproject.Utils.DataSource;
import com.harshitfit.mvisampleproject.model.MainView;
import com.harshitfit.mvisampleproject.pojo.SuperHero;
import com.jakewharton.rxbinding2.view.RxView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.reactivex.Observable;

public class MainActivity extends MviActivity<MainView, MainPresenter> implements MainView {

    private static final int IMAGE = 1;
    private static final String TAG = MainActivity.class.getSimpleName();
    CardView card;
    ImageView superHeroImage;
    TextView superHeroName;
    TextView superOccupation;
    Button button;
    ProgressBar progressBar;
    List<SuperHero> ListOfHeroes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        card = findViewById(R.id.card_superhero);
        superHeroImage = findViewById(R.id.image_superhero);
        superHeroName = findViewById(R.id.superhero_name);
        superOccupation = findViewById(R.id.superhero_occupation);
        button = findViewById(R.id.button);
        progressBar = findViewById(R.id.progress_bar);
        ListOfHeroes = createListOfHeroes();
    }

    private List<SuperHero> createListOfHeroes() {
        List<SuperHero> superHeroList = new ArrayList<>();
        superHeroList.add(new SuperHero("Iron Man", "https://www.superherodb.com/pictures2/portraits/10/100/85.jpg", "Inventor, Industrialist; former United States Secretary of Defense"));
        superHeroList.add(new SuperHero("Wolverine", "https://www.superherodb.com/pictures2/portraits/10/100/161.jpg", "Adventurer, instructor, former bartender, bouncer, spy, government operative, mercenary, soldier"));
        superHeroList.add(new SuperHero("Spider-Man", "https://www.superherodb.com/pictures2/portraits/10/100/133.jpg", "Freelance photographer, teacher"));
        superHeroList.add(new SuperHero("Thor", "https://www.superherodb.com/pictures2/portraits/10/100/140.jpg", "King of Asgard, formerly EMS Technician, Physician"));
        superHeroList.add(new SuperHero("Captain America", "https://www.superherodb.com/pictures2/portraits/10/100/274.jpg", "Adventurer, federal official, intelligence operative; former soldier, Hydra agent, police officer, teacher, sparring partner"));
        return superHeroList;
    }

    @NonNull
    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter(new DataSource(ListOfHeroes));
    }

    @Override
    public Observable<Integer> getSuperheroIntent() {
        return RxView.clicks(button).map(click -> 4);
    }

    private Integer getRandomNumberInRange(int min, int max) {
        if (min >= max)
            throw new IllegalArgumentException("max must be grater than min");
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    @Override
    public void render(MainViewState viewState) {
        if (viewState.isLoading) {
            progressBar.setVisibility(View.VISIBLE);
            card.setVisibility(View.GONE);
            button.setEnabled(false);
        } else if (viewState.error != null) {
            progressBar.setVisibility(View.GONE);
            card.setVisibility(View.GONE);
            button.setEnabled(true);
            Toast.makeText(this, viewState.error.getMessage(), Toast.LENGTH_SHORT).show();
        } else if (viewState.isSuperheroCardShown) {
            progressBar.setVisibility(View.GONE);
            card.setVisibility(View.VISIBLE);
            button.setEnabled(true);
            Picasso.get().load(viewState.SuperHero.getImage()).fetch(new Callback() {
                @Override
                public void onSuccess() {
                    Picasso.get().load(viewState.SuperHero.getImage()).into(superHeroImage);
                    superHeroName.setText(getString(R.string.name) + viewState.SuperHero.getName());
                    superOccupation.setText(getString(R.string.beside_superhero) + viewState.SuperHero.getDescription());

                }
                @Override
                public void onError(Exception e) {
                    Log.d(TAG, e.getMessage());
                }
            });


        }

    }
}
