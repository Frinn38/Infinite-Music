package fr.frinn.infinitemusic.fabric;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import fr.frinn.infinitemusic.InfiniteMusic;
import fr.frinn.infinitemusic.InfiniteMusicConfig;
import me.shedaniel.autoconfig.AutoConfig;

public class InfiniteMusicModMenuIntegration implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        if(InfiniteMusic.isClothConfigLoaded())
            return screen -> AutoConfig.getConfigScreen(InfiniteMusicConfig.class, screen).get();
        return screen -> null;
    }
}
