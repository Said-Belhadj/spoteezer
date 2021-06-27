package musichub.business;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LanguageTest {
    @Test
    void testGetter() {

        String t1 = "t1";
        String a1 = "a1";
        int l1 = 1;
        String c1 = a1+" - "+t1;
        String lan1 = "FRENCH";
        String cat1 = "novel";
        AudioBook s1 = new AudioBook(t1, a1, l1, c1, lan1,  cat1);
        assertEquals(s1.getLanguage(), Language.FRENCH);
        s1.setLanguage("french");
        assertEquals(s1.getLanguage(), Language.FRENCH);
    }

}
