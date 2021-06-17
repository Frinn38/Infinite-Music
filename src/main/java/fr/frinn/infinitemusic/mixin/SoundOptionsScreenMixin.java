package fr.frinn.infinitemusic.mixin;

import fr.frinn.infinitemusic.InfiniteConfig;
import fr.frinn.infinitemusic.InfiniteMusic;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.ConfigManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.screen.option.SoundOptionsScreen;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.client.option.DoubleOption;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SoundOptionsScreen.class)
public class SoundOptionsScreenMixin extends GameOptionsScreen {

    private static final DoubleOption TIMER_LIMIT = new DoubleOption("options.musicTimer", 0.0D, 300.0D, 5.0F, (settings) ->
            (double)InfiniteConfig.getInstance().timer
    , (settings, value) -> {
        InfiniteConfig.getInstance().timer = value.intValue();
        ConfigHolder<InfiniteConfig> holder = AutoConfig.getConfigHolder(InfiniteConfig.class);
        if(holder instanceof ConfigManager)
            ((ConfigManager<InfiniteConfig>)holder).save();
    }, (settings, slider) -> new TranslatableText("options.musicTimer").append(": ").append(InfiniteConfig.getInstance().timer + "s"));

    public SoundOptionsScreenMixin(Screen parent, GameOptions gameOptions, Text title) {
        super(parent, gameOptions, title);
    }

    @Inject(at = @At("HEAD"), method = "init()V")
    protected void init(CallbackInfo info) {
        // TODO: 1.17 removed this method, must replace
        
        // int i = 11;
        // this.addButton(TIMER_LIMIT.createButton(MinecraftClient.getInstance().options, this.width / 2 - 155 + i % 2 * 160, this.height / 6 - 12 + 24 * (i >> 1), 150));
        // this.addButton(new TexturedButtonWidget(
        //                 this.width / 2 - 155 + i % 2 * 160 + 155,
        //                 this.height / 6 - 12 + 24 * (i >> 1),
        //                 20,
        //                 20,
        //                 0,
        //                 0,
        //                 20,
        //                 new Identifier("infinitemusic", "textures/gui/buttons.png"),
        //                 (button) -> InfiniteMusic.skip = true
        //         )
        // );
    }
}
