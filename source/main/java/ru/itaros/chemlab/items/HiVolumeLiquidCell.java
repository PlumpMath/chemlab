package ru.itaros.chemlab.items;

import java.util.Hashtable;
import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.IFluidHandler;
import ru.itaros.chemlab.ChemLabCreativeTab;
import ru.itaros.chemlab.addon.cl3.userspace.CL3AddonLoader;
import ru.itaros.hoe.fluid.HOEFluid;
import ru.itaros.hoe.itemhandling.IUniversalStack;
import ru.itaros.hoe.utils.StackUtility;

public class HiVolumeLiquidCell extends ChemLabItem {
	
	private static Hashtable<HOEFluid,ItemStack> registered = new Hashtable<HOEFluid,ItemStack>(); 
	
	private HVLCIndex[] index;
	
	public HiVolumeLiquidCell(HVLCIndex...indexes){
		super("hvlc","cell");
		this.index=indexes;
		this.setCreativeTab(ChemLabCreativeTab.getInstance());
		
		this.setNoRepair();
		this.hasSubtypes=true;
		
		for(int i = 0; i<index.length;i++){
			registered.put(index[i].getFluid(), new ItemStack(this,1,i));
		}
	}
	
	public HOEFluid getFluid(ItemStack stack){
		return index[stack.getItemDamage()].getFluid();
	}
	
	public HOEFluid getFluid(IUniversalStack stack) {
		return getFluid((ItemStack) stack.getProxy());
	}	
	
	public static ItemStack getByFluid(HOEFluid fluid){
		return registered.get(fluid).copy();
	}

	CL3AddonLoader invoker;
	
	@Override
	public void setIcon(CL3AddonLoader invoker) {
		this.invoker=invoker;
	}

	@Override
	public void registerIcons(IIconRegister p_94581_1_) {
		for(HVLCIndex i:index){
			i.registerIcons(p_94581_1_,invoker);
		}
	}
	
	@Override
	public IIcon getIconFromDamage(int meta) {
		return index[meta].getIcon();
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return index[stack.getItemDamage()].getUnlocalizedName();
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tabs,
			List list) {
		for(int i = 0; i<index.length;i++){
			HVLCIndex ind = index[i];
			ItemStack local = new ItemStack(this,1,i);
			list.add(local);
		}
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player,
			World world, int x, int y, int z,
			int side, float px, float py,
			float pz) {
		
		TileEntity e = world.getTileEntity(x, y, z);
		if(e==null){return false;}
		
		if(e instanceof IFluidHandler){
			IFluidHandler tank = (IFluidHandler)e;
			ForgeDirection dir = ForgeDirection.getOrientation(side);
			HVLCIndex i = index[stack.getItemDamage()];
			if(tank.canFill(dir, i.getFluid().getForgeFluid())){
				int o = tank.fill(dir,i.getCachedForgeFluidStack(), true);
				if(o>0){
					stack.stackSize--;
					stack = StackUtility.verify(stack);
				}
				return true;
			}
		}
		return false;
	}
	

	
	
}
