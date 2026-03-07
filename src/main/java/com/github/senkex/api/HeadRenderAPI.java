package com.github.senkex.api;

import com.github.senkex.model.RenderOptions;
import com.github.senkex.render.HeadRenderer;
import org.bukkit.entity.Player;

/**
 * Public API entry point for HeadRender.
 *
 * <p>This class exposes simple methods for rendering
 * Minecraft player heads directly into chat.</p>
 *
 * <p>
 * Developed by Senkex <br>
 * Powered by Nautic Studios
 * </p>
 *
 * @since 1.0.0
 */
public final class HeadRenderAPI {

    private HeadRenderAPI() {
        throw new UnsupportedOperationException("API class");
    }

    /**
     * Renders the viewer's own head.
     *
     * @param player the player
     */
    public static void renderHead(Player player) {
        HeadRenderer.render(player);
    }

    /**
     * Renders a specific player's head using default options.
     *
     * @param viewer the viewer
     * @param target the target player name
     */
    public static void renderHead(Player viewer, String target) {
        HeadRenderer.render(viewer, target, RenderOptions.defaults());
    }

    /**
     * Renders a player's head with custom render options.
     *
     * @param viewer the viewer
     * @param target the target player name
     * @param options render options
     */
    public static void renderHead(Player viewer, String target, RenderOptions options) {
        HeadRenderer.render(viewer, target, options);
    }

}