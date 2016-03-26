package fr.pwal.graphics.base.graphics.level;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import fr.pwal.graphics.base.graphics.window.BlockOverlay;

public class Sprite {

	private final int SIZE;
	private Image texture;
	
	public Sprite(int size, String path) {
		this.SIZE = size;
		try {
			this.texture = ImageIO.read(getClass().getResourceAsStream(path)).getScaledInstance(SIZE, SIZE, BufferedImage.SCALE_DEFAULT);
		} catch (IOException e) {
			e.printStackTrace();
			this.texture = new BufferedImage(SIZE, SIZE, BufferedImage.TYPE_INT_ARGB);
		}
	}
	
	public Image getTexure(){
		return texture;
	}
	
	public void addBlockOverlay(BlockOverlay blockOverlay){
		//blockOverlay.setupOverlay(this.texture);
	}
}
