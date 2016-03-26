package fr.pwal.graphics.base.graphics.window;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class PWAL_Picture implements App_Component {

	private BufferedImage picture;
	private int x, y;

	public PWAL_Picture(BufferedImage img, int x, int y) {
		this.picture = img;
	}

	@Override
	public void drawHUD(Graphics g) {
		g.drawImage(picture, x, y, null);
	}
	
	@Override
	public void drawIG(Graphics g, float scale) {
	}
}
