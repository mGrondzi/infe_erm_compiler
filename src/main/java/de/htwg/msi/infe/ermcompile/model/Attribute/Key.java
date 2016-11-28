package de.htwg.msi.infe.ermcompile.model.Attribute;


import lombok.Getter;

import javax.xml.bind.annotation.XmlAttribute;

public abstract class Key extends Attribute{

    public Key(String name, Boolean notNull){
        super(name, notNull);
    }
}
