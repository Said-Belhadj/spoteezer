package musichub.business;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class AudioBook extends AudioElement {
    private Language language;
    private Category category;

    public AudioBook(String title, String artist, int lengthInSeconds, String uid, String content, String language, String category) {
        super(title, artist, lengthInSeconds, uid, content);
        this.setLanguage(language);
        this.setCategory(category);
    }

    public AudioBook(String title, String artist, int lengthInSeconds, String content, String language, String category) {
        super(title, artist, lengthInSeconds, content);
        this.setLanguage(language);
        this.setCategory(category);
    }

    public AudioBook(Element xmlElement) {
        super(xmlElement);
        this.setLanguage(xmlElement.getElementsByTagName("language").item(0).getTextContent());
        this.setCategory(xmlElement.getElementsByTagName("category").item(0).getTextContent());
    }

    public Language getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        switch (language.toLowerCase()) {
            default -> this.language = Language.ENGLISH;
            case "french" -> this.language = Language.FRENCH;
            case "german" -> this.language = Language.GERMAN;
            case "spanish" -> this.language = Language.SPANISH;
            case "italian" -> this.language = Language.ITALIAN;
        }
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        switch (category.toLowerCase()) {
            default -> this.category = Category.YOUTH;
            case "novel" -> this.category = Category.NOVEL;
            case "theater" -> this.category = Category.THEATER;
            case "documentary" -> this.category = Category.DOCUMENTARY;
            case "speech" -> this.category = Category.SPEECH;
        }
    }


    public String toString() {
        return super.toString() + ", Language = " + getLanguage() + ", Category = " + getCategory() + "\n";
    }


    public void createXMLElement(Document document, Element parentElement) {
        // audiobook element
        Element audioBook = document.createElement("audiobook");

        super.createXMLElement(document, audioBook);

        Element languageElement = document.createElement("language");
        languageElement.appendChild(document.createTextNode(language.getLanguage()));
        audioBook.appendChild(languageElement);

        Element categoryElement = document.createElement("category");
        categoryElement.appendChild(document.createTextNode(category.getCategory()));
        audioBook.appendChild(categoryElement);

        parentElement.appendChild(audioBook);
    }
}