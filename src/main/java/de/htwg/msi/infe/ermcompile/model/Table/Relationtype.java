package de.htwg.msi.infe.ermcompile.model.Table;

import de.htwg.msi.infe.ermcompile.model.EntityLink;
import de.htwg.msi.infe.ermcompile.model.Attribute.Attribute;
import lombok.Getter;
import lombok.NonNull;

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

    public void addEntityLink(EntityLink entityLink){
        this.links.add(entityLink);
    }

    public void removeEntityLink(int position){
        this.links.remove(position);
    }
}
