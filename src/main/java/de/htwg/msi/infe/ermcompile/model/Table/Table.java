package de.htwg.msi.infe.ermcompile.model.Table;

import de.htwg.msi.infe.ermcompile.XMLAdapter.AttributeXMLAdapter;
import de.htwg.msi.infe.ermcompile.XMLAdapter.KeyXMLAdapter;
import de.htwg.msi.infe.ermcompile.model.Attribute.AK;
import de.htwg.msi.infe.ermcompile.model.Attribute.Attribute;
import de.htwg.msi.infe.ermcompile.model.Attribute.FK;
import de.htwg.msi.infe.ermcompile.model.Attribute.PK;
import lombok.Getter;
import lombok.NonNull;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;

@XmlAccessorType(XmlAccessType.FIELD)
@Getter
public abstract class Table {

    @NonNull
    @XmlAttribute
    private String name;
    @XmlElementWrapper(name="attributes")
    @XmlElements({
            @XmlElement(name="attribute",type = Attribute.class),
            @XmlElement(name="attribute",type = PK.class)})
    @XmlJavaTypeAdapter(AttributeXMLAdapter.class)
    private ArrayList<Attribute> attributes;
    @XmlElementWrapper(name="primaryKey")
    @XmlElement(name = "attribute")
    @XmlJavaTypeAdapter(KeyXMLAdapter.class)
    private ArrayList<PK> pkKeys = null;
    @XmlElementWrapper(name="alternateKeys")
    @XmlElement(name = "alternateKey",nillable = true)
    private ArrayList<AK> akKeys = null;
    @XmlElementWrapper(name="foreignKeys")
    @XmlElement(name = "foreignKey",nillable = true)
   // @XmlJavaTypeAdapter(KeyXMLAdapter.class)
    private ArrayList<FK> fkKeys = null;

    public Table(String name, ArrayList<Attribute> attributes) {
        this.name = name;
        this.attributes = attributes;
        if (!attributes.isEmpty())
            CreateKeyTable();
    }
    public Table(String name) {
        this.name = name;
        this.attributes = new ArrayList<Attribute>();
    }


    private void CreateKeyTable(){
        for (Attribute a: this.attributes) {
            CheckIfKey(a);
        }
    }

    private void CheckIfKey(Attribute a){
        if(a instanceof PK){
            if(this.pkKeys == null)
                this.pkKeys = new ArrayList<PK>();
            this.pkKeys.add((PK) a);
        }
    }

    public void addAttribute(Attribute attribute){
        this.attributes.add(attribute);
        CheckIfKey(attribute);
    }

    public void addForeignKey(FK fk){
        if(this.fkKeys == null)
            fkKeys = new ArrayList<FK>();
        this.fkKeys.add(fk);
    }

    public void addAlternateKey(AK ak){
        if(this.akKeys == null)
            akKeys = new ArrayList<AK>();
        this.akKeys.add(ak);
    }

    public void removeAttribute(Attribute attribute){
        if(this.pkKeys.contains(attribute))
            this.pkKeys.remove(attribute);
    }

}
