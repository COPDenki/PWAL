package fr.pwal.graphics.base.graphics.window;

import java.awt.Graphics;

import fr.pwal.graphics.base.graphics.HUD_rendering;
import fr.pwal.graphics.base.graphics.IG_Rendering;

public class App_Component implements IG_Rendering, HUD_rendering{
	
	boolean isActive = true;
	
	public boolean isActive(){
		return isActive;
	}
	
	public void setActive(boolean val){
		this.isActive = val;
	}

	@Override
	public void render_HUD(Graphics g) {}

	@Override
	public void render_IG(Graphics g, float scale) {}
}
