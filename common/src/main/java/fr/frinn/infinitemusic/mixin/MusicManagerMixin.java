package fr.frinn.infinitemusic.mixin;

import fr.frinn.infinitemusic.InfiniteMusic;
import net.minecraft.client.sounds.MusicManager;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Debug(export = true)
@Mixin(MusicManager.class)
public class MusicManagerMixin {

    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Ljava/lang/Math;min(II)I", ordinal = 1))
    private int infinitemusic_removeMusicDelay(int nextSongDelay, int maxDelay) {
        return Math.min(nextSongDelay, InfiniteMusic.getTimer() * 20);
    }
}
