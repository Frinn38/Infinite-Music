package fr.frinn.continousmusic;

import java.lang.reflect.Field;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.crash.CrashException;
import net.minecraft.util.crash.CrashReport;

public class ContinousMusic implements ClientModInitializer {
	
	public static final String MODID = "infinitemusic";
	
	public static MinecraftClient mc = MinecraftClient.getInstance();
	
	public static MusicTickHandler ticker = new MusicTickHandler(mc);

	@Override
	public void onInitializeClient() {
		//Replace the vanilla music ticker with custom one
		try {
			for(Field f : mc.getClass().getDeclaredFields()) {
				if(f.getName().equals("musicTracker") || f.getName().equals("field_1714")) {
					f.setAccessible(true);
					f.set(mc, ticker);
					System.out.println("Infinite Music Ticker set !");
				}
			}
		}
		catch(Exception e) {
			throw new CrashException(new CrashReport("Music Choices couldn't load in it's music ticker! Things won't work.", e));
		}
	}
}
