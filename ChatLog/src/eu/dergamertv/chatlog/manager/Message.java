package eu.dergamertv.chatlog.manager;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.Server;

public class Message {
	
	private ProxiedPlayer p;
	private Server server;
	private long timestamp;
	private String msg;
	
	public Message(ProxiedPlayer p, String msg) {
		this.p = p;
		this.server = p.getServer();
		this.timestamp = System.currentTimeMillis();
		this.msg = msg;
		
	}

	public ProxiedPlayer getPlayer() {
		return p;
	}
	
	
	public Server getServer() {
		return server;
	}
	
	public long getTime() {
		return timestamp;
	}
	
	public String getMessage() {
		return msg;
	}
	
}
