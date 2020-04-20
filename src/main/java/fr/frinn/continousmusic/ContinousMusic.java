package fr.frinn.continousmusic;

import java.lang.reflect.Field;

import net.minecraft.client.Minecraft;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.ReportedException;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ContinousMusic.MODID)
public class ContinousMusic {
	
	public static final String MODID = "infinitemusic";
	
	public static Minecraft mc = Minecraft.getInstance();
	
	public static MusicTickHandler ticker = new MusicTickHandler(mc);
	
	public ContinousMusic() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
	}

    public void clientSetup(FMLClientSetupEvent event) {
    	//Replace the vanilla music ticker with custom one
    	try {
    		for(Field f : mc.getClass().getDeclaredFields()) {
    			if(f.getName().equals("musicTicker") || f.getName().equals("field_147126_aw")) {
    				f.setAccessible(true);
    				f.set(mc, ticker);
    			}
    		}
    	}
    	catch(Exception e) {
    		throw new ReportedException(new CrashReport("Music Choices couldn't load in it's music ticker! Things won't work.", e));
    	}    
	}
}
