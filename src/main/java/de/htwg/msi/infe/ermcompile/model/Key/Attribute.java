package de.htwg.msi.infe.ermcompile.model.Key;

import lombok.Getter;
import lombok.NonNull;

/**
 * Representation of Attribute
 * see xml in resources

 */
@Getter
public class Attribute {

    @NonNull
    private String name;
    @NonNull
    private boolean notnull;

    public Attribute(String name, boolean notnull) {
        this.name = name;
        this.notnull = notnull;
    }
}
