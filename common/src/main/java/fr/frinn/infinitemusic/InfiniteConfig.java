package fr.frinn.infinitemusic;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;

@Config(name = Common.MODID)
public class InfiniteConfig implements ConfigData {

    public int timer;

    public static void registerConfig() {
        AutoConfig.register(InfiniteConfig.class, Toml4jConfigSerializer::new);
    }

    public static InfiniteConfig getInstance() {
        return AutoConfig.getConfigHolder(InfiniteConfig.class).getConfig();
    }

    public static void setTimer(int timer) {
        ConfigHolder<InfiniteConfig> holder = AutoConfig.getConfigHolder(InfiniteConfig.class);
        holder.getConfig().timer = timer;
        holder.save();
    }

    @Override
    public void validatePostLoad() throws ValidationException {

    }
}
