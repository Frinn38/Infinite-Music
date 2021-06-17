package fr.frinn.infinitemusic;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;

public class InfiniteMusic implements ClientModInitializer {

	public static boolean skip = false;
	public static boolean pause = false;

	@Override
	public void onInitializeClient() {
		AutoConfig.register(InfiniteConfig.class, GsonConfigSerializer::new);
	}
}
