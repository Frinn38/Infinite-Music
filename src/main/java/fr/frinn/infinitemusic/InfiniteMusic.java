package fr.frinn.infinitemusic;

import fr.frinn.infinitemusic.mixins.MusicTickerMixin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.OptionsSoundsScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.SliderPercentageOption;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(InfiniteMusic.MODID)
public class InfiniteMusic {
	
	public static final String MODID = "infinitemusic";
	
	public static Minecraft mc = Minecraft.getInstance();

	public static final SliderPercentageOption TIMER_LIMIT = new SliderPercentageOption("options.musicTimer", 0.0D, 300.0D, 10.0F, (settings) -> {
		return ContinuousConfig.musicTimer;
	}, (settings, value) -> {
		ContinuousConfig.musicTimer = value;
		ContinuousConfig.saveConfig();
	}, (settings, slider) -> {
		return new StringTextComponent(I18n.format("options.musicTimer") + ": " + (int)ContinuousConfig.musicTimer + "s");
	});

	public static boolean pause = false;

	public static boolean skip = false;
	
	public InfiniteMusic() {
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ContinuousConfig.CLIENT_SPEC);
		MinecraftForge.EVENT_BUS.addListener(this::guiOpened);
	}

	public void guiOpened(GuiScreenEvent.InitGuiEvent event) {
		if(event.getGui() instanceof OptionsSoundsScreen) {
			int i = 11;
			event.addWidget(TIMER_LIMIT.createWidget(mc.gameSettings, event.getGui().width / 2 - 155 + i % 2 * 160, event.getGui().height / 6 - 12 + 24 * (i >> 1), 150));
			event.addWidget(new ImageButton(
				event.getGui().width / 2 - 155 + i % 2 * 160 + 155,
				event.getGui().height / 6 - 12 + 24 * (i >> 1),
				20,
				20,
				0,
				0,
				20,
				new ResourceLocation(MODID, "textures/gui/buttons.png"),
				(button) ->	skip = true
				)
			);
		}
	}

	private static void pause() {
		if(pause) {
			Minecraft.getInstance().getSoundHandler().resume();
			pause = false;
		} else {
			Minecraft.getInstance().getSoundHandler().pause();
			pause = true;
		}
	}
}
