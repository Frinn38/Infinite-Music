package fr.frinn.infinitemusic.fabric;

import dev.architectury.event.events.client.ClientGuiEvent;
import fr.frinn.infinitemusic.InfiniteMusic;
import fr.frinn.infinitemusic.InfiniteMusicConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class InfiniteMusicImpl implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        if(InfiniteMusic.isClothConfigLoaded())
            AutoConfig.register(InfiniteMusicConfig.class, Toml4jConfigSerializer::new);
        ClientGuiEvent.DEBUG_TEXT_LEFT.register(InfiniteMusic::addDebugText);
    }

    public static boolean isClothConfigLoaded() {
        return FabricLoader.getInstance().isModLoaded("cloth-config");
    }
}
