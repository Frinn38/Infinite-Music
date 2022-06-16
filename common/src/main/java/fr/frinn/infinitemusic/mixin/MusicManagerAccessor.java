package fr.frinn.infinitemusic.mixin;

import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.MusicManager;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Debug(export = true)
@Mixin(MusicManager.class)
public interface MusicManagerAccessor {

    @Accessor("nextSongDelay")
    int getTimer();

    @Accessor("currentMusic")
    SoundInstance getCurrentMusic();
}
