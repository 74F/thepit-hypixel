package net.hypixel.minigames.gamesystems.status;

public enum SpecialStatus {

	NONE("¡×cNo Special", false), EVENT("¡×6Event", true);
	
	private String status;
	private boolean visible;
	
	private SpecialStatus(String status, boolean visible) {
		this.status = status;
		this.visible = visible;
	}
	
	public String getStatus() {
		return status;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	
}
