package de.htwg.msi.infe.ermcompile.model;

import de.htwg.msi.infe.ermcompile.model.Table.Entitytype;
import lombok.Getter;
import lombok.NonNull;

/**
 * representation of links between enititys
 * see xml in ressources
 */
@Getter
public class EntityLink {
    @NonNull
    private Entitytype enititytype;
    @NonNull
    private Cardinality cardinality;
    private String functionality;

    public EntityLink(Entitytype enititytype, Cardinality cardinality, String functionality) {
        this.enititytype = enititytype;
        this.functionality = functionality;
        this.cardinality = cardinality;
    }
}
