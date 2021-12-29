package fr.frinn.infinitemusic;

public class Common {

    public static final String MODID = "infinitemusic";

    public static boolean skip = false;
    public static boolean cloth_config_loaded = false;

    public static int getTimer() {
        if(cloth_config_loaded)
            return InfiniteConfig.getInstance().timer;
        return 0;
    }

    public static void setTimer(int timer) {
        if(cloth_config_loaded)
            InfiniteConfig.setTimer(timer);
    }
}
