package fr.eletutour.pokedex.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Type implements Comparable<Type>{
    private String name;
    private String image;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Type type)) return false;

        if (!getName().equals(type.getName())) return false;
        return getImage().equals(type.getImage());
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getImage().hashCode();
        return result;
    }

    @Override
    public int compareTo(Type other) {
        return this.name.compareTo(other.getName());
    }
}
