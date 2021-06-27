package musichub.util;


import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class XMLHandlerTest {

    final String DIR = System.getProperty("user.dir");
    final String JUNIT_FILE_PATH = DIR + "/files/JUnit.xml";

/*    @Test
    void testXMLHandlerClass() {
            XMLHandler xmlHandler = new XMLHandler();
    }*/

    @Test
    void testCreateXML() {
        XMLHandler xmlHandler = new XMLHandler(); //XML class
        Document document = xmlHandler.createXMLDocument(); //XMLDocument method
        xmlHandler.createXMLFile(document, JUNIT_FILE_PATH); //XMLFile method
    }

    @Test
    void testParseXMLFile() {
        XMLHandler xmlHandler = new XMLHandler();
        NodeList albumNodes = xmlHandler.parseXMLFile(JUNIT_FILE_PATH);
    }
}
