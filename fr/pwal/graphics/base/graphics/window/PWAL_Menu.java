package fr.pwal.graphics.base.graphics.window;

import java.awt.event.MouseEvent;
import java.util.Vector;

import fr.pwal.graphics.base.control.App_Mouse_Listener;

public class PWAL_Menu extends App_Component {

	private Vector<App_Component> components = new Vector<App_Component>();

	public PWAL_Menu() {
		add(new PWAL_Button("Launch", 40, 50, 200, 50));
	}

	public void add(App_Component component) {
		this.components.addElement(component);
	}

	public void onClick(MouseEvent e) {
		for (int i = 0; i < components.size(); i++) {
			App_Component component = components.get(i);
			if (component instanceof App_Mouse_Listener) {
				App_Mouse_Listener componentLister = (App_Mouse_Listener) component;

				if (e.getX() >= componentLister.getPosX() && e.getY() >= componentLister.getPosY() && e.getX() <= componentLister.getPosX() + componentLister.getWidth() && e.getY() <= componentLister.getPosY() + componentLister.getHeight()) {

				}
			}
		}
	}
}
