package fr.eletutour.pokedex.views.forms;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import fr.eletutour.pokedex.models.*;
import fr.eletutour.pokedex.services.PokemonService;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class PokemonDetails {

    List<String> branchedEvolution = List.of("Ortide",
            "Têtarte",
            "Ramoloss",
            "Insécateur",
            "Évoli",
            "Debugant",
            "Chenipotte",
            "Kirlia",
            "Ningale",
            "Stalgamin",
            "Coquiperl",
            "Cheniti",
            "Cosmovum",
            "Verpom",
            "Charbambin");

    private final Pokemon pokemon;

    private final PokemonService service;

    public PokemonDetails(Pokemon pokemon, PokemonService service){
        this.pokemon = pokemon;
        this.service = service;
    }

    public void configureTalents(HorizontalLayout talentsLayout) {
        H4 label = new H4("Talents");
        label.addClassName("type");
        label.addClassName(pokemon.getTypes().get(0).getName().toLowerCase());
        OrderedList list = new OrderedList();
        for (Talent t: pokemon.getTalents()){
            list.add(new ListItem(t.getName()));
        }
        talentsLayout.add(label, list);
    }

    public void configureOeufs(HorizontalLayout oeufsLayout) {
        H4 label = new H4("oeufs");
        label.addClassName("type");
        label.addClassName(pokemon.getTypes().get(0).getName().toLowerCase());
        UnorderedList list = new UnorderedList();
        if(!CollectionUtils.isEmpty(pokemon.getEggGroups())){
            for (String s: pokemon.getEggGroups()){
                list.add(new ListItem(s));
            }
        } else {
            list.add(new ListItem("NA"));
        }

        oeufsLayout.add(label, list);
    }

    public void configureType(HorizontalLayout types) {
        pokemon.getTypes()
                .forEach(t -> types.add(new Image(t.getImage(), t.getName())));
    }

    public void configureNumAndName(HorizontalLayout numAndName) {
        var numFormat = String.format("%04d", pokemon.getPokedexId());
        H3 num = new H3("N°" + numFormat);
        H3 name = new H3(pokemon.getName().getFr());
        switch (pokemon.getTypes().size()){
            case 1 :
                num.addClassName(pokemon.getTypes().get(0).getName().toLowerCase());
                num.addClassName("type");
                name.addClassName(pokemon.getTypes().get(0).getName().toLowerCase());
                name.addClassName("type");
                break;
            case 2 :
                num.addClassName(pokemon.getTypes().get(1).getName().toLowerCase());
                num.addClassName("type");
                name.addClassName(pokemon.getTypes().get(0).getName().toLowerCase());
                name.addClassName("type");
                break;
        }
        numAndName.add(num, name);
    }

    public void configureSprite(Image sprite) {
        sprite.setSrc(pokemon.getSprites().getRegular());
        sprite.setAlt(pokemon.getName().getFr());
        sprite.setAriaLabel("Artworf of " + pokemon.getName().getFr());
    }

    public void configureCategorie(HorizontalLayout categorie) {
        H4 label = new H4("Catégorie");
        label.addClassName("type");
        label.addClassName(pokemon.getTypes().get(0).getName().toLowerCase());
        NativeLabel cat = new NativeLabel(pokemon.getCategory());
        categorie.add(label, cat);
    }

    public void configurePoidsLayout(HorizontalLayout poidsLayout) {
        H4 label = new H4("Poids");
        label.addClassName("type");
        label.addClassName(pokemon.getTypes().get(0).getName().toLowerCase());
        NativeLabel cat = new NativeLabel(pokemon.getWeight());
        poidsLayout.add(label, cat);
    }

    public void configureTailleLayout(HorizontalLayout tailleLayout) {
        H4 label = new H4("Taille");
        label.addClassName("type");
        label.addClassName(pokemon.getTypes().get(0).getName().toLowerCase());
        NativeLabel cat = new NativeLabel(pokemon.getHeight());
        tailleLayout.add(label, cat);
    }

    public void configureResistanceLayout(HorizontalLayout resistanceGrid) {
        H4 label = new H4("Resistance");
        label.addClassName("type");
        label.addClassName(pokemon.getTypes().get(0).getName().toLowerCase());

        VerticalLayout resitance = new VerticalLayout();
        pokemon.getResistances().forEach(r -> {
            Div div = new Div();
            NativeLabel type = new NativeLabel(r.getName());
            type.addClassName("type");
            type.addClassName(r.getName().toLowerCase());
            NativeLabel multiplier = new NativeLabel(String.valueOf(r.getMultiplier()));
            div.add(type, multiplier);
            resitance.add(div);
        });

        resistanceGrid.add(label, resitance);
    }

    public void configureEvolutions(HorizontalLayout evolutionLayout) {
        Evolution evolutions = pokemon.getEvolution();
        if(evolutions!= null && (evolutions.getNext() != null || evolutions.getPre() != null)){
            H4 label = new H4("Evolution");
            label.addClassName("type");
            label.addClassName(pokemon.getTypes().get(0).getName().toLowerCase());

            VerticalLayout steps = new VerticalLayout();
            VerticalLayout mega = new VerticalLayout();
            configurePreEvolution(evolutions, steps);
            configureNextEvolution(pokemon, steps);
            evolutionLayout.add(label, steps, mega);
        }

    }

    private void configureNextEvolution(Pokemon pokemon, VerticalLayout steps) {
        var evolutions = pokemon.getEvolution();
        if(!CollectionUtils.isEmpty(evolutions.getNext())){
            steps.add(new Div(new Span("Next")));
            if(branchedEvolution.contains(pokemon.getName().getFr())){
                evolutions.getNext().forEach(step -> {
                    HorizontalLayout evoStep = new HorizontalLayout(FlexComponent.JustifyContentMode.CENTER);
                    Pokemon next = service.findById(step.getPokedexId());

                    Image sprite = new Image(next.getSprites().getRegular(), next.getName().getFr());
                    sprite.setHeight(60, Unit.PIXELS);
                    sprite.setWidth(60, Unit.PIXELS);

                    Span condition = new Span(step.getCondition());

                    evoStep.add(sprite, condition);

                    steps.add(evoStep);
                });
            } else {
                EvolutionStep step = evolutions.getNext().get(0);
                Pokemon next = service.findById(step.getPokedexId());

                HorizontalLayout evoStep = new HorizontalLayout(FlexComponent.JustifyContentMode.CENTER);

                Image sprite = new Image(next.getSprites().getRegular(), next.getName().getFr());
                sprite.setHeight(60, Unit.PIXELS);
                sprite.setWidth(60, Unit.PIXELS);

                Span condition = new Span(step.getCondition());

                evoStep.add(sprite, condition);

                steps.add(evoStep);
            }
        }
    }

    private void configurePreEvolution(Evolution evolutions, VerticalLayout steps) {
        if(!CollectionUtils.isEmpty(evolutions.getPre())){
            EvolutionStep step = CollectionUtils.lastElement(evolutions.getPre());
            Pokemon pre = service.findById(step.getPokedexId());

            Image sprite = new Image(pre.getSprites().getRegular(), pre.getName().getFr());
            sprite.setHeight(60, Unit.PIXELS);
            sprite.setWidth(60, Unit.PIXELS);

            steps.add(new Div(new Span("Pré"), sprite));
        }
    }

    public void configureGigamax(HorizontalLayout gigamaxLayout){
        GmaxSprites gigamaxImg = pokemon.getSprites().getGmax();
        if(gigamaxImg != null){
            H4 label = new H4("Gigamax");
            label.addClassName("type");
            label.addClassName(pokemon.getTypes().get(0).getName().toLowerCase());

            Image sprite = new Image(gigamaxImg.getRegular(), "gigamax form");
            sprite.setHeight(150, Unit.PIXELS);
            sprite.setWidth(150, Unit.PIXELS);

            gigamaxLayout.add(label, sprite);
        }
    }

    public void configureMegaEvolutions(HorizontalLayout megaEvolutionLayout) {
        var evolutions = pokemon.getEvolution();
        if(evolutions != null && !CollectionUtils.isEmpty(evolutions.getMega())){
            H4 label = new H4("Méga evolution");
            label.addClassName("type");
            label.addClassName(pokemon.getTypes().get(0).getName().toLowerCase());

            var mega = evolutions.getMega().get(0);
            Image sprite = new Image(mega.getSprites().getRegular(), mega.getOrbe());
            sprite.setHeight(150, Unit.PIXELS);
            sprite.setWidth(150, Unit.PIXELS);

            Span orbe = new Span(mega.getOrbe());

            megaEvolutionLayout.add(label, sprite, orbe);

        }
    }
}
