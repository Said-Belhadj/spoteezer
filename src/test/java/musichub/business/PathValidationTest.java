package musichub.business;


import org.junit.jupiter.api.Test;

import static musichub.business.PathValidation.isPathValid;
import static org.junit.jupiter.api.Assertions.*;

public class PathValidationTest {
    @Test
    void testIsPathValid() {
        assertTrue(isPathValid("Song/Side_To_Side.wav")); //the right path
        assertFalse(isPathValid("wrong_path/Side_To_Side.wav")); //wrong path
        assertFalse(isPathValid("Song/Side_To_Side.mp3")); //wrong extension
    }

    @Test
    void testPathValidationClass() {
        try {
            PathValidation pathValidationClass = new PathValidation();
        } catch (Error ex) {
            assertTrue(ex instanceof AssertionError);
            assertEquals("You just can't instantiate this class.", ex.getMessage());
        }
    }
}