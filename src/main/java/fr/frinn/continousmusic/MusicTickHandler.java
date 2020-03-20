package fr.frinn.continousmusic;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.math.MathHelper;

public class MusicTickHandler extends MusicTicker {
	
	private final Random rand = new Random();
	private final Minecraft mc;
    private ISound currentMusic;
    private int timeUntilNextMusic = 20;

	public MusicTickHandler(Minecraft mc) {
		super(mc);
		this.mc = mc;
	}
	
	@Override
	public void update() {
		
		MusicTicker.MusicType musictype = this.mc.getAmbientMusicType();

        if (this.currentMusic != null)
        {
            if (!musictype.getMusicLocation().getSoundName().equals(this.currentMusic.getSoundLocation()))
            {
                this.mc.getSoundHandler().stopSound(this.currentMusic);
                this.timeUntilNextMusic = 20;
            }

            if (!this.mc.getSoundHandler().isSoundPlaying(this.currentMusic))
            {
                this.currentMusic = null;
                this.timeUntilNextMusic = 20;
            }
        }

        if (this.currentMusic == null && this.timeUntilNextMusic-- <= 0)
        {
        	this.currentMusic = PositionedSoundRecord.getMusicRecord(musictype.getMusicLocation());
        	this.mc.getSoundHandler().playSound(this.currentMusic);
            this.timeUntilNextMusic = 20;
        }
	}
}
