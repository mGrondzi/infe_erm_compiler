package de.htwg.msi.infe.ermcompile.model.Attribute;

import lombok.Getter;
import lombok.NonNull;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlValue;

/**
 * Representation of Attribute
 * see xml in resources

 */
@Getter
public class Attribute {

    @NonNull
    @XmlValue
    private String name;
    @NonNull
    @XmlAttribute
    private boolean notNull;

    public Attribute(String name, boolean notnull) {
        this.name = name;
        this.notNull = notnull;
    }
}
