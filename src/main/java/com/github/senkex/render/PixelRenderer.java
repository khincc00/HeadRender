package com.github.senkex.render;

import com.github.senkex.color.ColorUtil;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Converts images into Minecraft chat pixel lines.
 *
 * <p>This renderer scans every pixel of a {@link BufferedImage}
 * and converts it into colored chat characters.</p>
 *
 * <p>
 * Developed by Senkex <br>
 * Powered by Nautic Studios
 * </p>
 *
 * @since 1.0.0
 */
public final class PixelRenderer {

    private PixelRenderer() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Converts an image into colored chat lines.
     *
     * @param image the source image
     * @param character the pixel character
     * @param hex whether HEX colors should be used
     * @return list of chat lines
     */
    public static List<String> renderImage(
            BufferedImage image,
            String character,
            boolean hex
    ) {

        int width = image.getWidth();
        int height = image.getHeight();

        List<String> lines = new ArrayList<>(height);

        for (int y = 0; y < height; y++) {

            StringBuilder builder = new StringBuilder(width * 14);

            for (int x = 0; x < width; x++) {

                int rgb = image.getRGB(x, y);
                Color color = new Color(rgb, true);

                if (color.getAlpha() < 10) {
                    builder.append(" ");
                    continue;
                }

                String colorCode;

                if (hex) {
                    colorCode = ColorUtil.toHex(color);
                } else {
                    colorCode = ColorUtil.toLegacy(color);
                }

                builder.append(colorCode).append(character);
            }

            lines.add(builder.toString());
        }

        return lines;
    }
}