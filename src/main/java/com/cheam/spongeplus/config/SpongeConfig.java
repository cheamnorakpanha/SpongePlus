package com.cheam.spongeplus.config;

public final class SpongeConfig {

    public static final int DEFAULT_RADIUS = 6;
    public static final int MIN_RADIUS = 6;
    public static final int MAX_RADIUS = 64;

    private static int radius = DEFAULT_RADIUS;

    private SpongeConfig() {
        // Prevent instantiation
    }

    public static int getRadius() {
        return radius;
    }

    /**
     * Sets the sponge radius.
     *
     * @throws IllegalArgumentException if the radius is outside the valid range.
     */
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

    public static void resetRadius() {
        radius = DEFAULT_RADIUS;
    }

    public static boolean isValidRadius(int value) {
        return value >= MIN_RADIUS && value <= MAX_RADIUS;
    }
}