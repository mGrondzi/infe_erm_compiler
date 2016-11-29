package de.htwg.msi.infe.ermcompile.model.Table;

import de.htwg.msi.infe.ermcompile.XMLAdapter.KeyXMLAdapter;
import de.htwg.msi.infe.ermcompile.model.Attribute.*;
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
    @XmlElementWrapper(name="primaryKey")
    @XmlElement(name = "attribute")
    private List<PK> pkKeys = null;
    @XmlElementWrapper(name="alternativeKey")
    @XmlElement(name = "attribute",nillable = true)
    private List<AK> akKeys = null;
    @XmlElementWrapper(name="foreignKeys")
    @XmlElement(name = "foreignKey",nillable = true)
    private List<FK> fkKeys = null;

    public Table(String name, List<Attribute> attributes) {
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
        else if (a instanceof AK){
            if(this.akKeys == null)
                this.akKeys = new ArrayList<AK>();
            this.akKeys.add((AK) a);
        }
        else if (a instanceof  FK){
            if(this.fkKeys == null)
                this.fkKeys = new ArrayList<FK>();
            this.fkKeys.add((FK) a);
        }
    }

    public void addAttribute(Attribute attribute){
        this.attributes.add(attribute);
        CheckIfKey(attribute);
    }

    public void removeAttribute(Attribute attribute){
        this.attributes.remove(attribute);
        if(this.pkKeys.contains(attribute))
            this.pkKeys.remove(attribute);
    }

}
