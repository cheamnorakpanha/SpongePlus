package com.cheam.spongeplus.config;

public final class SpongeConfig {

    public static final int DEFAULT_RADIUS = 10;
    public static final int MIN_RADIUS = 10;
    public static final int MAX_RADIUS = 1000;

    public static final boolean DEFAULT_ABSORB_LAVA = true;

    private static int radius = DEFAULT_RADIUS;
    private static boolean absorbLava = DEFAULT_ABSORB_LAVA;

    private SpongeConfig() {
    }

    public static int getRadius() {
        return radius;
    }

    public static void setRadius(int value) {
        if (!isValidRadius(value)) {
            throw new IllegalArgumentException(
                    "Radius must be between "
                            + MIN_RADIUS
                            + " and "
                            + MAX_RADIUS
                            + "."
            );
        }

        radius = value;
    }

    public static boolean shouldAbsorbLava() {
        return absorbLava;
    }

    public static void setAbsorbLava(boolean value) {
        absorbLava = value;
    }

    public static void reset() {
        radius = DEFAULT_RADIUS;
        absorbLava = DEFAULT_ABSORB_LAVA;
    }

    public static boolean isValidRadius(int value) {
        return value >= MIN_RADIUS && value <= MAX_RADIUS;
    }
}