package de.htwg.msi.infe.ermcompile.model.Table;

import de.htwg.msi.infe.ermcompile.model.Key.Attribute;
import lombok.Getter;
import lombok.NonNull;

import java.security.Key;
import java.util.List;

/**
 * representation of the Entitytype
 * see xml in resources
 */
@Getter
public class Entitytype extends Table{

    public Entitytype(String name, List<Attribute> attributes) {
        super(name,attributes);
    }
}


