package ru.itaros.chemlab.client.ui;

import net.minecraft.entity.player.InventoryPlayer;
import ru.itaros.chemlab.client.ui.common.GUIHOEClassicalMachine;
import ru.itaros.chemlab.tiles.CentrifugalExtractorTileEntity;
import ru.itaros.hoe.tiles.MachineTileEntity;

public class GUICentrifugalExtractor extends GUIHOEClassicalMachine {

	
	public GUICentrifugalExtractor(InventoryPlayer playerInv, MachineTileEntity tile){
		this(playerInv,(CentrifugalExtractorTileEntity)tile);
	}
	private GUICentrifugalExtractor(InventoryPlayer playerInv, CentrifugalExtractorTileEntity tile) {
		super(new CentrifugalExtractorContainer(playerInv,tile));
		this.playerInv=playerInv;
		this.tile=tile;
	}	
	
	@Override
	public String getMachineUnlocalizedName() {
		return "Centrifugal Extractor";
	}

}
