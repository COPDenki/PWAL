package fr.pwal.graphics.base.control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import fr.pwal.level.Player;

public class Keyboard implements KeyListener{
	
	private Player[] players;
	
	public Keyboard(Player[] players) {
		this.players = players;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		for (int i = 0; i < players.length; i++) {
			for (int j = 0; j < players[i].getKeysCodes().length; j++) {
				if(players[i].getKeysCodes()[j] == e.getKeyCode())
					players[i].setKeyState(j, true);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		for (int i = 0; i < players.length; i++) {
			for (int j = 0; j < players[i].getKeysCodes().length; j++) {
				if(players[i].getKeysCodes()[j] == e.getKeyCode())
					players[i].setKeyState(j, false);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
