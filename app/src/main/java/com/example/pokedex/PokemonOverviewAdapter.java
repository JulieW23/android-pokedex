package com.example.pokedex;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PokemonOverviewAdapter extends RecyclerView.Adapter<PokemonOverviewAdapter.PokemonViewHolder> {

    private List<PokemonOverview> pokemonOverviewList;

    class PokemonViewHolder extends RecyclerView.ViewHolder {
        TextView pokemonName;

        PokemonViewHolder(View view) {
            super(view);
            pokemonName = view.findViewById(R.id.row_pokemon_name);
        }
    }

    PokemonOverviewAdapter(List<PokemonOverview> pokemonOverviewList){
        this.pokemonOverviewList = pokemonOverviewList;
    }

    // Create new views invoked by the layout manager
    @Override @NonNull
    public PokemonOverviewAdapter.PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                                       int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemon_row, parent,false);
        return new PokemonViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        // - get element from dataset at this position
        // - replace the contents of the view with that element
        PokemonOverview pokemon = pokemonOverviewList.get(position);
        holder.pokemonName.setText(pokemon.name);
        // TODO: set the pokemon sprite here
    }

    // Return the size of dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return pokemonOverviewList.size();
    }
}
