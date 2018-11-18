package net.hypixel.minigames.gamesystems.status;

public enum UserStatus {

	IDLE("¡×aIdling"), FIGHTING("¡×cFighting"), BOUNTY("¡×cBountied");
	
	private String status;
	
	private UserStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}
}
