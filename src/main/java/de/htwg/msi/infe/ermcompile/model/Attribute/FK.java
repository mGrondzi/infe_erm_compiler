package de.htwg.msi.infe.ermcompile.model.Attribute;

import lombok.Getter;

import javax.xml.bind.annotation.XmlElement;

@Getter
public class FK extends Key{

    @XmlElement(name="referenceTable")
    private String referencedTableName;

    public FK(String name, String referencedTableName){
        super(name,true);
        this.referencedTableName=referencedTableName;
    }
}
