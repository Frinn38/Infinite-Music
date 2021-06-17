package fr.frinn.infinitemusic;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = "infinitemusic")
public class InfiniteConfig implements ConfigData {

    public int timer;

    public static InfiniteConfig getInstance() {
        return AutoConfig.getConfigHolder(InfiniteConfig.class).getConfig();
    }

    @Override
    public void validatePostLoad() throws ValidationException {

    }
}
