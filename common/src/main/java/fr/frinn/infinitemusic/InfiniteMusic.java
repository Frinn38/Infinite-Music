package fr.frinn.infinitemusic;

import dev.architectury.injectables.annotations.ExpectPlatform;

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
}
