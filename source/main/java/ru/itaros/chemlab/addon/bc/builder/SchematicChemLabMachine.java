package ru.itaros.chemlab.addon.bc.builder;

import java.util.LinkedList;

import ru.itaros.chemlab.addon.bc.builder.HOENBTManifold.ManifoldFilter;
import ru.itaros.chemlab.loader.BlockLoader;
import ru.itaros.chemlab.tiles.syndication.SyndicationHubTileEntity;
import ru.itaros.hoe.blocks.IRotatableBlock;
import ru.itaros.hoe.blocks.RotatableBlockUtility;
import ru.itaros.hoe.tiles.MachineTileEntity;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import buildcraft.api.blueprints.BuildingPermission;
import buildcraft.api.blueprints.IBuilderContext;
import buildcraft.api.blueprints.MappingNotFoundException;
import buildcraft.api.blueprints.MappingRegistry;
import buildcraft.api.blueprints.SchematicRegistry;
import buildcraft.api.blueprints.SchematicTile;

public class SchematicChemLabMachine extends SchematicTile {

	public SchematicChemLabMachine(){}
	
	private HOENBTManifold manifold;
	
	private boolean isSyndicated=false;
	
	@Override
	public void writeToBlueprint(IBuilderContext context, int x, int y,
			int z) {

		MachineTileEntity tile = (MachineTileEntity)context.world().getTileEntity(x, y, z);

		if (tile != null) {
			manifold = tile.writeBlueprintNBT();
		}
	
	}

	@Override
	public void writeToWorld(IBuilderContext context, int x, int y,
			int z, LinkedList<ItemStack> stacks) {
		//super.writeToWorld(context, x, y, z, stacks);

		context.world().setBlock(x, y, z, block, meta, 3);
		context.world().setBlockMetadataWithNotify(x, y, z, meta, 3);
		
		if (block.hasTileEntity(meta)) {
			MachineTileEntity tile = (MachineTileEntity) context.world().getTileEntity(x, y, z);
	
//			if(tile==null){
//				tile=(MachineTileEntity) block.createTileEntity(context.world(), 0);
			//To ensure correct HOEFingerprint Data
			//TODO: shouldn't do that. It should be in manifolded postloader
			tile.setWorldObj(context.world());
			tile.xCoord=x;
			tile.yCoord=y;
			tile.zCoord=z;
			tile.onPostLoad();
//			}
			
			//if (tile != null) {
			//Tile data should be accessed imperatively
			tile.readBlueprintNBT(manifold);
			//}
		}		
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt, MappingRegistry registry) {
		nbt.setInteger("blockId", registry.getIdForBlock(block));
		nbt.setInteger("blockMeta", meta);
		
		manifold.filter(ManifoldFilter.BCSCHEMATICS,(block != BlockLoader.syndicationhub));
		manifold.mergeInto(nbt);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt, MappingRegistry registry) {
		try {
			block = registry.getBlockForId(nbt.getInteger("blockId"));
		} catch (MappingNotFoundException e) {
			defaultPermission = BuildingPermission.CREATIVE_ONLY;
			return;
		}
		
		meta = nbt.getInteger("blockMeta");
		
		manifold = HOENBTManifold.deploy(nbt);
		if(block == BlockLoader.syndicationhub)
		{
			NBTTagCompound syndicationData=manifold.holdSyndication();
			isSyndicated=syndicationData.getBoolean("isSyndicated");
			manifold.filter(ManifoldFilter.BCSCHEMATICS,true);
		}
	}	

	
	@Override
	public void writeRequirementsToBlueprint(IBuilderContext context, int x,
			int y, int z) {
		
	}
	
	

	@Override
	public void writeRequirementsToWorld(IBuilderContext arg0,
			LinkedList<ItemStack> requirements) {
		requirements.add(new ItemStack(block, 1));
	}

	@Override
	public void postProcessing(IBuilderContext context, int x, int y, int z) {
		super.postProcessing(context, x, y, z);
		if(isSyndicated){
			MachineTileEntity tile = (MachineTileEntity) context.world().getTileEntity(x, y, z);
			if(tile instanceof SyndicationHubTileEntity){
				SyndicationHubTileEntity t2 = (SyndicationHubTileEntity)tile;
				t2.engageSyndicationInspection();
			}
		}
	}

	public static void init(Block...blocks){
		for(Block b:blocks){
			SchematicRegistry.registerSchematicBlock(b, SchematicChemLabMachine.class);
		}
	}

	@Override
	public void rotateLeft(IBuilderContext context) {
		IRotatableBlock irb = (IRotatableBlock)block;
		ForgeDirection[] rotChain = irb.getRotationChain();
		meta = RotatableBlockUtility.calculateSpinIncrement(meta,rotChain.length);
	}	
	
	
	
	
}