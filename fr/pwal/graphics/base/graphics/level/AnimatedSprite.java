package fr.pwal.graphics.base.graphics.level;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import fr.pwal.graphics.base.Application;

public class AnimatedSprite extends Sprite {

	private Image[] textures;

	public AnimatedSprite(int size, String path) {
		super(size);
		try {
			Image temp = ImageIO.read(getClass().getResourceAsStream(path));
			this.textures = new Image[temp.getHeight(null)/this.SIZE];
			for (int i = 0; i < textures.length; i++) {
				this.textures[i] = ((BufferedImage) temp).getSubimage(0, i*this.SIZE, this.SIZE, this.SIZE);
			}
		} catch (IOException e) {
			e.printStackTrace();
			this.textures = new Image[1];
			this.textures[1] = new BufferedImage(SIZE, SIZE, BufferedImage.TYPE_INT_ARGB);
		}
	}
	
	@Override
	public Image getTexure() {
		return this.textures[Application.timer%this.textures.length];
	}

}
