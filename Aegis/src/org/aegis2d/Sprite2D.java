/*
 * The MIT License
 *
 * Copyright 2014 Rogue <Alice Q>.
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.aegis.ui.RenderItem;

/**
 * Class which functions as the main graphical component for graphical sprites
 * with multiple animations. However, this does not allow rescaling.
 *
 * @author Rogue <Alice Q>
 */
public class Sprite2D implements RenderItem {

    // WORLD X AND Y
    private float worldX;
    private float worldY;

    // ALL THE ANIMATIONS AND iMAGES
    private List<AnimatedGraphic> animations;

    // THE CURRENT STATE
    private AnimatedGraphic currentState;

    // USED TO PAUSE THE ANIMATION
    private boolean running;

    /**
     * Constructor for an empty Sprite2D initialized at the origin
     */
    public Sprite2D() {
        this(0, 0);
    }

    /**
     * Constructor for an empty Sprite2D
     *
     * @param initX the initial x coordinate
     * @param initY the initial y coordinate
     */
    public Sprite2D(int initX, int initY) {
        animations = new ArrayList();
        worldX = initX;
        worldY = initY;
        running = true;
    }

    /**
     * Sets the new x coordinate
     *
     * @param worldX the new x coordinate
     */
    public void setWorldX(float worldX) {
        this.worldX = worldX;
    }

    /**
     * Sets the new y coordinate
     *
     * @param worldY the new y coordinate
     */
    public void setWorldY(float worldY) {
        this.worldY = worldY;
    }

    /**
     * Simultaneously sets both the x and y coordinates
     *
     * @param worldX the new x coordinate
     * @param worldY the new y coordinate
     */
    public void setWorldCoordinates(float worldX, float worldY) {
        this.worldX = worldX;
        this.worldY = worldY;
    }

    /**
     * Returns the current world x coordinate
     *
     * @return the current world x coordinate
     */
    public float getWorldX() {
        return worldX;
    }

    /**
     * Returns the current world y coordinate
     *
     * @return the current world y coordinate
     */
    public float getWorldY() {
        return worldY;
    }

    /**
     * Adds a animation (or AnimatedGraphic) to the internal structure
     * containing all the different animations the Sprite2D can be in,
     * initializing the current animation to this if it hasn't been already
     * initialized
     *
     * @param animation the animation to add
     * @return the total number of animations
     */
    public int addState(AnimatedGraphic animation) {
        if (currentState == null) {
            currentState = animation;
        }
        animations.add(animation);
        return animations.size();
    }

    /**
     * Adds a collection of animations (or AnimatedGraphics) to the internal
     * structure containing all the different animations the Sprite2D can be in,
     * initializing the current animation to the first animation in the
     * structure if it hasn't already been initialized
     *
     * @param animations a Collection of all the animations to add
     * @return the total number of animations
     */
    public int addStates(Collection<AnimatedGraphic> animations) {
        this.animations.addAll(animations);
        if (currentState == null) {
            currentState = this.animations.get(0);
        }
        return this.animations.size();
    }

    /**
     * Changes the current animation for another one
     *
     * @param index the index of the animation to switch to
     * @throws IndexOutOfBoundsException if the specified index is outside the
     * valid range
     */
    public void setCurrentAnimation(int index) {
        if (index < 0 || index >= animations.size()) {
            throw new IndexOutOfBoundsException();
        }
        currentState = animations.get(index);
        currentState.reset();
    }

    /**
     * Gets the animation associated with a given index, allowing for
     * modifications
     *
     * @param index the index of the animation to retrieve
     * @return the animation at the given position
     * @throws IndexOutOfBoundsException if the specified index is outside the
     * valid range
     */
    public AnimatedGraphic getState(int index) {
        if (index < 0 || index >= animations.size()) {
            throw new IndexOutOfBoundsException();
        }
        return animations.get(index);
    }

    /**
     * Returns the current animation the Sprite2D is in
     *
     * @return the current animation the Sprite2D is in
     */
    public AnimatedGraphic getCurrentAnimation() {
        return currentState;
    }

    /**
     * Returns a Collection of all the animations the Sprite2D can be in
     *
     * @return a Collection of all the animations the Sprite2D can be in
     */
    public Collection<AnimatedGraphic> getStates() {
        return animations;
    }

    /**
     * Returns the total number of animations
     *
     * @return the total number of animations
     */
    public int size() {
        return animations.size();
    }

    /**
     * Method used to pause the animation
     *
     * @param on true in order to pause the animation, false to resume the
     * animation running where it left off
     */
    public void pause(boolean on) {
        running = !on;
    }

    /**
     * Returns true if the animation is paused, false otherwise
     *
     * @return true if the animation is paused, false otherwise
     */
    public boolean isPaused() {
        return !running;
    }

    /**
     * Resets the current animation to its initial frame
     */
    public void reset() {
        currentState.reset();
    }

    @Override
    public void render(Graphics g, float offsetX, float offsetY) {
        currentState.render(g, worldX + offsetX, worldY + offsetY);
        if (running) {
            currentState.update();
        }
    }

    @Override
    public String toString() {
        return super.toString() + '@' + worldX + ',' + worldY;
    }
}
