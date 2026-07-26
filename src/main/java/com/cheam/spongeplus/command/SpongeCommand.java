package com.cheam.spongeplus.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class SpongeCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {

        dispatcher.register(CommandManager.literal("sponge").then(CommandManager.literal("hello").executes(context -> {
            context.getSource().sendFeedback(() -> Text.literal("Hello from Sponge+!"), false);
            return 1;
        })));
    }
}