package ru.itaros.toolkit.hoe.machines.basic.io.minecraft.recipes;

public class RecipesCollection {
	
	protected int incomingReq,outcomingReq;
	
	public final int getIncomingReq() {
		return incomingReq;
	}

	public final int getOutcomingReq() {
		return outcomingReq;
	}

	public RecipesCollection(Recipe... recipes){
		this.recipes=recipes;
		recalcRequirments();
	}
	
	private void recalcRequirments() {
		for(Recipe r:recipes){
			int curI=r.getIncomingSlots();
			int curO=r.getOutcomingSlots();
			if(curI>incomingReq){incomingReq=curI;}
			if(curO>outcomingReq){outcomingReq=curO;}
		}
		
	}

	protected Recipe[] recipes;
}
