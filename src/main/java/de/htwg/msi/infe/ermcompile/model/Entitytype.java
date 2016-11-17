package de.htwg.msi.infe.ermcompile.model;

import lombok.Getter;
import lombok.NonNull;

import java.util.List;

/**
 * representation of the Entitytype
 * see xml in resources
 */
@Getter
public class Entitytype {

    @NonNull
    private String name;
    private List<Attribute> attributes;

    public Entitytype(String name, List<Attribute> attributes) {
        this.name = name;
        this.attributes = attributes;
    }

    public void addAttribute(Attribute attribute){
        this.attributes.add(attribute);
    }

    public void removeAttribute(int position){
        this.attributes.remove(position);
    }
}


