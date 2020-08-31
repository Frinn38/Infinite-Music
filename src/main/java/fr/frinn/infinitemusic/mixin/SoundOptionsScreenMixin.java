package fr.frinn.infinitemusic.mixin;

import fr.frinn.infinitemusic.IExtendedGameOptions;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.options.GameOptionsScreen;
import net.minecraft.client.gui.screen.options.SoundOptionsScreen;
import net.minecraft.client.options.DoubleOption;
import net.minecraft.client.options.GameOptions;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SoundOptionsScreen.class)
public class SoundOptionsScreenMixin extends GameOptionsScreen {

    private static final DoubleOption TIMER_LIMIT = new DoubleOption("options.musicTimer", 0.0D, 300.0D, 5.0F, (settings) ->
            (double)((IExtendedGameOptions)settings).getTimer()
    , (settings, value) -> {
        ((IExtendedGameOptions)settings).setTimer(value.intValue());
        settings.write();
    }, (settings, slider) -> new TranslatableText("options.musicTimer").append(((IExtendedGameOptions)settings).getTimer() + "s"));

    public SoundOptionsScreenMixin(Screen parent, GameOptions gameOptions, Text title) {
        super(parent, gameOptions, title);
    }

    @Inject(at = @At("HEAD"), method = "init()V")
    protected void init(CallbackInfo info) {
        int i = 11;
        this.addButton(TIMER_LIMIT.createButton(MinecraftClient.getInstance().options, this.width / 2 - 155 + i % 2 * 160, this.height / 6 - 12 + 24 * (i >> 1), 150));
    }
}
