package fr.frinn.infinitemusic.mixin;

import net.minecraft.client.sound.Channel;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.client.sound.SoundSystem;
import net.minecraft.sound.SoundCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(SoundSystem.class)
public class SoundSystemMixin {

    @Shadow
    private Map<SoundInstance, Integer> soundEndTicks;

    @Shadow
    private Map<SoundInstance, Channel.SourceManager> sources;

    @Inject(method = "updateSoundVolume(Lnet/minecraft/sound/SoundCategory;F)V", at = @At("TAIL"))
    private void updateSoundVolume(SoundCategory category, float volume, CallbackInfo info) {
        if(category == SoundCategory.MUSIC && volume <= 0.0F) {
            this.sources.forEach((sound, manager) -> {
                if(sound.getCategory() == SoundCategory.MUSIC) {
                    manager.run(source -> {
                        this.sources.remove(sound);
                        this.soundEndTicks.remove(sound);
                    });
                }
            });
        }
    }
}
