package de.htwg.msi.infe.ermcompile.model;


import lombok.Getter;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by Nexoc on 17.11.2016.
 */

@Getter
public class Cardinality {
    @XmlAttribute
    private String min;
    @XmlAttribute
    private String max;

    public Cardinality(String min, String max) {this.min=min; this.max=max;}

}
