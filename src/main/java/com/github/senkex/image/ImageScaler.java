package com.github.senkex.image;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Utility class responsible for scaling {@link BufferedImage} instances.
 * <p>
 * This scaler provides two different algorithms depending on the use-case:
 * <ul>
 *     <li><b>Smooth scaling</b> – High quality resizing using {@link RenderingHints}.</li>
 *     <li><b>Pixel scaling</b> – Fast nearest-neighbour scaling ideal for pixel-art images.</li>
 * </ul>
 *
 * <p>This class is mainly used internally by HeadRender to resize Minecraft
 * player skins into small pixel matrices (for chat rendering).
 *
 * <p>
 * Developed by <b>Senkex</b><br>
 * Powered by <b>Nautic Studios</b>
 *
 * @since 1.0.0
 */
public final class ImageScaler {

    private ImageScaler() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }

    /**
     * Scales the given image using a high-quality algorithm.
     *
     * @param source the source image
     * @param width  target width
     * @param height target height
     * @return a new scaled {@link BufferedImage}
     *
     * @since 1.0.0
     */
    public static BufferedImage scaleSmooth(
            final BufferedImage source,
            final int width,
            final int height
    ) {

        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Target dimensions must be greater than zero.");
        }

        BufferedImage scaled = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D graphics = scaled.createGraphics();

        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        graphics.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        graphics.drawImage(source, 0, 0, width, height, null);
        graphics.dispose();

        return scaled;
    }

    /**
     * Scales the given image using nearest-neighbour scaling.
     * <p>
     * This method is recommended for pixel-art images such as Minecraft skins
     * because it preserves sharp pixel edges.
     *
     * @param source the source image
     * @param width  target width
     * @param height target height
     * @return the scaled image
     *
     * @since 1.0.0
     */
    public static BufferedImage scalePixel(
            final BufferedImage source,
            final int width,
            final int height
    ) {

        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Target dimensions must be greater than zero.");
        }

        BufferedImage scaled = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D graphics = scaled.createGraphics();

        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);

        graphics.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_SPEED);

        graphics.drawImage(source, 0, 0, width, height, null);
        graphics.dispose();

        return scaled;
    }

    /**
     * Automatically scales the image keeping its aspect ratio.
     *
     * @param source the source image
     * @param targetSize the maximum target dimension
     * @return the scaled image
     *
     * @since 1.0.0
     */
    public static BufferedImage scaleAuto(
            final BufferedImage source,
            final int targetSize
    ) {

        int width = source.getWidth();
        int height = source.getHeight();

        float ratio = Math.min(
                (float) targetSize / width,
                (float) targetSize / height
        );

        int newWidth = Math.round(width * ratio);
        int newHeight = Math.round(height * ratio);

        return scalePixel(source, newWidth, newHeight);
    }

}