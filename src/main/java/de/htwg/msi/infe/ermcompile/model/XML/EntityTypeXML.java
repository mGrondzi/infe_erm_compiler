package de.htwg.msi.infe.ermcompile.model.XML;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class EntityTypeXML {

    private String name;
    private List<AttributesXML> attributesXMLList;

    public EntityTypeXML() {
        this.attributesXMLList = new ArrayList<AttributesXML>();
    }


}
