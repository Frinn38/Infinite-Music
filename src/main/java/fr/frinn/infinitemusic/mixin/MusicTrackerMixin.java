package fr.frinn.infinitemusic.mixin;

import fr.frinn.infinitemusic.InfiniteConfig;
import fr.frinn.infinitemusic.InfiniteMusic;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.MusicTracker;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.sound.MusicSound;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Random;

@Mixin(MusicTracker.class)
public class MusicTrackerMixin {

    @Shadow
    private Random random;

    @Shadow
    private int timeUntilNextSong = InfiniteConfig.getInstance().timer * 20;

    @Shadow
    private SoundInstance current;

    @Overwrite
    public void tick() {
        if(InfiniteMusic.pause) return;

        MusicSound musicSound = MinecraftClient.getInstance().getMusicType();

        if(InfiniteMusic.skip) {
            MinecraftClient.getInstance().getSoundManager().stop(this.current);
            this.current = null;
            this.timeUntilNextSong = 0;
            InfiniteMusic.skip = false;
        }

        if (this.current != null) {
            if (!musicSound.getSound().getId().equals(this.current.getId()) && musicSound.shouldReplaceCurrentMusic()) {
                MinecraftClient.getInstance().getSoundManager().stop(this.current);
                this.timeUntilNextSong = MathHelper.nextInt(this.random, 0, musicSound.getMinDelay() / 2);
            }

            if (!MinecraftClient.getInstance().getSoundManager().isPlaying(this.current)) {
                this.current = null;
                this.timeUntilNextSong = Math.min(this.timeUntilNextSong, InfiniteConfig.getInstance().timer * 20);
            }
        }

        this.timeUntilNextSong = Math.min(this.timeUntilNextSong, InfiniteConfig.getInstance().timer * 20);
        if (this.current == null && this.timeUntilNextSong-- <= 0) {
            this.play(musicSound);
        }
    }

    @Shadow
    public void play(MusicSound selector) {}
}
