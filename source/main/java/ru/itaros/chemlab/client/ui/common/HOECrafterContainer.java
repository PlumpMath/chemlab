package ru.itaros.chemlab.client.ui.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import ru.itaros.hoe.vanilla.tiles.MachineCrafterTileEntity;
import ru.itaros.hoe.vanilla.tiles.MachineTileEntity;
import ru.itaros.toolkit.hoe.machines.basic.HOEMachineData;
import ru.itaros.toolkit.hoe.machines.basic.io.minecraft.gui.HOESlotType;
import ru.itaros.toolkit.hoe.machines.basic.io.minecraft.gui.MachineSlot;
import ru.itaros.toolkit.hoe.machines.basic.io.minecraft.gui.ProgrammerSlot;
import ru.itaros.toolkit.hoe.machines.basic.io.minecraft.gui.ReadonlySlot;
import ru.itaros.toolkit.hoe.machines.interfaces.ISynchroportItems;

public class HOECrafterContainer extends HOEContainer {

	public HOECrafterContainer(InventoryPlayer playerInv, MachineTileEntity tile) {
		super(playerInv, tile);
	}

	@Override
	public void bindSlots() {
		MachineCrafterTileEntity crafter = (MachineCrafterTileEntity)tile;
		
		INBOUND=new MachineSlot(crafter,0,16,17,HOESlotType.INPUT);
		OUTBOUND=new MachineSlot(crafter,1,16,55,HOESlotType.OUTPUT);
		
		addSlotToContainer(INBOUND);//INBOUND
		addSlotToContainer(OUTBOUND);//OUTBOUND
		
		//Adding inbounds slots
		int stepping = 19;
		int y_start = 36-stepping;
		for(int i = 0 ; i < 3 ; i++){
			addSlotToContainer(new ReadonlySlot(crafter,-1-i,48,y_start+(stepping*i)).setType(HOESlotType.INPUT));//INBOUND 1
		}
		//Adding outbound slots
		for(int i = 0 ; i < 3 ; i++){		
			addSlotToContainer(new ReadonlySlot(crafter,-11-i,112,y_start+(stepping*i)).setType(HOESlotType.OUTPUT));//OUTBOUND 1
		}
		
		psio = new ProgrammerSlot(crafter,16,29);
		addSlotToContainer(psio);//PROGRAMMER IO

	}

	@Override
	public void detectAndSendChanges() {
		//Polling Data ISynchroport dirty state...
		HOEMachineData data = tile.getServerData();
		if(data!=null && data instanceof ISynchroportItems){
			boolean wasDirty = ((ISynchroportItems)data).pollDirty();
			if(wasDirty){
				tile.markDirty();
			}
		}
		//Default operation
		super.detectAndSendChanges();
	}


}
