package de.htwg.msi.infe.ermcompile.model.XML;

import lombok.Getter;
import lombok.Setter;


/**
 * 1to1 representation from XML
 */
@Getter
@Setter
public class AttributesXML {

    private String name;
    private boolean isprimkey;
    private boolean notnull;

    public AttributesXML() {

    }
}
