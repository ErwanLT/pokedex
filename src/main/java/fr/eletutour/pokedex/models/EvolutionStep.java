package fr.eletutour.pokedex.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EvolutionStep {
    @JsonProperty("pokedex_id")
    private int pokedexId;
    private String name;
    private String condition;
}
