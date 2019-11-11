package com.example.pokedex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements GetAllPokemonResponse {
    private RecyclerView.Adapter mAdapter;
    private List<PokemonOverview> pokemonOverviewList = new ArrayList<>();
    private GetAllPokemonRequest getAllPokemonRequest = new GetAllPokemonRequest();
    public static final String POKEMON_OVERVIEW_URL = "https://pokeapi.co/api/v2/pokemon?limit=151";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        mAdapter = new PokemonOverviewAdapter(pokemonOverviewList);
        recyclerView.setAdapter(mAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        getAllPokemonRequest.delegate = this;

        preparePokemonData();
    }

    private void preparePokemonData() {
        try {
            getAllPokemonRequest.execute(POKEMON_OVERVIEW_URL).get();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void processFinish(List<PokemonOverview> result) {
        pokemonOverviewList.addAll(result);
        mAdapter.notifyDataSetChanged();
    }
}
