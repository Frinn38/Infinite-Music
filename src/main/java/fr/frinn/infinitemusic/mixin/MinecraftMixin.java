package fr.frinn.infinitemusic.mixin;

import fr.frinn.infinitemusic.MusicTickHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.MusicTracker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftMixin {

    @Shadow
    private MusicTracker musicTracker;

    @Inject(method = "<init>(Lnet/minecraft/client/RunArgs;)V", at = @At("TAIL"))
    public void inject(CallbackInfo info) {
        this.musicTracker = new MusicTickHandler(MinecraftClient.getInstance());
    }
}
