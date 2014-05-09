package ru.itaros.toolkit.hoe.machines.basic;

import net.minecraft.nbt.NBTTagCompound;
import ru.itaros.api.hoe.exceptions.HOEWrongSyncDirection;
import ru.itaros.api.hoe.internal.HOEData;
import ru.itaros.api.hoe.internal.HOEIO;
import ru.itaros.toolkit.hoe.machines.basic.io.HOEMachineIO;

public class HOEMachineData extends HOEData {

	/*
	 * Reflection autocaster
	 */
	public HOEMachineData(HOEData parent){
		this((HOEMachineData)parent);
		//Reflection autocaster
	}
	protected HOEMachineData(HOEMachineData parent){
		super();
		this.isSided=true;
		bindChildToParent(parent);
	}	
	
	protected double power;

	public void readNBT(NBTTagCompound nbt){
		//super.readNBT(nbt);
		//Power
		power=nbt.getDouble("power");
		maxpower=nbt.getDouble("maxpower");
		//FMLLog.log(Level.INFO, "Maxpower was:"+maxpower);
		//Ticks
		ticksRequared=nbt.getInteger("ticksRequared");
		ticksAccumulated=nbt.getInteger("ticksAccumulated");
		//IO recognition Signature
		String ioname = nbt.getString("io_sign");
		if(ioname!=""){
			io =  (HOEMachineIO) HOEIO.getIORegistry().get(ioname);
		}
	}

	public void writeNBT(NBTTagCompound nbt){
		//super.writeNBT(nbt);
		//Power
		nbt.setDouble("power", power);
		nbt.setDouble("maxpower", maxpower);
		//Ticks
		nbt.setInteger("ticksRequared", ticksRequared);
		nbt.setInteger("ticksAccumulated", ticksAccumulated);
		//IO recognition Signature
		if(io!=null){
			nbt.setString("io_sign", io.getClass().getName());
		}
	}
	
	/*
	 * This method initialized data for first use
	 */
	protected void init(){}

	public void sync(){
		if(child==null){throw new HOEWrongSyncDirection();}
		
		
		HOEMachineData childd=(HOEMachineData) child;
		childd.power=power;
		childd.maxpower=maxpower;
			
		childd.ticksRequared=ticksRequared;
		childd.ticksAccumulated=ticksAccumulated;
		
	}
	
	/*
	 * This methods creates initial HARDLINK between child and client
	 */
	protected void bindChildToParent(HOEMachineData parent){
		this.io=parent.io;
	};

	protected double maxpower;
	public int ticksRequared;

	public double getPower() {
		return power;
	}

	public double getPowerMax() {
		return maxpower;
	}

	protected int ticksAccumulated = 0;
	protected HOEMachineIO io;

	public int getTicks() {
		return ticksAccumulated;
	}

	public void incrementTick() {
		ticksAccumulated++;
	}

	public void voidTicks() {
		ticksAccumulated=0;
	}

	private boolean isConfigured = false;

	public void setMaxPower(int maxpower) {
		this.maxpower=maxpower;
	}

	public void setMachine(HOEMachineIO io) {
		this.io=io;
	}

	public static HOEMachineData generateFromNBT(String refltype, NBTTagCompound nbt) {
		//HOEMachineData data = new HOEMachineData();
		HOEMachineData data=null;
		if(refltype==""){
			data = new HOEMachineData();
		}else{
			try{
				data = (HOEMachineData) Class.forName(refltype).getConstructor().newInstance();
			}catch(Exception e){
				System.err.println("REFL: "+refltype);
				throw new RuntimeException(e);//TODO: Data lookup failure exception
			}
		}
		//data.readNBT(nbt);
		return data;
	}

	public void setConfigured() {
		init();
		child=spawnChild();
		sync();
		isConfigured=true;
	}

	@Override
	public boolean isConfigured() {
		return isConfigured;
	}

	public HOEMachineIO getIO() {
		return io;
	}

	public double incrementPower(double i) {
		power+=i;
		if(power>maxpower){
			double overflow = power-maxpower;
			return overflow;
		}
		return 0;
	}

	public void decrementPower(double powerReqPerTick) {
		power-=powerReqPerTick;
		if(power<0){power=0;}
	}	
	
	public HOEMachineData() {
		super();
	}
	
	


}