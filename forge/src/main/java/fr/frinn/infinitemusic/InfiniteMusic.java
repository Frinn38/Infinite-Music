package fr.frinn.infinitemusic;

import me.shedaniel.autoconfig.AutoConfig;
import net.minecraftforge.client.ConfigGuiHandler;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkConstants;

@Mod(Common.MODID)
public class InfiniteMusic {

    public InfiniteMusic() {
        ModLoadingContext.get().registerExtensionPoint(IExtensionPoint.DisplayTest.class, () -> new IExtensionPoint.DisplayTest(() -> NetworkConstants.IGNORESERVERONLY, (a, b) -> true));
        if(ModList.get().isLoaded("cloth_config")) {
            InfiniteConfig.registerConfig();
            ModLoadingContext.get().registerExtensionPoint(ConfigGuiHandler.ConfigGuiFactory.class, () ->
                    new ConfigGuiHandler.ConfigGuiFactory((client, parent) ->
                            AutoConfig.getConfigScreen(InfiniteConfig.class, parent).get()
                    )
            );
        }
    }
}
