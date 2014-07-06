package ru.itaros.toolkit.hoe.machines.basic.io;

import ru.itaros.api.hoe.internal.HOEData;
import ru.itaros.toolkit.hoe.machines.basic.HOEMachineCrafterData;
import ru.itaros.toolkit.hoe.machines.basic.io.minecraft.recipes.RecipesCollection;


public abstract class HOEMachineCrafterIO extends HOEMachineIO{

	
	public abstract RecipesCollection getRecipesCollection();

	@Override
	public abstract void configureData(HOEData data);


	@Override
	protected void produce(HOEData data, boolean doReal){
		HOEMachineCrafterData hm = (HOEMachineCrafterData) data;
		//hm.shutCycleOff();//Cycled thought
		hm.produce(doReal);
	}	
	
	protected boolean isMachineActive(HOEData data){
		//if(data==null){return false;}//SOMETHING REALLY WRONG
		HOEMachineCrafterData machine = (HOEMachineCrafterData)data;
		if(!machine.hasWork()){return false;}
			
		//if(!machine.isRecipeSet){return false;}
		//if(!machine.checkStorage()){return false;}
		
		//power
		if(machine.useEnergy()){
			return true;
		}else{
			return false;
		}
	}
	
}