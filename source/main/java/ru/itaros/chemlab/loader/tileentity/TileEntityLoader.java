package ru.itaros.chemlab.loader.tileentity;

import cpw.mods.fml.common.registry.GameRegistry;
import ru.itaros.chemlab.hoe.*;
import ru.itaros.chemlab.hoe.special.GasChimneyIO;
import ru.itaros.chemlab.hoe.special.HVLCFillerIO;
import ru.itaros.chemlab.minecraft.tileentity.*;
import ru.itaros.toolkit.hoe.io.IOCollectionHelper;

public class TileEntityLoader {

	public static IOCollectionHelper load(){
		IOCollectionHelper iocollection = new IOCollectionHelper(
    			new BiogrinderIO(),
    			new CentrifugalExtractorIO(),
    			new WasherIO(),
    			new ImpregnatorIO(),
    			new PressIO(),
    			new SteamBoilerIO(),
    			new SteamExplosionUnitIO(),
    			new CrusherIO(),
    			new DiaphragmalElectrolyzerIO(),
    			new HiTFurnaceIO(),
    			new AirCollectorIO(),
    			new FluidCompressorIO(),
    			new TurboexpanderIO(),
    			new EvaporationUnitIO(),
    			new CatalyticTankIO(),
    			new HiResistantMixerIO(),
    			new ServiceBayIO(),
    			new HVLCFillerIO(),
    			new GasChimneyIO()
    			);
		iocollection.registerInHOE();
		
		
		
		GameRegistry.registerTileEntity(BiogrinderTileEntity.class, BiogrinderTileEntity.class.getName());
		GameRegistry.registerTileEntity(CentrifugalExtractorTileEntity.class, CentrifugalExtractorTileEntity.class.getName());
		GameRegistry.registerTileEntity(WasherTileEntity.class, WasherTileEntity.class.getName());
		GameRegistry.registerTileEntity(ImpregnatorTileEntity.class, ImpregnatorTileEntity.class.getName());
		GameRegistry.registerTileEntity(PressTileEntity.class, PressTileEntity.class.getName());
		GameRegistry.registerTileEntity(SteamBoilerTileEntity.class, SteamBoilerTileEntity.class.getName());
		GameRegistry.registerTileEntity(SteamExplosionUnitTileEntity.class, SteamExplosionUnitTileEntity.class.getName());
		GameRegistry.registerTileEntity(CrusherTileEntity.class, CrusherTileEntity.class.getName());
		GameRegistry.registerTileEntity(DiaphragmalElectrolyzerTileEntity.class,DiaphragmalElectrolyzerTileEntity.class.getName());
		GameRegistry.registerTileEntity(HiTFurnaceTileEntity.class,HiTFurnaceTileEntity.class.getName());
		GameRegistry.registerTileEntity(AirCollectorTileEntity.class,AirCollectorTileEntity.class.getName());
		GameRegistry.registerTileEntity(FluidCompressorTileEntity.class,FluidCompressorTileEntity.class.getName());
		GameRegistry.registerTileEntity(TurboexpanderTileEntity.class,TurboexpanderTileEntity.class.getName());
		GameRegistry.registerTileEntity(EvaporationUnitTileEntity.class,EvaporationUnitTileEntity.class.getName());
		GameRegistry.registerTileEntity(CatalyticTankTileEntity.class,CatalyticTankTileEntity.class.getName());
		GameRegistry.registerTileEntity(HiResistantMixerTileEntity.class,HiResistantMixerTileEntity.class.getName());
		GameRegistry.registerTileEntity(ServiceBayTileEntity.class,ServiceBayTileEntity.class.getName());
		GameRegistry.registerTileEntity(HVLCFillerTileEntity.class,HVLCFillerTileEntity.class.getName());
		GameRegistry.registerTileEntity(GasChimneyTileEntity.class,GasChimneyTileEntity.class.getName());
		
		//GameRegistry.registerTileEntity(.class,.class.getName());
		
		return iocollection;
	}
	
}
