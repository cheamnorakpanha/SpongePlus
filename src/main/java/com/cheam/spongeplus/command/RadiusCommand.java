package com.cheam.spongeplus.command;

import com.cheam.spongeplus.config.ConfigManager;
import com.cheam.spongeplus.config.SpongeConfig;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public final class RadiusCommand {

    private RadiusCommand() {
    }

    public static int execute(CommandContext<ServerCommandSource> context) {

        int radius = IntegerArgumentType.getInteger(context, "value");

        SpongeConfig.setRadius(radius);

        ConfigManager.save();

        context.getSource().sendFeedback(() -> Text.literal("[Sponge+] Radius updated to " + radius).formatted(Formatting.GREEN), false);

        return Command.SINGLE_SUCCESS;

    }

}