package de.htwg.msi.infe.ermcompile.model.Attribute;

import lombok.Getter;
import lombok.NonNull;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Representation of Attribute
 * see xml in resources

 */
@Getter
public class Attribute {

    @NonNull
    @XmlAttribute
    private String name;
    @NonNull
    @XmlAttribute
    private boolean notNull;

    public Attribute(String name, boolean notnull) {
        this.name = name;
        this.notNull = notnull;
    }
}
