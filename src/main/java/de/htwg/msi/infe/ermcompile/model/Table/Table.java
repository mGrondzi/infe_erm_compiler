package de.htwg.msi.infe.ermcompile.model.Table;

import de.htwg.msi.infe.ermcompile.XMLAdapter.KeyXMLAdapter;
import de.htwg.msi.infe.ermcompile.model.Attribute.Attribute;
import de.htwg.msi.infe.ermcompile.model.Attribute.Key;
import de.htwg.msi.infe.ermcompile.model.EntityLink;
import lombok.Getter;
import lombok.NonNull;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@Getter
public abstract class Table {

    @NonNull
    @XmlAttribute
    private String name;
    @XmlElementWrapper(name="attrributes")
    @XmlElement(name="attribute")
    private List<Attribute> attributes;
    @XmlElementWrapper(name="keys")
    @XmlElement(name = "key")
    //@XmlJavaTypeAdapter(KeyXMLAdapter.class)
    private List<Key> keys;


    public Table(String name, List<Attribute> attributes) {
        this.name = name;
        this.attributes = attributes;
        if (!attributes.isEmpty())
            CreateKeyTable();
    }
    public Table(String name) {
        this.name = name;
        this.attributes = new ArrayList<Attribute>();
        this.keys = new ArrayList<Key>();
    }


    private void CreateKeyTable(){
        this.keys = new ArrayList<Key>();
        for (Attribute a: this.attributes) {
            CheckIfKey(a);
        }
    }

    private void CheckIfKey(Attribute a){
        if(a instanceof Key)
            this.keys.add((Key) a);
    }

    public void addAttribute(Attribute attribute){
        this.attributes.add(attribute);
        CheckIfKey(attribute);
    }

    public void removeAttribute(Attribute attribute){
        this.attributes.remove(attribute);
        if(this.keys.contains(attribute))
            this.keys.remove(attribute);
    }

}
