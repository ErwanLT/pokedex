package fr.eletutour.pokedex.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Stats {
    private int hp;
    private int atk;
    private int def;
    private int spe_atk;
    private int spe_def;
    private int vit;
}
