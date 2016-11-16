package de.htwg.msi.infe.ermcompile.model;

import java.util.List;

/**
 * representation of the Entitytype
 * see xml in resources
 */
public class Entitytype {

    private String name;
    private List<Attribute> attributes;

    public Entitytype(String name, List<Attribute> attributes) {
        this.name = name;
        this.attributes = attributes;
    }

    public String getName() {
        return name;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }
}


