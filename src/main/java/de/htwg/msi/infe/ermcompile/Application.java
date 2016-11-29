package de.htwg.msi.infe.ermcompile;

import de.htwg.msi.infe.ermcompile.model.Attribute.Attribute;
import de.htwg.msi.infe.ermcompile.model.Attribute.PK;
import de.htwg.msi.infe.ermcompile.model.ERM.Erm;
import de.htwg.msi.infe.ermcompile.model.Table.Entitytype;
import de.htwg.msi.infe.ermcompile.utils.XmlSaxHandler;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;

public class Application {

    public static void main(String[] args) {

        try {
            final File inputFile = new File("src/main/resources/relational_schema_example_WS1617.xml");
            final SAXParserFactory factory = SAXParserFactory.newInstance();
            final SAXParser saxParser = factory.newSAXParser();
            final XmlSaxHandler handler = new XmlSaxHandler();
            saxParser.parse(inputFile, handler);
            Erm erm = handler.getErm();
            TestXMLCreator(erm);

            /*System.out.println("\nnumber of Entitys: " + handler.getEntityTypeXMLList().size());
            System.out.println("1st. Entity: " + handler.getEntityTypeXMLList().get(0).getName());
            System.out.println("2nd. Entity: " + handler.getEntityTypeXMLList().get(1).getName());
            System.out.println("3rd. Entity: " + handler.getEntityTypeXMLList().get(2).getName());

            System.out.println("\nnumber of Relations: " + handler.getRelationtypeXMLList().size());
            System.out.println("1st. Relation: " + handler.getRelationtypeXMLList().get(0).getName());
            System.out.println("2nd. Relation: " + handler.getRelationtypeXMLList().get(1).getName());

*/

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void TestXMLCreator(Erm erm) {
//        Erm erm = new Erm();
//        ArrayList<Attribute> attributeArrayList = new ArrayList<Attribute>();
//        attributeArrayList.add(new PK("sad"));
//        attributeArrayList.add(new Attribute("sawasd", true));
//        erm.addTable(new Entitytype("Test", attributeArrayList));
//        erm.addTable(new Entitytype("Test2", attributeArrayList));


        File file = new File("C:\\Users\\manue\\Documents\\file.xml");
        JAXBContext jaxbContext = null;
        try {

            jaxbContext = JAXBContext.newInstance(Erm.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(erm, file);
            jaxbMarshaller.marshal(erm, System.out);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
