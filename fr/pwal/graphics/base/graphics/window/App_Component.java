package fr.pwal.graphics.base.graphics.window;

import java.awt.Graphics;

public interface App_Component {

	public abstract void drawIG(Graphics g, float scale);

	public abstract void drawHUD(Graphics g);
}
