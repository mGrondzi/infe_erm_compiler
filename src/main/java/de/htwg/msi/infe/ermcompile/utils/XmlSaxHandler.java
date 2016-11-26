package de.htwg.msi.infe.ermcompile.utils;

import de.htwg.msi.infe.ermcompile.model.XML.AttributesXML;
import de.htwg.msi.infe.ermcompile.model.XML.EntityTypeXML;
import de.htwg.msi.infe.ermcompile.model.XML.LinksXML;
import de.htwg.msi.infe.ermcompile.model.XML.RelationtypeXML;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Locator;

import java.util.ArrayList;
import java.util.List;

public class XmlSaxHandler extends DefaultHandler {

    //locator to get current position
    private Locator locator;

    //xml specifig tags
    private final String entityTypes = "entitytypes";
    private final String entityType = "entitytype";
    private final String relationTypes = "relationtypes";
    private final String relationType = "relationtype";
    private final String entityLink = "entitylink";
    private final String attribute = "attribute";
    private final String name = "name";
    private final String isprimkey = "isprimkey";
    private final String notnull = "notnull";
    private final String min = "min";
    private final String max = "max";
    private final String funct = "functionality";

    //stack to check in which DOM we are
    private boolean inAttribute = false;
    private boolean inRelationtypes = false;
    private boolean inEntitytypes = false;
    private boolean inLink = false;

    //list to return which represent the xml document
    private List<EntityTypeXML> entityTypeXMLList = null;
    private List<RelationtypeXML> relationtypeXMLList = null;

    //simple representation of XML document
    private EntityTypeXML tempEntity = null;
    private AttributesXML tempAttribute = null;
    private RelationtypeXML tempRelation = null;
    private LinksXML tempLink = null;

    public XmlSaxHandler() {
        this.entityTypeXMLList = new ArrayList<EntityTypeXML>();
        this.relationtypeXMLList = new ArrayList<RelationtypeXML>();
    }

    public List<EntityTypeXML> getEntityTypeXMLList() {
        return this.entityTypeXMLList;
    }

    public List<RelationtypeXML> getRelationtypeXMLList() {
        return this.relationtypeXMLList;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equalsIgnoreCase(this.entityTypes)) {
            this.inEntitytypes = true;
        }

        if (qName.equalsIgnoreCase(this.entityType)) {
            this.tempEntity = new EntityTypeXML();
            this.checkValidTag(attributes.getValue(this.name), this.name, this.locator);
            System.out.println();
            this.tempEntity.setName(attributes.getValue(this.name));
            System.out.println("\nentitytype : " + attributes.getValue(this.name));
        }
        if (qName.equalsIgnoreCase(this.attribute) && (this.inEntitytypes || this.inRelationtypes)) {
            this.inAttribute = true;
            this.tempAttribute = new AttributesXML();
            this.checkValidTag(attributes.getValue(this.isprimkey), this.isprimkey, locator);
            this.checkValidTag(attributes.getValue(this.notnull), this.notnull, locator);
            this.tempAttribute.setIsprimkey(Boolean.parseBoolean(attributes.getValue(this.isprimkey)));
            this.tempAttribute.setNotnull(Boolean.parseBoolean(attributes.getValue(this.notnull)));
            System.out.print("attribute: isprimkey:" + attributes.getValue(this.isprimkey) + "| notnull: " + attributes.getValue(this.notnull));
        }
        if (qName.equalsIgnoreCase(this.relationTypes)) {
            this.inRelationtypes = true;
        }

        if (qName.equalsIgnoreCase(this.relationType)) {
            this.tempRelation = new RelationtypeXML();
            this.checkValidTag(attributes.getValue(this.name), name, locator);
            this.tempRelation.setName(attributes.getValue(this.name));
            System.out.println("\nrelationtype : " + attributes.getValue(this.name));
        }

        if (qName.equalsIgnoreCase(this.entityLink)) {
            this.inLink = true;
            this.tempLink = new LinksXML();
            this.checkValidTag(attributes.getValue(this.min), this.min, this.locator);
            this.checkValidTag(attributes.getValue(this.max), this.max, this.locator);
            this.tempLink.setMin(attributes.getValue(this.min));
            this.tempLink.setMax(attributes.getValue(this.max));
            this.tempLink.setFunctionality(attributes.getValue(this.funct));
            System.out.print("link: min:" + attributes.getValue(this.min) + "| max: " + attributes.getValue(this.max)
                    + "| functionality :" + attributes.getValue(this.funct));
        }
    }


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (this.inAttribute) {
            String name = new String(ch, start, length);
            System.out.print(" name: " + name + "\n");
            this.checkValidTag(name, this.name, this.locator);
            this.tempAttribute.setName(name);
            this.inAttribute = false;
        }
        if (this.inLink) {
            String name = new String(ch, start, length);
            System.out.print(" name: " + name + "\n");
            this.checkValidTag(name, this.name, this.locator);
            this.tempLink.setName(name);
            this.inLink = false;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase(this.attribute) && this.inEntitytypes) {
            this.tempEntity.getAttributesXMLList().add(this.tempAttribute);
        }
        if (qName.equalsIgnoreCase(this.attribute) && this.inRelationtypes) {
            this.tempRelation.getAttributesXMLList().add(this.tempAttribute);
        }
        if (qName.equalsIgnoreCase(this.entityLink) && this.inRelationtypes) {
            this.tempRelation.getEntityLinksXMLList().add(this.tempLink);
        }
        if (qName.equalsIgnoreCase(this.entityType)) {
            this.entityTypeXMLList.add(this.tempEntity);
        }
        if (qName.equalsIgnoreCase(this.relationType)) {
            this.relationtypeXMLList.add(this.tempRelation);
        }
        if (qName.equalsIgnoreCase(this.entityTypes)) {
            this.inEntitytypes = false;
        }
        if (qName.equalsIgnoreCase(this.entityTypes)) {
            this.inRelationtypes = false;
        }
    }

    @Override
    public void setDocumentLocator(Locator locator) {
        this.locator = locator;
    }


    private void checkValidTag(String s, String tagType, Locator locator) throws SAXException {

        if (tagType.equals(this.name)) {
            if (s.isEmpty() || s.trim().isEmpty()) {
                throw new SAXException("XML Tag Error:\n '"
                        + tagType + "' or value of Element cant be empty in line: " + locator.getLineNumber());
            }
        }
        if (tagType.equals(this.notnull) || tagType.equals(this.isprimkey)) {
            if (!s.equals("true") && !s.equals("false")) {
                throw new SAXException("XML Tag Error:\n '"
                        + tagType + "' must be either 'true' or 'false' but is '" + s + "' in line: " + locator.getLineNumber());
            }
        }

        if (tagType.equals(this.min) || tagType.equals(this.max)) {
            //TODO check min max values in tags
        }


    }
}

