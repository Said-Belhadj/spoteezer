package musichub.business;

import musichub.util.XMLHandler;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.util.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

class SortByDate implements Comparator<Album> {
    public int compare(Album a1, Album a2) {
        return a1.getDate().compareTo(a2.getDate());
    }
}

class SortByGenre implements Comparator<Song> {
    public int compare(Song s1, Song s2) {
        return s1.getGenre().compareTo(s2.getGenre());
    }
}

class SortByAuthor implements Comparator<AudioElement> {
    public int compare(AudioElement e1, AudioElement e2) {
        return e1.getArtist().compareTo(e2.getArtist());
    }
}

public class MusicHub {
    public static final String DIR = System.getProperty("user.dir");
    public static final String ALBUMS_FILE_PATH = DIR + "/files/albums.xml";
    public static final String PLAYLISTS_FILE_PATH = DIR + "/files/playlists.xml";
    public static final String ELEMENTS_FILE_PATH = DIR + "/files/elements.xml";
    private final List<Album> albums;
    private final List<PlayList> playlists;
    private final List<AudioElement> elements;
    private final XMLHandler xmlHandler = new XMLHandler();

    public MusicHub() {
        albums = new LinkedList<>();
        playlists = new LinkedList<>();
        elements = new LinkedList<>();
        this.loadElements();
        this.loadAlbums();
        this.loadPlaylists();
    }

    public void addElement(AudioElement element) {
        elements.add(element);
    }

    public void addAlbum(Album album) {
        albums.add(album);
    }

    public void addPlaylist(PlayList playlist) {
        playlists.add(playlist);
    }

    public void deletePlayList(String playListTitle) throws NoPlayListFoundException {

        PlayList thePlayList = null;
        boolean result = false;
        for (PlayList pl : playlists) {
            if (pl.getTitle().equalsIgnoreCase(playListTitle)) {
                thePlayList = pl;
                break;
            }
        }

        if (thePlayList != null)
            result = playlists.remove(thePlayList);
        if (!result) throw new NoPlayListFoundException("Playlist " + playListTitle + " not found!");
    }

    public Iterator<Album> albums() {
        return albums.listIterator();
    }

    public Iterator<PlayList> playlists() {
        return playlists.listIterator();
    }

    public Iterator<AudioElement> elements() {
        return elements.listIterator();
    }

    public String getAlbumsTitlesSortedByDate() {
        StringBuilder titleList = new StringBuilder();
        albums.sort(new SortByDate());
        for (Album al : albums)
            titleList.append(al.getTitle()).append("\n");
        return titleList.toString();
    }

    public String getAudiobooksTitlesSortedByAuthor() {
        StringBuilder titleList = new StringBuilder();
        List<AudioElement> audioBookList = new ArrayList<>();
        for (AudioElement ae : elements)
            if (ae instanceof AudioBook)
                audioBookList.add(ae);
        audioBookList.sort(new SortByAuthor());
        for (AudioElement ab : audioBookList)
            titleList.append(ab.getArtist()).append("\n");
        return titleList.toString();
    }

    public List<AudioElement> getAlbumSongs(String albumTitle) throws NoAlbumFoundException {
        Album theAlbum = null;
        ArrayList<AudioElement> songsInAlbum = new ArrayList<>();
        for (Album al : albums) {
            if (al.getTitle().equalsIgnoreCase(albumTitle)) {
                theAlbum = al;
                break;
            }
        }
        if (theAlbum == null) throw new NoAlbumFoundException("No album with this title in the MusicHub!");

        List<UUID> songIDs = theAlbum.getSongs();
        for (UUID id : songIDs)
            for (AudioElement el : elements) {
                if (el instanceof Song) {
                    if (el.getUUID().equals(id)) songsInAlbum.add(el);
                }
            }
        return songsInAlbum;

    }

    public List<Song> getAlbumSongsSortedByGenre(String albumTitle) throws NoAlbumFoundException {
        Album theAlbum = null;
        ArrayList<Song> songsInAlbum = new ArrayList<>();
        for (Album al : albums) {
            if (al.getTitle().equalsIgnoreCase(albumTitle)) {
                theAlbum = al;
                break;
            }
        }
        if (theAlbum == null) throw new NoAlbumFoundException("No album with this title in the MusicHub!");

        List<UUID> songIDs = theAlbum.getSongs();
        for (UUID id : songIDs)
            for (AudioElement el : elements) {
                if (el instanceof Song) {
                    if (el.getUUID().equals(id)) songsInAlbum.add((Song) el);
                }
            }
        songsInAlbum.sort(new SortByGenre());
        return songsInAlbum;

    }

    public void addElementToAlbum(String elementTitle, String albumTitle) throws NoAlbumFoundException, NoElementFoundException {
        Album theAlbum = null;
        int i;
        boolean found = false;
        for (i = 0; i < albums.size(); i++) {
            if (albums.get(i).getTitle().equalsIgnoreCase(albumTitle)) {
                theAlbum = albums.get(i);
                found = true;
                break;
            }
        }

        if (found) {
            AudioElement theElement = null;
            for (AudioElement ae : elements) {
                if (ae.getTitle().equalsIgnoreCase(elementTitle)) {
                    theElement = ae;
                    break;
                }
            }
            if (theElement != null) {
                theAlbum.addSong(theElement.getUUID());
                //replace the album in the list
                albums.set(i, theAlbum);
            } else throw new NoElementFoundException("Element " + elementTitle + " not found!");
        } else throw new NoAlbumFoundException("Album " + albumTitle + " not found!");

    }

    public void addElementToPlayList(String elementTitle, String playListTitle) throws NoPlayListFoundException, NoElementFoundException {
        PlayList thePlaylist = null;
        int i;
        boolean found = false;

        for (i = 0; i < playlists.size(); i++) {
            if (playlists.get(i).getTitle().equalsIgnoreCase(playListTitle)) {
                thePlaylist = playlists.get(i);
                found = true;
                break;
            }
        }

        if (found) {
            AudioElement theElement = null;
            for (AudioElement ae : elements) {
                if (ae.getTitle().equalsIgnoreCase(elementTitle)) {
                    theElement = ae;
                    break;
                }
            }
            if (theElement != null) {
                thePlaylist.addElement(theElement.getUUID());
                //replace the album in the list
                playlists.set(i, thePlaylist);
            } else throw new NoElementFoundException("Element " + elementTitle + " not found!");

        } else throw new NoPlayListFoundException("Playlist " + playListTitle + " not found!");

    }

    private void loadAlbums() {
        NodeList albumNodes = xmlHandler.parseXMLFile(ALBUMS_FILE_PATH);
        if (albumNodes == null) return;

        for (int i = 0; i < albumNodes.getLength(); i++) {
            if (albumNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element albumElement = (Element) albumNodes.item(i);
                if (albumElement.getNodeName().equals("album")) {
                    try {
                        this.addAlbum(new Album(albumElement));
                    } catch (Exception ex) {
                        System.out.println("Something is wrong with the XML album element");
                    }
                }
            }
        }
    }

    private void loadPlaylists() {
        NodeList playlistNodes = xmlHandler.parseXMLFile(PLAYLISTS_FILE_PATH);
        if (playlistNodes == null) return;

        for (int i = 0; i < playlistNodes.getLength(); i++) {
            if (playlistNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element playlistElement = (Element) playlistNodes.item(i);
                if (playlistElement.getNodeName().equals("playlist")) {
                    try {
                        this.addPlaylist(new PlayList(playlistElement));
                    } catch (Exception ex) {
                        System.out.println("Something is wrong with the XML playlist element");
                    }
                }
            }
        }
    }

    private void loadElements() {
        NodeList audioelementsNodes = xmlHandler.parseXMLFile(ELEMENTS_FILE_PATH);
        if (audioelementsNodes == null) return;

        for (int i = 0; i < audioelementsNodes.getLength(); i++) {
            if (audioelementsNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element audioElement = (Element) audioelementsNodes.item(i);
                if (audioElement.getNodeName().equals("song")) {
                    try {
                        AudioElement newSong = new Song(audioElement);
                        this.addElement(newSong);
                    } catch (Exception ex) {
                        System.out.println("Something is wrong with the XML song element");
                    }
                }
                if (audioElement.getNodeName().equals("audiobook")) {
                    try {
                        AudioElement newAudioBook = new AudioBook(audioElement);
                        this.addElement(newAudioBook);
                    } catch (Exception ex) {
                        System.out.println("Something is wrong with the XML audiobook element");
                    }
                }
            }
        }
    }


    public void saveAlbums() {
        Document document = xmlHandler.createXMLDocument();
        if (document == null) return;

        // root element
        Element root = document.createElement("albums");
        document.appendChild(root);

        //save all albums
        for (Iterator<Album> albumsIter = this.albums(); albumsIter.hasNext(); ) {
            Album currentAlbum = albumsIter.next();
            currentAlbum.createXMLElement(document, root);
        }
        xmlHandler.createXMLFile(document, ALBUMS_FILE_PATH);
    }

    public void savePlayLists() {
        Document document = xmlHandler.createXMLDocument();
        if (document == null) return;

        // root element
        Element root = document.createElement("playlists");
        document.appendChild(root);

        //save all playlists
        for (Iterator<PlayList> playlistsIter = this.playlists(); playlistsIter.hasNext(); ) {
            PlayList currentPlayList = playlistsIter.next();
            currentPlayList.createXMLElement(document, root);
        }
        xmlHandler.createXMLFile(document, PLAYLISTS_FILE_PATH);
    }

    public void saveElements() {
        Document document = xmlHandler.createXMLDocument();
        if (document == null) return;

        // root element
        Element root = document.createElement("elements");
        document.appendChild(root);

        //save all AudioElements
        for (AudioElement currentElement : elements) {

            if (currentElement instanceof Song) {
                currentElement.createXMLElement(document, root);
            }
            if (currentElement instanceof AudioBook) {
                currentElement.createXMLElement(document, root);
            }
        }
        xmlHandler.createXMLFile(document, ELEMENTS_FILE_PATH);
    }
    
    public void getSong(List<AudioElement> Songs, String songTitle) throws NoAlbumFoundException, UnsupportedAudioFileException, IOException, LineUnavailableException {
        for (AudioElement s : Songs) {
            if (s.getTitle().equalsIgnoreCase(songTitle)) {
                s.playSong();
            }
        }

    }
}