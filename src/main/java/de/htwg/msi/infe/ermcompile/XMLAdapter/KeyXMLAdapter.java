package de.htwg.msi.infe.ermcompile.XMLAdapter;

import de.htwg.msi.infe.ermcompile.model.Attribute.Attribute;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class KeyXMLAdapter extends XmlAdapter<String, Attribute>{

    public Attribute unmarshal(String v) throws Exception {
        return null;
    }

    public String marshal(Attribute v) throws Exception {
        return v.getName();
    }
}
