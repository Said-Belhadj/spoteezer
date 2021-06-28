package musichub.business;

public enum Category {
    YOUTH("youth"), NOVEL("novel"), THEATER("theater"), DOCUMENTARY("documentary"), SPEECH("speech");
    private final String category;

    Category(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}