package eu.dergamertv.chatlog.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQL_Chatlog {
	
	public static void create(String server, String name, String message, long time, String id) {
		try {
			PreparedStatement st = MySQL.con.prepareStatement("INSERT INTO reportmessages(server,name,message,timestamp,reportid) VALUES (?,?,?,?,?)");
			st.setString(1, server);
			st.setString(2, (name.replaceAll("-", "")));
			st.setString(3, message);
			st.setLong(4, (time/1000));
			st.setString(5, id);
			st.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public static boolean exists (String id) {
		try {
			PreparedStatement st = MySQL.con.prepareStatement("SELECT * FROM reportmessages WHERE reportid = ?");
			st.setString(1, id);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				return rs.getString("name") != null;
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
