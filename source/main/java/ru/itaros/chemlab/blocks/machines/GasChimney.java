package ru.itaros.chemlab.blocks.machines;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import ru.itaros.chemlab.ChemLab;
import ru.itaros.chemlab.client.fx.ChimneySmoke;
import ru.itaros.chemlab.client.ui.common.HOEContainer;
import ru.itaros.chemlab.client.ui.special.GasChimneyContainer;
import ru.itaros.chemlab.convenience.ChemLabCreativeTab;
import ru.itaros.chemlab.minecraft.tileentity.GasChimneyTileEntity;
import ru.itaros.toolkit.hoe.facilities.client.textures.MetaIconFolder;
import ru.itaros.toolkit.hoe.machines.basic.io.minecraft.blocks.IOMachineBlock;
import ru.itaros.toolkit.hoe.machines.basic.io.minecraft.tileentity.MachineTileEntity;
import ru.itaros.toolkit.hoe.machines.interfaces.tileentity.ITileEntityParticleManager;

public class GasChimney extends IOMachineBlock {

	public GasChimney() {
		super(Material.iron);
		this.setBlockNameRaw("machine."+"gaschimney");
		this.setCreativeTab(ChemLabCreativeTab.getInstance());
	}

	@Override
	protected int getUIID() {
		return HOEContainer.getID(GasChimneyContainer.class);
	}

	@Override
	protected Object getOwnerMod() {
		return ChemLab.getInstance();
	}

	@Override
	protected MachineTileEntity getNewTileEntity() {
		return new GasChimneyTileEntity();
	}

	private static final int METADATA_VARIATIONS = 1;	
	//Graphics
	

	@Override
	public void registerBlockIcons(IIconRegister reg) {
		icons = new MetaIconFolder(METADATA_VARIATIONS);
		icons.Register(0, "chemlab", new String[]{"machine_gaschimney_bottom","machine_gaschimney_top","machine_gaschimney_side","machine_gaschimney_side","machine_gaschimney_side","machine_gaschimney_side"}, reg);
	}

	@Override
	public void randomDisplayTick(World w, int x,
			int y, int z, Random random) {
		if(ChemLab.getConfig().gfx_gasChimneyFX){
			TileEntity te = w.getTileEntity(x, y, z);
			if(te==null){return;}
			if(te instanceof ITileEntityParticleManager){
				ITileEntityParticleManager pm = (ITileEntityParticleManager)te;
				pm.pushParticlesIntoRenderer();
			}
		}
		
	}

	


	
	
	
	
	
	
}
