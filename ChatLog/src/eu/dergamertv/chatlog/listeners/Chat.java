package eu.dergamertv.chatlog.listeners;

import eu.dergamertv.chatlog.Main;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class Chat implements Listener{
	
	@EventHandler
	public void onChat(ChatEvent e) {
		if(e.getSender() instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) e.getSender();
			String msg = e.getMessage();
			if(!(msg.toLowerCase().startsWith("/"))) {
				Main.getInstance().log(p, msg);
			}
		}
	}

}
