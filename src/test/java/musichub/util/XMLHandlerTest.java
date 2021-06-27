package musichub.util;


import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class XMLHandlerTest {

    final String DIR = System.getProperty("user.dir");
    final String JUNIT_FILE_PATH = DIR + "/files/JUnit.xml";

    @Test
    void testCreateXML() {
        XMLHandler xmlHandler = new XMLHandler(); //XML class
        Document document = xmlHandler.createXMLDocument(); //XMLDocument method
        xmlHandler.createXMLFile(document, JUNIT_FILE_PATH); //XMLFile method
    }

    @Test
    void testParseXMLFile() {
        final String PARSE_DIR = System.getProperty("user.dir");
        final String PARSE_FILE_PATH = PARSE_DIR + "/files/parse_JUnit.xml";
        XMLHandler xmlHandler = new XMLHandler();
        //wrong content of file resulting in an exception, will print it during test but it's normal
        NodeList junitNodes = xmlHandler.parseXMLFile(JUNIT_FILE_PATH);
        //right content of file
        NodeList parseNodes = xmlHandler.parseXMLFile(PARSE_FILE_PATH);
    }
}