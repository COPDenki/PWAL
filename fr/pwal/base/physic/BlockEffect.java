package fr.pwal.base.physic;

import fr.pwal.level.Player;

public interface BlockEffect {

	public abstract void doSpecialEffect(Player p, int blockPosX, int blockPosY);
}
