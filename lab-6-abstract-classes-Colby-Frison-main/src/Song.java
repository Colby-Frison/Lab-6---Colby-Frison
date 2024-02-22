import java.util.Arrays;
import java.util.Collections;


public class Song {
	
	private String title;
	private String artist;
	private int[] time;

	private static final String INFO_DELIMITER = "; ";
	private static final String TIME_DELIMITER = ":";

	private static final int IDX_TITLE = 0;
	private static final int IDX_ARTIST = 1;
	private static final int IDX_TIME = 2;

	
	public Song(String title, String artist, int[] time) {
		this.title = title;
		this.artist = artist;
		this.time = Arrays.copyOf(time, time.length);
	}

	public Song(String info){
		String[] info2 = info.split(INFO_DELIMITER);
		title = info2[0];
		artist = info2[1];
		String[] timeInfo = info2[2].split(TIME_DELIMITER);

		Collections.reverse(Arrays.asList(timeInfo));

		int[] temp = new int[timeInfo.length];

		for(int i = 0; i < timeInfo.length; i++){
			temp[i] = Integer.parseInt(timeInfo[i]);
		}

		time = temp;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public int[] getTime() {
		return Arrays.copyOf(time, time.length);
	}

	@Override
	public String toString(){
		String timeStr = "";
		if(time.length == 3){
			String form = String.format("%d:%02d:%02d", time[2], time[1], time[0]);
			return "" + title + "; " + artist + "; " + form;
		}
		else if(time.length == 2){
			String form = String.format("%d:%02d", time[1], time[0]);
			return "" + title + "; " + artist + "; " + form;
		}
		else{
			String form = String.format("%02d", time[0]);
			return "" + title + "; " + artist + "; " + form;
		}
	}
}
