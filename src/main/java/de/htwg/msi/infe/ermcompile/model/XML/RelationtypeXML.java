package de.htwg.msi.infe.ermcompile.model.XML;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by John on 26.11.2016.
 */
@Getter
@Setter
public class RelationtypeXML {

    private String name;
    private List<AttributesXML> attributesXMLList;
    private List<LinksXML> entityLinksXMLList;

    public RelationtypeXML() {
        this.attributesXMLList = new ArrayList<AttributesXML>();
        this.entityLinksXMLList = new ArrayList<LinksXML>();
    }

}
