package musichub.business;

public class NoAlbumFoundException extends Exception {

    public NoAlbumFoundException(String msg) {
        super(msg);
    }
}