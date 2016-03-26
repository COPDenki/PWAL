package fr.pwal.graphics.base.graphics.level;

import java.awt.Color;
import java.awt.Image;

public class BlockOverlay {

	private Color color;

	public BlockOverlay(Color color) {
		this.color = color;
	}

	public void setupOverlay(Image texture) {
		texture.getGraphics().setColor(color);
		texture.getGraphics().drawRect(0, 0, 16, 16);
	}

}
