package fr.pwal.base.physic;

import fr.pwal.level.Player;

public class Gravity {

	public static final int DIRECTION_UP = 0;
	public static final int DIRECTION_RIGHT = 1;
	public static final int DIRECTION_DOWN = 2;
	public static final int DIRECTION_LEFT = 3;

	private float intensity;
	private int direction;

	public static Gravity GRAVITY_DOWN = new Gravity(DIRECTION_DOWN, 0.008f);
	public static Gravity GRAVITY_UP = new Gravity(DIRECTION_UP, 0.001f);
	public static Gravity GRAVITY_LEFT = new Gravity(DIRECTION_LEFT, 0.001f);
	public static Gravity GRAVITY_RIGHT = new Gravity(DIRECTION_RIGHT, 0.001f);

	public Gravity(int direction, float intensity) {
		this.setDirection(direction);
		this.setIntensity(intensity);
	}

	public void appliedOn(Player p) {
		switch (getDirection()) {
		case DIRECTION_UP:
			if(!p.getHitbox().getSuperCollisionTablOfTheDeadXDPtdr()[AABB.UP])
				p.getHitbox().setPosY((p.getHitbox().getPosY()-getIntensity()));
			break;
		case DIRECTION_DOWN:
			if(!p.getHitbox().getSuperCollisionTablOfTheDeadXDPtdr()[AABB.DOWN])
				p.getHitbox().setPosY((p.getHitbox().getPosY()+getIntensity()));
			break;
		case DIRECTION_LEFT:
			if(!p.getHitbox().getSuperCollisionTablOfTheDeadXDPtdr()[AABB.LEFT])
				p.getHitbox().setPosX((p.getHitbox().getPosX()-getIntensity()));
			break;
		case DIRECTION_RIGHT:
			if(!p.getHitbox().getSuperCollisionTablOfTheDeadXDPtdr()[AABB.RIGHT])
				p.getHitbox().setPosX((p.getHitbox().getPosX()+getIntensity()));
			break;
		}
	}

	public float getIntensity() {
		return intensity;
	}

	public void setIntensity(float intensity) {
		this.intensity = intensity;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}
}
