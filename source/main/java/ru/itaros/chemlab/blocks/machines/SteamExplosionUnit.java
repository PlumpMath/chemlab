package ru.itaros.chemlab.blocks.machines;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import ru.itaros.chemlab.ChemLab;
import ru.itaros.chemlab.client.ui.SteamBoilerContainer;
import ru.itaros.chemlab.client.ui.SteamExplosionUnitContainer;
import ru.itaros.chemlab.convenience.ChemLabCreativeTab;
import ru.itaros.chemlab.minecraft.tileentity.SteamBoilerTileEntity;
import ru.itaros.chemlab.minecraft.tileentity.SteamExplosionUnitTileEntity;
import ru.itaros.toolkit.hoe.facilities.client.textures.MetaIconFolder;
import ru.itaros.toolkit.hoe.machines.basic.io.minecraft.blocks.IOMachineBlock;
import ru.itaros.toolkit.hoe.machines.basic.io.minecraft.tileentity.MachineTileEntity;

public class SteamExplosionUnit extends IOMachineBlock {
	@Override
	protected Object getOwnerMod() {
		return ChemLab.getInstance();
	}	
	@Override
	protected int getUIID() {
		return SteamExplosionUnitContainer.ID;
	}	
	

	public SteamExplosionUnit() {
		super(Material.iron);
		this.setBlockNameRaw("chemlab:machinesteamexplosionunit");
		this.setCreativeTab(ChemLabCreativeTab.getInstance());
		//this.setTickRandomly(true);
	}


	@Override
	protected MachineTileEntity getNewTileEntity() {
		return new SteamExplosionUnitTileEntity();
	}


	private static final int METADATA_VARIATIONS = 1;	
	//Graphics
	
	MetaIconFolder icons;
	
	@Override
	public IIcon getIcon(int side, int meta) {
		return icons.GetIcon(side, meta);
	}
	@Override
	public void registerBlockIcons(IIconRegister reg) {
		icons = new MetaIconFolder(METADATA_VARIATIONS);
		icons.Register(0, "chemlab", new String[]{"machine_part_chambernozzle","machine_base","machine_base","machine_steamexplosionunit_face","machine_base","machine_base"}, reg);
	}

}