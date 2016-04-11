package fr.pwal.graphics.base.graphics.window;

import java.awt.Color;
import java.awt.Graphics;

public class PWAL_Label extends App_Component {
	
	private String text;
	protected int x;
	protected int y;
	private Color color = Color.BLACK;
	
	public PWAL_Label(String text, int x, int y) {
		setText(text);
		this.x = x;
		this.y = y;
	}

	@Override
	public void render_HUD(Graphics g) {
		g.setColor(color);
		g.drawString(text, x, y);
	}
	
	public PWAL_Label setText(String text){
		this.text = text;
		return this;
	}
	
	public String getText(){
		return this.text;
	}
	
	public PWAL_Label setColor(Color color){
		this.color = color;
		return this;
	}

}
