package de.htwg.msi.infe.ermcompile.model.Attribute;

import de.htwg.msi.infe.ermcompile.XMLAdapter.KeyXMLAdapter;
import lombok.Getter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;

@Getter
public class FK {

    @XmlElementWrapper
    @XmlElement(name = "attribute")
    @XmlJavaTypeAdapter(KeyXMLAdapter.class)
    private ArrayList<Attribute> attributes;

    @XmlElement(name="referenceTable")
    private String referencedTableName;

    @XmlElementWrapper
    @XmlElement(name = "attribute")
    @XmlJavaTypeAdapter(KeyXMLAdapter.class)
    private ArrayList<Attribute> referencedAttributes;

    public FK(String referencedTableName){
        this.attributes = new ArrayList<Attribute>();
        this.referencedTableName=referencedTableName;
        this.referencedAttributes = new ArrayList<Attribute>();
    }

    public FK(String referencedTableName,Attribute attribute, Attribute referencedAttribute){
        this.attributes = new ArrayList<Attribute>();
        this.referencedTableName=referencedTableName;
        this.referencedAttributes = new ArrayList<Attribute>();
        this.addForeignKeyPair(attribute,referencedAttribute);
    }

    public FK(String referencedTableName, ArrayList<Attribute> referencedAttributes){
        this.attributes = new ArrayList<Attribute>();
        for(Attribute a : referencedAttributes)
            this.attributes.add(new Attribute(a.getName() + "_FK", a.isNotNull()));
        this.referencedTableName=referencedTableName;
        this.referencedAttributes = referencedAttributes;
    }

    public void addForeignKeyPair(Attribute attribute, Attribute referencedAttribute){
        this.attributes.add(attribute);
        this.referencedAttributes.add(referencedAttribute);
    }
}
