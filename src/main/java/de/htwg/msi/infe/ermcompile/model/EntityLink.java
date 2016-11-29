package de.htwg.msi.infe.ermcompile.model;

import de.htwg.msi.infe.ermcompile.model.Table.Entitytype;
import lombok.Getter;
import lombok.NonNull;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * representation of links between enititys
 * see xml in ressources
 */
@Getter
public class EntityLink {
    @NonNull
    @XmlElement
    private Entitytype enititytype;
    @NonNull
    @XmlElement
    private Cardinality cardinality;
    @XmlAttribute
    private String functionality;

    public EntityLink(Entitytype enititytype, Cardinality cardinality, String functionality) {
        this.enititytype = enititytype;
        this.functionality = functionality;
        this.cardinality = cardinality;
    }
}
