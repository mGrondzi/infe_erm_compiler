package de.htwg.msi.infe.ermcompile.logic;

import de.htwg.msi.infe.ermcompile.model.Attribute.Attribute;
import de.htwg.msi.infe.ermcompile.model.ERM.Erm;
import de.htwg.msi.infe.ermcompile.model.Table.EntityLink;
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
        Entitytype leftEntity = linkLeft.getEnititytype();
        Entitytype rightEntity = linkRight.getEnititytype();

        if (linkLeft.getFunctionality().equals("1") && linkRight.getFunctionality().equals("1")) {
            if (linkLeft.getCardinality().getMin().equals("1") && linkLeft.getCardinality().getMax().equals("1")) {
                if (linkRight.getCardinality().getMin().equals("1") && linkRight.getCardinality().getMax().equals("1")) {
                    //Case [L](1,1)--[]--(1,1)[R]
                    //TODO Fasse beide in einer Zusammen
                    ArrayList<Attribute> rightAttributes = rightEntity.getAttributes();

                    for(Attribute attribute: rightAttributes){
                        leftEntity.addAttribute(attribute);
                    }

                    /* 1. Erstelle neuen PK aus Kombination der PK's beider Tabellen
                       2. Lösche rechte Tabelle
                     */


                } else {
                    //Case [L](1,1)--[]--(0,1)[R]
                    //TODO Packe PK von R als FK in L
                }
            } else {
                if (linkRight.getCardinality().getMin().equals("1") && linkRight.getCardinality().getMax().equals("1")) {
                    //Case [L](0,1)--[]--(1,1)[R]
                    //TODO Packe PK von L als FK in R
                } else {
                    //Case [L](0,1)--[]--(0,1)[R]
                    //TODO Erstelle Neue Tabelle PK ist PK(L)+PK(R)
                }
            }

        } else if (linkLeft.getFunctionality().equals("1") && linkRight.getFunctionality().equals("N")) {

        } else if (linkLeft.getFunctionality().equals("N") && linkRight.getFunctionality().equals("1")) {

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
