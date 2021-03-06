package de.htwg.msi.infe.ermcompile.model.ERM;

import de.htwg.msi.infe.ermcompile.model.Table.Relationtype;
import de.htwg.msi.infe.ermcompile.model.Table.Table;
import lombok.Getter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement
public class Erm {
    @Getter
    @XmlElementWrapper
    @XmlElement(name="table")   // @XmlJavaTypeAdapter(KeyXMLAdapter.class)
    private ArrayList<Table> tables = new ArrayList<Table>();

    public void addTable(Table table){
        getTables().add(table);}

    public void removeTable(int pos){
        getTables().remove(pos);}

    public void removeTable(Table table){
        if(this.tables.contains(table))
            this.tables.remove(table);
    }

    public ArrayList<Relationtype> getRelationtypes(){
        ArrayList<Relationtype> rts = new ArrayList<Relationtype>();

        for(Table table: this.getTables()){
            if(table instanceof Relationtype){
                rts.add((Relationtype) table);
            }
        }

        return rts;
    }

}
