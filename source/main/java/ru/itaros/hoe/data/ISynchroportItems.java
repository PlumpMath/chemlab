package ru.itaros.hoe.data;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;



public interface ISynchroportItems {

	public ItemStack tryToPutItemsIn(ItemStack source);
	public ItemStack tryToPutItemsIn(ItemStack source, ItemStack filter);
	public ItemStack tryToGetItemsOut(ItemStack target);
	public ItemStack tryToGetItemsOut(ItemStack target, ItemStack filter);
	
	public FluidStack tryToPutFluidsIn(FluidStack fluid);
	public FluidStack tryToPutFluidsIn(FluidStack fluid, FluidStack filter);
	public FluidStack tryToGetFluidsOut(FluidStack fluid);
	public FluidStack tryToGetFluidsOut(FluidStack fluid, FluidStack filter);	
	
	
	public void markDirty();
	public boolean pollDirty();
	
}


//Implementation example:

//StackTransferTuple transferTuple = new StackTransferTuple();
//
//public IUniversalStack tryToPutIn(IUniversalStack source){
//	transferTuple.fill(exemplar_cell_in, source);
//	source=StackUtility.tryToPutIn(transferTuple);
//	exemplar_cell_in=transferTuple.retr1();
//	this.markDirty();
//	return source;
//}
//public IUniversalStack tryToGetOut(IUniversalStack target){
//	transferTuple.fill(target, exemplar_cell_out);
//	target = StackUtility.tryToGetOut(transferTuple);
//	exemplar_cell_out=transferTuple.retr2();
//	this.markDirty();
//	return target;
//}