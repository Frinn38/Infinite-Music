package fr.frinn.continousmusic;

import java.io.File;
import java.lang.reflect.Field;

import net.minecraft.client.Minecraft;
import net.minecraft.crash.CrashReport;
import net.minecraft.util.ReportedException;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ContinousMusic.MODID, name = ContinousMusic.NAME, version = ContinousMusic.VERSION, clientSideOnly = true)
public class ContinousMusic {
	
	public static final String MODID = "continousmusic";
	public static final String NAME = "Continous Music";
	public static final String VERSION = "1.0.0";
	
	public static Minecraft mc = Minecraft.getMinecraft();
	
	public static MusicTickHandler ticker = new MusicTickHandler(mc);
	
	public ContinousMusic() {
		
	}
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		
	}
	
	@EventHandler
    public void postInit(FMLPostInitializationEvent event) {
		
    	//Replace the vanilla music ticker with custom one
    	try {
    		for(Field f : mc.getClass().getDeclaredFields()) {
    			if(f.getName().equals("mcMusicTicker") || f.getName().equals("field_147126_aw")) {
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
