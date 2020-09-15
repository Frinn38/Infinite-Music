package fr.frinn.infinitemusic;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;

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
