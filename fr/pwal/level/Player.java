package fr.pwal.level;

import fr.pwal.graphics.base.graphics.level.Sprite;

public class Player {

	private String name;
	private int[] keyCodes;
	private Sprite sprite;
	
	private int posX = 0, posY = 0;
	
	
	
	
	
	public Player(String name, String spritePath, int[] keyCodes) {
		this.name = name;
		this.keyCodes = keyCodes;
		this.sprite = new Sprite(16, spritePath);
	}
	
	
	
	
	public String getName(){
		return this.name;
	}
	
	public Sprite getSprite(){
		return this.sprite;
	}
	
	public int getPosX(){
		return this.posX;
	}
	
	public int getPosY(){
		return this.posY;
	}
}
