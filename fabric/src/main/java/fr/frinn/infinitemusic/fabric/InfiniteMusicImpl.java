package fr.frinn.infinitemusic.fabric;

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
    }

    public static boolean isClothConfigLoaded() {
        return FabricLoader.getInstance().isModLoaded("cloth-config");
    }
}
