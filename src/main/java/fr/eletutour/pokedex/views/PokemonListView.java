package fr.eletutour.pokedex.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import fr.eletutour.pokedex.models.Pokemon;
import fr.eletutour.pokedex.services.NavigationService;
import fr.eletutour.pokedex.services.PokemonService;

@Route(value = "/pokemons", layout = MainView.class)
@PageTitle(value = "Pokémons")
public class PokemonListView extends VerticalLayout {

    Grid<Pokemon> grid = new Grid<>(Pokemon.class);
    TextField filterText = new TextField();

    PokemonService service;
    NavigationService navigationService;

    public PokemonListView(PokemonService pokemonService, NavigationService navigationService){
        this.service = pokemonService;
        this.navigationService = navigationService;
        setSizeFull();
        configureGrid();

        add(getToolbar(), getContent());
        updateList();
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

    private void updateList() {
        grid.setItems(service.findAllPokemon(filterText.getValue()));
    }

    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        grid.setColumns("pokedexId");

        grid.addColumn(pokemon -> pokemon.getName().getFr()).setHeader("Name");

        grid.addComponentColumn(pokemon -> {
            Div div = new Div();
            pokemon.getTypes().forEach(t -> div.add(new Image(t.getImage(), t.getName())));
            return div;
        }).setHeader("Types");
        grid.addComponentColumn(pokemon ->
            {
                Image img = new Image(pokemon.getSprites().getRegular(), String.valueOf(pokemon.getPokedexId()));
                img.setHeight(150, Unit.PIXELS);
                img.setWidth(150, Unit.PIXELS);
                return img;
            }).setHeader("Sprite");
        grid.addComponentColumn(pokemon -> {
            Button button = new Button("Voir Détails");
            button.addClickListener(e -> {
                navigationService.setSelectedPokemon(pokemon);
                UI.getCurrent().navigate(PokemonDetailView.class);
            });
            return button;
        }).setHeader("Détails");

        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        var toolbar = new HorizontalLayout(filterText);
        toolbar.addClassName("toolbar");
        return toolbar;
    }
}
