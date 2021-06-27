package musichub.business;

import musichub.util.XMLHandler;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SongTest {
    String title = "Side To Side";
    String artist = "Ariana Grande";
    int length = 186;
    String uid = "66d277ca-cbc4-471c-a07e-082363375bcc";
    String content = "Song/Side_To_Side.wav";
    String genre = "rock";

    @Test
    void testSongClasses() {
        Song song_uuid = new Song(title, artist, length, uid, content, genre);
        Song song = new Song(title, artist, length, content, genre);

    }

    @Test
    void testSongXml() {
        final String DIR = System.getProperty("user.dir");
        final String ELEMENTS_FILE_PATH = DIR + "/files/elements.xml";
        final XMLHandler xmlHandler = new XMLHandler();
        NodeList audioelementsNodes = xmlHandler.parseXMLFile(ELEMENTS_FILE_PATH);
        Element audioElement = (Element) audioelementsNodes.item(1);
        Song song_xml = new Song(audioElement);
    }

    @Test
    void testGetGenre() {
        assertEquals(new Song(title, artist, length, content, genre).getGenre(), "rock");
        assertNotEquals(new Song(title, artist, length, content, genre).getGenre(), "pop");
    }

    @Test
    void testSetGenre() {
        // Not Implemented Yet
    }
}
