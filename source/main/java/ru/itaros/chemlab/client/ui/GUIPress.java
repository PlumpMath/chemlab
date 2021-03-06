package ru.itaros.chemlab.client.ui;

import net.minecraft.entity.player.InventoryPlayer;
import ru.itaros.chemlab.client.ui.common.GUIHOEClassicalMachine;
import ru.itaros.chemlab.tiles.PressTileEntity;
import ru.itaros.hoe.tiles.MachineTileEntity;


public class GUIPress extends GUIHOEClassicalMachine {
	public GUIPress(InventoryPlayer playerInv, MachineTileEntity tile){
		this(playerInv,(PressTileEntity)tile);
	}
	private GUIPress(InventoryPlayer playerInv, PressTileEntity tile) {
		super(new PressContainer(playerInv,tile));
		this.playerInv=playerInv;
		this.tile=tile;
	}
	
	@Override
	public String getMachineUnlocalizedName() {
		return "Press";
	}	
}
