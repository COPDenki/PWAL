package fr.pwal.graphics.base.graphics.window;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import fr.pwal.graphics.base.control.App_Mouse_Listener;

public class PWAL_Button extends PWAL_Label implements App_Mouse_Listener {

	private int w;
	private int h;

	public PWAL_Button(String text, int x, int y, int w, int h) {
		super(text, x, y);
		this.w = w;
		this.h = h;
	}

	@Override
	public void render_HUD(Graphics g) {
		super.render_HUD(g);
		g.setColor(Color.BLUE);
		g.fillRect(x, y, w, h);
		g.setColor(Color.YELLOW);
		g.drawString(getText(), x, y);
	}

	@Override
	public void onClick(MouseEvent e) {}

	@Override
	public int getPosX() {
		return x;
	}

	@Override
	public int getPosY() {
		return y;
	}

	@Override
	public int getWidth() {
		return w;
	}

	@Override
	public int getHeight() {
		return h;
	}
}
