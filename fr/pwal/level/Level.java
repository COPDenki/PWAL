package fr.pwal.level;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import fr.pwal.base.physic.AABB;
import fr.pwal.base.physic.BlockEffect;
import fr.pwal.base.physic.Gravity;
import fr.pwal.graphics.base.graphics.window.App_Component;

public class Level extends App_Component {

	private char[][] blocksIds;

	private int width, height;
	private int startPosX, startPosY;
	private int endPosX, endPosY;

	private LevelChain levelChain;

	private Block[] blocks;

	private Gravity gravity;

	public Level(Gravity gravity, String path, Block[] blocks) {
		this.gravity = gravity;
		this.blocks = blocks;
		try { // On essaie de ...
			BufferedReader brLvl = new BufferedReader(
					new InputStreamReader(getClass().getResourceAsStream(path + ".pwal")));// ...
																							// lire
																							// le
																							// fichier.
			BufferedReader brProp = new BufferedReader(
					new InputStreamReader(getClass().getResourceAsStream(path + ".p~")));// ...
																							// lire
																							// le
																							// fichier
																							// de
																							// prop.

			String line = " "; // Notre ligne actuelle vaut " ".

			while ((line = brProp.readLine()) != null) { // Ligne = ligne pas
															// encore lue. Si
															// cette ligne n'est
															// pas vide :
				try {
					String[] infos = line.split("=");
					switch (infos[0]) {
					case "width":
						this.width = Integer.parseInt(infos[1]);
						break;
					case "height":
						this.height = Integer.parseInt(infos[1]);
						break;
					case "inX":
						this.startPosX = Integer.parseInt(infos[1]);
						break;
					case "inY":
						this.startPosY = Integer.parseInt(infos[1]);
						break;
					case "outX":
						this.endPosX = Integer.parseInt(infos[1]);
						break;
					case "outY":
						this.endPosY = Integer.parseInt(infos[1]);
						break;
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}

			}

			blocksIds = new char[height][width];

			line = " ";

			int i = 0;
			while ((line = brLvl.readLine()) != null) {
				blocksIds[i] = line.toCharArray();
				i++;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void render(Graphics g, float scale) {
		this.render_IG(g, scale);
		this.render_HUD(g);
	}

	public void update(Player[] players) {
		for (int i = 0; i < players.length; i++) {
			Player p = players[i];
			if (p.getLife() > 0) {
				if (p.isJumping()) {
					p.jump();
					p.getHitbox()
							.setPosY(p.getHitbox().getPosY() - (getGravity().getIntensity()
									* ((p.getJumpTime() - p.getJumpTimer()) * (p.getJumpTime() - p.getJumpTimer()))
									* 0.000023f
									* p.getSlow()));
				} else if (p.isJumpFalling() && p.getHitbox().getSuperCollisionTablOfTheDeadXDPtdr()[AABB.DOWN]) {
					p.setJumpingFalling(false);
					p.resetJumpCounter();
					p.setJumpSlow(1);
				} else {
					getGravity().appliedOn(p);
				}
				int x = (int) (p.getPosX());
				int y = (int) (p.getPosY());
				int xW = (int) (p.getPosX() + p.getHitbox().getWidth());
				int yH = (int) (p.getPosY() + p.getHitbox().getHeight());

				if ((x == getEndPosX() || xW == getEndPosX()) && (y == getEndPosY() || yH == getEndPosY()))
					levelChain.nextLevel();

				try {
					p.getHitbox().setSuperCollisionTablOfTheDeadXDPtdr(AABB.DOWN,
							(getBlockAt(x, y + 1).getIsHard() || ((p.getPosX() + p.getHitbox().getWidth() - xW) >= 0.01f
									&& getBlockAt(xW, y + 1).getIsHard())));
				} catch (ArrayIndexOutOfBoundsException e) {
				}

				try {
					p.getHitbox().setSuperCollisionTablOfTheDeadXDPtdr(AABB.UP,
							(getBlockAt(x, y).getIsHard() || ((p.getPosX() + p.getHitbox().getWidth() - xW) >= 0.01f
									&& getBlockAt(xW, y).getIsHard())));
				} catch (ArrayIndexOutOfBoundsException e) {
				}

				try {
					p.getHitbox().setSuperCollisionTablOfTheDeadXDPtdr(AABB.LEFT,
							(p.getPosX() <= 0 || getBlockAt(x, y).getIsHard()
									|| ((p.getPosY() + p.getHitbox().getHeight() - yH) >= 0.01f
											&& getBlockAt(x, yH).getIsHard())));
				} catch (ArrayIndexOutOfBoundsException e) {
				}

				try {
					p.getHitbox().setSuperCollisionTablOfTheDeadXDPtdr(AABB.RIGHT,
							(p.getPosX() + 1 >= width || getBlockAt(x + 1, y).getIsHard()
									|| ((p.getPosY() + p.getHitbox().getHeight() - yH) >= 0.01f
											&& getBlockAt(x + 1, yH).getIsHard())));
				} catch (ArrayIndexOutOfBoundsException e) {
				}

				for (int j = -5; j <= 5; j++) {
					for (int k = -5; k <= 5; k++) {
						int x2 = x + k, y2 = y + j;
						try {
							if (getBlockAt(x2, y2) instanceof BlockEffect)
								((BlockEffect) getBlockAt(x2, y2)).doSpecialEffect(p, x2, y2);
						} catch (Exception e) {
						}
					}
				}
				p.move();
			} else {
				p.setDeathTimer(p.getDeathTimer()+1);
				if(p.getDeathTimer() >= 2000) {
					p.setLife(p.getMaxLife());
					p.setDeathTimer(0);
					p.setPosX(getStartPosX());
					p.setPosY(getStartPosY());
				}
			}
		}
	}

	public Block getBlockAt(int x, int y) {
		x = Math.abs(x);
		y = Math.abs(y);
		for (int i = 0; i < blocks.length; i++)
			if (blocks[i].getId() == blocksIds[y][x])
				return blocks[i];
		return blocks[0];
	}

	@Override
	public void render_IG(Graphics g, float scale) {
		for (int y = 0; y < blocksIds.length; y++) {
			for (int x = 0; x < blocksIds[y].length; x++) {
				g.drawImage(getBlockAt(x, y).getSprite().getTexure(), (int) (x * 16 * scale), (int) (y * 16 * scale),
						(int) (16 * scale), (int) (16 * scale), null);
			}
		}

	}

	public int getStartPosX() {
		return this.startPosX;
	}

	public int getStartPosY() {
		return this.startPosY;
	}

	public int getEndPosX() {
		return this.endPosX;
	}

	public int getEndPosY() {
		return this.endPosY;
	}

	public Gravity getGravity() {
		return this.gravity;

	}

	public void setLevelChain(LevelChain levelChain) {
		this.levelChain = levelChain;
	}
}
