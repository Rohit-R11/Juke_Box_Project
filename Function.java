package jukebox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class Function {
	static Connection con;
	
static {
	
	try {
		Connectionclass cn=new Connectionclass();
		con=cn.getConnection();
		}
		catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
}
static String user() throws SQLException {
	 Statement smt=con.createStatement();
	 ResultSet rs=smt.executeQuery("select *from USERS");
	 while(rs.next()) {
			
			return rs.getString("NAME");
		}
		return null;
}
static List<ModelSongs> SongsIntoList() throws SQLException{	
	List<ModelSongs> list = new ArrayList<>();
    Statement smt=con.createStatement();
	//	PreparedStatement ps=con.prepareStatement("select s.songid, s.title_of_song, s.duration, a.album_name, s.genre, s.artist from songs s inner join albums a on s.albumid = a.albumid");
	ResultSet rs=smt.executeQuery("select s.songid, s.title_of_song, s.duration, a.album_name, s.genre, s.artist from songs s inner join albums a on s.albumid = a.albumid");
	while(rs.next()) {
		ModelSongs model  = new ModelSongs();
		model.setSongId(rs.getInt(1));
		model.setSongName(rs.getString(2));
		model.setsDuration(rs.getTime(3));
		model.setAlbumName(rs.getString(4));
		model.setGenre(rs.getString(5));
		model.setArtistName(rs.getString(6));
		list.add(model);
	}
	return list;
}
static void SearchsongName(String name) throws SQLException{
	//list.stream().filter(p->p.getSongName().startsWith(name)).forEach(System.out::println);}
	PreparedStatement ps=con.prepareStatement("select s.songid, s.title_of_song, s.duration, a.album_name, s.genre, s.artist from songs s inner join albums a on s.albumid = a.albumid where title_of_song like '"+name+"%'");
	ResultSet rs=ps.executeQuery();
	while(rs.next()) {
		System.out.format("%-25s %-25s %-25s %-25s %-25s %-1s",rs.getInt(1),rs.getString(2),rs.getTime(3),rs.getString(4),rs.getString(5),rs.getString(6));
		System.out.println();
	}
}
static void SearchAlbum(String name) throws SQLException{
	PreparedStatement ps=con.prepareStatement("select s.songid, s.title_of_song, s.duration, a.album_name, s.genre, s.artist from songs s inner join albums a on s.albumid = a.albumid where album_name like '"+name+"%'");
	ResultSet rs=ps.executeQuery();
	while(rs.next()) {
		System.out.format("%-25s %-25s %-25s %-25s %-25s %-1s",rs.getInt(1),rs.getString(2),rs.getTime(3),rs.getString(4),rs.getString(5),rs.getString(6));
		System.out.println();
	}
}
static void SearchArtist(String name) throws SQLException{
	PreparedStatement ps=con.prepareStatement("select s.songid, s.title_of_song, s.duration, a.album_name, s.genre, s.artist from songs s inner join albums a on s.albumid = a.albumid where artist like '"+name+"%'");
	ResultSet rs=ps.executeQuery();
	while(rs.next()) {
		System.out.format("%-25s %-25s %-25s %-25s %-25s %-1s",rs.getInt(1),rs.getString(2),rs.getTime(3),rs.getString(4),rs.getString(5),rs.getString(6));
		System.out.println();
	}
}
static List<ModelSongs> PodcastIntoList() throws SQLException{
	List<ModelSongs> list1=new ArrayList<>();
	Statement smt=con.createStatement();
	ResultSet rs=smt.executeQuery("select *from PODCAST");
	while(rs.next()) {
		ModelSongs model1=new ModelSongs();
		model1.setPodcastId(rs.getInt(1));
		model1.setPodcastName(rs.getString(2));
		model1.setCelebrityName(rs.getString(3));
		model1.setDescription(rs.getString(4));
		model1.setsDuration(rs.getTime(5));
		model1.setPublishedDate(rs.getDate(6));
		list1.add(model1);
	}
			return list1;
}
static void SearchCelebrityName(String name) throws SQLException{
	PreparedStatement ps=con.prepareStatement("select *from PODCAST where celebrity_name like '"+name+"%'");
	ResultSet rs=ps.executeQuery();
	while(rs.next()) {
		System.out.format("%-25s %-25s %-25s %-25s %-25s %-1s",rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getTime(5),rs.getDate(6));
		System.out.println();
	}
}
static void SearchPublishdate(Date 	date) throws SQLException{
	PreparedStatement ps=con.prepareStatement("select *from PODCAST where PUBLISHED_DATE like '"+date+"%'");
	ResultSet rs=ps.executeQuery();
	while(rs.next()) {
		System.out.format("%-25s %-25s %-25s %-25s %-25s %-1s",rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getTime(5),rs.getDate(6));
		System.out.println();
		}
}
static boolean addPlaylists(String sName,String pName,List<ModelSongs> list) throws SQLException {
	for(ModelSongs l: list) {
		if(sName.equalsIgnoreCase(l.getSongName())) {
			PreparedStatement ps=con.prepareStatement("Insert into PLAYLIST(PLAYLIST_NAME,TRACK_TYPE,TRACK_NAME,SID)values(?,'SONG',?,?)");
			ps.setString(1, pName);
			ps.setString(2, l.getSongName());
			ps.setInt(3, l.getSongId());
			return ps.execute();
		}
	}
	return false;	
}
static boolean addPlaylistp(String podName,String pName,List<ModelSongs>list) throws SQLException {
	
	for(ModelSongs l: list) {
		System.out.println(podName);
		if(podName.equalsIgnoreCase(l.getPodcastName())) {
			PreparedStatement ps=con.prepareStatement("Insert into PLAYLIST(PLAYLIST_NAME,TRACK_TYPE,TRACK_NAME,PID)values(?,'PODCAST',?,?)");
			ps.setString(1, pName);
			ps.setString(2, l.getPodcastName());
			ps.setInt(3, l.getPodcastId());
			return ps.execute();
		}
	}
	return false;
}
public  List<Playlist> PlaylistintoList() throws SQLException{
	List<Playlist> list=new ArrayList<>();
	Statement smt=con.createStatement();
	ResultSet rs=smt.executeQuery("Select * from PLAYLIST");
	while(rs.next()) {
		Playlist pl=new Playlist();
		pl.setPlaylistId(rs.getInt(1));
		pl.setPlaylistName(rs.getString(2));
		pl.setTrackType(rs.getString(3));
		pl.setTrackName(rs.getString(4));
		pl.setSongid(rs.getInt(5));
		pl.setPodcastId(rs.getInt(6));
		list.add(pl);
	}
	return list;
}
static void displayPlaylist() throws SQLException {
	Statement smt=con.createStatement();
	ResultSet rs=smt.executeQuery("Select *from PLAYLIST");
	System.out.format("%1s %15s %15s %15s %15s %15s","PLALIST_ID","PLAYLIST_NAME","TRACK_TYPE","TRACK_NAME","SONG_ID","PODI");
	System.out.println();
	while(rs.next()) {
		System.out.format("%1s %18s %20s %18s %15s %15s",rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6));
		System.out.println();
	}
}
}

