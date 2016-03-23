package fr.pwal.level;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import fr.pwal.graphics.base.graphics.window.App_Component;

public class Level implements App_Component{

	private final String path;
	private char[][] blocksIds;

	private int width, height;
	private int startPosX, startPosY;
	private int endPosX, endPosY;
	private Player[] players;
	
	private Block[] blocks;

	public Level(String path, Block[] blocks, Player[] players) {
		this.path = path;
		this.blocks = blocks;
		this.players = players;

		try { // On essaie de ...
			BufferedReader brLvl = new BufferedReader(
					new InputStreamReader(getClass().getResourceAsStream(path + "/game.pwal")));// ...
																								// lire
																								// le
																								// fichier.
			BufferedReader brProp = new BufferedReader(
					new InputStreamReader(getClass().getResourceAsStream(path + "/game.p~")));// ...
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

			
			blocksIds = new char[width][height];
			
			
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

	
	private Block getBlockAt(int x, int y){
		for (int i = 0; i < blocks.length; i++) {
			if (blocks[i].getId() == blocksIds[y][x]) {
//				System.out.println("(" + x + ";" + y + ") = " + blocks[i].getId());
				return blocks[i];
			}
		}
		return blocks[0];
	}

	@Override
	public void draw(Graphics g) {

		for (int y = 0; y < blocksIds.length; y++) {
			for (int x = 0; x < blocksIds[y].length; x++) {
				g.drawImage(getBlockAt(x, y).getSprite().getTexure().getScaledInstance(32, 32, BufferedImage.SCALE_DEFAULT), x*32, y*32, null);
			}
		}
		
		for (int i = 0; i < players.length; i++) {
			Player p = players[i];
			g.drawImage(p.getSprite().getTexure(), p.getPosX()*32, p.getPosY()*32, null);
		}
	}
}
