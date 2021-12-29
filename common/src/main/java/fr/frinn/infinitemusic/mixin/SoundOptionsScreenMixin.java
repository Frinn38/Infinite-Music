package fr.frinn.infinitemusic.mixin;

import fr.frinn.infinitemusic.Common;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.ProgressOption;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.OptionsSubScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.SoundOptionsScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SoundOptionsScreen.class)
public class SoundOptionsScreenMixin extends OptionsSubScreen {

    private static final ProgressOption TIMER_LIMIT = new ProgressOption("options.musicTimer", 0.0D, 300.0D, 5.0F,
            (settings) -> (double)Common.getTimer(),
            (settings, value) -> Common.setTimer(value.intValue()),
            (settings, slider) -> new TranslatableComponent("options.musicTimer").append(": ").append(Common.getTimer() + "s"));

    public SoundOptionsScreenMixin(Screen parent, Options options, Component text) {
        super(parent, options, text);
    }

    @Inject(at = @At("HEAD"), method = "init")
    protected void init(CallbackInfo info) {
        int i = 11;
        this.addRenderableWidget(TIMER_LIMIT.createButton(Minecraft.getInstance().options, this.width / 2 - 155 + i % 2 * 160, this.height / 6 - 12 + 22 * (i >> 1), 150));
        this.addRenderableWidget(new ImageButton(
                this.width / 2 - 155 + i % 2 * 160 + 155,
                this.height / 6 - 12 + 22 * (i >> 1),
                20,
                20,
                0,
                0,
                20,
                new ResourceLocation("infinitemusic", "textures/buttons.png"),
                256,
                256,
                (button) -> Common.skip = true,
                (button, matrix, mouseX, mouseY) -> this.renderTooltip(matrix, new TranslatableComponent("option.skip"), mouseX, mouseY),
                TextComponent.EMPTY)
        );
    }
}