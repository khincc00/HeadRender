package com.github.senkex.cache;

import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Simple in-memory cache for storing player skins.
 *
 * <p>This cache prevents repeated HTTP requests when rendering
 * the same player's head multiple times.</p>
 *
 * <p>
 * Developed by Senkex <br>
 * Powered by Nautic Studios
 * </p>
 *
 * @since 1.0.0
 */
public final class SkinCache {

    private static final Map<String, CacheEntry> CACHE = new ConcurrentHashMap<>();

    /**
     * Default cache lifetime (10 minutes).
     */
    private static final long CACHE_TTL = 10 * 60 * 1000;

    private SkinCache() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Retrieves a cached skin if it exists and is still valid.
     *
     * @param player player name
     * @return cached image or null
     */
    public static BufferedImage get(String player) {

        CacheEntry entry = CACHE.get(player.toLowerCase());

        if (entry == null) {
            return null;
        }

        if (System.currentTimeMillis() > entry.expireAt) {
            CACHE.remove(player.toLowerCase());
            return null;
        }

        return entry.image;
    }

    /**
     * Stores a skin in the cache.
     *
     * @param player player name
     * @param image skin image
     */
    public static void put(String player, BufferedImage image) {

        long expire = System.currentTimeMillis() + CACHE_TTL;

        CACHE.put(player.toLowerCase(), new CacheEntry(image, expire));
    }

    /**
     * Clears all cached skins.
     */
    public static void clear() {
        CACHE.clear();
    }

    /**
     * Returns the current cache size.
     *
     * @return number of cached skins
     */
    public static int size() {
        return CACHE.size();
    }

    /**
     * Internal cache entry model.
     */
    private static class CacheEntry {

        private final BufferedImage image;
        private final long expireAt;

        CacheEntry(BufferedImage image, long expireAt) {
            this.image = image;
            this.expireAt = expireAt;
        }
    }
}