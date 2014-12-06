package ru.itaros.chemlab.hoe.io;

import ru.itaros.api.hoe.internal.HOEData;
import ru.itaros.chemlab.ChemLabValues;
import ru.itaros.chemlab.hoe.data.BloomeryData;
import ru.itaros.hoe.io.HOEMachineIO;

public class BloomeryIO extends HOEMachineIO {


	public static final int MAXPOWER = ChemLabValues.ENERGY_FRACTION*100*2;
	public static final int INCOMING_PORTS		=	2;
	public static final int OUTCOMING_PORTS	=	1;
	
	
	public BloomeryIO(){
		this.setReq(INCOMING_PORTS, OUTCOMING_PORTS);
		this.allowToStart();
	}


	
	@Override
	public void configureData(HOEData data) {
		BloomeryData machine=(BloomeryData) data;
		machine.setMaxPower(MAXPOWER);
		machine.setMachine(this);
		machine.setConfigured();
	}


	@Override
	protected boolean isMachineActive(HOEData data) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	protected void produce(HOEData data, boolean doReal) {
		// TODO Auto-generated method stub
		
	}

}
