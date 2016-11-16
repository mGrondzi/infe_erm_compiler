package de.htwg.msi.infe.ermcompile.model;

import java.util.List;

/**
 * representation of Relationtype
 * see xml in ressources
 */
public class Relationtype {

    private String name;
    private List<Attribute> attributes;
    private List<EntityLink> links;

    public Relationtype(String name, List<Attribute> attributes, List<EntityLink> links) {
        this.name = name;
        this.attributes = attributes;
        this.links = links;
    }

    public String getName() {
        return name;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public List<EntityLink> getLinks() {
        return links;
    }
}
