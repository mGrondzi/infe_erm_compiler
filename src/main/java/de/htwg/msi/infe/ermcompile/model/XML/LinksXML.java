package de.htwg.msi.infe.ermcompile.model.XML;

import lombok.Getter;
import lombok.Setter;


/**
 * 1to1 representation of EntityLink from XML
 */
@Getter
@Setter
public class LinksXML {

    private String name;
    private String min;
    private String max;
    private String functionality;

    public LinksXML() {

    }

}
