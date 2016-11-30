package de.htwg.msi.infe.ermcompile.model.Table;

import de.htwg.msi.infe.ermcompile.model.Attribute.Attribute;
import lombok.Getter;
import lombok.NonNull;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;

/**
 * representation of Relationtype
 * see xml in ressources
 */
@Getter
public class Relationtype extends Table{

    @NonNull
    @XmlElementWrapper(name = "links")
    @XmlElement(name = "link")
    private ArrayList<EntityLink> links;

    public Relationtype(String name, ArrayList<Attribute> attributes, ArrayList<EntityLink> links) {
        super(name, attributes);
        this.links = links;
    }

    public Relationtype(String name) {
        super(name);
        this.links = new ArrayList<EntityLink>();
    }

    public void addEntityLink(EntityLink entityLink){
        this.getLinks().add(entityLink);
    }

    public void removeEntityLink(int position){
        this.getLinks().remove(position);
    }

}
