package de.htwg.msi.infe.ermcompile.XMLAdapter;

import de.htwg.msi.infe.ermcompile.model.Attribute.Key;
import de.htwg.msi.infe.ermcompile.model.Attribute.PK;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.ArrayList;

public class KeyXMLAdapter extends XmlAdapter<ArrayList<String>, ArrayList<Key>>{


    public ArrayList<Key> unmarshal(ArrayList<String> v) throws Exception {
        return null;
    }

    public ArrayList<String> marshal(ArrayList<Key> v) throws Exception {
        ArrayList result = new ArrayList<Key>();
        for(Key k : v)
            result.add(k.getName());
        return result;
    }
}
