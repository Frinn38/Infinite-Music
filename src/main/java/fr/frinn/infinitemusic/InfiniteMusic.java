package fr.frinn.infinitemusic;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;

public class InfiniteMusic implements ClientModInitializer {

	public static boolean skip = false;
	public static boolean pause = false;

	@Override
	public void onInitializeClient() {
		AutoConfig.register(InfiniteConfig.class, GsonConfigSerializer::new);
	}
}
