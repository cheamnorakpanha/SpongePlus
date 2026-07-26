package com.cheam.spongeplus.command;

import com.cheam.spongeplus.config.SpongeConfig;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public final class SpongeCommand {

    private SpongeCommand() {
    }

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {

        dispatcher.register(
                CommandManager.literal("sponge")

                        // Only OPs (permission level 2+) can use Sponge+ commands
                        .requires(CommandManager.requirePermissionLevel(CommandManager.GAMEMASTERS_CHECK))

                        .then(
                                CommandManager.literal("radius")
                                        .then(
                                                CommandManager.argument(
                                                                "value",
                                                                IntegerArgumentType.integer(
                                                                        SpongeConfig.MIN_RADIUS,
                                                                        SpongeConfig.MAX_RADIUS
                                                                )
                                                        )
                                                        .executes(RadiusCommand::execute)
                                        )
                        )

                        .then(
                                CommandManager.literal("info")
                                        .executes(InfoCommand::execute)
                        )

                        .then(
                                CommandManager.literal("reload")
                                        .executes(ReloadCommand::execute)
                        )
        );
    }
}