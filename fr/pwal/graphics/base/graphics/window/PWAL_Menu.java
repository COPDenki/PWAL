package fr.pwal.graphics.base.graphics.window;

import java.awt.Graphics;
import java.util.Vector;

public class PWAL_Menu implements App_Component{
	
	private Vector<App_Component> components = new Vector<App_Component>();
	
	
	public PWAL_Menu() {
	}

	@Override
	public void drawIG(Graphics g, float scale) {	}

	@Override
	public void drawHUD(Graphics g) {
		
	}

}
