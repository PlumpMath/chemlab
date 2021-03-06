package ru.itaros.chemlab.loader;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import ru.itaros.chemlab.addon.cl3.userspace.CL3AddonLoader;
import ru.itaros.chemlab.items.CIOWrench;
import ru.itaros.chemlab.items.CraftingHammer;
import ru.itaros.chemlab.items.HVLCIndex;
import ru.itaros.chemlab.items.HiVolumeLiquidCell;
import ru.itaros.chemlab.items.HiVolumeLiquidCellEmpty;
import ru.itaros.chemlab.items.ItemPortApplianceItem;
import ru.itaros.chemlab.items.PipeWrench;
import ru.itaros.chemlab.items.Programmer;
import ru.itaros.hoe.fluid.HOEFluid;
import ru.itaros.hoe.tiles.ioconfig.PortType;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemLoader {

	public static Programmer programmer;
	public static PipeWrench wrench;
	public static CIOWrench ciowrench;
	
	public static CraftingHammer craftingHammer;
	
	public static HiVolumeLiquidCellEmpty emptyhvlc;

	
	//general components
	public static ItemPortApplianceItem panel;
	
	//IPAIs
	public static ItemPortApplianceItem ipai_items;
	public static ItemPortApplianceItem ipai_fluids;	
	
	public static void loadItems(CL3AddonLoader addonLoader){
		
		//COMPONENTS
		panel=new ItemPortApplianceItem("component.panel",null);
		GameRegistry.registerItem(panel,panel.getUnlocalizedName());
		
		
		//IPAIs
		ipai_items= new ItemPortApplianceItem("items",PortType.ITEM);
		ipai_fluids= new ItemPortApplianceItem("fluids",PortType.FLUID);
		GameRegistry.registerItem(ipai_items,ipai_items.getUnlocalizedName());
		GameRegistry.registerItem(ipai_fluids,ipai_fluids.getUnlocalizedName());
		
		hiVolumeLiquidCellAutoloader(addonLoader);
		
		fixForgeOreDict();
		
	}
	
	private static void fixForgeOreDict() {
		Block clay = Blocks.clay;
		int[] ids = OreDictionary.getOreIDs(new ItemStack(clay));
		boolean approved=true;
		for(int i : ids){
			if(OreDictionary.getOreName(i).equals("clay")){
				approved=false;
			}
		}
		if(approved){
			OreDictionary.registerOre("clay", clay);
		}
	}

	private static void hiVolumeLiquidCellAutoloader(CL3AddonLoader addonLoader) {
		HOEFluid[] all = HOEFluid.getFluidRegistry().all();
		HVLCIndex[] index = new HVLCIndex[all.length];
		int j = -1;
		for (HOEFluid f : all) {
			j++;
			HVLCIndex i = new HVLCIndex(f);
			index[j] = i;
		}
		HiVolumeLiquidCell hvlc = new HiVolumeLiquidCell(index);
		hvlc.setIcon(addonLoader);
		GameRegistry.registerItem(hvlc, hvlc.getUnlocalizedName());
	}

	public static void loadTools() {
		programmer = new Programmer();
		GameRegistry.registerItem(programmer,programmer.getUnlocalizedName());
		
		wrench = new PipeWrench();
		GameRegistry.registerItem(wrench,wrench.getUnlocalizedName());
		
		ciowrench = new CIOWrench();
		GameRegistry.registerItem(ciowrench,ciowrench.getUnlocalizedName());
		
		emptyhvlc = new HiVolumeLiquidCellEmpty();
		GameRegistry.registerItem(emptyhvlc,emptyhvlc.getUnlocalizedName());
		
		craftingHammer = new CraftingHammer();
		GameRegistry.registerItem(craftingHammer, craftingHammer.getUnlocalizedName());
		
	}	
	

}
