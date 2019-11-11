package com.example.pokedex;

import java.util.List;

public interface GetAllPokemonResponse {
    void processFinish(List<PokemonOverview> result);
}
