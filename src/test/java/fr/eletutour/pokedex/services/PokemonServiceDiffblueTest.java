package fr.eletutour.pokedex.services;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PokemonService.class})
@ExtendWith(SpringExtension.class)
class PokemonServiceDiffblueTest {
    @Autowired
    private PokemonService pokemonService;

    @Test
    void testLireFichierJson() throws IOException {
        // Arrange and Act
        var pokemons = pokemonService.loadPokemonFromJson();
        Assertions.assertNotNull(pokemons);
        Assertions.assertFalse(pokemons.isEmpty());
    }
}
