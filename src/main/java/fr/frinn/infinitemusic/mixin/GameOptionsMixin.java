package fr.frinn.infinitemusic.mixin;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import fr.frinn.infinitemusic.IExtendedGameOptions;
import net.minecraft.client.options.GameOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.*;

@Mixin(GameOptions.class)
public class GameOptionsMixin implements IExtendedGameOptions {

    @Shadow
    File optionsFile;

    private int timer;

    @Override
    public int getTimer() {
        return timer;
    }

    @Override
    public void setTimer(int timer) {
        this.timer = timer;
    }

    @Inject(method = "load()V", at = @At("HEAD"))
    private void load(CallbackInfo info) {
        try {
            if (!this.optionsFile.exists()) {
                return;
            }

            BufferedReader bufferedReader = Files.newReader(this.optionsFile, Charsets.UTF_8);
            Throwable var3 = null;

            try {
                bufferedReader.lines().forEach((s) -> {
                    if(s.contains("musicTimer")) {
                        this.timer = Integer.parseInt(s.replace("musicTimer:", ""));
                    }
                });
            } catch (Throwable var17) {
                var3 = var17;
                throw var17;
            } finally {
                if (bufferedReader != null) {
                    if (var3 != null) {
                        try {
                            bufferedReader.close();
                        } catch (Throwable var16) {
                            var3.addSuppressed(var16);
                        }
                    } else {
                        bufferedReader.close();
                    }
                }
            }
        } catch (Exception e) {

        }
    }

    @Inject(method = "write()V", at = @At("TAIL"))
    private void write(CallbackInfo info) {
        try {
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(this.optionsFile, true)));
            Throwable var2 = null;

            try {
                printWriter.println("musicTimer:" + this.timer);
            } catch (Throwable var15) {
                var15.printStackTrace();
                var2 = var15;
                throw var15;
            } finally {
                if (printWriter != null) {
                    if (var2 != null) {
                        try {
                            printWriter.close();
                        } catch (Throwable var14) {
                            var14.printStackTrace();
                            var2.addSuppressed(var14);
                        }
                    } else {
                        printWriter.close();
                    }
                }

            }
        } catch (Exception var17) {
            var17.printStackTrace();
        }
    }
}
