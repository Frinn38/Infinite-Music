package fr.frinn.infinitemusic;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import org.apache.commons.lang3.tuple.Pair;

@Mod.EventBusSubscriber(modid = InfiniteMusic.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ContinuousConfig {
    public static final ClientConfig CLIENT;
    public static final ForgeConfigSpec CLIENT_SPEC;
    static {
        final Pair<ClientConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ClientConfig::new);
        CLIENT = specPair.getLeft();
        CLIENT_SPEC = specPair.getRight();
    }

    public static double musicTimer;

    @SubscribeEvent
    public static void onModConfigEvent(final ModConfigEvent event) {
        if(event.getConfig().getSpec() == CLIENT_SPEC)
            bakeConfig();
    }

    public static void bakeConfig() {
        musicTimer = CLIENT.musicTimer.get();
    }

    public static void saveConfig() {
        CLIENT.musicTimer.set(musicTimer);
    }

    public static class ClientConfig {

        public final ForgeConfigSpec.DoubleValue musicTimer;

        public ClientConfig(ForgeConfigSpec.Builder builder) {
            musicTimer = builder.comment("Time between vanilla musics (in seconds)").defineInRange("musicTimer", 0.0D, 0.0D, 300.0D);
        }
    }
}
