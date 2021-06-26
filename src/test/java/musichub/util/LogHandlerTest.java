package musichub.util;


import org.junit.jupiter.api.Test;

import java.io.IOException;

import static musichub.util.LogHandler.read;
import static musichub.util.LogHandler.write;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogHandlerTest {
    @Test
    void testWrite() {
        try {
            write("JUnit test", "INFO");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testRead() {
        try {
            read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testLogHandlerClass() {
        try {
            LogHandler logHandlerClass = new LogHandler();
        } catch (Error ex) {
            assertTrue(ex instanceof AssertionError);
            assertEquals("You just can't instantiate this class.", ex.getMessage());
        }
    }
}
