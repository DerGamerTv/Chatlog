package eu.dergamertv.chatlog.manager;

import java.util.Random;

import eu.dergamertv.chatlog.mysql.MySQL_Chatlog;

public class KeyGenerator {
	
	private static Random rdm;
	private static String pattern = "";
	private static int i = 0;
	
	static {
		rdm = new Random();
		pattern = pattern + "abcdefghijklmnopqrstuvwxyz";
		pattern = pattern + pattern.toLowerCase();
		pattern = pattern + "1234567890";
	}
	
	public static String getRandomKey(int lenght) {
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < lenght; i++) {
			builder.append(pattern.charAt(rdm.nextInt(pattern.length())));
		}
		if(MySQL_Chatlog.exists(builder.toString())) {
			if(i < 5) {
				return getRandomKey(lenght);
			}else {
				return "";
			}
		}
		return builder.toString();
	}

}
