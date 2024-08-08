package fr.eletutour.pokedex.services;

import fr.eletutour.pokedex.models.Pokemon;
import org.springframework.stereotype.Service;

@Service
public class NavigationService {

    private Pokemon selectedPokemon;

    public Pokemon getSelectedPokemon() {
        return selectedPokemon;
    }

    public void setSelectedPokemon(Pokemon selectedPokemon) {
        this.selectedPokemon = selectedPokemon;
    }
}
