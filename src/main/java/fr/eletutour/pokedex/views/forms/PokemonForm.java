package fr.eletutour.pokedex.views.forms;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;
import fr.eletutour.pokedex.models.Pokemon;
import fr.eletutour.pokedex.services.PokemonService;
import lombok.Getter;

public class PokemonForm extends FormLayout {

    Button close = new Button("Cancel");
    TextField pokedexId = new TextField("pokedex id");
    HorizontalLayout numAndName = new HorizontalLayout(FlexComponent.Alignment.CENTER);
    Image sprite = new Image();
    HorizontalLayout types = new HorizontalLayout(FlexComponent.Alignment.CENTER);
    HorizontalLayout cat = new HorizontalLayout(FlexComponent.Alignment.CENTER);
    HorizontalLayout talentsLayout = new HorizontalLayout(FlexComponent.Alignment.CENTER);
    HorizontalLayout tailleLayout = new HorizontalLayout();
    HorizontalLayout poidsLayout = new HorizontalLayout();
    HorizontalLayout eggsLayout = new HorizontalLayout(FlexComponent.Alignment.CENTER);
    HorizontalLayout resistanceLayout = new HorizontalLayout(FlexComponent.Alignment.CENTER);
    HorizontalLayout evolutionLayout = new HorizontalLayout(FlexComponent.Alignment.CENTER);
    HorizontalLayout gigamaxLayout = new HorizontalLayout(FlexComponent.Alignment.CENTER);
    Binder<Pokemon> binder = new Binder<>(Pokemon.class);

    public PokemonForm(){
        binder.bindInstanceFields(this);

        add(numAndName, sprite, types, cat, tailleLayout, poidsLayout);
        add(talentsLayout, eggsLayout, evolutionLayout, gigamaxLayout);
        add(createButtonsLayout());
    }

    private HorizontalLayout createButtonsLayout() {
        close.addClickShortcut(Key.ESCAPE);
        close.addClickListener(event -> fireEvent(new PokemonFormEvent.CloseEvent(this)));
        return new HorizontalLayout(close);
    }

    public void setPokemon(Pokemon pokemon){
        binder.setBean(pokemon);
        if(pokemon != null){
            initComponents();
            PokemonDetails details = new PokemonDetails(pokemon, new PokemonService());
            details.configureNumAndName(numAndName);
            details.configureSprite(sprite);
            details.configureType(types);
            details.configureCategorie(cat);
            details.configureTalents(talentsLayout);
            details.configurePoidsLayout(poidsLayout);
            details.configureTailleLayout(tailleLayout);
            details.configureOeufs(eggsLayout);
            details.configureResistanceLayout(resistanceLayout);
            details.configureEvolutions(evolutionLayout);
            details.configureGigamax(gigamaxLayout);
        }

    }

    private void initComponents() {
        numAndName.removeAll();
        types.removeAll();
        talentsLayout.removeAll();
        cat.removeAll();
        tailleLayout.removeAll();
        poidsLayout.removeAll();
        eggsLayout.removeAll();
        resistanceLayout.removeAll();
        evolutionLayout.removeAll();
        gigamaxLayout.removeAll();
    }

    @Getter
    public abstract static class PokemonFormEvent extends ComponentEvent<PokemonForm> {

        private Pokemon pokemon;

        public PokemonFormEvent(PokemonForm source, Pokemon pokemon) {
            super(source, false);
            this.pokemon = pokemon;
        }

        public static class CloseEvent extends PokemonFormEvent {
            CloseEvent(PokemonForm source) {
                super(source, null);
                source.setVisible(false);
            }
        }
    }

    public Registration addCloseListener(ComponentEventListener<PokemonFormEvent.CloseEvent> listener) {
        return addListener(PokemonFormEvent.CloseEvent.class, listener);
    }

}

