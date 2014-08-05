/*
 * The MIT License
 *
 * Copyright 2014 Rogue <Alice Q.>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.aegis2d;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * Simple class which functions as a Decal with additional functionality in the
 * form of scalability and local coordinates
 *
 * @author Rogue <Alice Q.>
 */
public class DynamicDecal extends Decal {

    // X AND Y COORDINATES
    private float worldX;
    private float worldY;

    // SCALING FACTOR
    private float width;
    private float height;

    /**
     * Constructor for a DynamicDecal from a pre-loaded Decal located at 0,0
     *
     * @param decal the Decal to wrap this class around
     */
    public DynamicDecal(Decal decal) {
        this(decal.getImage(), 0, 0);
    }

    /**
     * * Constructor for a DynamicDecal from a pre-loaded Decal located at 0,0
     *
     * @param decal the Decal to wrap this class around
     * @param initX the initial x position
     * @param initY the initial y position
     *
     */
    public DynamicDecal(Decal decal, float initX, float initY) {
        this(decal.getImage(), initX, initY);
    }

    /**
     * * Constructor for a DynamicDecal from a pre-loaded Decal located at 0,0
     *
     * @param decal the Decal to wrap this class around
     * @param initX the initial x position
     * @param initY the initial y position
     * @param initHeight the initial x scaling
     * @param initWidth the initial y scaling
     */
    public DynamicDecal(Decal decal, float initX, float initY, float initWidth, float initHeight) {
        this(decal.getImage(), initX, initY, initWidth, initHeight);
    }

    /**
     * Constructor for a DynamicDecal from a pre-loaded BufferedImage located at
     * 0,0
     *
     * @param image the BufferedImage to wrap this class around
     */
    public DynamicDecal(BufferedImage image) {
        this(image, 0, 0);
    }

    /**
     * Constructor for a DynamicDecal from a pre-loaded BufferedImage
     *
     * @param image the BufferedImage to wrap this class around
     * @param initX the initial x position
     * @param initY the initial y position
     */
    public DynamicDecal(BufferedImage image, float initX, float initY) {
        this(image, initX, initY, image.getWidth(), image.getHeight());
    }

    /**
     * Constructor for a DynamicDecal from a pre-loaded BufferedImage
     *
     * @param image the BufferedImage to wrap this class around
     * @param initX the initial x position
     * @param initY the initial y position
     * @param initHeight the initial x scaling
     * @param initWidth the initial y scaling
     */
    public DynamicDecal(BufferedImage image, float initX, float initY, float initHeight, float initWidth) {
        super(image);
        this.worldX = initX;
        this.worldY = initY;
        this.width = initWidth;
        this.height = initHeight;
    }

    /**
     * Returns the x position of the DynamicDecal
     *
     * @return the x position of the DynamicDecal
     */
    public float x() {
        return worldX;
    }

    /**
     * Returns the y position of the DynamicDecal
     *
     * @return the y position of the DynamicDecal
     */
    public float y() {
        return worldY;
    }

    /**
     * Moves the DynamicDecal a certain amount to the right
     *
     * @param amount the amount to move
     * @return the new x position
     */
    public float moveX(float amount) {
        return worldX += amount;
    }

    /**
     * Moves the DynamicDecal a certain amount towards the bottom
     *
     * @param amount the amount to move
     * @return the new y position
     */
    public float moveY(float amount) {
        return worldY += amount;
    }

    /**
     * Moves the DynamicDecal a specified distance to the right and downwards
     *
     * @param dX the amount to move towards the right
     * @param dY the amount to move down
     */
    public void move(float dX, float dY) {
        worldX += dX;
        worldY += dY;
    }

    /**
     * Moves the DynamicDecal to a new absolute position
     *
     * @param worldX the x coordinate to move to
     * @param worldY the y coordinate to move to
     */
    public void moveTo(float worldX, float worldY) {
        this.worldX = worldX;
        this.worldY = worldY;
    }

    /**
     * Resizes the image
     *
     * @param width
     * @param height
     */
    public void resize(float width, float height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Re-scales the image by a given percent
     *
     * @param scaleX the percent to rescale to in the x direction
     * @param scaleY the percent to rescale to in the y direction
     */
    public void rescale(float scaleX, float scaleY) {
        width *= scaleX;
        height *= scaleY;
    }

    @Override
    public void render(Graphics g, float offsetX, float offsetY) {
        g.drawImage(super.getImage(), (int) (worldX + offsetX), (int) (worldY + offsetY), (int) width, (int) height, null);
    }

    @Override
    public String toString() {
        return super.toString() + '@' + worldX + ',' + worldY;
    }
}
