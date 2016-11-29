package de.htwg.msi.infe.ermcompile.utils;

import de.htwg.msi.infe.ermcompile.model.Attribute.Attribute;
import de.htwg.msi.infe.ermcompile.model.Attribute.PK;
import de.htwg.msi.infe.ermcompile.model.Cardinality;
import de.htwg.msi.infe.ermcompile.model.ERM.Erm;
import de.htwg.msi.infe.ermcompile.model.EntityLink;
import de.htwg.msi.infe.ermcompile.model.Table.Entitytype;
import de.htwg.msi.infe.ermcompile.model.Table.Relationtype;
import de.htwg.msi.infe.ermcompile.model.Table.Table;

import lombok.Getter;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Locator;

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
    private boolean inRelationtypes = false;
    private boolean inEntitytypes = false;

    //representErm
    @Getter
    private Erm erm;

    public XmlSaxHandler() {
        this.erm = new Erm();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equalsIgnoreCase(this.entityTypes)) {
            this.inEntitytypes = true;
        }

        if (qName.equalsIgnoreCase(this.entityType)) {
            this.checkValidTag(attributes.getValue(this.name), this.name, this.locator);
            //Create Entity
            this.erm.addTable(new Entitytype(attributes.getValue(this.name)));
        }
        if (qName.equalsIgnoreCase(this.attribute) && (this.inEntitytypes || this.inRelationtypes)) {

            this.checkValidTag(attributes.getValue(this.isprimkey), this.isprimkey, locator);
            this.checkValidTag(attributes.getValue(this.notnull), this.notnull, locator);
            this.checkValidTag(attributes.getValue(this.name), this.name, this.locator);

            if (Boolean.parseBoolean(attributes.getValue(this.isprimkey))) {
                this.erm.getTables().get(this.erm.getTables().size() - 1).addAttribute(new PK(attributes.getValue(this.name)));
            } else {
                this.erm.getTables().get(this.erm.getTables().size() - 1).addAttribute(new Attribute(attributes.getValue(this.name),
                        Boolean.parseBoolean(attributes.getValue(this.notnull))));
            }
        }

        if (qName.equalsIgnoreCase(this.relationTypes)) {
            this.inRelationtypes = true;
        }

        if (qName.equalsIgnoreCase(this.relationType)) {
            this.checkValidTag(attributes.getValue(this.name), name, locator);
            erm.addTable(new Relationtype(attributes.getValue(this.name)));
        }

        if (qName.equalsIgnoreCase(this.entityLink) && this.inRelationtypes) {
            this.checkValidTag(attributes.getValue(this.min), this.min, this.locator);
            this.checkValidTag(attributes.getValue(this.max), this.max, this.locator);

            Relationtype relationtype = (Relationtype) this.erm.getTables().get(this.erm.getTables().size() - 1);
            for (Table t : this.erm.getTables()) {
                if (t.getName().equals(attributes.getValue(this.name))) {
                    System.out.println(t.getName());
                    relationtype.addEntityLink(new EntityLink((Entitytype) t, new Cardinality(attributes.getValue(this.min),
                            attributes.getValue(this.max)), attributes.getValue(this.funct)));
                }
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
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
