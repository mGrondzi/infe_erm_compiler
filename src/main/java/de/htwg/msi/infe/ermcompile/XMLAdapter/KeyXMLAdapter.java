package de.htwg.msi.infe.ermcompile.XMLAdapter;

import de.htwg.msi.infe.ermcompile.model.Attribute.Key;


import javax.xml.bind.annotation.adapters.XmlAdapter;

public class KeyXMLAdapter extends XmlAdapter<String, Key>{

    public Key unmarshal(String v) throws Exception {
        return null;
    }

    public String marshal(Key v) throws Exception {
        return v.getName();
    }
}
