package fr.frinn.infinitemusic.mixin;

import fr.frinn.infinitemusic.Common;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.MusicManager;
import net.minecraft.sounds.Music;
import net.minecraft.util.Mth;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Random;

@Mixin(MusicManager.class)
public class MusicManagerMixin {

    @Shadow
    private Random random;

    @Shadow
    private int nextSongDelay = Common.getTimer() * 20;

    @Shadow
    private SoundInstance currentMusic;

    @Overwrite
    public void tick() {

        Music music = Minecraft.getInstance().getSituationalMusic();

        if(Common.skip) {
            Minecraft.getInstance().getSoundManager().stop(this.currentMusic);
            this.currentMusic = null;
            this.nextSongDelay = 0;
            Common.skip = false;
        }

        if (this.currentMusic != null) {
            if (!music.getEvent().getLocation().equals(this.currentMusic.getLocation()) && music.replaceCurrentMusic()) {
                Minecraft.getInstance().getSoundManager().stop(this.currentMusic);
                this.nextSongDelay = Mth.nextInt(this.random, 0, music.getMinDelay() / 2);
            }

            if (!Minecraft.getInstance().getSoundManager().isActive(this.currentMusic)) {
                this.currentMusic = null;
                this.nextSongDelay = Math.min(this.nextSongDelay, Common.getTimer() * 20);
            }
        }

        this.nextSongDelay = Math.min(this.nextSongDelay, Common.getTimer() * 20);
        if (this.currentMusic == null && this.nextSongDelay-- <= 0) {
            this.startPlaying(music);
        }
    }

    @Shadow
    public void startPlaying(Music music) {}
}
