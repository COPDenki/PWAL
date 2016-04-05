package fr.pwal.level;

import java.awt.Color;

import fr.pwal.graphics.base.graphics.level.Sprite;

public class Player implements EventEntity{

	public static final int KEY_UP = 0;
	public static final int KEY_LEFT = 1;
	public static final int KEY_DOWN = 2;
	public static final int KEY_RIGHT = 3;
	public static final int KEY_JUMP = 4;

	private String name;
	private int life;
	private int maxLife;
	private int[] keysCodes;
	private boolean[] keyStates;
	private Sprite sprite;
	private Color color;

	private float posX = 0, posY = 0;

	public Player(String name, String spritePath, int[] keysCodes, Color color) {
		this.name = name;
		this.keysCodes = keysCodes;
		this.keyStates = new boolean[keysCodes.length];
		this.sprite = new Sprite(16, spritePath);
		this.color = color;
	}

	public String getName() {
		return this.name;
	}

	public Sprite getSprite() {
		return this.sprite;
	}

	public float getPosX() {
		return this.posX;
	}

	public float getPosY() {
		return this.posY;
	}

	public int[] getKeysCodes() {
		return keysCodes;
	}

	public void setKeyState(int code, boolean val) {
		this.keyStates[code] = val;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public void move() {
		if (this.keyStates[KEY_LEFT])
			this.posX -= 0.01;
		if (this.keyStates[KEY_RIGHT])
			this.posX += 0.01;
	}

	public Color getColor() {
		return this.color;
	}

	public void decreaseLife(int damage) {
		this.setLife(this.getLife() - damage);
		if(this.getLife() <= 0) onDeath();
	}

	public void increaseLife(int heal) {
		if (this.maxLife > heal + this.getLife())
			this.setLife(maxLife);
		else
			this.setLife(this.getLife() + heal);
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	@Override
	public void onDeath() {
	}
}
