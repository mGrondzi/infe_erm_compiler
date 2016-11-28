package de.htwg.msi.infe.ermcompile.model.Table;

import de.htwg.msi.infe.ermcompile.model.EntityLink;
import de.htwg.msi.infe.ermcompile.model.Attribute.Attribute;
import lombok.Getter;
import lombok.NonNull;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

/**
 * representation of Relationtype
 * see xml in ressources
 */
@Getter
public class Relationtype extends Table{

    @NonNull
    private List<EntityLink> links;

    public Relationtype(String name, List<Attribute> attributes, List<EntityLink> links) {
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

    @XmlElement(name = "link")
    public List<EntityLink> getLinks() {
        return links;
    }
}
