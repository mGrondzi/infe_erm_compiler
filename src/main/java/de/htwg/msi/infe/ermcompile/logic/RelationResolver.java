package de.htwg.msi.infe.ermcompile.logic;

import de.htwg.msi.infe.ermcompile.model.Attribute.AK;
import de.htwg.msi.infe.ermcompile.model.Attribute.Attribute;
import de.htwg.msi.infe.ermcompile.model.Attribute.FK;
import de.htwg.msi.infe.ermcompile.model.Attribute.PK;
import de.htwg.msi.infe.ermcompile.model.ERM.Erm;
import de.htwg.msi.infe.ermcompile.model.Table.EntityLink;
import de.htwg.msi.infe.ermcompile.model.Table.Entitytype;
import de.htwg.msi.infe.ermcompile.model.Table.Relationtype;
import de.htwg.msi.infe.ermcompile.model.Table.Table;
import javafx.scene.control.Tab;

import javax.management.relation.Relation;
import javax.management.relation.RelationType;
import java.util.ArrayList;

/**
 * Created by phili on 29.11.2016.
 */
public class RelationResolver {

    private Erm erm;

    public RelationResolver(Erm erm) {
        this.erm = erm;
    }

    public void resolve() {

        for (Table table : this.erm.getRelationtypes()) {
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

                    this.combineBinary1to1(rt, leftEntity, rightEntity);

                } else {
                    /*
                    Case [L](1,1)--[]--(0,1)[R]
                    Goal: Packe PK von R als FK in L
                    Step:
                        1. Get PK from R
                        2. Add key as FK to L
                     */

                    //leftEntity.addAttribute(new FK(attribute.getName()));


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

    /**
     * Case [L](1,1)--[]--(1,1)[R]
     * Goal: Fasse beide in einer Zusammen
     * Step:
     * 1. Get all attributes from RIGHT and LINK table
     * 2. Add them to LEFT table
     * 4. Add all PK's from RIGHT as AK's to LEFT
     * 3. Delete RIGHT and LINK table from erm
     */
    private void combineBinary1to1(Relationtype rt, Entitytype leftEntity, Entitytype rightEntity) {

        ArrayList<Attribute> rightAttributes = rightEntity.getAttributes();
        ArrayList<Attribute> linkAttributes = rt.getAttributes();

        leftEntity.addAlternateKey(new AK((ArrayList) rightEntity.getPkKeys()));
        //TODO Add FK's of RIGHT as FK's to LEFT
        for (Attribute attribute1 : rightAttributes) {
            if (attribute1 instanceof PK) {
                Attribute temp = new Attribute(attribute1.getName(), attribute1.isNotNull());
                leftEntity.addAttribute(temp);
            } else {
                leftEntity.addAttribute(attribute1);
            }
        }

        leftEntity.addAlternateKey(new AK((ArrayList) rt.getPkKeys()));
        for (Attribute attribute2 : linkAttributes) {
            if (attribute2 instanceof PK) {
                Attribute temp = new Attribute(attribute2.getName(), attribute2.isNotNull());
                leftEntity.addAttribute(temp);
            } else {
                leftEntity.addAttribute(attribute2);
            }
        }

        this.erm.removeTable(this.erm.getTables().indexOf(rightEntity));
        this.erm.removeTable(this.erm.getTables().indexOf(rt));
        this.relinkingBinaryRelations(leftEntity, rightEntity);
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

    private void relinkingBinaryRelations(Entitytype newTable, Entitytype deletedTable) {
        ArrayList<Relationtype> rts = this.erm.getRelationtypes();
        for (Relationtype relation : rts) {
            if (relation.getLinks().get(0).getEnititytype().getName().equals(deletedTable.getName())) {
                relation.getLinks().add(new EntityLink(newTable, relation.getLinks().get(0).getCardinality(), relation.getLinks().get(0).getFunctionality()));
                relation.getLinks().remove(0);
            } else if (relation.getLinks().get(1).getEnititytype().getName().equals(deletedTable.getName())) {
                relation.getLinks().add(new EntityLink(newTable, relation.getLinks().get(1).getCardinality(), relation.getLinks().get(1).getFunctionality()));
                relation.getLinks().remove(1);
            }
        }
    }
}
