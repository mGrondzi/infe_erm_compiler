package de.htwg.msi.infe.ermcompile.utils;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import java.io.File;
import java.rmi.server.ExportException;

/**
 * Reads xml as dom
 */
public class XmlReader {

    private DocumentBuilderFactory factory;

    public XmlReader(boolean validation, boolean ignoreElementContentWhiteSpace) {
        this.factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(validation);
        factory.setIgnoringElementContentWhitespace(ignoreElementContentWhiteSpace);
    }

    /**
     * reads and xml file and returns a Document element
     * <p>
     * path needs to be relative to project file e.g src/main/resources/foo.xml
     * <p>
     *
     * @return null if the document was not found or could not be read
     */
    public Document readXmlFile(String filepath) {

        try {
            DocumentBuilder builder = this.factory.newDocumentBuilder();
            return builder.parse(new File(filepath));
        } catch (Exception e) {
            System.out.print("XmlReader | Shit happened: " + e);
        }
        return null;
    }
}
