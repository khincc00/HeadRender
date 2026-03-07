package com.github.senkex.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Responsible for fetching Minecraft player skins and head renders from remote providers.
 *
 * <p>This implementation currently uses the Minotar service to retrieve player
 * avatars and skins. The fetched images are returned as {@link BufferedImage}
 * instances ready for processing or rendering.</p>
 *
 * <p>
 * Developed by Senkex <br>
 * Powered by Nautic Studios
 * </p>
 *
 * @since 1.0.0
 */
public final class SkinFetcher {

    private static final String MINOTAR_AVATAR =
            "https://minotar.net/avatar/%s/%d.png";

    private static final String MINOTAR_HELM =
            "https://minotar.net/helm/%s/%d.png";

    private static final String MINOTAR_SKIN =
            "https://minotar.net/skin/%s";

    private static final int TIMEOUT = 5000;

    private SkinFetcher() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    /**
     * Fetches the player's avatar (head without helmet overlay).
     *
     * @param player the player name
     * @param size the desired image size
     * @return the fetched image
     * @throws IOException if the request fails
     */
    public static BufferedImage fetchHead(String player, int size) throws IOException {
        String url = String.format(MINOTAR_AVATAR, player, size);
        return fetchImage(url);
    }

    /**
     * Fetches the player's head including helmet/hat layer.
     *
     * @param player the player name
     * @param size the desired image size
     * @return the fetched image
     * @throws IOException if the request fails
     */
    public static BufferedImage fetchHelm(String player, int size) throws IOException {
        String url = String.format(MINOTAR_HELM, player, size);
        return fetchImage(url);
    }

    /**
     * Fetches the player's full skin texture.
     *
     * @param player the player name
     * @return the skin image
     * @throws IOException if the request fails
     */
    public static BufferedImage fetchSkin(String player) throws IOException {
        String url = String.format(MINOTAR_SKIN, player);
        return fetchImage(url);
    }

    /**
     * Fetches an image from the given URL.
     *
     * @param urlString the image URL
     * @return the loaded image
     * @throws IOException if the connection or read fails
     */
    private static BufferedImage fetchImage(String urlString) throws IOException {

        URL url = new URL(urlString);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(TIMEOUT);
        connection.setReadTimeout(TIMEOUT);
        connection.setUseCaches(true);
        connection.setRequestProperty("User-Agent", "HeadRender/NauticStudios");

        try {
            return ImageIO.read(connection.getInputStream());
        } finally {
            connection.disconnect();
        }
    }
}