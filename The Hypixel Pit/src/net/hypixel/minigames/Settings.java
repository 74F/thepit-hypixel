package net.hypixel.minigames;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

import net.hypixel.minigames.gamesystems.event.MajorEvent;
//import net.hypixel.minigames.gamesystems.gui.Prestige;
import net.hypixel.minigames.gamesystems.status.SpecialStatus;
import net.hypixel.minigames.gamesystems.status.UserStatus;

public class Settings {

	public static SpecialStatus serverStatus = SpecialStatus.NONE;
	public static HashMap<Player, UserStatus> userStatus = new HashMap<Player, UserStatus>();
	public static MajorEvent currentEvent = MajorEvent.NONE;
	public static File languageFile = new File(ThePit.getInstance().getDataFolder(), "en-US.yml");
	public static boolean waiting_event = false;
	public static boolean doing_event = false;
	public static String[] prestigeStringList = {"-", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX"};
	public static String[] prestigeColorArray = {"7","9","9","9","9","e","e","e","e","e","6","6","6","6","6","c","c","c","c","c","5","5","5","5","5","d","d","d","d","d","f"};
	public static Integer[] levelRegularXP = {0, 15, 30, 45, 60, 75, 90, 105, 120, 135, 165, 195, 225, 255, 285, 315, 345, 375, 405, 435, 485, 535, 585, 635, 685, 735, 785, 835, 885, 935, 1010, 1085, 1160, 1235, 1310, 1385, 1460, 1535, 1610, 1685, 1810, 1935, 2060, 2185, 2310, 2435, 2560, 2685, 2810, 2935, 3185, 3435, 3685, 3935, 4185, 4435, 4685, 4935, 5185, 5435, 6035, 6635, 7235, 7835, 8435, 9035, 9635, 10235, 10835, 11435, 12235, 13035, 13835, 14635, 15435, 16235, 17035, 17835, 18635, 19435, 20335, 21235, 22135, 23035, 23935, 24835, 25735, 26635, 27535, 28435, 29435, 30435, 31435, 32435, 33435, 34435, 35435, 36435, 37435, 38435, 39635, 40835, 42035, 43235, 44435, 45635, 46835, 48035, 49235, 50435, 51935, 53435, 54935, 56435, 57935, 59435, 60935, 62435, 63935, 65435}; // 11435
	public static Double[] prestigeEXPPersent = {1.0, 1.1, 1.2, 1.3, 1.4, 1.5, 1.75, 2.0, 2.5, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 12.0, 14.0, 16.0, 18.0, 20.0, 24.0, 28.0, 32.0, 36.0, 40.0, 60.0, 80.0, 100.0, 120.0};
	public static double[] neededGoldToPrestige = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	public static String[] levelColor = {"」7", "」7", "」7", "」7", "」7", "」7", "」7", "」7", "」7", "」9", "」9", "」9", "」9", "」9", "」9", "」9", "」9", "」9", "」9", "」3", "」3", "」3", "」3", "」3", "」3", "」3", "」3", "」3", "」3", "」2", "」2", "」2", "」2", "」2", "」2", "」2", "」2", "」2", "」2", "」a", "」a", "」a", "」a", "」a", "」a", "」a", "」a", "」a", "」a", "」e", "」e", "」e", "」e", "」e", "」e", "」e", "」e", "」e", "」e", "」6」l", "」6」l", "」6」l", "」6」l", "」6」l", "」6」l", "」6」l", "」6」l", "」6」l", "」6」l", "」c」l", "」c」l", "」c」l", "」c」l", "」c」l", "」c」l", "」c」l", "」c」l", "」c」l", "」c」l", "」4」l", "」4」l", "」4」l", "」4」l", "」4」l", "」4」l", "」4」l", "」4」l", "」4」l", "」4」l", "」5」l", "」5」l", "」5」l", "」5」l", "」5」l", "」5」l", "」5」l", "」5」l", "」5」l", "」5」l", "」d」l", "」d」l", "」d」l", "」d」l", "」d」l", "」d」l", "」d」l", "」d」l", "」d」l", "」d」l", "」f」l", "」f」l", "」f」l", "」f」l", "」f」l", "」f」l", "」f」l", "」f」l", "」f」l", "」f」l", "」b」l"};
	public static int[] prestigeUpRenownRewards = {0, 10, 10, 10, 10, 20, 20, 20, 20, 20, 30, 30, 30, 30, 30, 40, 40, 40, 40, 40, 50, 50, 50, 50, 50, 60, 60, 60, 60, 60, 60};
	public static Map<Player, Integer> fightTimer = new HashMap();
	//public static Prestige prestige = new Prestige("Prestige", 27);
	
	public static SpecialStatus getServerStatus() {
		return serverStatus;
	}
	
	public static void setServerStatus(SpecialStatus stats) {
		serverStatus = stats;
	}
	
	public static UserStatus getPlayerStatus(Player p) {
		return userStatus.get(p);
	}
	
	public static void setPlayerStatus(Player p, UserStatus s) {
		if(userStatus.containsKey(p)) {
			userStatus.remove(p);
			userStatus.put(p, s);
		}
	}
	
	public static MajorEvent getCurrentEvent() {
		return currentEvent;
	}
	
	public static void setCurrentEvent(MajorEvent e) {
		currentEvent = e;
	}
	
	public static File getLanguageFile()  {
		return languageFile;
	}
	
	public static void setLanguageFile(File f) {
		languageFile = f;
	}

	public static ThePit getMinigame() {
		// TODO Auto-generated method stub
		return ThePit.getInstance();
	}
	
	public static boolean hasWaitingEvent() {
		return waiting_event;
	}
	
	public static void setWaitingEvent(boolean b) {
		waiting_event = b;
	}
	
	public static boolean duringEvent() {
		return doing_event;
	}
	
	public static void setDuringEvent(boolean b) {
		doing_event = b;
	}
}
