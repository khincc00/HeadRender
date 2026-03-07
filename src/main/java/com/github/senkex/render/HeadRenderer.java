package com.github.senkex.render;

import com.github.senkex.image.ImageScaler;
import com.github.senkex.image.SkinFetcher;
import com.github.senkex.model.RenderOptions;
import org.bukkit.entity.Player;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

/**
 * Responsible for rendering player heads into Minecraft chat.
 *
 * <p>This renderer converts Minecraft skins into colored chat pixels
 * using the provided {@link RenderOptions} configuration.</p>
 *
 * <p>
 * Developed by Senkex <br>
 * Powered by Nautic Studios
 * </p>
 *
 * @since 1.0.0
 */
public final class HeadRenderer {

    private HeadRenderer() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    /**
     * Renders the given player's head to the viewer.
     *
     * @param viewer the player who will see the render
     * @param target the player whose head will be rendered
     * @param options render configuration
     */
    public static void render(Player viewer, String target, RenderOptions options) {

        try {

            BufferedImage head;

            if (options.useHelmetLayer()) {
                head = SkinFetcher.fetchHelm(target, options.getSize());
            } else {
                head = SkinFetcher.fetchHead(target, options.getSize());
            }

            BufferedImage scaled = ImageScaler.scalePixel(
                    head,
                    options.getSize(),
                    options.getSize()
            );

            List<String> lines = PixelRenderer.renderImage(
                    scaled,
                    options.getCharacter(),
                    options.useHexColors()
            );

            for (String line : lines) {
                viewer.sendMessage(line);
            }

        } catch (IOException exception) {
            viewer.sendMessage("§cFailed to render player head.");
            exception.printStackTrace();
        }

    }

    /**
     * Renders a player's own head using default render options.
     *
     * @param viewer the player
     */
    public static void render(Player viewer) {
        render(viewer, viewer.getName(), RenderOptions.defaults());
    }

}