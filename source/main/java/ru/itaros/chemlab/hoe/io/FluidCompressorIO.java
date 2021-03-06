package ru.itaros.chemlab.hoe.io;

import ru.itaros.api.hoe.internal.HOEData;
import ru.itaros.chemlab.loader.recipes.FluidCompressorRecipes;
import ru.itaros.hoe.data.machines.HOEMachineCrafterData;
import ru.itaros.hoe.io.HOEMachineCrafterIO;
import ru.itaros.hoe.recipes.RecipesCollection;

public class FluidCompressorIO extends HOEMachineCrafterIO {

	public static final int MAXPOWER = 2000;
	
	
	@Override
	public RecipesCollection getRecipesCollection() {
		return FluidCompressorRecipes.recipes;
	}	
	
	public FluidCompressorIO(){
		this.allowToStart();
	}

	
	@Override
	public void configureData(HOEData data) {
		HOEMachineCrafterData machine=(HOEMachineCrafterData) data;
		machine.setMaxPower(MAXPOWER);
		machine.setMachine(this);
		super.configureData(data);
	}

	@Override
	public long getMeltdownTemperature() {
		return 600L;
	}
	
}
