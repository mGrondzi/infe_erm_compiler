package de.htwg.msi.infe.ermcompile.logic;

import de.htwg.msi.infe.ermcompile.model.Attribute.AK;
import de.htwg.msi.infe.ermcompile.model.Attribute.Attribute;
import de.htwg.msi.infe.ermcompile.model.Attribute.FK;
import de.htwg.msi.infe.ermcompile.model.ERM.Erm;
import de.htwg.msi.infe.ermcompile.model.Table.EntityLink;
import de.htwg.msi.infe.ermcompile.model.Table.Entitytype;
import de.htwg.msi.infe.ermcompile.model.Table.Relationtype;
import de.htwg.msi.infe.ermcompile.model.Table.Table;

import java.util.ArrayList;

public class RelationResolver {

    private Erm originalErm;

    public RelationResolver(Erm erm) {
        this.originalErm = erm;
    }

    public Erm resolve() {
        for (Table table : this.originalErm.getRelationtypes()) {
            if (this.isRLBinary((Relationtype) table)) {
                this.combineBinaryRelation((Relationtype) table);
            } else {
                // combine ternary relations
            }
        }
        return this.originalErm;
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
                    /*
                    Case [L](1,1)--[]--(1,1)[R]
                    Goal: Fasse beide in einer Zusammen
                    Step:
                        1. Get all attributes from RIGHT and LINK table
                        2. Add them to LEFT table
                        4. Add all PK's from RIGHT as AK's to LEFT
                        3. Delete RIGHT and LINK table from originalErm
                     */
                    //
                    this.Solve1to1(rt, leftEntity, rightEntity);
                } else {
                    /*
                    Case [L](1,1)--[]--(0,1)[R]
                    Goal: Packe PK von R als FK in L
                    Step:
                        1. Get PK from R
                        2. Add key as FK to L
                     */
                    this.SolveNto1(rt, leftEntity, rightEntity);
                }
            } else {
                if (linkRight.getCardinality().getMin().equals("1") && linkRight.getCardinality().getMax().equals("1")) {
                    //Case [L](0,1)--[]--(1,1)[R]
                    //TODO Packe PK von L als FK in R
                    this.Solve1to1(rt, rightEntity, leftEntity);
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

    private void Solve1to1(Relationtype rt, Entitytype leftEntity, Entitytype rightEntity) {
        if (!rt.getAttributes().isEmpty()) {
            this.originalErm.getTables().get(this.originalErm.getTables().indexOf(leftEntity)).addAlternateKey(new AK((ArrayList) rt.getPkKeys()));
            for (Attribute attribute : rt.getAttributes()) {
                this.originalErm.getTables().get(this.originalErm.getTables().indexOf(leftEntity)).addAttribute(new Attribute(attribute));
            }
        }

        this.originalErm.getTables().get(this.originalErm.getTables().indexOf(leftEntity)).addAlternateKey(new AK((ArrayList) rightEntity.getPkKeys()));
        //TODO Add FK's of RIGHT as FK's to LEFT
        for (Attribute attribute : this.originalErm.getTables().get(this.originalErm.getTables().indexOf(rightEntity)).getAttributes()) {
            this.originalErm.getTables().get(this.originalErm.getTables().indexOf(leftEntity)).addAttribute(new Attribute(attribute));
        }

        this.originalErm.removeTable(this.originalErm.getTables().indexOf(rightEntity));
        this.originalErm.removeTable(this.originalErm.getTables().indexOf(rt));
        this.relinkingBinaryRelations(leftEntity, rightEntity);
    }

    private void SolveNto1(Relationtype rt, Entitytype leftEntity, Entitytype rightEntity) {
        if (!rt.getAttributes().isEmpty()) {
            this.originalErm.getTables().get(this.originalErm.getTables().indexOf(leftEntity)).addAlternateKey(new AK((ArrayList) rt.getPkKeys()));
            for (Attribute attribute : rt.getAttributes()) {
                this.originalErm.getTables().get(this.originalErm.getTables().indexOf(leftEntity)).addAttribute(new Attribute(attribute));
            }
        }

        this.originalErm.getTables().get(this.originalErm.getTables().indexOf(leftEntity)).addForeignKey(
                new FK(this.originalErm.getTables().get(this.originalErm.getTables().indexOf(rightEntity)).getName(),
                        (ArrayList) rightEntity.getPkKeys()));
        for (Attribute attribute : rightEntity.getPkKeys()) {
            this.originalErm.getTables().get(this.originalErm.getTables().indexOf(leftEntity)).addAttribute(
                    new Attribute(attribute.getName() + "_FK", attribute.isNotNull()));
        }

        this.originalErm.removeTable(this.originalErm.getTables().indexOf(rt));
    }

    private void ResloveNtoM() {

    }

    private boolean isRLBinary (Relationtype rl) {

        if (rl.getLinks().size() == 2) {
            return true;
        }
        return false;
    }

    private void relinkingBinaryRelations (Entitytype newTable, Entitytype deletedTable) {
        for (Relationtype relation : this.originalErm.getRelationtypes()) {
            for (EntityLink el : relation.getLinks()) {
                if (deletedTable.equals(el.getEnititytype())) {
                    el.changeEntityType(newTable);
                }
            }
        }
    }
}

