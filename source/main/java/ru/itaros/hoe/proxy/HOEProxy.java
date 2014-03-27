package ru.itaros.hoe.proxy;

import ru.itaros.api.hoe.IHOEInterfacer;

public abstract class HOEProxy {

	public abstract void initHOE();

	public abstract void shutdownHOE();

	public abstract IHOEInterfacer getInterfacer();

}
