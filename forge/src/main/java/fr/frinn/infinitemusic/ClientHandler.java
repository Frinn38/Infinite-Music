package fr.frinn.infinitemusic;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.lwjgl.glfw.GLFW;

public class ClientHandler {

    @Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Common.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModBus {

        @SubscribeEvent
        public static void clientSetup(final FMLClientSetupEvent event) {
            ClientRegistry.registerKeyBinding(Client.SKIP_KEY);
        }
    }

    @Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Common.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class ForgeBus {

        @SubscribeEvent
        public static void inputEvent(final InputEvent.KeyInputEvent event) {
            if(event.getAction() == GLFW.GLFW_RELEASE && Client.SKIP_KEY.getKey().getValue() == event.getKey()) {
                Common.skip = true;
            }
        }
    }
}
