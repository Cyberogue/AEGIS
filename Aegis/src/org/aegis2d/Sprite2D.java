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
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.aegis.ui.RenderItem;

/**
 * Simple class which contains a reference to a graphic as well as its X and Y
 * coordinates
 *
 * @author Rogue <Alice Q.>
 */
public class Sprite2D implements RenderItem {

    // THE IMAGE
    private Decal graphic;

    // X AND Y COORDINATES
    private float worldX;
    private float worldY;

    /**
     * Creates a new Sprite2D from an image located at 0,0
     *
     * @param imgDir the directory of the image to load
     * @throws IOException when there is a problem reading the image file
     */
    public Sprite2D(String imgDir) throws IOException {
        this(ImageIO.read(new File(imgDir)));
    }

    /**
     * Creates a new Sprite2D from an image
     *
     * @param imgDir the directory of the image to load
     * @param initX the initial x position
     * @param initY the initial y position
     * @throws IOException when there is a problem reading the image file
     */
    public Sprite2D(String imgDir, float initX, float initY) throws IOException {
        this(ImageIO.read(new File(imgDir)), initX, initY);
    }

    /**
     * Constructor for a Sprite2D from a pre-loaded BufferedImage located at 0,0
     *
     * @param image the BufferedImage to wrap this class around
     */
    public Sprite2D(BufferedImage image) {
        this.graphic = new Decal(image);
        this.worldX = 0.0f;
        this.worldY = 0.0f;
    }

    /**
     * Constructor for a Sprite2D from a pre-loaded BufferedImage
     *
     * @param image the BufferedImage to wrap this class around
     * @param initX the initial x position
     * @param initY the initial y position
     */
    public Sprite2D(BufferedImage image, float initX, float initY) {
        this.graphic = new Decal(image);
        this.worldX = initX;
        this.worldY = initY;
    }

    /**
     * Constructor for a Sprite2D from a pre-loaded BufferedImage located at 0,0
     *
     * @param decal a pre-defined Decal which represents the Sprite's render
     * image
     */
    public Sprite2D(Decal decal) {
        this.graphic = decal;
        this.worldX = 0.0f;
        this.worldY = 0.0f;
    }

    /**
     * Constructor for a Sprite2D from a pre-loaded BufferedImage
     *
     * @param decal a pre-defined Decal which represents the Sprite's render
     * image
     * @param initX the initial x position
     * @param initY the initial y position
     */
    public Sprite2D(Decal decal, float initX, float initY) {
        this.graphic = decal;
        this.worldX = initX;
        this.worldY = initY;
    }

    /**
     * Returns the x position of the Sprite2D
     *
     * @return the x position of the Sprite2D
     */
    public float x() {
        return worldX;
    }

    /**
     * Returns the y position of the Sprite2D
     *
     * @return the y position of the Sprite2D
     */
    public float y() {
        return worldY;
    }

    /**
     * Returns the buffered image being drawn by the Sprite2D
     *
     * @return the buffered image being drawn by the Sprite2D
     */
    public Decal getDecal() {
        return graphic;
    }

    /**
     * Moves the Sprite2D a certain amount to the right
     *
     * @param amount the amount to move
     * @return the new x position
     */
    public float moveX(float amount) {
        return worldX += amount;
    }

    /**
     * Moves the Sprite2D a certain amount towards the bottom
     *
     * @param amount the amount to move
     * @return the new y position
     */
    public float moveY(float amount) {
        return worldY += amount;
    }

    /**
     * Moves the Sprite2D a specified distance to the right and downwards
     *
     * @param dX the amount to move towards the right
     * @param dY the amount to move down
     */
    public void move(float dX, float dY) {
        worldX += dX;
        worldY += dY;
    }

    /**
     * Moves the Sprite2D to a new absolute position
     *
     * @param worldX the x coordinate to move to
     * @param worldY the y coordinate to move to
     */
    public void moveTo(float worldX, float worldY) {
        this.worldX = worldX;
        this.worldY = worldY;
    }

    @Override
    public void render(Graphics g, float offsetX, float offsetY) {
        graphic.render(g, (int) (worldX + offsetX), (int) (worldY + offsetY));
    }

    @Override
    public String toString() {
        return graphic.toString() + '@' + worldX + ',' + worldY;
    }
}
