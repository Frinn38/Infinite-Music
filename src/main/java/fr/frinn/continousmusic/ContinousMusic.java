package fr.frinn.continousmusic;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.crash.CrashException;
import net.minecraft.util.crash.CrashReport;
import org.apache.commons.lang3.reflect.FieldUtils;

public class ContinousMusic implements ClientModInitializer {

	public static MinecraftClient mc = MinecraftClient.getInstance();
	
	public static MusicTickHandler ticker = new MusicTickHandler(mc);

	@Override
	public void onInitializeClient() {
		ClientTickCallback.EVENT.register((mc) -> {
			try {
				if(!(FieldUtils.readField(mc, "field_1714", true) instanceof MusicTickHandler))
					FieldUtils.writeField(mc, "field_1714", ticker, true);
			}
			catch(Exception e) {
				throw new CrashException(new CrashReport("Music Choices couldn't load in it's music ticker! Things won't work.", e));
			}
		});
	}
}
