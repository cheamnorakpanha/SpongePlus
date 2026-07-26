package com.cheam.spongeplus;

import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cheam.spongeplus.command.SpongeCommand;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class SpongePlus implements ModInitializer {
    public static final String MOD_ID = "sponge-plus";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("=================================");
        LOGGER.info(" Sponge+ has been loaded!");
        LOGGER.info(" Welcome to Sponge+ Development");
        LOGGER.info("=================================");

        LOGGER.info("Initializing Sponge+!");

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            SpongeCommand.register(dispatcher);
        });

        LOGGER.info("Sponge+ initialized.");
    }

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }
}
