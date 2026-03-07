package com.github.senkex.util;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

/**
 * Utility class for handling asynchronous and synchronous tasks
 * within the Bukkit scheduler.
 *
 * <p>This class simplifies task execution and avoids repetitive
 * scheduler boilerplate when running async operations such as
 * HTTP requests or image processing.</p>
 *
 * <p>
 * Developed by Senkex <br>
 * Powered by Nautic Studios
 * </p>
 *
 * @since 1.0.0
 */
public final class AsyncUtil {

    private static Plugin plugin;

    private AsyncUtil() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Initializes the scheduler utility with the plugin instance.
     *
     * This must be called once during plugin startup.
     *
     * @param pluginInstance the plugin instance
     */
    public static void init(Plugin pluginInstance) {
        plugin = pluginInstance;
    }

    /**
     * Runs a task asynchronously.
     *
     * Useful for:
     * - downloading skins
     * - processing images
     *
     * @param runnable the task
     */
    public static void runAsync(Runnable runnable) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, runnable);
    }

    /**
     * Runs a task synchronously on the main server thread.
     *
     * Required for:
     * - sending messages
     * - interacting with Bukkit API
     *
     * @param runnable the task
     */
    public static void runSync(Runnable runnable) {
        Bukkit.getScheduler().runTask(plugin, runnable);
    }

    /**
     * Runs a task asynchronously after a delay.
     *
     * @param runnable the task
     * @param delay the delay in ticks
     */
    public static void runAsyncLater(Runnable runnable, long delay) {
        Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, runnable, delay);
    }

    /**
     * Runs a task synchronously after a delay.
     *
     * @param runnable the task
     * @param delay the delay in ticks
     */
    public static void runSyncLater(Runnable runnable, long delay) {
        Bukkit.getScheduler().runTaskLater(plugin, runnable, delay);
    }

}