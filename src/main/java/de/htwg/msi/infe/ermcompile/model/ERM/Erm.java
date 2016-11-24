package de.htwg.msi.infe.ermcompile.model.ERM;

import de.htwg.msi.infe.ermcompile.model.Table.Table;
import lombok.Getter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/**
 * Created by manue on 23.11.2016.
 */
@XmlRootElement
public class Erm {
    @Getter
    private ArrayList<Table> tables = new ArrayList<Table>();

    public void addTable(Table table){
        getTables().add(table);}

    public void removeTable(int pos){
        getTables().remove(pos);}

    @XmlElement(name = "table")
    public ArrayList<Table> getTables() {
        return tables;
    }
}
