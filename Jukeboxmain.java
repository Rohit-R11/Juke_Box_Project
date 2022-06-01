package jukebox;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
public class Jukeboxmain {
public static void main(String []args) throws SQLException, UserException, UnsupportedAudioFileException, IOException, LineUnavailableException {
	List<ModelSongs> List=Function.SongsIntoList();//This function returns me list
	List<ModelSongs> list1=Function.PodcastIntoList();
	Function f=new Function();
	List<Playlist> list=f.PlaylistintoList();//This function is not static that why we are creating object
	//ModelSongs m=new ModelSongs();
	Scanner sc=new Scanner(System.in);
	String username=Function.user();
	System.out.println("\t\t\t\t--------------------------JUKEBOX----------------------------\n\nPlease enter your User Name\n");
	String name=sc.next();
	if(username.equalsIgnoreCase(name)) {
	boolean Start1=false;
	while(!Start1){
		System.out.println("\n\nPress 1 for Songs\n\nPress 2 for Podcast\n\nPress 3 for adding Songs in PlayList\n\nPress 4 for adding Podcast in PlayList\n\nPress 5 for Display PlayList\n\nPress 6 for play track\n");
		int enter=sc.nextInt();//ask choice from user
	switch(enter) {
	case 1:
		boolean s=false;
		while(!s) {
			System.out.println("\nPress 1 for  Display Songs\n\nPress 2 for Search Song Name \n\nPress 3 for Search Album Name \n\nPress 4 for Seach Artist\n\nPress 5 for Stop Searching songs\n");
			int choices=sc.nextInt();
		switch(choices) {
		case 1:
		System.out.println("\t\t\t\t---------------------Display Songs---------------------------\n");
		System.out.format("%-25s %-25s %-25s %-25s %-25s %-1s","Song_ID","Song_Name","Song_Duration","Album_Name","Genre_Name","Artist_Name\n");
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
		System.out.println();
		////Using stream API
		List.stream().forEach((l)-> {
			System.out.format("%-25s %-25s %-25s %-25s %-25s %-1s",l.getSongId(),l.getSongName(),l.getsDuration(),l.getAlbumName(),l.getGenre(),l.getArtistName());
			System.out.println();
		});	
		break;
		case 2:
			System.out.println("Enter the Songs Name");
			String name3=sc.next();
			System.out.println("\t\t\t\t---------------------Display Songs---------------------------\n");
			System.out.format("%-25s %-25s %-25s %-25s %-25s %-1s","Song_ID","Song_Name","Song_Duration","Album_Name","Genre_Name","Artist_Name\n");
			System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
			Function.SearchsongName(name3);
			break;
		case 3:
			System.out.println("Enter the Album Name");
			String name1=sc.next();
			System.out.println("\t\t\t\t---------------------Display Songs---------------------------\n");
			System.out.format("%-25s %-25s %-25s %-25s %-25s %-1s","Song_ID","Song_Name","Song_Duration","Album_Name","Genre_Name","Artist_Name\n");
			System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
			Function.SearchAlbum(name1);
			break;
		case 4:
			System.out.println("Enter the Artist Name");
			String name2=sc.next();
			System.out.println("\t\t\t\t---------------------Display Songs---------------------------\n");
			System.out.format("%-25s %-25s %-25s %-25s %-25s %-1s","Song_ID","Song_Name","Song_Duration","Album_Name","Genre_Name","Artist_Name\n");
			System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
			Function.SearchArtist(name2);
			break;
		case 5:
			s=true;
			break;
		}
	}
		break;
	case 2:
		boolean Start11=false;
		while(!Start11){
			System.out.println("\nPress 1 for Display Podcast\n\nPress 2 for Search Celebrity Name\n\nPress 3 for Search Published Date\n\nPress 4 Stop searching\n");
		int choices1=sc.nextInt();
		switch(choices1) {
		case 1:
		
		System.out.println("\t\t\t\t---------------------Display Podcast---------------------------\n");
		System.out.format("%-25s %-25s %-45s %-80s %-25s %-1s","Podcast_ID","PodcastName","Celebrity_Name","Description","Duration","Published_Date");
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
		System.out.println();
		////Using stream API
		list1.stream().forEach((k)-> { 
			System.out.format("%-25s %-25s %-25s %-25s %-25s %-1s",k.getPodcastId(),k.getPodcastName(),k.getCelebrityName(),k.getDescription(),k.getsDuration(),k.getPublishedDate());
			System.out.println();
		});
		break;
		case 2:
			System.out.println("Enter the Celebrity Name");
			String name4=sc.next();
			System.out.println("\t\t\t\t---------------------Display Podcast---------------------------\n");
			System.out.format("%-25s %-25s %-45s %-80s %-25s %-1s","Podcast_ID","PodcastName","Celebrity_Name","Description","Duration","Published_Date");
			System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
			System.out.println();
			Function.SearchCelebrityName(name4);
			break;
		case 3:
			System.out.println("Enter the Album Name");
			String date=sc.next();
			Date d=Date.valueOf(date);
			System.out.println("\t\t\t\t---------------------Display Podcast---------------------------\n");
			System.out.format("%-25s %-25s %-45s %-80s %-25s %-1s","Podcast_ID","PodcastName","Celebrity_Name","Description","Duration","Published_Date");
			System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
			System.out.println();
			Function.SearchPublishdate(d);
			break;
		case 4:
			Start11=true;
			break;
		}
		}
		break;
	case 3:
		System.out.println("Write Playlist Name");
		sc.nextLine();
		String pname=sc.nextLine();
		System.out.println("Enter the Name of Song which you want to add in Playlist");
		String sname=sc.nextLine();
		Function.addPlaylists(sname,pname,List);
		System.out.println("\t\t\t\t---------------------Save Into PlayList---------------------------\n");
		break;
	case 4:
		System.out.println("Write Playlist Name");
		sc.nextLine();
		String pname1=sc.nextLine();
		System.out.println("Enter the Name of Podcast which you want to add in Playlist");
		String podname=sc.nextLine();
		Function.addPlaylistp(podname,pname1,list1);
		System.out.println("\t\t\t\t---------------------Save Into PlayList---------------------------\n");
		break;
	case 5:
		System.out.println("\t\t\t\t---------------------PlayList---------------------------\n");
		Function.displayPlaylist();
		break;
	case 6:
		Audio a=new Audio();
		System.out.println("Enter the Track Name");
		sc.nextLine();
		String trackName=sc.nextLine();
		a.filepath(trackName, list);
		break;
	}
	}
}
	throw new UserException("USER NAME IS INCORRECT----------PLEASE ENTER CORRECT USER NAME");
}

}