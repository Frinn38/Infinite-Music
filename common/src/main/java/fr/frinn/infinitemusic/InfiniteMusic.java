package fr.frinn.infinitemusic;

import dev.architectury.injectables.annotations.ExpectPlatform;
import fr.frinn.infinitemusic.mixin.MusicManagerAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.MusicManager;
import net.minecraft.network.chat.Component;

import java.util.List;

public class InfiniteMusic {

    public static final String MODID = "infinitemusic";

    @ExpectPlatform
    public static boolean isClothConfigLoaded() {
        throw new RuntimeException();
    }

    public static int getTimer() {
        if(isClothConfigLoaded())
            return InfiniteMusicConfig.getConfig().timer;
        return 0;
    }

    public static void addDebugText(List<String> texts) {
        MusicManager manager = Minecraft.getInstance().getMusicManager();
        if(manager instanceof MusicManagerAccessor accessor) {
            SoundInstance currentMusic = accessor.getCurrentMusic();
            if(currentMusic == null)
                texts.add(Component.translatable("infinitemusic.debug.timer", accessor.getTimer()).getString());
            else
                texts.add(Component.translatable("infinitemusic.debug.music", currentMusic.getLocation()).getString());
        }
    }
}
