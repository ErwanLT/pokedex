package fr.eletutour.pokedex.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import fr.eletutour.pokedex.models.EvolutionStep;
import fr.eletutour.pokedex.models.MegaEvolution;
import fr.eletutour.pokedex.models.Pokemon;
import fr.eletutour.pokedex.models.Talent;
import fr.eletutour.pokedex.services.NavigationService;
import fr.eletutour.pokedex.services.PokemonService;
import org.springframework.util.CollectionUtils;


@Route(value = "pokemon-detail")
@PageTitle("Détails du Pokémon")
public class PokemonDetailView extends VerticalLayout {

    private final PokemonService pokemonService;
    private final NavigationService navigationService;

    private final Pokemon pokemon;

    private final HorizontalLayout numAndName = new HorizontalLayout();
    private final Image sprite = new Image();
    private final HorizontalLayout types = new HorizontalLayout();
    private final HorizontalLayout categorie = new HorizontalLayout();
    private final HorizontalLayout talentsLayout = new HorizontalLayout();
    private final HorizontalLayout tailleLayout = new HorizontalLayout();
    private final HorizontalLayout poidsLayout = new HorizontalLayout();
    private final HorizontalLayout eggsLayout = new HorizontalLayout();
    private final VerticalLayout evolutionsLayout = new VerticalLayout();
    private final VerticalLayout gigamaxLayout = new VerticalLayout();

    public PokemonDetailView(NavigationService navigationService, PokemonService pokemonService) {
        this.navigationService = navigationService;
        this.pokemonService = pokemonService;

        pokemon = navigationService.getSelectedPokemon();

        setSizeFull();
        setAlignItems(Alignment.CENTER);

        configureNumAndName();
        configureSprite();
        configureType();
        configureCategorie();
        configureTailleLayout();
        configurePoidsLayout();
        configureTalents();
        configureOeufs();
        configureEvolutions();
        configureGigamax();

        Button backButton = new Button("Précédent", e -> getUI().ifPresent(ui -> ui.getPage().executeJs("window.history.back()")));
        add(numAndName, sprite, types, categorie, tailleLayout, poidsLayout, talentsLayout, eggsLayout, evolutionsLayout, gigamaxLayout, backButton);
    }

    private void configureNumAndName() {
        var numFormat = String.format("%04d", pokemon.getPokedexId());
        VerticalLayout numero = new VerticalLayout(new H3("N°" + numFormat));
        VerticalLayout nom = new VerticalLayout(new H3(pokemon.getName().getFr()));

        switch (pokemon.getTypes().size()){
            case 1 :
                numero.addClassName(pokemon.getTypes().getFirst().getName().toLowerCase());
                numero.addClassNames("explain","type","entete");
                break;
            case 2 :
                numero.addClassName(pokemon.getTypes().get(1).getName().toLowerCase());
                numero.addClassNames("explain","type","entete");
                break;
        }
        nom.addClassName(pokemon.getTypes().getFirst().getName().toLowerCase());
        nom.addClassName("type");

        numAndName.add(numero, nom);
        numAndName.setWidthFull();
    }

    private void configureSprite() {
        sprite.setSrc(pokemon.getSprites().getRegular());
        sprite.setAlt(pokemon.getName().getFr());
        sprite.setAriaLabel("Artworf of " + pokemon.getName().getFr());
    }

    private void configureType() {
        pokemon.getTypes().forEach(t -> types.add(new Image(t.getImage(), t.getName())));
    }

    private void configureCategorie() {
        VerticalLayout label = PokemonDetailUtils.createLabelWithClassName("Catégorie", pokemon.getTypes().getFirst().getName().toLowerCase());
        VerticalLayout cat = new VerticalLayout(new NativeLabel(pokemon.getCategory()));
        categorie.add(label, cat);
        categorie.setWidthFull();
    }

    private void configurePoidsLayout() {
        VerticalLayout label = PokemonDetailUtils.createLabelWithClassName("Poids", pokemon.getTypes().getFirst().getName().toLowerCase());
        VerticalLayout poids = new VerticalLayout(new NativeLabel(pokemon.getWeight()));
        poidsLayout.add(label, poids);
        poidsLayout.setWidthFull();
    }

    private void configureTailleLayout() {
        VerticalLayout label = PokemonDetailUtils.createLabelWithClassName("Taille", pokemon.getTypes().getFirst().getName().toLowerCase());
        VerticalLayout taille = new VerticalLayout(new NativeLabel(pokemon.getHeight()));
        tailleLayout.add(label, taille);
        tailleLayout.setWidthFull();
    }

    private void configureTalents() {
        VerticalLayout label = PokemonDetailUtils.createLabelWithClassName("Talents", pokemon.getTypes().getFirst().getName().toLowerCase());
        OrderedList list = new OrderedList();
        for (Talent t : pokemon.getTalents()) {
            list.add(new ListItem(t.getName()));
        }
        talentsLayout.add(label, list);
        talentsLayout.setWidthFull();
    }

    private void configureOeufs() {
        VerticalLayout label = PokemonDetailUtils.createLabelWithClassName("Oeufs", pokemon.getTypes().getFirst().getName().toLowerCase());
        UnorderedList list = new UnorderedList();
        if (!CollectionUtils.isEmpty(pokemon.getEggGroups())) {
            for (String s : pokemon.getEggGroups()) {
                list.add(new ListItem(s));
            }
        } else {
            list.add(new ListItem("NA"));
        }
        eggsLayout.add(label, list);
        eggsLayout.setWidthFull();
    }

    private void configureEvolutions() {
        if (pokemon.getEvolution() != null) {
            HorizontalLayout label = PokemonDetailUtils.createEvolutionLayout("Evolutions", pokemon.getTypes().getFirst().getName().toLowerCase());
            evolutionsLayout.add(label);

            HorizontalLayout stepLayout = new HorizontalLayout();
            stepLayout.setWidthFull();

            configurePreEvolutions(stepLayout);
            configureNextEvolutions(stepLayout);
            configureMegaEvolutions(stepLayout);

            evolutionsLayout.add(stepLayout);
        }
    }

    private void configurePreEvolutions(HorizontalLayout stepLayout) {
        if (!CollectionUtils.isEmpty(pokemon.getEvolution().getPre())) {
            VerticalLayout preEvolutionLayout = new VerticalLayout();
            HorizontalLayout preEvolutionLabel = PokemonDetailUtils.createEvolutionLayout("Pré évolution", pokemon.getTypes().getFirst().getName().toLowerCase());
            preEvolutionLayout.add(preEvolutionLabel);

            EvolutionStep step = CollectionUtils.lastElement(pokemon.getEvolution().getPre());
            Pokemon pre = pokemonService.findById(step.getPokedexId());

            var numFormat = String.format("%04d", pre.getPokedexId());
            VerticalLayout numero = new VerticalLayout(new H3("N°" + numFormat));
            VerticalLayout nom = new VerticalLayout(new H3(pre.getName().getFr()));

            switch (pre.getTypes().size()){
                case 1 :
                    numero.addClassName(pre.getTypes().getFirst().getName().toLowerCase());
                    numero.addClassNames("explain","type","entete");
                    break;
                case 2 :
                    numero.addClassName(pre.getTypes().get(1).getName().toLowerCase());
                    numero.addClassNames("explain","type","entete");
                    break;
            }
            nom.addClassName(pre.getTypes().getFirst().getName().toLowerCase());
            nom.addClassName("type");

            PokemonDetailUtils.addEvolutionStep(preEvolutionLayout, "N°" + String.format("%04d", pre.getPokedexId()) + " " + pre.getName().getFr(), pre.getSprites().getRegular(), "", pokemon.getTypes().getFirst().getName().toLowerCase());

            stepLayout.add(preEvolutionLayout);
        }
    }

    private void configureNextEvolutions(HorizontalLayout stepLayout) {
        if (!CollectionUtils.isEmpty(pokemon.getEvolution().getNext())) {
            VerticalLayout nextEvolutionLayout = new VerticalLayout();
            HorizontalLayout nextEvolutionLabel = PokemonDetailUtils.createEvolutionLayout("Prochaine évolution", pokemon.getTypes().getFirst().getName().toLowerCase());
            nextEvolutionLayout.add(nextEvolutionLabel);

            for (EvolutionStep step : pokemon.getEvolution().getNext()) {
                Pokemon next = pokemonService.findById(step.getPokedexId());
                PokemonDetailUtils.addEvolutionStep(nextEvolutionLayout, "N°" + String.format("%04d", next.getPokedexId()) + " " + next.getName().getFr(), next.getSprites().getRegular(), step.getCondition(), pokemon.getTypes().getFirst().getName().toLowerCase());
            }

            stepLayout.add(nextEvolutionLayout);
        }
    }

    private void configureMegaEvolutions(HorizontalLayout stepLayout) {
        if (!CollectionUtils.isEmpty(pokemon.getEvolution().getMega())) {
            VerticalLayout megaEvolutionLayout = new VerticalLayout();
            HorizontalLayout megaEvolutionLabel = PokemonDetailUtils.createEvolutionLayout("Méga évolution", pokemon.getTypes().getFirst().getName().toLowerCase());
            megaEvolutionLayout.add(megaEvolutionLabel);

            for (MegaEvolution mega : pokemon.getEvolution().getMega()) {
                PokemonDetailUtils.addEvolutionStep(megaEvolutionLayout, "", mega.getSprites().getRegular(), mega.getOrbe(), pokemon.getTypes().getFirst().getName().toLowerCase());
            }

            stepLayout.add(megaEvolutionLayout);
        }
    }

    private void configureGigamax() {
        if(pokemon.getSprites().getGmax() != null){
            HorizontalLayout label = PokemonDetailUtils.createEvolutionLayout("Gigamax", pokemon.getTypes().getFirst().getName().toLowerCase());
            gigamaxLayout.add(label);

            HorizontalLayout spriteLayout = new HorizontalLayout();
            Image sprite = new Image(pokemon.getSprites().getGmax().getRegular(), pokemon.getName().getFr() + "gigamax");
            spriteLayout.add(sprite);
            spriteLayout.setWidthFull();
            spriteLayout.setAlignItems(FlexComponent.Alignment.CENTER);
            spriteLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

            gigamaxLayout.add(spriteLayout);
        }
    }
}