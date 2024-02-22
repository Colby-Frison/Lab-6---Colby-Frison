import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Playlist {
	
	private ArrayList<Song> songs;
	
	public Playlist(String filename) {
		String line = null;
		BufferedReader reader;

		ArrayList<Song> songTemps = new ArrayList<Song>();

		try{
			reader = new BufferedReader(new FileReader(filename));
			line = reader.readLine();

			while(line != null){
				songTemps.add(new Song(line));
	
				line = reader.readLine();
			}
	
			songs = songTemps;
			
			reader.close();
		}
		catch(IOException e){
			System.out.println("no work");
		}
	}

	public Playlist(){
		songs = new ArrayList<Song>();
	}
	
	public int getNumSongs() {
		return songs.size();
	}
	
	public Song getSong(int index) {
		if (index < 0 || index >= getNumSongs()) {
			return null;
		}
		return songs.get(index);
	}
	
	public Song[] getSongs() {
		return songs.toArray(new Song[0]);
	}
	
	public boolean addSong(Song song) {
		return addSong(getNumSongs(), song);
	}
	
	public boolean addSong(int index, Song song) {
        //make sure its a valid mehtod call
        if( index < 0 || index > songs.size() || song == null) {
            return false;
		}
        
        songs.add(index, song);
        
        return true;
	}
	
	public int addSongs(Playlist playlist) {
        if(playlist == null){
            return 0;
		}

		int cnt = 0;
        for(Song song : playlist.getSongs()){
			if(addSong(song))
            	cnt++;
        }
        return cnt;
	}

	public int addSongs(String filename) {
        String line = null;
		BufferedReader reader;

		int num = 0;

		try{
			reader = new BufferedReader(new FileReader(filename));
			line = reader.readLine();

			while(line != null){
				songs.add(new Song(line));
				num++;
	
				line = reader.readLine();
			}
			
			reader.close();
		}
		catch(IOException e){
			System.out.println("no work");
		}

		return num;
	}
	
	public Song removeSong() {
		return removeSong(getNumSongs() - 1);
	}
	
	public Song removeSong(int index) {

		if(!(index < 0 || index >= getNumSongs())){
			Song temp = songs.get(index);

			songs.remove(index);

			return temp;
		}
		else{
			return null;
		}
	}

	
	public String toString(){
		String str = "";
		String temp1 = "";
		String temp2 = "";

		for(int i = 0; i < songs.size(); i++){
			temp2 = str;
			temp1 = songs.get(i).toString();
			if(i + 1 == songs.size()){
				str = temp2.concat(temp1);
			}
			else{
				str = temp2.concat(temp1 + System.lineSeparator());
			}
		}


		return str;
	}

	public void saveSongs(String filename){
		File playlistFile = new File(filename);

		try {
      		FileWriter myWriter = new FileWriter(filename);
      		myWriter.write(toString());
      		myWriter.close();
    	} catch (IOException e) {
      		System.out.println("An error occurred.");
      		e.printStackTrace();
    	}
	}
}
