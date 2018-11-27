package project_x.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Song extends Model{
	public int id ;
	public Album songAlbum ;
	public String songName;
	
	
	public Song(int sid, int alid, String songname) {
		// TODO Auto-generated constructor stub
		id=sid;
		songAlbum =Album.find(alid);
		songName=songname;
	
	}

	public static List<Song> loadAll()  throws Exception{
		ArrayList<Song> list =  new ArrayList<Song>();
		ResultSet s =  DatabaseOperator.initialize().fetchQuery("Select * from Songs");
		
		while(s.next()) {
//			System.out.println("enter");
			list.add(new Song(s.getInt("SongId"),s.getInt("AlbumId"),s.getString("Song")));
		}
		return list;
	}
	
	public static List<Song> loadAllForAlbum(int id)  throws Exception{
		ArrayList<Song> list =  new ArrayList<Song>();
		ResultSet s =  DatabaseOperator.initialize().fetchQuery("Select * from Artists where AlbumId="+id);
		
		while(s.next()) {
//			System.out.println("enter");
			list.add(new Song(s.getInt("SongId"),s.getInt("AlbumId"),s.getString("Song")));
		}
		return list;
	}
	
	public static List<Song> searchByName(String str){
		String pattern = "%"+str+"%";
		String query= "Select * from Songs where Song like '"+pattern+"'";
		ArrayList<Song> list =  new ArrayList<Song>();
		ResultSet s;
		try {
			s = DatabaseOperator.initialize().fetchQuery(query);
			while(s.next()) {
//				System.out.println("enter");
				list.add(new Song(s.getInt("SongId"),s.getInt("AlbumId"),s.getString("Song")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	private static void delete(int id) throws SQLException, Exception {
		DatabaseOperator.initialize().executeQuery("Delete from Songs where SongId="+id );
	}
	
	public void delete() throws SQLException, Exception {
		Song.delete(this.id);
	}

	public static boolean create(String text, int id2) throws SQLException, Exception {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		String query = "Insert into Songs(AlbumId,Song) values("+id2+",'"+text+"')";
		return DatabaseOperator.initialize().executeQuery(query);
	}
}
