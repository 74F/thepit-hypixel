package net.hypixel.minigames.gamesystems.event;

public enum MinorEvent {

	EVERYONE_GETS_BOUNTY("Everyone gets bounty!");
	
	private String name;
	
	private MinorEvent(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
