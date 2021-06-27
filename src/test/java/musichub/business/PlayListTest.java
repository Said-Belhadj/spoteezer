package musichub.business;

import musichub.util.XMLHandler;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class PlayListTest {
    final String DIR = System.getProperty("user.dir");
    final String PLAYLISTS_FILE_PATH = DIR + "/files/playlists.xml";
    final XMLHandler xmlHandler = new XMLHandler();
    String title = "Side To Side";
    String id = "66d277ca-cbc4-471c-a07e-082363375bcc";
    ArrayList<UUID> elementUUIDs = null;

    @Test
    void testPlaylistClass() {
        new PlayList(title, id, elementUUIDs);
        new PlayList(title);

    }

    @Test
    void testPlaylistClassXML() {
        NodeList playlistNodes = xmlHandler.parseXMLFile(PLAYLISTS_FILE_PATH);
        Element playlistElement = (Element) playlistNodes.item(1);
        new PlayList(playlistElement);
    }

    @Test
    void testGetElements() {
        assertNull(new PlayList(title, id, elementUUIDs).getElements());
    }

    @Test
    void testGetTitle() {
        assertEquals(new PlayList(title).getTitle(), "Side To Side");
        assertNotEquals(new PlayList(title).getTitle(), "God Is A Woman");
    }

    @Test
    void testCreateXMLElement() {
        PlayList p = new PlayList(title);
        Document document = xmlHandler.createXMLDocument();
        Element root = document.createElement("playlists");
        p.createXMLElement(document, root);
    }
}
