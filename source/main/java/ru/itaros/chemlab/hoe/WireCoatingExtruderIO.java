package ru.itaros.chemlab.hoe;

import ru.itaros.api.hoe.internal.HOEData;
import ru.itaros.chemlab.loader.recipes.WireCoatingExtruderRecipes;
import ru.itaros.toolkit.hoe.machines.basic.HOEMachineCrafterData;
import ru.itaros.toolkit.hoe.machines.basic.io.HOEMachineCrafterIO;
import ru.itaros.toolkit.hoe.machines.basic.io.minecraft.recipes.RecipesCollection;

public class WireCoatingExtruderIO extends HOEMachineCrafterIO {

	public static final int MAXPOWER = 500;
	public static final int INCOMING_PORTS		=	2;
	public static final int OUTCOMING_PORTS	=	1;
	
	
	@Override
	public RecipesCollection getRecipesCollection() {
		return WireCoatingExtruderRecipes.recipes;
	}	
	
	public WireCoatingExtruderIO(){
		this.setReq(INCOMING_PORTS, OUTCOMING_PORTS);
		this.allowToStart();
	}

	
	@Override
	public void configureData(HOEData data) {
		HOEMachineCrafterData machine=(HOEMachineCrafterData) data;
		machine.setMaxPower(MAXPOWER);
		machine.setDepots(INCOMING_PORTS, OUTCOMING_PORTS);
		machine.setMachine(this);
		machine.setConfigured();
	}

}