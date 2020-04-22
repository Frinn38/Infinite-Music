package fr.frinn.continousmusic;

import java.util.Random;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.MusicTracker;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.SoundInstance;

public class MusicTickHandler extends MusicTracker {

	private final MinecraftClient mc;
    private SoundInstance currentMusic;
    private int timeUntilNextMusic = 20;

	public MusicTickHandler(MinecraftClient mc) {
		super(mc);
		this.mc = mc;
	}
	
	@Override
	public void tick() {
		MusicTracker.MusicType musictype = this.mc.getMusicType();

        if (this.currentMusic != null)
        {
            if (!musictype.getSound().getId().equals(this.currentMusic.getId()))
            {
                this.mc.getSoundManager().stop(this.currentMusic);
                this.timeUntilNextMusic = 20;
            }

            if (!this.mc.getSoundManager().isPlaying(this.currentMusic))
            {
                this.currentMusic = null;
                this.timeUntilNextMusic = 20;
            }
        }

        if (this.currentMusic == null && this.timeUntilNextMusic-- <= 0)
        {
        	this.currentMusic = PositionedSoundInstance.music(musictype.getSound());
        	this.mc.getSoundManager().play(this.currentMusic);
            this.timeUntilNextMusic = 20;
        }
	}
}
