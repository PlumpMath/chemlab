package ru.itaros.chemlab.proxy;

import net.minecraft.client.entity.EntityClientPlayerMP;
import cpw.mods.fml.client.FMLClientHandler;
import ru.itaros.chemlab.HOELinker;
import ru.itaros.chemlab.client.ui.GUIToolProgrammer;
import ru.itaros.toolkit.hoe.machines.basic.io.minecraft.tileentity.MachineTileEntity;

public class Client extends Proxy {

	@Override
	public void initLinker() {
		//NOP
	}

	@Override
	public HOELinker getLinker() {
		//NOP
		return null;
	}

	@Override
	public void openProgrammerGUI(MachineTileEntity tile) {
		EntityClientPlayerMP player = FMLClientHandler.instance().getClientPlayerEntity();
		FMLClientHandler.instance().displayGuiScreen(player, new GUIToolProgrammer(tile));
		
	}

}
