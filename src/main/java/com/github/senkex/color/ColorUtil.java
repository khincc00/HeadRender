package com.github.senkex.color;

import org.bukkit.ChatColor;

import java.awt.Color;

/**
 * Utility responsible for converting RGB colors into
 * Minecraft chat color formats.
 *
 * <p>Supports:</p>
 * <ul>
 *     <li>HEX colors (1.16+)</li>
 *     <li>Legacy ChatColor fallback</li>
 * </ul>
 *
 * <p>
 * Developed by Senkex <br>
 * Powered by Nautic Studios
 * </p>
 *
 * @since 1.0.0
 */
public final class ColorUtil {

    private ColorUtil() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Converts a color into Minecraft HEX chat format.
     *
     * Example output:
     * §x§f§f§0§0§0§0
     *
     * @param color the color
     * @return the hex chat color string
     */
    public static String toHex(Color color) {

        String hex = String.format("%06x", color.getRGB() & 0xFFFFFF);

        StringBuilder builder = new StringBuilder(14);
        builder.append("§x");

        for (char c : hex.toCharArray()) {
            builder.append('§').append(c);
        }

        return builder.toString();
    }

    /**
     * Converts a color into the closest legacy ChatColor.
     *
     * @param color the color
     * @return legacy chat color
     */
    public static String toLegacy(Color color) {
        return getClosest(color).toString();
    }

    /**
     * Finds the closest ChatColor to the given RGB color.
     *
     * @param color the RGB color
     * @return closest ChatColor
     */
    private static ChatColor getClosest(Color color) {

        ChatColor closest = ChatColor.WHITE;
        double bestDistance = Double.MAX_VALUE;

        for (ChatColor chatColor : LEGACY_COLORS) {

            Color compare = LEGACY_RGB[chatColor.ordinal()];

            double distance = colorDistance(color, compare);

            if (distance < bestDistance) {
                bestDistance = distance;
                closest = chatColor;
            }
        }

        return closest;
    }

    /**
     * Calculates RGB color distance.
     */
    private static double colorDistance(Color c1, Color c2) {

        int r = c1.getRed() - c2.getRed();
        int g = c1.getGreen() - c2.getGreen();
        int b = c1.getBlue() - c2.getBlue();

        return r * r + g * g + b * b;
    }

    /**
     * Legacy Minecraft chat colors.
     */
    private static final ChatColor[] LEGACY_COLORS = {
            ChatColor.BLACK,
            ChatColor.DARK_BLUE,
            ChatColor.DARK_GREEN,
            ChatColor.DARK_AQUA,
            ChatColor.DARK_RED,
            ChatColor.DARK_PURPLE,
            ChatColor.GOLD,
            ChatColor.GRAY,
            ChatColor.DARK_GRAY,
            ChatColor.BLUE,
            ChatColor.GREEN,
            ChatColor.AQUA,
            ChatColor.RED,
            ChatColor.LIGHT_PURPLE,
            ChatColor.YELLOW,
            ChatColor.WHITE
    };

    /**
     * RGB equivalents for legacy colors.
     */
    private static final Color[] LEGACY_RGB = {
            new Color(0, 0, 0),
            new Color(0, 0, 170),
            new Color(0, 170, 0),
            new Color(0, 170, 170),
            new Color(170, 0, 0),
            new Color(170, 0, 170),
            new Color(255, 170, 0),
            new Color(170, 170, 170),
            new Color(85, 85, 85),
            new Color(85, 85, 255),
            new Color(85, 255, 85),
            new Color(85, 255, 255),
            new Color(255, 85, 85),
            new Color(255, 85, 255),
            new Color(255, 255, 85),
            new Color(255, 255, 255)
    };
}