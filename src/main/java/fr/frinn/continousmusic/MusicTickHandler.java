package fr.frinn.continousmusic;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.*;

public class MusicTickHandler extends MusicTicker {

	private final Minecraft mc;
    private ISound currentMusic;
    private double timeUntilNextMusic = ContinuousConfig.musicTimer * 20;

	public MusicTickHandler(Minecraft mc) {
		super(mc);
		this.mc = mc;
	}
	
	@Override
	public void tick() {
		BackgroundMusicSelector musictype = this.mc.func_238178_U_();

        if (this.currentMusic != null)
        {
            if (!musictype.getSoundEvent().getName().equals(this.currentMusic.getSoundLocation()))
            {
                this.mc.getSoundHandler().stop(this.currentMusic);
                this.timeUntilNextMusic = ContinuousConfig.musicTimer * 20;
            }

            if (!this.mc.getSoundHandler().isPlaying(this.currentMusic))
            {
                this.currentMusic = null;
                this.timeUntilNextMusic = ContinuousConfig.musicTimer * 20;
            }
        }

        if (this.currentMusic == null) {
            this.timeUntilNextMusic--;
            if(this.timeUntilNextMusic <= 0) {
                this.currentMusic = SimpleSound.music(musictype.getSoundEvent());
                this.mc.getSoundHandler().play(this.currentMusic);
                this.timeUntilNextMusic = ContinuousConfig.musicTimer * 20;
            }
        }
	}
}
