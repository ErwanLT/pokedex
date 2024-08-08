package fr.eletutour.pokedex.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import fr.eletutour.pokedex.models.EvolutionStep;
import fr.eletutour.pokedex.models.Pokemon;
import fr.eletutour.pokedex.models.Talent;
import fr.eletutour.pokedex.services.NavigationService;
import fr.eletutour.pokedex.services.PokemonService;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Route(value = "pokemon-detail")
@PageTitle("Détails du Pokémon")
public class PokemonDetailView extends VerticalLayout {

    HorizontalLayout numAndName = new HorizontalLayout();
    Image sprite = new Image();
    HorizontalLayout types = new HorizontalLayout();
    HorizontalLayout categorie = new HorizontalLayout();
    HorizontalLayout talentsLayout = new HorizontalLayout();
    HorizontalLayout tailleLayout = new HorizontalLayout();
    HorizontalLayout poidsLayout = new HorizontalLayout();
    HorizontalLayout eggsLayout = new HorizontalLayout();
    VerticalLayout evolutionsLayout = new VerticalLayout();

    private final PokemonService pokemonService;
    private final NavigationService navigationService;

    Pokemon pokemon;

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

        //Button backButton = new Button("Retour à la liste des Pokémon", e -> UI.getCurrent().navigate(FirstGenView.class));

        add(numAndName, sprite, types, categorie, tailleLayout, poidsLayout);
        add(talentsLayout, eggsLayout, evolutionsLayout);
        //add(backButton);
        Button backButton = new Button("Précédent", e -> {
            getUI().ifPresent(ui -> ui.getPage().executeJs("window.history.back()"));
        });
        add(backButton);
    }

    public void configureTalents() {
        VerticalLayout label = new VerticalLayout(new H4("Talents"));
        label.addClassNames("type", "entete", pokemon.getTypes().getFirst().getName().toLowerCase());
        OrderedList list = new OrderedList();
        for (Talent t: pokemon.getTalents()){
            list.add(new ListItem(t.getName()));
        }
        talentsLayout.add(label, list);
        talentsLayout.setWidthFull();
    }

    public void configureOeufs() {
        VerticalLayout label = new VerticalLayout(new H4("oeufs"));
        label.addClassNames("type", "entete", pokemon.getTypes().getFirst().getName().toLowerCase());
        UnorderedList list = new UnorderedList();
        if(!CollectionUtils.isEmpty(pokemon.getEggGroups())){
            for (String s: pokemon.getEggGroups()){
                list.add(new ListItem(s));
            }
        } else {
            list.add(new ListItem("NA"));
        }

        eggsLayout.add(label, list);
        eggsLayout.setWidthFull();
    }

    public void configureType() {
        pokemon.getTypes()
                .forEach(t -> types.add(new Image(t.getImage(), t.getName())));
    }

    public void configureNumAndName() {
        var numFormat = String.format("%04d", pokemon.getPokedexId());
        VerticalLayout numero = new VerticalLayout();
        VerticalLayout nom = new VerticalLayout();
        numero.add(new H3("N°" + numFormat));
        nom.add(new H3(pokemon.getName().getFr()));
        switch (pokemon.getTypes().size()){
            case 1 :
                numero.addClassName(pokemon.getTypes().getFirst().getName().toLowerCase());
                numero.addClassNames("explain","type","entete");
                nom.addClassName(pokemon.getTypes().getFirst().getName().toLowerCase());
                nom.addClassName("type");
                break;
            case 2 :
                numero.addClassName(pokemon.getTypes().get(1).getName().toLowerCase());
                numero.addClassNames("explain","type","entete");
                nom.addClassName(pokemon.getTypes().getFirst().getName().toLowerCase());
                nom.addClassName("type");
                break;
        }
        numAndName.add(numero, nom);
        numAndName.setWidthFull();
    }

    public void configureSprite() {
        sprite.setSrc(pokemon.getSprites().getRegular());
        sprite.setAlt(pokemon.getName().getFr());
        sprite.setAriaLabel("Artworf of " + pokemon.getName().getFr());
    }

    public void configureCategorie() {
        VerticalLayout label = new VerticalLayout(new H4("Catégorie"));
        label.addClassNames("type", "entete", pokemon.getTypes().getFirst().getName().toLowerCase());
        VerticalLayout cat = new VerticalLayout(new NativeLabel(pokemon.getCategory()));
        categorie.add(label, cat);
        categorie.setWidthFull();
    }

    public void configurePoidsLayout() {
        VerticalLayout label = new VerticalLayout(new H4("Poids"));
        label.addClassNames("type", "entete", pokemon.getTypes().getFirst().getName().toLowerCase());
        VerticalLayout poids = new VerticalLayout(new NativeLabel(pokemon.getWeight()));
        poidsLayout.add(label, poids);
        poidsLayout.setWidthFull();
    }

    public void configureTailleLayout() {
        VerticalLayout label = new VerticalLayout(new H4("Taille"));
        label.addClassNames("type", "entete", pokemon.getTypes().getFirst().getName().toLowerCase());
        VerticalLayout taille = new VerticalLayout(new NativeLabel(pokemon.getHeight()));
        tailleLayout.add(label, taille);
        tailleLayout.setWidthFull();
    }

    public void configureEvolutions(){
        if(pokemon.getEvolution() != null){
            HorizontalLayout label = new HorizontalLayout(new H3("Evolutions"));
            label.addClassNames("type", pokemon.getTypes().getFirst().getName().toLowerCase());
            label.setAlignItems(Alignment.CENTER);
            label.setJustifyContentMode(JustifyContentMode.CENTER);
            label.setWidthFull();
            evolutionsLayout.add(label);

            HorizontalLayout stepLayout = new HorizontalLayout();
            stepLayout.setWidthFull();

            if(!CollectionUtils.isEmpty(pokemon.getEvolution().getPre())){
                VerticalLayout preEvolutionLayout = new VerticalLayout();
                HorizontalLayout preEvolutionLabel = new HorizontalLayout(new H4("Pré évolution"));
                preEvolutionLabel.setWidthFull();
                preEvolutionLabel.addClassNames("type", pokemon.getTypes().getFirst().getName().toLowerCase());
                preEvolutionLabel.setAlignItems(Alignment.CENTER);
                preEvolutionLabel.setJustifyContentMode(JustifyContentMode.CENTER);

                EvolutionStep step = CollectionUtils.lastElement(pokemon.getEvolution().getPre());
                Pokemon pre = pokemonService.findById(step.getPokedexId());

                HorizontalLayout preEvolutionName = new HorizontalLayout(new H4("N°" +String.format("%04d", pre.getPokedexId())+ " " + pre.getName().getFr()));
                preEvolutionName.setWidthFull();
                preEvolutionName.addClassNames("type", pokemon.getTypes().getFirst().getName().toLowerCase());
                preEvolutionName.setAlignItems(Alignment.CENTER);
                preEvolutionName.setJustifyContentMode(JustifyContentMode.CENTER);

                HorizontalLayout spriteLayout = new HorizontalLayout();
                Image sprite = new Image(pre.getSprites().getRegular(), pre.getName().getFr());
                spriteLayout.add(sprite);
                spriteLayout.setWidthFull();
                spriteLayout.setAlignItems(Alignment.CENTER);
                spriteLayout.setJustifyContentMode(JustifyContentMode.CENTER);

                preEvolutionLayout.add(preEvolutionLabel, preEvolutionName, spriteLayout);
                stepLayout.add(preEvolutionLayout);
            }
            if(!CollectionUtils.isEmpty(pokemon.getEvolution().getNext())){
                VerticalLayout nextEvolutionLayout = new VerticalLayout();
                HorizontalLayout nextEvolutionLabel = new HorizontalLayout(new H4("Prochaine évolution"));
                nextEvolutionLabel.setWidthFull();
                nextEvolutionLabel.addClassNames("type", pokemon.getTypes().getFirst().getName().toLowerCase());
                nextEvolutionLabel.setAlignItems(Alignment.CENTER);
                nextEvolutionLabel.setJustifyContentMode(JustifyContentMode.CENTER);
                nextEvolutionLayout.add(nextEvolutionLabel);
                if(branchedEvolution.contains(pokemon.getName().getFr())){
                    pokemon.getEvolution().getNext().forEach(step -> {
                        VerticalLayout evoStep = new VerticalLayout(FlexComponent.JustifyContentMode.CENTER);
                        Pokemon next = pokemonService.findById(step.getPokedexId());

                        HorizontalLayout nextEvolutionName = new HorizontalLayout(new H4("N°" +String.format("%04d", next.getPokedexId())+ " " + next.getName().getFr()));
                        nextEvolutionName.setWidthFull();
                        nextEvolutionName.addClassNames("type", pokemon.getTypes().getFirst().getName().toLowerCase());
                        nextEvolutionName.setAlignItems(Alignment.CENTER);
                        nextEvolutionName.setJustifyContentMode(JustifyContentMode.CENTER);

                        HorizontalLayout spriteLayout = new HorizontalLayout();
                        Image sprite = new Image(next.getSprites().getRegular(), next.getName().getFr());
                        spriteLayout.add(sprite);
                        spriteLayout.setWidthFull();
                        spriteLayout.setAlignItems(Alignment.CENTER);
                        spriteLayout.setJustifyContentMode(JustifyContentMode.CENTER);

                        HorizontalLayout condition = new HorizontalLayout(new Span(step.getCondition()));
                        condition.setWidthFull();
                        condition.setAlignItems(Alignment.CENTER);
                        condition.setJustifyContentMode(JustifyContentMode.CENTER);

                        evoStep.add(nextEvolutionName, spriteLayout, condition);
                        nextEvolutionLayout.add(evoStep);
                    });
                } else {
                    EvolutionStep step = CollectionUtils.firstElement(pokemon.getEvolution().getNext());
                    Pokemon next = pokemonService.findById(step.getPokedexId());

                    HorizontalLayout nextEvolutionName = new HorizontalLayout(new H4("N°" +String.format("%04d", next.getPokedexId())+ " " + next.getName().getFr()));
                    nextEvolutionName.setWidthFull();
                    nextEvolutionName.addClassNames("type", pokemon.getTypes().getFirst().getName().toLowerCase());
                    nextEvolutionName.setAlignItems(Alignment.CENTER);
                    nextEvolutionName.setJustifyContentMode(JustifyContentMode.CENTER);

                    HorizontalLayout spriteLayout = new HorizontalLayout();
                    Image sprite = new Image(next.getSprites().getRegular(), next.getName().getFr());
                    spriteLayout.add(sprite);
                    spriteLayout.setWidthFull();
                    spriteLayout.setAlignItems(Alignment.CENTER);
                    spriteLayout.setJustifyContentMode(JustifyContentMode.CENTER);

                    nextEvolutionLayout.add(nextEvolutionName, spriteLayout);
                }

                stepLayout.add(nextEvolutionLayout);
            }
            if(!CollectionUtils.isEmpty(pokemon.getEvolution().getMega())){
                VerticalLayout megaEvolutionLayout = new VerticalLayout();
                HorizontalLayout megaEvolutionLabel = new HorizontalLayout(new H4("Méga évolution"));
                megaEvolutionLabel.setWidthFull();
                megaEvolutionLabel.addClassNames("type", pokemon.getTypes().getFirst().getName().toLowerCase());
                megaEvolutionLabel.setAlignItems(Alignment.CENTER);
                megaEvolutionLabel.setJustifyContentMode(JustifyContentMode.CENTER);

                megaEvolutionLayout.add(megaEvolutionLabel);

                pokemon.getEvolution().getMega().forEach(step -> {
                    VerticalLayout evoStep = new VerticalLayout(FlexComponent.JustifyContentMode.CENTER);

                    HorizontalLayout spriteLayout = new HorizontalLayout();
                    Image sprite = new Image(step.getSprites().getRegular(), step.getOrbe());
                    spriteLayout.add(sprite);
                    spriteLayout.setWidthFull();
                    spriteLayout.setAlignItems(Alignment.CENTER);
                    spriteLayout.setJustifyContentMode(JustifyContentMode.CENTER);

                    HorizontalLayout orbe = new HorizontalLayout(new Span(step.getOrbe()));
                    orbe.setWidthFull();
                    orbe.setAlignItems(Alignment.CENTER);
                    orbe.setJustifyContentMode(JustifyContentMode.CENTER);

                    evoStep.add(spriteLayout, orbe);
                    megaEvolutionLayout.add(evoStep);
                });
                stepLayout.add(megaEvolutionLayout);
            }

            evolutionsLayout.add(stepLayout);
        }

    }
}
