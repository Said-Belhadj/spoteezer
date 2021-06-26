package musichub.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.sql.Timestamp;

/**
 * Class offering a log writing method.
 *
 * @author Aimeric ADJUTOR
 * @version 1.0
 */

public final class LogHandler {

    /**
     * Method that just throws an AssertionError if the class is called
     *
     * @throws AssertionError you can't instantiate this class
     * @author Aimeric ADJUTOR
     */

    public LogHandler() {
        throw new AssertionError("You just can't instantiate this class.");
    }

    public static void write(String msg, String type) throws IOException {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        //Create the log using the given message
        String logMsg = "\n[" + timestamp + "] " + type + ": " + msg;

        // Define the file name of the file
        Path fileName = Path.of("log/spoteezer.log");

        // Write into the file
        Files.writeString(fileName, logMsg, StandardOpenOption.APPEND);


    }

    public static void read() throws IOException {
        Path fileName = Path.of("log/spoteezer.log");

        // Read the content of the file
        String file_content = Files.readString(fileName);

        // Print the content inside the file
        System.out.println("\n" + file_content + "\n");

    }

}