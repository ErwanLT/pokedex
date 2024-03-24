package fr.eletutour.pokedex.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sprites {
    private String regular;
    private String shiny;
    private GmaxSprites gmax;
}
