package eu.dergamertv.chatlog.commands;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import eu.dergamertv.chatlog.Main;
import eu.dergamertv.chatlog.manager.KeyGenerator;
import eu.dergamertv.chatlog.manager.Message;
import eu.dergamertv.chatlog.mysql.MySQL_Chatlog;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Commands extends Command{

	public Commands(String name) {
		super(name);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {
		if(sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			
			List<ProxiedPlayer> players = new ArrayList<>();
			int error = 0;
			
			if(args.length == 0) {
				p.sendMessage(Main.prefix + "Verwende §c/chatlog <Spieler> [...]");
				return;
			}else for(int i = 0; i< args.length; i++) {
				ProxiedPlayer k = ProxyServer.getInstance().getPlayer(args[i]);
				if(k != null) {
					players.add(k);
				}else {
					error++;
				}
			}
			if(error != 0) {
				p.sendMessage(Main.prefix + "§cEs sind §e" + error + " §cSpieler die du reporten wolltest nicht online!");
				return;
			}
			
			long end = (System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(15));
			List<Message> save = new ArrayList<>();
			for(ProxiedPlayer on : players) {
				if(Main.getInstance().chatlog.containsKey(on)) {
					for(Message ms : Main.getInstance().chatlog.get(on)) {
						if(ms.getTime() > end) {
							save.add(ms);
						}
					}
				}
			}
			
			if(save.isEmpty()) {
				p.sendMessage(Main.prefix + "§cEs wurden keine Nachrichten gefunden!");
				return;
			}
			
			String id = KeyGenerator.getRandomKey(8);
			for(Message ms : save) {
				MySQL_Chatlog.create(ms.getServer().getInfo().getName(), ms.getPlayer().getUniqueId().toString(), ms.getMessage(), ms.getTime(), id);
			}
			p.sendMessage(Main.prefix + "Der Chat wurde erfolgreich gespeichert!");
			p.sendMessage(Main.prefix + "http://rage-games.eu/chatlog/?report" + id);
		}
		
		
	}
	

}
