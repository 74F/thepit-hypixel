package net.hypixel.minigames.gamesystems.event;

public enum MajorEvent {

	NONE("None"), RAFFLE("Raffle"), RAGE_PIT("」cRage Pit"), BEAST("」a」lBeast"), PIZZA("Pizza"), TDM("」Team DeathMatch"), ROBBERY("」e」lRobbery");
	
	private String scoreboardEventName;
	
	private MajorEvent(String scoreboardEventName) {
		this.scoreboardEventName = scoreboardEventName;
	}
	
	public String getEventName() {
		return scoreboardEventName;
	}
	
}
