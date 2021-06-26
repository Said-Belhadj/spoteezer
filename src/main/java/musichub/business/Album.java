package musichub.business;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Album {
    private final String title;
    private final int lengthInSeconds;
    private final UUID uuid;
    private String artist;
    private Date date;
    private ArrayList<UUID> songsUIDs;

    public Album(String title, String artist, int lengthInSeconds, String id, String date, ArrayList<UUID> songsUIDs) {
        this.title = title;
        this.artist = artist;
        this.lengthInSeconds = lengthInSeconds;
        this.uuid = UUID.fromString(id);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            this.date = sdf.parse(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        this.songsUIDs = songsUIDs;
    }

    public Album(String title, String artist, int lengthInSeconds, String date) {
        this.title = title;
        this.artist = artist;
        this.lengthInSeconds = lengthInSeconds;
        this.uuid = UUID.randomUUID();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            this.date = sdf.parse(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        this.songsUIDs = new ArrayList<>();
    }

    public Album(Element xmlElement) throws Exception {
        {
            this.title = xmlElement.getElementsByTagName("title").item(0).getTextContent();
            this.lengthInSeconds = Integer.parseInt(xmlElement.getElementsByTagName("lengthInSeconds").item(0).getTextContent());
            String uuid = null;
            try {
                uuid = xmlElement.getElementsByTagName("UUID").item(0).getTextContent();
            } catch (Exception ex) {
                System.out.println("Empty album UUID, will create a new one");
            }
            if ((uuid == null) || (uuid.isEmpty()))
                this.uuid = UUID.randomUUID();
            else this.uuid = UUID.fromString(uuid);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            this.date = sdf.parse(xmlElement.getElementsByTagName("date").item(0).getTextContent());
            //parse list of songs:
            Node songsElement = xmlElement.getElementsByTagName("songs").item(0);
            NodeList songUUIDNodes = songsElement.getChildNodes();
            if (songUUIDNodes == null) return;

            this.songsUIDs = new ArrayList<>();

            for (int i = 0; i < songUUIDNodes.getLength(); i++) {
                if (songUUIDNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element songElement = (Element) songUUIDNodes.item(i);
                    if (songElement.getNodeName().equals("UUID")) {
                        try {
                            this.addSong(UUID.fromString(songElement.getTextContent()));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        }
    }


    public void addSong(UUID song) {
        songsUIDs.add(song);
    }


    public List<UUID> getSongs() {
        return songsUIDs;
    }

    public ArrayList<UUID> getSongsRandomly() {
        ArrayList<UUID> shuffledSongs = songsUIDs;
        Collections.shuffle(shuffledSongs);
        return shuffledSongs;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    public void createXMLElement(Document document, Element parentElement) {
        Element albumElement = document.createElement("album");
        parentElement.appendChild(albumElement);

        Element nameElement = document.createElement("title");
        nameElement.appendChild(document.createTextNode(title));
        albumElement.appendChild(nameElement);

        Element artistElement = document.createElement("artist");
        artistElement.appendChild(document.createTextNode(artist));
        albumElement.appendChild(artistElement);

        Element lengthElement = document.createElement("lengthInSeconds");
        lengthElement.appendChild(document.createTextNode(Integer.valueOf(lengthInSeconds).toString()));
        albumElement.appendChild(lengthElement);

        Element UUIDElement = document.createElement("UUID");
        UUIDElement.appendChild(document.createTextNode(uuid.toString()));
        albumElement.appendChild(UUIDElement);

        Element dateElement = document.createElement("date");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        dateElement.appendChild(document.createTextNode(sdf.format(date)));
        albumElement.appendChild(dateElement);

        Element songsElement = document.createElement("songs");
        for (UUID currentUUID : this.songsUIDs) {
            Element songUUIDElement = document.createElement("UUID");
            songUUIDElement.appendChild(document.createTextNode(currentUUID.toString()));
            songsElement.appendChild(songUUIDElement);
        }
        albumElement.appendChild(songsElement);

    }
}