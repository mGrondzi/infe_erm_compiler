package de.htwg.msi.infe.ermcompile.model;

import lombok.Getter;
import lombok.NonNull;

import java.util.List;

/**
 * representation of Relationtype
 * see xml in ressources
 */
@Getter
public class Relationtype {

    @NonNull
    private String name;
    @NonNull
    private List<EntityLink> links;
    private List<Attribute> attributes;

    public Relationtype(String name, List<Attribute> attributes, List<EntityLink> links) {
        this.name = name;
        this.attributes = attributes;
        this.links = links;
    }

    public void addEntityLink(EntityLink entityLink){
        this.links.add(entityLink);
    }

    public void removeEntityLink(int position){
        this.links.remove(position);
    }

    public void addAttribute(Attribute attribute){
        this.attributes.add(attribute);
    }

    public void removeAttribute(int position){
        this.attributes.remove(position);
    }
}
