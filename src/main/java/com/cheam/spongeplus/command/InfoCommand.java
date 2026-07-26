package com.cheam.spongeplus.command;

import com.cheam.spongeplus.config.SpongeConfig;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public final class InfoCommand {

    private InfoCommand() {
    }

    public static int execute(CommandContext<ServerCommandSource> context) {

        String lavaStatus = SpongeConfig.shouldAbsorbLava()
                ? "Enabled"
                : "Disabled";

        Formatting lavaColor = SpongeConfig.shouldAbsorbLava()
                ? Formatting.GREEN
                : Formatting.RED;

        context.getSource().sendFeedback(
                () -> Text.literal("========== Sponge+ ==========")
                        .formatted(Formatting.GOLD),
                false
        );

        context.getSource().sendFeedback(
                () -> Text.literal("Radius: ")
                        .formatted(Formatting.YELLOW)
                        .append(Text.literal(String.valueOf(SpongeConfig.getRadius()))
                                .formatted(Formatting.WHITE)),
                false
        );

        context.getSource().sendFeedback(
                () -> Text.literal("Lava Absorption: ")
                        .formatted(Formatting.YELLOW)
                        .append(Text.literal(lavaStatus)
                                .formatted(lavaColor)),
                false
        );

        context.getSource().sendFeedback(
                () -> Text.literal("=============================")
                        .formatted(Formatting.GOLD),
                false
        );

        return Command.SINGLE_SUCCESS;
    }
}