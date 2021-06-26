package musichub.business;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public abstract class AudioElement {
    protected String title;
    protected String artist;
    protected int lengthInSeconds;
    protected UUID uuid;
    protected String content;

    public AudioElement(String title, String artist, int lengthInSeconds, String id, String content) {
        this.title = title;
        this.artist = artist;
        this.lengthInSeconds = lengthInSeconds;
        this.uuid = UUID.fromString(id);
        this.content = content;
    }

    public AudioElement(String title, String artist, int lengthInSeconds, String content) {
        this.title = title;
        this.artist = artist;
        this.lengthInSeconds = lengthInSeconds;
        this.content = content;
        this.uuid = UUID.randomUUID();
    }

    public AudioElement(Element xmlElement) {
        {
            title = xmlElement.getElementsByTagName("title").item(0).getTextContent();
            artist = xmlElement.getElementsByTagName("artist").item(0).getTextContent();
            lengthInSeconds = Integer.parseInt(xmlElement.getElementsByTagName("length").item(0).getTextContent());
            content = xmlElement.getElementsByTagName("content").item(0).getTextContent();
            String uuid = null;
            try {
                uuid = xmlElement.getElementsByTagName("UUID").item(0).getTextContent();
            } catch (Exception ex) {
                System.out.println("Empty element UUID, will create a new one");
            }
            if ((uuid == null) || (uuid.isEmpty()))
                this.uuid = UUID.randomUUID();
            else this.uuid = UUID.fromString(uuid);
        }
    }

    public UUID getUUID() {
        return this.uuid;
    }

    public String getArtist() {
        return this.artist;
    }

    public String getTitle() {
        return this.title;
    }

    public String toString() {
        return "Title = " + this.title + ", Artist = " + this.artist + ", Length = " + this.lengthInSeconds + ", Content = " + this.content;
    }

    public void createXMLElement(Document document, Element parentElement) {
        Element nameElement = document.createElement("title");
        nameElement.appendChild(document.createTextNode(title));
        parentElement.appendChild(nameElement);

        Element artistElement = document.createElement("artist");
        artistElement.appendChild(document.createTextNode(artist));
        parentElement.appendChild(artistElement);

        Element lengthElement = document.createElement("length");
        lengthElement.appendChild(document.createTextNode(Integer.valueOf(lengthInSeconds).toString()));
        parentElement.appendChild(lengthElement);

        Element UUIDElement = document.createElement("UUID");
        UUIDElement.appendChild(document.createTextNode(uuid.toString()));
        parentElement.appendChild(UUIDElement);

        Element contentElement = document.createElement("content");
        contentElement.appendChild(document.createTextNode(content));
        parentElement.appendChild(contentElement);

    }
    
    public void manageAudioElement() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
    	
    	Scanner scanner = new Scanner(System.in); 
    	
    	File file = new File(this.content);
    	AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
    	Clip clip = AudioSystem.getClip();
    	clip.open(audioStream);
    	
    	String action = "";
    			
    	while(!action.equals("Q")) {
    		System.out.println("P = Play \b S = Stop \b R = Reset \b Q = Quit");
    		System.out.println("Enter your choice");
    		action = scanner.next();
        	action = action.toUpperCase();
        	
        	switch(action) {
        		case "S" : clip.stop();
        		break;
        		case "P" : clip.start();
        		break;
        		case "R" : clip.setMicrosecondPosition(0);
        		break;
        		case "Q" : clip.stop();
        		break;
        		default : System.out.println("try again");
        	}
        	System.out.println("You stoped the Audio element");
    	}
    	
    	clip.close();
    
    }

}