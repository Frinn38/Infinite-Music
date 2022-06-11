package fr.frinn.infinitemusic.forge;

import fr.frinn.infinitemusic.InfiniteMusic;
import fr.frinn.infinitemusic.InfiniteMusicConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkConstants;

@Mod(InfiniteMusic.MODID)
public class InfiniteMusicImpl {

    public InfiniteMusicImpl() {
        ModLoadingContext.get().registerExtensionPoint(IExtensionPoint.DisplayTest.class, () -> new IExtensionPoint.DisplayTest(() -> NetworkConstants.IGNORESERVERONLY, (a, b) -> true));
        if(InfiniteMusic.isClothConfigLoaded())
            DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ClientHandler::setupConfig);
    }

    public static boolean isClothConfigLoaded() {
        return ModList.get().isLoaded("cloth_config");
    }
}
