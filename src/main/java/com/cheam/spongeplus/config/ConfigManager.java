package com.cheam.spongeplus.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class ConfigManager {

    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    private static final Path CONFIG_PATH = FabricLoader.getInstance()
            .getConfigDir()
            .resolve("spongeplus.json");

    private ConfigManager() {
    }

    public static void load() {
        try {

            if (!Files.exists(CONFIG_PATH)) {
                save();
                return;
            }

            String json = Files.readString(CONFIG_PATH);

            ConfigData data = GSON.fromJson(json, ConfigData.class);

            if (data == null) {
                data = new ConfigData();
            }

            try {
                SpongeConfig.setRadius(data.radius);
                SpongeConfig.setAbsorbLava(data.absorbLava);

            } catch (IllegalArgumentException e) {

                SpongeConfig.reset();
                save();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        try {

            // Ensure the config directory exists
            Files.createDirectories(CONFIG_PATH.getParent());

            ConfigData data = new ConfigData();

            data.radius = SpongeConfig.getRadius();
            data.absorbLava = SpongeConfig.shouldAbsorbLava();

            String json = GSON.toJson(data);

            Files.writeString(CONFIG_PATH, json);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}