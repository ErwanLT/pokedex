package fr.eletutour.pokedex.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class PokemonTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Pokemon#Pokemon()}
     *   <li>{@link Pokemon#setCatchRate(int)}
     *   <li>{@link Pokemon#setCategory(String)}
     *   <li>{@link Pokemon#setEggGroups(List)}
     *   <li>{@link Pokemon#setEvolution(Evolution)}
     *   <li>{@link Pokemon#setFormes(List)}
     *   <li>{@link Pokemon#setGeneration(int)}
     *   <li>{@link Pokemon#setHeight(String)}
     *   <li>{@link Pokemon#setLevel100(int)}
     *   <li>{@link Pokemon#setName(Name)}
     *   <li>{@link Pokemon#setPokedexId(int)}
     *   <li>{@link Pokemon#setResistances(List)}
     *   <li>{@link Pokemon#setSexe(Sexe)}
     *   <li>{@link Pokemon#setSprites(Sprites)}
     *   <li>{@link Pokemon#setStats(Stats)}
     *   <li>{@link Pokemon#setTalents(List)}
     *   <li>{@link Pokemon#setTypes(List)}
     *   <li>{@link Pokemon#setWeight(String)}
     *   <li>{@link Pokemon#getCatchRate()}
     *   <li>{@link Pokemon#getCategory()}
     *   <li>{@link Pokemon#getEggGroups()}
     *   <li>{@link Pokemon#getEvolution()}
     *   <li>{@link Pokemon#getFormes()}
     *   <li>{@link Pokemon#getGeneration()}
     *   <li>{@link Pokemon#getHeight()}
     *   <li>{@link Pokemon#getLevel100()}
     *   <li>{@link Pokemon#getName()}
     *   <li>{@link Pokemon#getPokedexId()}
     *   <li>{@link Pokemon#getResistances()}
     *   <li>{@link Pokemon#getSexe()}
     *   <li>{@link Pokemon#getSprites()}
     *   <li>{@link Pokemon#getStats()}
     *   <li>{@link Pokemon#getTalents()}
     *   <li>{@link Pokemon#getTypes()}
     *   <li>{@link Pokemon#getWeight()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        Pokemon actualPokemon = new Pokemon();
        actualPokemon.setCatchRate(1);
        actualPokemon.setCategory("Category");
        ArrayList<String> eggGroups = new ArrayList<>();
        actualPokemon.setEggGroups(eggGroups);
        Evolution evolution = new Evolution();
        actualPokemon.setEvolution(evolution);
        ArrayList<Forme> formes = new ArrayList<>();
        actualPokemon.setFormes(formes);
        actualPokemon.setGeneration(1);
        actualPokemon.setHeight("Height");
        actualPokemon.setLevel100(1);
        Name name = new Name("Fr", "En", "Jp");

        actualPokemon.setName(name);
        actualPokemon.setPokedexId(1);
        ArrayList<Resistance> resistances = new ArrayList<>();
        actualPokemon.setResistances(resistances);
        Sexe sexe = new Sexe(10.0d, 10.0d);

        actualPokemon.setSexe(sexe);
        Sprites sprites = new Sprites();
        actualPokemon.setSprites(sprites);
        Stats stats = new Stats(1, 1, 1, 1, 1, 1);

        actualPokemon.setStats(stats);
        ArrayList<Talent> talents = new ArrayList<>();
        actualPokemon.setTalents(talents);
        ArrayList<Type> types = new ArrayList<>();
        actualPokemon.setTypes(types);
        actualPokemon.setWeight("Weight");
        int actualCatchRate = actualPokemon.getCatchRate();
        String actualCategory = actualPokemon.getCategory();
        List<String> actualEggGroups = actualPokemon.getEggGroups();
        Evolution actualEvolution = actualPokemon.getEvolution();
        List<Forme> actualFormes = actualPokemon.getFormes();
        int actualGeneration = actualPokemon.getGeneration();
        String actualHeight = actualPokemon.getHeight();
        int actualLevel100 = actualPokemon.getLevel100();
        Name actualName = actualPokemon.getName();
        int actualPokedexId = actualPokemon.getPokedexId();
        List<Resistance> actualResistances = actualPokemon.getResistances();
        Sexe actualSexe = actualPokemon.getSexe();
        Sprites actualSprites = actualPokemon.getSprites();
        Stats actualStats = actualPokemon.getStats();
        List<Talent> actualTalents = actualPokemon.getTalents();
        List<Type> actualTypes = actualPokemon.getTypes();

        // Assert that nothing has changed
        assertEquals("Category", actualCategory);
        assertEquals("Height", actualHeight);
        assertEquals("Weight", actualPokemon.getWeight());
        assertEquals(1, actualCatchRate);
        assertEquals(1, actualGeneration);
        assertEquals(1, actualLevel100);
        assertEquals(1, actualPokedexId);
        assertEquals(actualEggGroups, actualFormes);
        assertEquals(actualEggGroups, actualResistances);
        assertEquals(actualEggGroups, actualTalents);
        assertEquals(actualEggGroups, actualTypes);
        assertSame(evolution, actualEvolution);
        assertSame(name, actualName);
        assertSame(sexe, actualSexe);
        assertSame(sprites, actualSprites);
        assertSame(stats, actualStats);
        assertSame(eggGroups, actualEggGroups);
        assertSame(formes, actualFormes);
        assertSame(resistances, actualResistances);
        assertSame(talents, actualTalents);
        assertSame(types, actualTypes);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link Pokemon#Pokemon(int, int, String, Name, Sprites, List, List, Stats, List, Evolution, String, String, List, Sexe, int, int, List)}
     *   <li>{@link Pokemon#setCatchRate(int)}
     *   <li>{@link Pokemon#setCategory(String)}
     *   <li>{@link Pokemon#setEggGroups(List)}
     *   <li>{@link Pokemon#setEvolution(Evolution)}
     *   <li>{@link Pokemon#setFormes(List)}
     *   <li>{@link Pokemon#setGeneration(int)}
     *   <li>{@link Pokemon#setHeight(String)}
     *   <li>{@link Pokemon#setLevel100(int)}
     *   <li>{@link Pokemon#setName(Name)}
     *   <li>{@link Pokemon#setPokedexId(int)}
     *   <li>{@link Pokemon#setResistances(List)}
     *   <li>{@link Pokemon#setSexe(Sexe)}
     *   <li>{@link Pokemon#setSprites(Sprites)}
     *   <li>{@link Pokemon#setStats(Stats)}
     *   <li>{@link Pokemon#setTalents(List)}
     *   <li>{@link Pokemon#setTypes(List)}
     *   <li>{@link Pokemon#setWeight(String)}
     *   <li>{@link Pokemon#getCatchRate()}
     *   <li>{@link Pokemon#getCategory()}
     *   <li>{@link Pokemon#getEggGroups()}
     *   <li>{@link Pokemon#getEvolution()}
     *   <li>{@link Pokemon#getFormes()}
     *   <li>{@link Pokemon#getGeneration()}
     *   <li>{@link Pokemon#getHeight()}
     *   <li>{@link Pokemon#getLevel100()}
     *   <li>{@link Pokemon#getName()}
     *   <li>{@link Pokemon#getPokedexId()}
     *   <li>{@link Pokemon#getResistances()}
     *   <li>{@link Pokemon#getSexe()}
     *   <li>{@link Pokemon#getSprites()}
     *   <li>{@link Pokemon#getStats()}
     *   <li>{@link Pokemon#getTalents()}
     *   <li>{@link Pokemon#getTypes()}
     *   <li>{@link Pokemon#getWeight()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange
        Name name = new Name("Fr", "En", "Jp");

        Sprites sprites = new Sprites();
        ArrayList<Type> types = new ArrayList<>();
        ArrayList<Talent> talents = new ArrayList<>();
        Stats stats = new Stats(1, 1, 1, 1, 1, 1);

        ArrayList<Resistance> resistances = new ArrayList<>();
        Evolution evolution = new Evolution();
        ArrayList<String> eggGroups = new ArrayList<>();
        Sexe sexe = new Sexe(10.0d, 10.0d);

        // Act
        Pokemon actualPokemon = new Pokemon(1, 1, "Category", name, sprites, types, talents, stats, resistances, evolution,
                "Height", "Weight", eggGroups, sexe, 1, 1, new ArrayList<>());
        actualPokemon.setCatchRate(1);
        actualPokemon.setCategory("Category");
        ArrayList<String> eggGroups2 = new ArrayList<>();
        actualPokemon.setEggGroups(eggGroups2);
        Evolution evolution2 = new Evolution();
        actualPokemon.setEvolution(evolution2);
        ArrayList<Forme> formes = new ArrayList<>();
        actualPokemon.setFormes(formes);
        actualPokemon.setGeneration(1);
        actualPokemon.setHeight("Height");
        actualPokemon.setLevel100(1);
        Name name2 = new Name("Fr", "En", "Jp");

        actualPokemon.setName(name2);
        actualPokemon.setPokedexId(1);
        ArrayList<Resistance> resistances2 = new ArrayList<>();
        actualPokemon.setResistances(resistances2);
        Sexe sexe2 = new Sexe(10.0d, 10.0d);

        actualPokemon.setSexe(sexe2);
        Sprites sprites2 = new Sprites();
        actualPokemon.setSprites(sprites2);
        Stats stats2 = new Stats(1, 1, 1, 1, 1, 1);

        actualPokemon.setStats(stats2);
        ArrayList<Talent> talents2 = new ArrayList<>();
        actualPokemon.setTalents(talents2);
        ArrayList<Type> types2 = new ArrayList<>();
        actualPokemon.setTypes(types2);
        actualPokemon.setWeight("Weight");
        int actualCatchRate = actualPokemon.getCatchRate();
        String actualCategory = actualPokemon.getCategory();
        List<String> actualEggGroups = actualPokemon.getEggGroups();
        Evolution actualEvolution = actualPokemon.getEvolution();
        List<Forme> actualFormes = actualPokemon.getFormes();
        int actualGeneration = actualPokemon.getGeneration();
        String actualHeight = actualPokemon.getHeight();
        int actualLevel100 = actualPokemon.getLevel100();
        Name actualName = actualPokemon.getName();
        int actualPokedexId = actualPokemon.getPokedexId();
        List<Resistance> actualResistances = actualPokemon.getResistances();
        Sexe actualSexe = actualPokemon.getSexe();
        Sprites actualSprites = actualPokemon.getSprites();
        Stats actualStats = actualPokemon.getStats();
        List<Talent> actualTalents = actualPokemon.getTalents();
        List<Type> actualTypes = actualPokemon.getTypes();

        // Assert that nothing has changed
        assertEquals("Category", actualCategory);
        assertEquals("Height", actualHeight);
        assertEquals("Weight", actualPokemon.getWeight());
        assertEquals(1, actualCatchRate);
        assertEquals(1, actualGeneration);
        assertEquals(1, actualLevel100);
        assertEquals(1, actualPokedexId);
        assertEquals(types, actualEggGroups);
        assertEquals(types, actualFormes);
        assertEquals(types, actualResistances);
        assertEquals(types, actualTalents);
        assertEquals(types, actualTypes);
        assertSame(evolution2, actualEvolution);
        assertSame(name2, actualName);
        assertSame(sexe2, actualSexe);
        assertSame(sprites2, actualSprites);
        assertSame(stats2, actualStats);
        assertSame(eggGroups2, actualEggGroups);
        assertSame(formes, actualFormes);
        assertSame(resistances2, actualResistances);
        assertSame(talents2, actualTalents);
        assertSame(types2, actualTypes);
    }
}
