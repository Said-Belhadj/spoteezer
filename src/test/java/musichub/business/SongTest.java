package musichub.business;

import musichub.util.XMLHandler;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
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

    final String DIR = System.getProperty("user.dir");
    final String ELEMENTS_FILE_PATH = DIR + "/files/elements.xml";
    final XMLHandler xmlHandler = new XMLHandler();


    @Test
    void testSongClass() {
        new Song(title, artist, length, uid, content, genre);
        new Song(title, artist, length, content, genre);

    }

    @Test
    void testSongClassXML() {
        NodeList audioelementsNodes = xmlHandler.parseXMLFile(ELEMENTS_FILE_PATH);
        Element audioElement = (Element) audioelementsNodes.item(1);
        new Song(audioElement);
    }

    @Test
    void testGetGenre() {
        assertEquals(new Song(title, artist, length, content, genre)
                        .getGenre(),
                "rock");
        assertNotEquals(new Song(title, artist, length, content, genre)
                        .getGenre(),
                "pop");
    }

    @Test
    void testSetGenre() {
        new Song(title, artist, length, content, "classic");
        new Song(title, artist, length, content, "hiphop");
        new Song(title, artist, length, content, "rock");
        new Song(title, artist, length, content, "pop");
        new Song(title, artist, length, content, "rap");

        Song s = new Song(title, artist, length, content, "cgfdhdfhj");
        assertEquals(s.getGenre(), "jazz");
    }

    @Test
    void testToString() {
        assertEquals(
                new Song(title, artist, length, content, genre)
                        .toString(),
                "Title = Side To Side, Artist = Ariana Grande, Length = 186, Content = Song/Side_To_Side.wav, Genre = rock\n"
        );
        assertNotEquals(
                new Song(title, artist, length, content, genre)
                        .toString(),
                "Title = God is a woman, Artist = Ariana Grande, Length = 186, Content = Song/Side_To_Side.wav, Genre = rock\n"
        );
    }

    @Test
    void testCreateXMLElement() {
        Song s = new Song(title, artist, length, content, genre);
        Document document = xmlHandler.createXMLDocument();
        Element root = document.createElement("elements");
        s.createXMLElement(document, root);

    }
}
