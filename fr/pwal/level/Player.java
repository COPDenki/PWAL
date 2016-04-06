package fr.pwal.level;

import java.awt.Color;

import fr.pwal.base.physic.AABB;
import fr.pwal.graphics.base.graphics.level.Sprite;

public class Player implements EventEntity {

	public static final int KEY_UP = 0;
	public static final int KEY_LEFT = 1;
	public static final int KEY_DOWN = 2;
	public static final int KEY_RIGHT = 3;

	public static final int KEY_UP_LEFT = 7;
	public static final int KEY_DOWN_LEFT = 6;
	public static final int KEY_DOWN_RIGHT = 5;
	public static final int KEY_UP_RIGHT = 4;
	
	public static final int KEY_JUMP = 8;

	private String name;
	private int life;
	private int maxLife;
	private int[] keysCodes;
	private boolean[] keyStates;
	private Sprite sprite;
	private Color color;

	private AABB hitbox = new AABB(0, 0, 1, 1);

	public Player(String name, String spritePath, int[] keysCodes, Color color) {
		this.name = name;
		this.keysCodes = keysCodes;
		this.keyStates = new boolean[keysCodes.length*keysCodes.length];
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
		return this.hitbox.getPosX();
	}

	public float getPosY() {
		return this.hitbox.getPosY();
	}

	public int[] getKeysCodes() {
		return keysCodes;
	}

	public void setKeyState(int code, boolean val) {
		this.keyStates[code] = val;
	}

	public void setPosX(int posX) {
		this.hitbox.setPosX(posX);
	}

	public void setPosY(int posY) {
		this.hitbox.setPosY(posY);
	}

	public void move() {
		if (this.keyStates[KEY_LEFT] && !this.getHitbox().getSuperCollisionTablOfTheDeadXDPtdr()[AABB.LEFT]
				&& !this.getHitbox().getSuperCollisionTablOfTheDeadXDPtdr()[AABB.DOWN_LEFT]
				&& !this.getHitbox().getSuperCollisionTablOfTheDeadXDPtdr()[AABB.UP_LEFT])
			this.hitbox.setPosX(this.hitbox.getPosX() - 0.005f);
		if (this.keyStates[KEY_RIGHT] && !this.getHitbox().getSuperCollisionTablOfTheDeadXDPtdr()[AABB.RIGHT]
				&& !this.getHitbox().getSuperCollisionTablOfTheDeadXDPtdr()[AABB.DOWN_RIGHT]
				&& !this.getHitbox().getSuperCollisionTablOfTheDeadXDPtdr()[AABB.UP_RIGHT])
			this.hitbox.setPosX(this.hitbox.getPosX() + 0.005f);
		if (this.keyStates[KEY_DOWN] && !this.getHitbox().getSuperCollisionTablOfTheDeadXDPtdr()[AABB.DOWN]
				&& !this.getHitbox().getSuperCollisionTablOfTheDeadXDPtdr()[AABB.DOWN_LEFT]
				&& !this.getHitbox().getSuperCollisionTablOfTheDeadXDPtdr()[AABB.DOWN_RIGHT])
			this.hitbox.setPosY(this.hitbox.getPosY() + 0.005f);
		if (this.keyStates[KEY_UP] && !this.getHitbox().getSuperCollisionTablOfTheDeadXDPtdr()[AABB.UP]
				&& !this.getHitbox().getSuperCollisionTablOfTheDeadXDPtdr()[AABB.UP_RIGHT]
				&& !this.getHitbox().getSuperCollisionTablOfTheDeadXDPtdr()[AABB.UP_LEFT])
			this.hitbox.setPosY(this.hitbox.getPosY() - 0.005f);
	}

	public AABB getHitbox() {
		return this.hitbox;
	}

	public Color getColor() {
		return this.color;
	}

	public void decreaseLife(int damage) {
		this.setLife(this.getLife() - damage);
		if (this.getLife() <= 0)
			onDeath();
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
