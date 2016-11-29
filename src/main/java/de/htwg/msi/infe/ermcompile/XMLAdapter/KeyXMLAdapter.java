package de.htwg.msi.infe.ermcompile.XMLAdapter;

import de.htwg.msi.infe.ermcompile.model.Attribute.Key;
import de.htwg.msi.infe.ermcompile.model.Attribute.PK;
import de.htwg.msi.infe.ermcompile.model.Table.Entitytype;
import de.htwg.msi.infe.ermcompile.model.Table.Relationtype;
import de.htwg.msi.infe.ermcompile.model.Table.Table;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.ArrayList;

public class KeyXMLAdapter extends XmlAdapter<Table, Table>{


    public ArrayList<Key> unmarshal(ArrayList<String> v) throws Exception {
        return null;
    }

    public ArrayList<String> marshal(ArrayList<Key> v) throws Exception {
        ArrayList result = new ArrayList<Key>();
        for(Key k : v)
            result.add(k.getName());
        return result;
    }

    public Table unmarshal(Table v) throws Exception {
        return null;
    }

    public Table marshal(Table v) throws Exception {
        if(v instanceof Relationtype)
            return (Relationtype)v;
        return  (Entitytype)v;
    }
}
