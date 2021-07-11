package eu.dergamertv.chatlog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import eu.dergamertv.chatlog.commands.Commands;
import eu.dergamertv.chatlog.listeners.Chat;
import eu.dergamertv.chatlog.manager.Message;
import eu.dergamertv.chatlog.mysql.MySQL;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

public class Main extends Plugin{
	
	public static String prefix = "§8[§4ChatLog§8] §7";
	private static Main instance;
	public HashMap<ProxiedPlayer, List<Message>> chatlog = new HashMap<>();
	
	@Override
	public void onDisable() {
		instance = null;
		MySQL.close();
	}

	@Override
	public void onEnable() {
		instance = this;
		MySQL.connect();
		MySQL.createTable();
		
		PluginManager pm = ProxyServer.getInstance().getPluginManager();
		pm.registerListener(this, new Chat());
		pm.registerCommand(this, new Commands("chatlog"));
		
	}

	public static Main getInstance() {
		return instance;
	}
	
	public void log(ProxiedPlayer p, String msg) {
		if(!chatlog.containsKey(p)) {
			chatlog.put(p, new ArrayList<Message>());
		}
		chatlog.get(p).add(new Message(p, msg));
	}
	
}
