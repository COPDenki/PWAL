package fr.pwal.level;

import fr.pwal.graphics.base.graphics.level.Sprite;

public class Block {
	
	private final int SIZE = 16;
	private final boolean isHard;
	private int posX, posY;
	private final char id;
	private Sprite sprite;

	public Block(char id, String spritePath) {
		this.sprite = new Sprite(SIZE, spritePath);
		this.isHard = true;
		this.id = id;
	}
	
	
	public int getPosX(){
		return this.posX;
	}
	
	public int getPosY(){
		return this.posY;
	}
	
	public boolean getIsHard(){
		return this.isHard;
	}
	
	public char getId(){
		return this.id;
	}
	
	public Sprite getSprite(){
		return this.sprite;
	}
}