package de.htwg.msi.infe.ermcompile.utils;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XmlSaxHandler extends DefaultHandler {

    private final String entityTypes = "entitytypes";
    private final String entityType = "entitytype";
    private final String relationTypes = "relationtypes";


    public XmlSaxHandler() {
    }

    boolean isAttribute = false;
    boolean inRelationtype = false;
    boolean inEntitytypes = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase(this.entityType)) {
            this.inEntitytypes = true;
            System.out.println("\nentitytype : " + attributes.getValue("name"));
        }
        if (qName.equalsIgnoreCase("attribute") && this.inEntitytypes) {
            this.isAttribute = true;
            System.out.print("attribute: " + localName + " isprimkey:" + attributes.getValue("isprimkey") + "| notnull: " + attributes.getValue("notnull"));
        }
        if (qName.equalsIgnoreCase(this.relationTypes)) {
            inEntitytypes = false;
            this.inRelationtype = true;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (this.isAttribute) {
            String name = new String(ch, start, length);
            System.out.print(" name: " + name + "\n");
            this.isAttribute = false;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equalsIgnoreCase(this.entityTypes)) {
            this.inEntitytypes = false;

        }
    }


}

