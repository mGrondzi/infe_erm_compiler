package de.htwg.msi.infe.ermcompile.model.ERM;

import de.htwg.msi.infe.ermcompile.XMLAdapter.KeyXMLAdapter;
import de.htwg.msi.infe.ermcompile.model.Table.Entitytype;
import de.htwg.msi.infe.ermcompile.model.Table.Relationtype;
import de.htwg.msi.infe.ermcompile.model.Table.Table;
import lombok.Getter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
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

}
