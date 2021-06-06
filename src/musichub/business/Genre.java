package musichub.business;

public enum Genre {
    JAZZ("jazz"), CLASSIC("classic"), HIPHOP("hiphop"), ROCK("rock"), POP("pop"), RAP("rap");
    private final String genre;

    Genre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }
}