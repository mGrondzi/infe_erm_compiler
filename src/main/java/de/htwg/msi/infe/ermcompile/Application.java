package de.htwg.msi.infe.ermcompile;

import de.htwg.msi.infe.ermcompile.utils.XmlReader;
import org.w3c.dom.Document;

public class Application {

    public static void main(String[] args) {

        XmlReader reader = new XmlReader(false, true);

        try {
            Document doc = reader.readXmlFile("src/main/resources/relational_schema_example_WS1617.xml");
            doc.normalize(); //normalizing doc first so we can traverse this easy
            System.out.print(doc.getDocumentElement().getNodeName());
        } catch (Exception e) {
            System.out.print(e);
        }
    }
}
