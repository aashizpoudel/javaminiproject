package project_x.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Album extends Model{
	public int id ;
	public String albumName ;
	public Artist albumArtist ;
	public int albumYear ;
	
	public Album(int int1, int int2, String string, int int3) {
		// TODO Auto-generated constructor stub
		id=int1;
		albumName=string;
		albumYear = int3;
		albumArtist = Artist.find(int2);
	}

	public static List<Album> loadAll()  throws Exception{
		ArrayList<Album> list =  new ArrayList<Album>();
		ResultSet s =  DatabaseOperator.initialize().fetchQuery("Select * from Albums");
		
		while(s.next()) {
			System.out.println("enter");
			list.add(new Album(s.getInt("AlbumId"),s.getInt("ArtistId"),s.getString("AlbumName"),s.getInt("AlbumYear")));
		}
		return list;
	}
	
	public static List<Album> loadAllForArtist(int id)  throws Exception{
		ArrayList<Album> list =  new ArrayList<Album>();
		ResultSet s =  DatabaseOperator.initialize().fetchQuery("Select * from Albums where ArtistId="+id);
		
		while(s.next()) {
//			System.out.println("enter");
			list.add(new Album(s.getInt("AlbumId"),s.getInt("ArtistId"),s.getString("AlbumName"),s.getInt("AlbumYear")));
		}
		return list;
	}
	
	public static List<Artist> searchByName(String str){
		return null;
	}
	
	private static void delete(int id) throws SQLException, Exception {
		DatabaseOperator.initialize().executeQuery("Delete from Albums where AlbumId="+id );
	}
	
	public void delete() throws SQLException, Exception {
		Album.delete(this.id);
	}

	public static Album find(int alid) {
		// TODO Auto-generated method stub
ResultSet s;
try {
	s = DatabaseOperator.initialize().fetchQuery("Select * from Albums where AlbumId="+alid);
	while(s.next()) {
//		System.out.println("enter");
		return new Album(s.getInt("AlbumId"),s.getInt("ArtistId"),s.getString("AlbumName"),s.getInt("AlbumYear"));
	}
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
return null;
		
		
	}

	public static boolean create(String text, int id2) throws Exception{
		// TODO Auto-generated method stub
		String query = "Insert into Albums(ArtistId,AlbumName) values("+id2+",'"+text+"')";
		return DatabaseOperator.initialize().executeQuery(query);
	}
}
