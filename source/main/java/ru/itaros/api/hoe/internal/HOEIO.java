package ru.itaros.api.hoe.internal;

/*
 * !ATTENTION!
 * !IT IS NOT ADVISED TO EXTEND HOEIO IN FIRST PLACE!
 * CHECK FIRST IOs from toolkit.hoe. Maybe they can fit in your purpose?
 * If you haven't found one for your usecase and decided to write your own HOEIO extender - consider sharing it via github PR ;)
 * If in doubt: contact me(Itaros)
 * !
 * 
 * HOEIO is a handler for machine operation. 
 * It contains all recipes and configuration to complete them.
 * 
 * Switching HOEIO recipe on the fly is possible leading to PURGE EVENT.
 * PURGE EVENT removes all items into world to start clean-state operation.
 * 
 * !!DO NOT CHANGE REQ WHILE IN OPERATION. THIS CAN INDUCE SYNC CRASHES!!
 */
public abstract class HOEIO {
	//Configuration
	private boolean isAllowedToStart=false;
	private int input_req,output_req;
	public int getInputReq(){
		return input_req;
	}
	public int getOutputReq(){
		return output_req;
	}
	protected void setReq(int input_req, int output_req){
		this.input_req=input_req;
		this.output_req=output_req;
	}
	public boolean isAllowedToStart(){
		return isAllowedToStart;
	}
	protected void allowToStart(){
		isAllowedToStart=true;
	}
	//Execution
	private int ticksAccumulated=0;
	public int getTicks(){
		return ticksAccumulated;
	}
	public void incrementTick(){
		ticksAccumulated++;
	}
	public void voidTicks(){
		ticksAccumulated=0;
	}
	public abstract void tick(HOEData data);
}
