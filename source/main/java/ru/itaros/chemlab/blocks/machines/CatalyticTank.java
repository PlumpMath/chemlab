package ru.itaros.chemlab.blocks.machines;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.itaros.chemlab.ChemLab;
import ru.itaros.chemlab.ChemLabCreativeTab;
import ru.itaros.chemlab.client.ui.CatalyticTankContainer;
import ru.itaros.chemlab.client.ui.common.HOEContainer;
import ru.itaros.chemlab.loader.ItemLoader;
import ru.itaros.chemlab.tiles.CatalyticTankTileEntity;
import ru.itaros.hoe.blocks.IOMachineBlock;
import ru.itaros.hoe.tiles.MachineTileEntity;
import ru.itaros.hoe.utils.MetaIconFolder;

public class CatalyticTank extends IOMachineBlock {

	@Override
	protected Object getOwnerMod() {
		return ChemLab.getInstance();
	}	
	@Override
	protected int getUIID() {
		return HOEContainer.getID(CatalyticTankContainer.class);
	}	
	

	public CatalyticTank() {
		super(Material.iron);
		this.setBlockNameRaw("machine."+"cattank");
		this.setCreativeTab(ChemLabCreativeTab.getInstance());
		//this.setTickRandomly(true);
	}


	@Override
	protected MachineTileEntity getNewTileEntity() {
		return new CatalyticTankTileEntity();
	}


	private static final int METADATA_VARIATIONS = 1;	
	//Graphics
	

	@Override
	public void registerBlockIcons(IIconRegister reg) {
		super.registerBlockIcons(reg, "chemlab");
		icons = new MetaIconFolder(METADATA_VARIATIONS);
		icons.Register(0, "chemlab", new String[]{"machine_base","machine_base","machine_base","machine_cattank_face","machine_base","machine_base"}, reg);
	}
	
	
	
	//SPECIAL
	
//	@Override
//	public boolean onBlockActivated(World world, int x, int y, int z,
//			EntityPlayer entplayer, int unknown, float px, float py, float pz) {
//		
//		ItemStack current = entplayer.inventory.getCurrentItem();
//		if(current!=null ){
//			if(current.getItem()==ItemLoader.platinum_catalization_grid){
//				TileEntity te = world.getTileEntity(x, y, z);
//				CatalyticTankTileEntity dete = (CatalyticTankTileEntity)te;
//				ItemStack rslt;
//				
//				rslt=dete.exchangeCatalyzer(current);
//
//				if(rslt==null){
//					current.stackSize=0;
//				}else{
//					current.setItemDamage(rslt.getItemDamage());
//				}
//				return true;
//			}else{
//				//GUI and stuff
//				return super.onBlockActivated(world, x, y, z, entplayer, unknown, px, py, pz);
//			}
//		}else{
//			//GUI and stuff
//			return super.onBlockActivated(world, x, y, z, entplayer, unknown, px, py, pz);
//		}
//		
//		
//		
//	}
	
	

}
