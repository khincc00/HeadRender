package com.github.senkex.model;

/**
 * Represents the rendering configuration used by HeadRender.
 *
 * <p>This class defines how a skin or head should be rendered
 * when converted into chat pixels.</p>
 *
 * <p>
 * Developed by Senkex <br>
 * Powered by Nautic Studios
 * </p>
 *
 * @since 1.0.0
 */
public final class RenderOptions {

    private final int size;
    private final String character;
    private final boolean useHexColors;
    private final boolean helmetLayer;

    /**
     * Creates a new render configuration.
     *
     * @param size the render resolution (ex: 8, 16)
     * @param character the character used to represent pixels
     * @param useHexColors whether HEX colors should be used
     * @param helmetLayer whether the helmet/hat layer should be included
     */
    public RenderOptions(int size, String character, boolean useHexColors, boolean helmetLayer) {
        this.size = size;
        this.character = character;
        this.useHexColors = useHexColors;
        this.helmetLayer = helmetLayer;
    }

    /**
     * Returns the render size.
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the character used for pixel rendering.
     */
    public String getCharacter() {
        return character;
    }

    /**
     * Whether HEX colors should be used.
     */
    public boolean useHexColors() {
        return useHexColors;
    }

    /**
     * Whether the helmet/hat layer should be rendered.
     */
    public boolean useHelmetLayer() {
        return helmetLayer;
    }

    /**
     * Returns the default render configuration.
     *
     * Default values:
     * - size: 8
     * - character: █
     * - hex colors: true
     * - helmet layer: true
     */
    public static RenderOptions defaults() {
        return new RenderOptions(8, "█", true, true);
    }

}