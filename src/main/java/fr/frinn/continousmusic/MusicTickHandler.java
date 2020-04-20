package fr.frinn.continousmusic;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.*;

public class MusicTickHandler extends MusicTicker {

	private final Minecraft mc;
    private ISound currentMusic;
    private int timeUntilNextMusic = 20;

	public MusicTickHandler(Minecraft mc) {
		super(mc);
		this.mc = mc;
	}
	
	@Override
	public void tick() {
		MusicTicker.MusicType musictype = this.mc.getAmbientMusicType();

        if (this.currentMusic != null)
        {
            if (!musictype.getSound().getName().equals(this.currentMusic.getSoundLocation()))
            {
                this.mc.getSoundHandler().stop(this.currentMusic);
                this.timeUntilNextMusic = 20;
            }

            if (!this.mc.getSoundHandler().isPlaying(this.currentMusic))
            {
                this.currentMusic = null;
                this.timeUntilNextMusic = 20;
            }
        }

        if (this.currentMusic == null && this.timeUntilNextMusic-- <= 0)
        {
        	this.currentMusic = SimpleSound.music(musictype.getSound());
        	this.mc.getSoundHandler().play(this.currentMusic);
            this.timeUntilNextMusic = 20;
        }
	}
}
