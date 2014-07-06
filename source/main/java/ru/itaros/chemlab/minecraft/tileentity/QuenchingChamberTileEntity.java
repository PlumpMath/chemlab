package ru.itaros.chemlab.minecraft.tileentity;

import ru.itaros.chemlab.ChemLab;
import ru.itaros.chemlab.HOELinker;
import ru.itaros.hoe.vanilla.tiles.MachineCrafterTileEntity;
import ru.itaros.toolkit.hoe.machines.basic.io.HOEMachineCrafterIO;
import ru.itaros.toolkit.hoe.machines.basic.io.HOEMachineIO;

public class QuenchingChamberTileEntity extends MachineCrafterTileEntity {

	@Override
	public HOELinker getLinker() {
		return ChemLab.proxy.getLinker();
	}

	@Override
	public HOEMachineIO getSuperIO() {
		return (HOEMachineCrafterIO) ChemLab.getIOCollection().getHOEIO("QuenchingChamberIO");
	}

}