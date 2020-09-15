package fr.frinn.infinitemusic.mixins;

import fr.frinn.infinitemusic.ContinuousConfig;
import fr.frinn.infinitemusic.InfiniteMusic;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.BackgroundMusicSelector;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Random;

@Mixin(MusicTicker.class)
public class MusicTickerMixin {

    @Shadow
    private Random random;

    @Shadow
    private int timeUntilNextMusic = (int)ContinuousConfig.musicTimer * 20;

    @Shadow
    private ISound currentMusic;

    @Overwrite
    public void tick() {
        if(InfiniteMusic.pause) return;

        BackgroundMusicSelector backgroundmusicselector = Minecraft.getInstance().func_238178_U_();

        if(InfiniteMusic.skip) {
            Minecraft.getInstance().getSoundHandler().stop(this.currentMusic);
            this.currentMusic = null;
            this.timeUntilNextMusic = 0;
            InfiniteMusic.skip = false;
        }

        if (this.currentMusic != null) {
            if (!backgroundmusicselector.getSoundEvent().getName().equals(this.currentMusic.getSoundLocation()) && backgroundmusicselector.shouldReplaceCurrentMusic()) {
                Minecraft.getInstance().getSoundHandler().stop(this.currentMusic);
                this.timeUntilNextMusic = MathHelper.nextInt(this.random, 0, backgroundmusicselector.getMinDelay() / 2);
            }

            if (!Minecraft.getInstance().getSoundHandler().isPlaying(this.currentMusic)) {
                this.currentMusic = null;
                this.timeUntilNextMusic = Math.min(this.timeUntilNextMusic, (int)ContinuousConfig.musicTimer * 20);
            }
        }

        this.timeUntilNextMusic = Math.min(this.timeUntilNextMusic, (int)ContinuousConfig.musicTimer * 20);
        if (this.currentMusic == null && this.timeUntilNextMusic-- <= 0) {
            this.selectRandomBackgroundMusic(backgroundmusicselector);
        }
    }

    @Shadow
    public void selectRandomBackgroundMusic(BackgroundMusicSelector selector) {}
}
