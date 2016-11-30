package de.htwg.msi.infe.ermcompile.model.Attribute;

import de.htwg.msi.infe.ermcompile.XMLAdapter.KeyXMLAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;

public class AK {

    @XmlJavaTypeAdapter(KeyXMLAdapter.class)
    @XmlElement(name="attribute")
    private ArrayList<Attribute> attributes;

    public AK(){
        this.attributes = new ArrayList<Attribute>();
    }

    public AK(Attribute attribute){
        this.attributes = new ArrayList<Attribute>();
        this.attributes.add(attribute);
    }

    public AK(ArrayList<Attribute> attribute){
        this.attributes = attribute;
    }

    public void addAttribute(Attribute attribute){
        this.attributes.add(attribute);
    }

    public void removeAttribute(Attribute attribute){
        if(this.attributes.contains(attribute))
            this.attributes.remove(attribute);
    }
}
