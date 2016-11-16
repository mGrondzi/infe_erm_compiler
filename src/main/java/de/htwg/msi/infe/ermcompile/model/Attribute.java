package de.htwg.msi.infe.ermcompile.model;

/**
 * Representation of Attribute
 * see xml in resources

 */
public class Attribute {

    private String name;
    private boolean isPrimaryKey;
    private boolean notnull;

    public Attribute(String name, boolean isPrimaryKey, boolean notnull) {
        this.name = name;
        this.isPrimaryKey = isPrimaryKey;
        this.notnull = notnull;
    }


    public String getName() {
        return name;
    }

    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public boolean isNotnull() {
        return notnull;
    }
}
