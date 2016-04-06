package fr.pwal.base.physic;

import fr.pwal.level.Block;

public class AABB {

	public static final int UP = 0;
	public static final int DOWN = 2;
	public static final int LEFT = 3;
	public static final int RIGHT = 1;

	private float posX, posY;
	private int width, height;
	private boolean[] superCollisionTablOfTheDeadXDPtdr = { false, false, false, false, false, false, false, false };

	public AABB(float x, float y, int w, int h) {
		this.posX = x;
		this.posY = y;
		this.width = w;
		this.height = h;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setPosX(float posX) {
		this.posX = posX;
	}

	public void setPosY(float posY) {
		this.posY = posY;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public float getPosX() {
		return posX;
	}

	public float getPosY() {
		return posY;
	}

	public int getWidth() {
		return width;
	}

	public boolean[] getSuperCollisionTablOfTheDeadXDPtdr() {
		return superCollisionTablOfTheDeadXDPtdr;
	}

	public void setSuperCollisionTablOfTheDeadXDPtdr(int id, boolean superCollisionTablOfTheDeadXDPtdr) {
		this.superCollisionTablOfTheDeadXDPtdr[id] = superCollisionTablOfTheDeadXDPtdr;
	}

	@Override
	public String toString() {
		return "Hitbox : x=" + getPosX() + " | y=" + getPosY() + " | x1=" + (getPosX() + getWidth()) + " | y1=" + (getPosY() + getHeight());
	}
}
