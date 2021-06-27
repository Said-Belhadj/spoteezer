package musichub.util;


import org.junit.jupiter.api.Test;

import static musichub.util.PathValidation.isPathValid;
import static org.junit.jupiter.api.Assertions.*;

public class PathValidationTest {
    @Test
    void testPathValidationClass() {
        try {
            PathValidation pathValidationClass = new PathValidation();
        } catch (Error e) {
            assertTrue(e instanceof AssertionError);
            assertEquals("You just can't instantiate this class.", e.getMessage());
        }
    }

    @Test
    void testIsPathValid() {
        assertTrue(isPathValid("Song/Side_To_Side.wav")); //the right path
        assertFalse(isPathValid("wrong_path/Side_To_Side.wav")); //wrong path
        assertFalse(isPathValid("Song/Side_To_Side.mp3")); //wrong extension
    }
}
