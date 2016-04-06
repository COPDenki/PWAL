package fr.pwal.graphics.base.graphics.level;

import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CharacterSprite {

	private Image[] texture = new Image[12];

	private final int SIZE = 16;

	public CharacterSprite(String path) {
		BufferedImage spriteSheet;
		try {
			spriteSheet = ImageIO.read(getClass().getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
			spriteSheet = new BufferedImage(SIZE, SIZE, BufferedImage.TYPE_INT_ARGB);
		}
		
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++){
				this.texture[i * 3 + j] = spriteSheet.getSubimage(j*16, i*16, 16, 16);
				System.out.println(i*3+j);
			}

	
		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(-16, 0);
		this.texture[9] =  new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR).filter((BufferedImage) texture[3], null);
		this.texture[10] = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR).filter((BufferedImage) texture[4], null);
		this.texture[11] = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR).filter((BufferedImage) texture[5], null);
		
		
	}

	public Image getSprite(int code) {
		return this.texture[code];
	}

}
