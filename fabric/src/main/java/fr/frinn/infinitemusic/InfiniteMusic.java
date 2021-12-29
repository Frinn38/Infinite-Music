package fr.frinn.infinitemusic;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;

public class InfiniteMusic implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        if(FabricLoader.getInstance().isModLoaded("cloth-config"))
            InfiniteConfig.registerConfig();
        KeyBindingHelper.registerKeyBinding(Client.SKIP_KEY);
        ClientTickEvents.START_CLIENT_TICK.register(this::checkForSkip);
    }

    private boolean isDown = false;
    public void checkForSkip(Minecraft mc) {
        if(Client.SKIP_KEY.isDown() && !isDown)
            isDown = true;

        if(!Client.SKIP_KEY.isDown() && isDown) {
            isDown = false;
            Common.skip = true;
        }
    }
}