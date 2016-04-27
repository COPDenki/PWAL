package fr.pwal.level;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

import fr.pwal.graphics.base.graphics.window.App_Component;

public class LevelChain extends App_Component {

	private Level[] levels;
	private String name;
	private Player[] players;

	private int id; // Id du level actuel.

	public LevelChain(Level[] levels, String name, Player[] players) {
		this.levels = levels;
		this.name = name;
		this.players = players;
		for (int i = 0; i < levels.length; i++) {
			levels[i].setLevelChain(this);
		}
		changeLevel(0);
	}

	public void changeLevel(int idLevel) {
		this.id = idLevel;
		for (int i = 0; i < players.length; i++) {
			this.players[i].setPosX(getLevel(idLevel).getStartPosX());
			this.players[i].setPosY(getLevel(idLevel).getStartPosY());
		}

	}

	public String getName() {
		return this.name;
	}

	public Level getLevel(int id) {
		return this.levels[id];
	}

	@Override
	public void render_IG(Graphics g, float scale) {
		getLevel(id).render_IG(g, scale);
		for (int i = 0; i < players.length; i++) {
			Player p = players[i];
			if (p.getLife() > 0)
				g.drawImage(p.getSprite(), (int) (p.getPosX() * 16 * scale), (int) (p.getPosY() * 16 * scale),
						(int) (p.getHitbox().getWidth() * 16 * scale), (int) (p.getHitbox().getHeight() * 16 * scale),
						null);
		}
	}

	@Override
	public void render_HUD(Graphics g) {
		for (int i = 0; i < players.length; i++) {
			Player p = players[i];
			g.setColor(Color.BLACK);
			g.fillRect(8, (i + 1) * 20 - 2, 150, 19);
			g.setColor(p.getColor());
			g.drawString(p.getName() + "(" + p.getDeathCounter() + ")", 120, (i + 2) * 20 - 8);
			g.setColor(Color.GREEN);
			g.fillRect(10, (i + 1) * 20, ((p.getLife()) / p.getMaxLife()) * 100, 15);
		}
	}

	public void update() {
		getLevel(id).update(getPlayers());
	}

	public Player[] getPlayers() {
		return players;
	}

	public void nextLevel() {
		if (levels.length != id + 1)
			this.changeLevel(id + 1);
		else {
			JOptionPane.showMessageDialog(null, "Game over !!!");
			System.exit(0);
		}
	}
}
