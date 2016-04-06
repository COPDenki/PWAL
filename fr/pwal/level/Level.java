package fr.pwal.level;

import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import fr.pwal.graphics.base.graphics.window.App_Component;

public class Level implements App_Component {

	private char[][] blocksIds;

	private int width, height;
	private int startPosX, startPosY;
	private int endPosX, endPosY;
	private Player[] players;

	private Block[] blocks;

	public Level(String path, Block[] blocks, Player[] players) {
		this.blocks = blocks;
		this.players = players;
		try { // On essaie de ...
			BufferedReader brLvl = new BufferedReader(
					new InputStreamReader(getClass().getResourceAsStream(path + "/game.pwal")));// ... lire le fichier.
			BufferedReader brProp = new BufferedReader(
					new InputStreamReader(getClass().getResourceAsStream(path + "/game.p~")));// ... lire le fichier de prop.

			String line = " "; // Notre ligne actuelle vaut " ".

			while ((line = brProp.readLine()) != null) { // Ligne = ligne pas encore lue. Si cette ligne n'est pas vide :
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
			for (int i = 0; i < players.length; i++) {
				this.players[i].setPosX(this.startPosX);
				this.players[i].setPosY(this.startPosY);
			}

			line = " ";

			int i = 0;
			while ((line = brLvl.readLine()) != null) {
				blocksIds[i] = line.toCharArray();
				i++;
			}

		} catch (IOException e) { // Si une erreur est lev�e ...
			e.printStackTrace(); // ... on �crit le rapport d'erreur dans
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
	public void drawIG(Graphics g, float scale) {
		for (int y = 0; y < blocksIds.length; y++) {
			for (int x = 0; x < blocksIds[y].length; x++) {
				g.drawImage(getBlockAt(x, y).getSprite().getTexure(), (int) (x * 16 * scale), (int) (y * 16 * scale), (int) (16 * scale), (int) (16 * scale), null);
			}
		}
		for (int i = 0; i < players.length; i++) {
			Player p = players[i];
			g.drawImage(p.getSprite().getTexure(), (int) (p.getPosX() * 16 * scale), (int) (p.getPosY() * 16 * scale), (int) (16 * scale), (int) (16 * scale), null);
		}
	}

	@Override
	public void drawHUD(Graphics g) {
		for (int i = 0; i < players.length; i++) {
			Player p = players[i];
			g.setColor(Color.BLACK);
			g.fillRect(8, (i + 1) * 20 - 2, 150, 19);
			g.setColor(p.getColor());
			g.drawString(p.getName(), 120, (i + 2) * 20 - 8);
			g.setColor(Color.GREEN);
			g.fillRect(10, (i + 1) * 20, 100, 15);
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

	public Player[] getPlayers() {
		return this.players;
	}
}
