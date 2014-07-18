package ru.itaros.chemlab.client.ui.special;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import ru.itaros.chemlab.client.ui.common.GUIHOEClassicalMachine;
import ru.itaros.chemlab.client.ui.common.HOEContainer;
import ru.itaros.hoe.vanilla.tiles.MachineTileEntity;
import ru.itaros.toolkit.hoe.machines.basic.io.minecraft.gui.HOESlotType;
import ru.itaros.toolkit.hoe.machines.basic.io.minecraft.gui.MachineSlot;
import ru.itaros.toolkit.hoe.machines.basic.io.minecraft.gui.ReadonlySlot;

public class HVLCFillerContainer extends HOEContainer {

	public HVLCFillerContainer(InventoryPlayer playerInv,
			MachineTileEntity tile) {
		super(playerInv,tile);
	}

	
	
	public static Class<? extends GUIHOEClassicalMachine> getGUIType() {
		return GUIHVLCFiller.class;
	}


	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		return true;
	}


	@Override
	public void bindSlots() {
		INBOUND=new MachineSlot((IInventory) tile,0,17,17,HOESlotType.INPUT);
		OUTBOUND=new MachineSlot((IInventory) tile,1,17,54,HOESlotType.OUTPUT);
		
		addSlotToContainer(INBOUND);//INBOUND
		addSlotToContainer(OUTBOUND);//OUTBOUND
		
		addSlotToContainer(new ReadonlySlot((IInventory) tile,-1,48+32,37));
		addSlotToContainer(new ReadonlySlot((IInventory) tile,-2,112,37));//OUTBOUND 1
		
	}		
	
}
