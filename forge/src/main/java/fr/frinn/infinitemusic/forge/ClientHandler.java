package fr.frinn.infinitemusic.forge;

import fr.frinn.infinitemusic.InfiniteMusic;
import fr.frinn.infinitemusic.InfiniteMusicConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ConfigGuiHandler;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = InfiniteMusic.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClientHandler {

    public static void setupConfig() {
        AutoConfig.register(InfiniteMusicConfig.class, Toml4jConfigSerializer::new);
        ModLoadingContext.get().registerExtensionPoint(ConfigGuiHandler.ConfigGuiFactory.class, () ->
                new ConfigGuiHandler.ConfigGuiFactory((client, parent) -> AutoConfig.getConfigScreen(InfiniteMusicConfig.class, parent).get())
        );
    }

    @SubscribeEvent
    public static void debugTextEvent(final RenderGameOverlayEvent.Text event) {
        if(!event.getLeft().isEmpty())
            InfiniteMusic.addDebugText(event.getLeft());
    }
}
