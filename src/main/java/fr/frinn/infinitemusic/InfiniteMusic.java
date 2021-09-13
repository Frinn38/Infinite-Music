package fr.frinn.infinitemusic;

import net.minecraft.client.Minecraft;
import net.minecraft.client.ProgressOption;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.OptionsScreen;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod(InfiniteMusic.MODID)
public class InfiniteMusic {
	
	public static final String MODID = "infinitemusic";
	
	public static Minecraft mc = Minecraft.getInstance();

	public static final ProgressOption TIMER_LIMIT = new ProgressOption("options.musicTimer", 0.0D, 300.0D, 10.0F,
			(settings) -> ContinuousConfig.musicTimer,
			(settings, value) -> {
				ContinuousConfig.musicTimer = value;
				ContinuousConfig.saveConfig();
			},
			(settings, slider) -> new TranslatableComponent("options.musicTimer").append(": " + (int)ContinuousConfig.musicTimer + "s")
	);

	public static boolean pause = false;

	public static boolean skip = false;
	
	public InfiniteMusic() {
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ContinuousConfig.CLIENT_SPEC);
		MinecraftForge.EVENT_BUS.addListener(this::guiOpened);
		ModLoadingContext.get().registerExtensionPoint(IExtensionPoint.DisplayTest.class, () -> new IExtensionPoint.DisplayTest(() -> "1", (s, b) -> b));
	}

	public void guiOpened(GuiScreenEvent.InitGuiEvent event) {
		if(event.getGui() instanceof OptionsScreen) {
			event.addWidget(TIMER_LIMIT.createButton(mc.options, event.getGui().width / 2 + 5, event.getGui().height / 6 + 144 - 6, 150));
			event.addWidget(new ImageButton(
				event.getGui().width / 2 + 5 + 155,
				event.getGui().height / 6 + 144 - 6,
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
			Minecraft.getInstance().getSoundManager().resume();
			pause = false;
		} else {
			Minecraft.getInstance().getSoundManager().pause();
			pause = true;
		}
	}
}
