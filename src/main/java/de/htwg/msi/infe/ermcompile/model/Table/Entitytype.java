package de.htwg.msi.infe.ermcompile.model.Table;

import de.htwg.msi.infe.ermcompile.model.Attribute.Attribute;
import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * representation of the Entitytype
 * see xml in resources
 */
public class Entitytype extends Table{

    public Entitytype(String name, ArrayList<Attribute> attributes) {
        super(name,attributes);
    }

    public Entitytype(String name) {
        super(name);
    }
}


