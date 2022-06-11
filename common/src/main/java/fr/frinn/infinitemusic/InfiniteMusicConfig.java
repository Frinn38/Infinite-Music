package fr.frinn.infinitemusic;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = InfiniteMusic.MODID)
public class InfiniteMusicConfig implements ConfigData {

    public int timer = 0;

    public static InfiniteMusicConfig getConfig() {
        return AutoConfig.getConfigHolder(InfiniteMusicConfig.class).getConfig();
    }
}
