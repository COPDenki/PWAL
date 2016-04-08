package fr.pwal.level;

import java.awt.Color;
import java.awt.Image;

import fr.pwal.base.physic.AABB;
import fr.pwal.graphics.base.graphics.level.CharacterSprite;

public class Player implements EventEntity {

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
	private CharacterSprite sprite;
	private Color color;

	private int moveDirection = 0;
	private float moveTimer = 0;
	private float speed = 0.007f;
	private float slow = 1;
	private int jump_time = 3*120;
	private int jump_time_counter;

	private boolean isJumping;

	private AABB hitbox;
	private boolean isJumpFalling;

	public Player(String name, String spritePath, int[] keysCodes, Color color) {
		this.name = name;
		this.keysCodes = keysCodes;
		this.keyStates = new boolean[keysCodes.length];
		this.sprite = new CharacterSprite(spritePath);
		this.color = color;
		this.hitbox = new AABB(0, 0, 1, 1);
	}

	public String getName() {
		return this.name;
	}

	public Image getSprite() {
		int moveTimer2 = (int) moveTimer;
		switch (moveDirection) {
		case 1:
			if (moveTimer2 == 1)
				return this.sprite.getSprite(4);
			else if (moveTimer2 == 3)
				return this.sprite.getSprite(5);
			else
				return this.sprite.getSprite(3);
		case 3:
			if (moveTimer2 == 1)
				return this.sprite.getSprite(10);
			else if (moveTimer2 == 3)
				return this.sprite.getSprite(11);
			else
				return this.sprite.getSprite(9);

		default:
			if (moveTimer2 == 1)
				return this.sprite.getSprite(0);
			else if (moveTimer2 == 3)
				return this.sprite.getSprite(2);
			else
				return this.sprite.getSprite(1);
		}
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

	public boolean getKeyState(int code) {
		return keyStates[code];
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
		if (this.keyStates[KEY_DOWN] && !this.getHitbox().getSuperCollisionTablOfTheDeadXDPtdr()[AABB.DOWN]) {
			moveTimer += 2 * speed * slow;
			moveDirection = 0;
			this.hitbox.setPosY(this.hitbox.getPosY() + speed * slow);
		}
		if (this.keyStates[KEY_UP] && !this.getHitbox().getSuperCollisionTablOfTheDeadXDPtdr()[AABB.UP]) {
			moveTimer += 2 * speed * slow;
			moveDirection = 0;
			this.hitbox.setPosY(this.hitbox.getPosY() - speed * slow);
		}
		if (this.keyStates[KEY_LEFT] && !this.getHitbox().getSuperCollisionTablOfTheDeadXDPtdr()[AABB.LEFT]) {
			moveTimer += 2 * speed * slow;
			moveDirection = 3;
			this.hitbox.setPosX(this.hitbox.getPosX() - speed * slow);
		}
		if (this.keyStates[KEY_RIGHT] && !this.getHitbox().getSuperCollisionTablOfTheDeadXDPtdr()[AABB.RIGHT]) {
			moveTimer += 2 * speed * slow;
			moveDirection = 1;
			this.hitbox.setPosX(this.hitbox.getPosX() + speed * slow);
		}
		if (this.keyStates[KEY_JUMP] && !(isJumpFalling || isJumping)) {
			jump();
		}
		if (moveTimer > 4)
			moveTimer = 0;
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

	public boolean isJumping() {
		return isJumping;
	}

	public void jump() {
		if (!isJumping()){
			this.jump_time_counter = 0;
			setJumping(true);	
			setJumpSlow(1.2f);
		}
		
		if(this.jump_time_counter >= this.jump_time){
			setJumping(false);
			setJumpingFalling(true);
		}
			
		this.jump_time_counter++;
	}

	public void setJumping(boolean isJumping) {
		this.isJumping = isJumping;
	}

	public float getJumpTimer() {
		return jump_time_counter;
	}

	public int getJumpTime() {
		return jump_time;
	}

	public boolean isJumpFalling() {
		return isJumpFalling;
	}

	public void setJumpingFalling(boolean b) {
		this.isJumpFalling = b;
	}

	public void setJumpSlow(float slow) {
		this.slow = slow;
	}
}
