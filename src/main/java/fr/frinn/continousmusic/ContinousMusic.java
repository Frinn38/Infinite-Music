package fr.frinn.continousmusic;

import java.lang.reflect.Field;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.OptionsSoundsScreen;
import net.minecraft.client.settings.SliderPercentageOption;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.ReportedException;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ContinousMusic.MODID)
public class ContinousMusic {
	
	public static final String MODID = "infinitemusic";
	
	public static Minecraft mc = Minecraft.getInstance();
	
	public static MusicTickHandler ticker = new MusicTickHandler(mc);

	public static final SliderPercentageOption TIMER_LIMIT = new SliderPercentageOption("options.musicTimer", 0.0D, 300.0D, 10.0F, (settings) -> {
		return ContinuousConfig.musicTimer;
	}, (settings, value) -> {
		ContinuousConfig.musicTimer = value;
		ContinuousConfig.saveConfig();
	}, (settings, slider) -> {
		return slider.getDisplayString() + (int)ContinuousConfig.musicTimer + "s";
	});
	
	public ContinousMusic() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ContinuousConfig.CLIENT_SPEC);
		MinecraftForge.EVENT_BUS.addListener(this::guiOpened);
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

	public void guiOpened(GuiScreenEvent.InitGuiEvent event) {
		if(event.getGui() instanceof OptionsSoundsScreen) {
			int i = 11;
			event.addWidget(TIMER_LIMIT.createWidget(mc.gameSettings, event.getGui().width / 2 - 155 + i % 2 * 160, event.getGui().height / 6 - 12 + 24 * (i >> 1), 150));
		}
	}
}
