package ru.itaros.chemlab.loader;

import ru.itaros.chemlab.blocks.multiblock.MBDefinition;
import ru.itaros.chemlab.blocks.multiblock.MBDefinitionArcFurnace;

public class MultiblockLoader {

	public static MBDefinitionArcFurnace arcFurnace;
	
	public static void load(){
		
		arcFurnace = new MBDefinitionArcFurnace();
		
	}
}
