package de.htwg.msi.infe.ermcompile.logic;

import de.htwg.msi.infe.ermcompile.model.ERM.Erm;
import de.htwg.msi.infe.ermcompile.model.EntityLink;
import de.htwg.msi.infe.ermcompile.model.Table.Entitytype;
import de.htwg.msi.infe.ermcompile.model.Table.Relationtype;
import de.htwg.msi.infe.ermcompile.model.Table.Table;

import java.util.ArrayList;

/**
 * Created by phili on 29.11.2016.
 */
public class RelationResolver {

    private Erm erm;
    private ArrayList<Table> rtables = new ArrayList<Table>();

    public RelationResolver(Erm erm) {
        this.erm = erm;
        this.rtables = this.erm.getTables();
    }

    public void resolve() {

        this.rtables = this.extractRelationTypes(rtables);

        for (Table table : this.rtables) {
            if (this.isRLBinary((Relationtype) table)) {
                this.combineBinaryRelation((Relationtype) table);
            } else {
                // combine ternary relations
            }
        }
    }

    private void combineBinaryRelation(Relationtype rt) {
        ArrayList<EntityLink> links = rt.getLinks();
        EntityLink linkLeft = rt.getLinks().get(0);
        EntityLink linkRight = rt.getLinks().get(1);

        if (linkLeft.getFunctionality() == "1" && linkRight.getFunctionality() == "1") {
            if (linkLeft.getCardinality().getMin() == "1" && linkLeft.getCardinality().getMax() == "1") {
                if (linkRight.getCardinality().getMin() == "1" && linkRight.getCardinality().getMax() == "1") {
                    // L(1,1)--(1,1)R
                    //Fasse beide in einer Zusammen
                } else {
                    // L(1,1)--(0,1)R
                    // Packe PK von R als FK in L
                }
            } else {
                if (linkRight.getCardinality().getMin() == "1" && linkRight.getCardinality().getMax() == "1") {
                    // L(0,1)--(1,1)R
                    // Packe PK von L als FK in R
                } else {
                    // L(0,1)--(0,1)R
                    // Erstelle Neue Tabelle PK ist PK(L)+PK(R)
                }
            }

        } else if (linkLeft.getFunctionality() == "1" && linkRight.getFunctionality() == "N") {

        } else if (linkLeft.getFunctionality() == "N" && linkRight.getFunctionality() == "1") {

        } else {// N M

        }
    }

    public ArrayList<Table> extractEntityTypes(ArrayList<Table> list) {
        ArrayList<Table> en = new ArrayList<Table>();
        for (Table table : list) {
            if (table instanceof Entitytype) {
                en.add(table);
            }
        }
        return en;
    }

    private ArrayList<Table> extractRelationTypes(ArrayList<Table> list) {
        ArrayList<Table> rl = new ArrayList<Table>();
        for (Table table : list) {
            if (table instanceof Relationtype) {
                rl.add(table);
            }
        }
        return rl;
    }

    private void getLinksOfRelationtype(Relationtype rl) {
        ArrayList<EntityLink> link = rl.getLinks();
    }

    private boolean isRLBinary(Relationtype rl) {

        if (rl.getLinks().size() == 2) {
            return true;
        }
        return false;
    }

}
