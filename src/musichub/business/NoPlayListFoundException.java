package musichub.business;

public class NoPlayListFoundException extends Exception {

    public NoPlayListFoundException(String msg) {
        super(msg);
    }
}