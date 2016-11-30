package de.htwg.msi.infe.ermcompile.XMLAdapter;

import de.htwg.msi.infe.ermcompile.model.Attribute.Attribute;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class AttributeXMLAdapter extends XmlAdapter<Attribute,Attribute> {
    public Attribute unmarshal(Attribute v) throws Exception {
        return null;
    }

    public Attribute marshal(Attribute v) throws Exception {
        return new Attribute(v.getName(),v.isNotNull());
    }
}
