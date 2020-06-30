package fr.frinn.infinitemusic;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.MusicTracker;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.sound.MusicSound;
import net.minecraft.sound.SoundCategory;

public class MusicTickHandler extends MusicTracker {

	private final MinecraftClient mc;
  private SoundInstance currentMusic;
  private int timeUntilNextMusic = ((IExtendedGameOptions)MinecraftClient.getInstance().options).getTimer() * 20;

  float prevTickMusicVolume;

	public MusicTickHandler(MinecraftClient mc) {
		super(mc);
		this.mc = mc;
	}
	
	@Override
	public void tick() {
		MusicSound musictype = this.mc.getMusicType();
        if (this.currentMusic != null)
        {
            if (!musictype.getEvent().getId().equals(this.currentMusic.getId()))
            {
                this.mc.getSoundManager().stop(this.currentMusic);
                this.timeUntilNextMusic = ((IExtendedGameOptions)mc.options).getTimer() * 20;
            }

            if (!this.mc.getSoundManager().isPlaying(this.currentMusic))
            {
                this.currentMusic = null;
                this.timeUntilNextMusic = ((IExtendedGameOptions)mc.options).getTimer() * 20;
            }

            if(this.mc.getSoundManager().isPlaying(this.currentMusic) && this.mc.options.getSoundVolume(SoundCategory.MUSIC) > 0 && this.prevTickMusicVolume == 0) {
                this.mc.getSoundManager().stop(this.currentMusic);
                this.currentMusic = null;
                this.timeUntilNextMusic = ((IExtendedGameOptions)mc.options).getTimer() * 20;
            }
        }

        if (this.currentMusic == null) {
            this.timeUntilNextMusic--;
            if(this.timeUntilNextMusic <= 0) {
                this.currentMusic = PositionedSoundInstance.music(musictype.getEvent());
                this.mc.getSoundManager().play(this.currentMusic);
                this.timeUntilNextMusic = ((IExtendedGameOptions)mc.options).getTimer() * 20;
            }
        }

        this.prevTickMusicVolume = mc.options.getSoundVolume(SoundCategory.MUSIC);
	}
}
