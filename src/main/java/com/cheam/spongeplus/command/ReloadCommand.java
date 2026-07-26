package com.cheam.spongeplus.command;

import com.cheam.spongeplus.config.ConfigManager;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public final class ReloadCommand {

    private ReloadCommand() {
    }

    public static int execute(CommandContext<ServerCommandSource> context) {

        ConfigManager.load();

        context.getSource().sendFeedback(
                () -> Text.literal("[Sponge+] Configuration reloaded successfully.")
                        .formatted(Formatting.GREEN),
                false
        );

        return Command.SINGLE_SUCCESS;
    }
}