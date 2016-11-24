package de.htwg.msi.infe.ermcompile;

import de.htwg.msi.infe.ermcompile.model.Attribute.Attribute;
import de.htwg.msi.infe.ermcompile.model.Attribute.PK;
import de.htwg.msi.infe.ermcompile.model.ERM.Erm;
import de.htwg.msi.infe.ermcompile.model.Table.Entitytype;
import de.htwg.msi.infe.ermcompile.model.Table.Table;
import de.htwg.msi.infe.ermcompile.utils.XmlReader;
import org.w3c.dom.Document;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.concurrent.SynchronousQueue;

public class Application {

    public static void main(String[] args) {

        //TestXMLCreator();

        XmlReader reader = new XmlReader(false, true);

        try {
            Document doc = reader.readXmlFile("src/main/resources/relational_schema_example_WS1617.xml");
            doc.normalize(); //normalizing doc first so we can traverse this easy
            System.out.print(doc.getDocumentElement().getNodeName());
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public static void TestXMLCreator(){
        Erm erm = new Erm();
        ArrayList<Attribute> attributeArrayList = new ArrayList<Attribute>();
        attributeArrayList.add(new PK("sad"));
        attributeArrayList.add(new Attribute("sawasd",true));
        erm.addTable(new Entitytype("Test",attributeArrayList));
        erm.addTable(new Entitytype("Test2",attributeArrayList));


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
