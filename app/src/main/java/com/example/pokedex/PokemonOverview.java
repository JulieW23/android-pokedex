package com.example.pokedex;

public class PokemonOverview {
    int identifier;
    String name;
    String spriteUrl;

    PokemonOverview(int identifier, String name, String spriteUrl) {
        this.identifier = identifier;
        this.name = name;
        this.spriteUrl = spriteUrl;
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpriteUrl() {
        return spriteUrl;
    }

    public void setSpriteUrl(String spriteUrl) {
        this.spriteUrl = spriteUrl;
    }
}
