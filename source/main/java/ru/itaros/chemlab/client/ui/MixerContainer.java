package ru.itaros.chemlab.client.ui;

import net.minecraft.entity.player.InventoryPlayer;
import ru.itaros.chemlab.client.ui.common.GUIHOEClassicalMachine;
import ru.itaros.chemlab.client.ui.common.HOECrafterContainer;
import ru.itaros.hoe.vanilla.tiles.MachineTileEntity;

public class MixerContainer extends HOECrafterContainer {
	public MixerContainer(InventoryPlayer playerInv,
			MachineTileEntity tile) {
		super(playerInv, tile);
	}

	
	
	public static Class<? extends GUIHOEClassicalMachine> getGUIType() {
		return GUIMixer.class;
	}
}
