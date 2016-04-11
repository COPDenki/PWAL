package fr.pwal.graphics.base.control;

import java.awt.event.MouseEvent;

public interface App_Mouse_Listener {

	public abstract void onClick(MouseEvent e);

	public abstract int getPosX();

	public abstract int getPosY();

	public abstract int getWidth();

	public abstract int getHeight();
}
