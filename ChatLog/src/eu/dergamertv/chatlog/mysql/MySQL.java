package eu.dergamertv.chatlog.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


import eu.dergamertv.chatlog.Main;
import net.md_5.bungee.api.ProxyServer;

public class MySQL {
	
	public static Connection con;
	
	public static String host = "localhost";
	public static String name = "root";
	public static String password = "root";
	public static String database = "chat";
	
	public static void connect() {
		if(!(isConnected())) {
			try{
				con = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + database + "?autoReconnect=true", name, password);
				sendConsoleMessage(Main.prefix + "§aVerbindung zur Datenbank erfolgreich!");
			}catch (SQLException e) {
				e.printStackTrace();
				sendConsoleMessage(Main.prefix + "§cVerbindung zur Datenbank konnte nicht hergestellt werden!");
			}
		}
	}
	
	public static void close() {
		if(isConnected()) {
			try {
				con.close();
				sendConsoleMessage(Main.prefix + "§3Verbindung erfolgreich beendet!");
			} catch (SQLException e) {
				e.printStackTrace();
				sendConsoleMessage(Main.prefix + "§cVerbindung konnte nicht beendet werden!");
			}
		}
	}

	public static boolean isConnected() {
		return con != null;
	}
	
	@SuppressWarnings("deprecation")
	public static void sendConsoleMessage(String str) {
		ProxyServer.getInstance().getConsole().sendMessage(str);
	}
	
	public static void update(String qry) {
		if(isConnected()) {
			try {
				con.createStatement().executeUpdate(qry);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static ResultSet getSet(String qry) {
		if(isConnected()) {
			try {
				return con.createStatement().executeQuery(qry);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static void createTable() {
		update("CREATE TABLE IF NOT EXISTS reportmessages (id int NOT NULL AUTO_INCREMENT, server varchar(100), name varchar(100), message varchar(400), timestamp varchar(50), reportid text, PRIMARY KEY (id)) DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci");
	}

	public static ResultSet getResult(String string) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
