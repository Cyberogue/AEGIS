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
package org.aegis.ui;

/**
 * An item which functions as a movable frame within a larger world, keeping
 * track of its location within the world as updated
 *
 * @author Rogue <Alice Q.>
 */
public class Viewport {

    // VIEWPORT WIDTH AND HEIGHT
    private float vpWidth;
    private float vpHeight;

    // VIEWPORT X AND Y within the world
    private float worldX;
    private float worldY;

    // WORLD EDGES
    private float minOffsetX;
    private float maxOffsetX;
    private float minOffsetY;
    private float maxOffsetY;

    /**
     * Constructor
     *
     * @param vpWidth the immutable width of the viewport
     * @param vpHeight the immutable height of the viewport
     */
    public Viewport(float vpWidth, float vpHeight) {
        this.vpWidth = vpWidth;
        this.vpHeight = vpHeight;

        minOffsetX = Float.MIN_VALUE;
        minOffsetY = Float.MIN_VALUE;
        maxOffsetX = Float.MAX_VALUE;
        maxOffsetY = Float.MAX_VALUE;
    }

    /**
     * Attempts the move the viewport the amount indicated towards the right
     * unless the viewport has reached its maximum or minimum offset
     *
     * @param amount the amount to move
     * @return the actual amount moved. This will differ from the amount passed
     * in the case that it has reached a limit
     */
    public float moveX(float amount) {
        float newX = worldX + amount;

        if (newX > maxOffsetX) {
            float moved = maxOffsetX - worldX;
            worldX = maxOffsetX;
            return moved;
        } else if (newX < minOffsetX) {
            float moved = minOffsetX - worldX;
            worldX = minOffsetX;
            return moved;
        }

        worldX = newX;
        return amount;
    }

    /**
     * Attempts the move the viewport the amount indicated towards the bottom
     * unless the viewport has reached its maximum or minimum offset
     *
     * @param amount the amount to move
     * @return the actual amount moved. This will differ from the amount passed
     * in the case that it has reached a limit
     */
    public float moveY(float amount) {
        float newY = worldY + amount;

        if (newY > maxOffsetY) {
            float moved = maxOffsetY - worldY;
            worldY = maxOffsetY;
            return moved;
        } else if (newY < minOffsetY) {
            float moved = minOffsetY - worldY;
            worldY = minOffsetY;
            return moved;
        }

        worldY = newY;
        return amount;
    }

    /**
     * Moves the viewport to a new location within the world
     *
     * @param worldX the x coordinate to move to
     * @param worldY the y coordinate to move to
     * @throws IllegalArgumentException when an attempt is made to move the
     * viewport outside the allowable range
     */
    public void moveTo(float worldX, float worldY) {
        if (worldX > maxOffsetX || worldX < minOffsetX || worldY > maxOffsetY || worldY < minOffsetY) {
            throw new IllegalArgumentException("Unable to move a viewport outside its allowable range");
        }
        this.worldX = worldX;
        this.worldY = worldY;
    }

    /**
     * Returns the viewport's global x position
     *
     * @return the viewport's global x position
     */
    public float getOffsetX() {
        return worldX;
    }

    /**
     * Returns the viewport's global y position
     *
     * @return the viewport's global y position
     */
    public float getOffsety() {
        return worldY;
    }

    /**
     * Sets the maximum and minimum offsets for the viewport, guaranteeing that
     * the viewport will never be offset outside this range. By default, these
     * are simply set to the minimum and maximum float values.
     *
     * @param minX the lowest x offset allowable (including negatives)
     * @param maxX the highest x offset allowable
     * @param minY the lowest y offset allowable (including negatives)
     * @param maxY the highest y offset allowable
     */
    public void limitOffset(float minX, float maxX, float minY, float maxY) {
        minOffsetX = minX;
        maxOffsetX = maxX;
        minOffsetY = minY;
        maxOffsetY = maxY;
    }

    @Override
    public String toString() {
        return "[" + vpWidth + 'x' + vpHeight + " @ " + worldX + ',' + worldY + ']';
    }
}
