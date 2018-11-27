package project_x.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Artist extends Model {
	public String name;
	public int id;
	
	private static String table_name = "Artists";
	
	Artist(int id,String artName) {
		this.id=id;
		name = artName ;
	}
	
	public static boolean create(String name) throws Exception{
		
			//do database operation
			DatabaseOperator operator = DatabaseOperator.initialize();
			
			return operator.executeQuery("Insert into "+table_name+"(name) values('"+name+"')");
			
		}
	
	public static Artist find(int id) {
		
		try {
			ResultSet s =  DatabaseOperator.initialize().fetchQuery("Select * from Artists where ArtistId="+id);

			while(s.next()) {
//			System.out.println("enter");
				return new Artist(s.getInt("ArtistId"),s.getString("name"));
			}
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			
		}
		return null;
	}
	

	
	public static List<Artist> searchByName(String str){
		return null;
	}
	
	public static List<Artist> loadAll()  throws Exception{
		ArrayList<Artist> list =  new ArrayList<Artist>();
		ResultSet s =  DatabaseOperator.initialize().fetchQuery("Select * from Artists");
		
		while(s.next()) {
//			System.out.println("enter");
			list.add(new Artist(s.getInt("ArtistId"),s.getString("name")));
		}
		return list;
	}
	
	private static void delete(int id) throws SQLException, Exception {
		DatabaseOperator.initialize().executeQuery("Delete from Artists where ArtistId="+id );
	}
	
	public void delete() throws SQLException, Exception {
		Artist.delete(this.id);
	}
}
