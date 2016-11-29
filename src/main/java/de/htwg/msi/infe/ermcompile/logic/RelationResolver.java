package de.htwg.msi.infe.ermcompile.logic;

import de.htwg.msi.infe.ermcompile.model.ERM.Erm;
import de.htwg.msi.infe.ermcompile.model.EntityLink;
import de.htwg.msi.infe.ermcompile.model.Table.Relationtype;
import de.htwg.msi.infe.ermcompile.model.Table.Table;

import java.util.ArrayList;

/**
 * Created by phili on 29.11.2016.
 */
public class RelationResolver {

    private Erm erm;
    private ArrayList<Table> rtables = new ArrayList<Table>();

    public RelationResolver(Erm erm){
        this.erm = erm;
        this.rtables =this.erm.getTables();

        this.rtables = this.extractRelationtypes(rtables);

        for(Table table: this.rtables){
            System.out.println(table.getName());
        }

    }

    public ArrayList<Table> extractRelationtypes(ArrayList<Table> list){
        ArrayList<Table> rl = new ArrayList<Table>();
        for(Table table: list ){
            if (table instanceof Relationtype) {
               rl.add(table);
            }
        }
        return rl;
    }
    /*


    private void getLinksOfRelationtype(Relationtype rl){
        ArrayList<EntityLink> link = rl.getLinks();

    }
     */





}
