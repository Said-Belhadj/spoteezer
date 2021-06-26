package musichub.business;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Class offering a path validation method.
 *
 * @author Aimeric ADJUTOR
 * @version 1.0
 */

public final class PathValidation {

    /**
     * Method that just throws an AssertionError if the class is called
     *
     * @throws AssertionError you can't instantiate this class
     * @author Aimeric ADJUTOR
     */

    public PathValidation() {
        throw new AssertionError("You just can't instantiate this class.");
    }

    /**
     * Method that checks the validity of a given path
     *
     * @param inputPath the path given by the user
     * @return a boolean
     */

    public static boolean isPathValid(String inputPath) {
        Path path = Paths.get(inputPath);
        return Files.exists(path);
    }

}