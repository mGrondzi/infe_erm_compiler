package de.htwg.msi.infe.ermcompile.model;

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
    private boolean isPrimaryKey;
    @NonNull
    private boolean notnull;

    public Attribute(String name, boolean isPrimaryKey, boolean notnull) {
        this.name = name;
        this.isPrimaryKey = isPrimaryKey;
        this.notnull = notnull;
    }
}
