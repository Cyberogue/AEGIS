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
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import org.aegis.ui.RenderItem;

/**
 * Similar to the Decal class, this is a RenderItem wrapper for an animated
 * decal with a single animation.
 *
 * @author Rogue <Alice Q>
 */
public class AnimatedGraphic implements RenderItem {

    // THE CURRENT FRAME WE'RE ON
    private int frameNum;

    // THE AMOUNT OF TIME LEFT TO SWITCH FRAMES
    private int timer;

    // ARRAYLIST CONTAINING ALL THE FRAMES TO RENDER
    private ArrayList<Frame> frames;

    /**
     * Default constructor for an empty AnimatedDecal
     */
    public AnimatedGraphic() {
        this.frameNum = 0;
        this.timer = 0;
        this.frames = new ArrayList();
    }

    /**
     * Constructor which creates an AnimatedDecal from an existing collection of
     * Frames
     *
     * @param frames a Collection of all the frames in the animation, each one
     * with its own image and duration
     */
    public AnimatedGraphic(Collection<Frame> frames) {
        this();
        this.frames.addAll(frames);
    }

    /**
     * Adds a frame to the end of the animation
     *
     * @param image the image to display
     * @param duration the amount of time to display it for
     * @return the number of frames in the animation
     */
    public int addFrame(BufferedImage image, int duration) {
        frames.add(new Frame(image, duration));
        return frames.size();
    }

    /**
     * Adds a frame to the end of the animation, extracted from a Decal.
     *
     * @param decal the decal containing the image to display
     * @param duration the amount of time to display it for
     * @return the number of frames in the animation
     */
    public int addFrame(Decal decal, int duration) {
        frames.add(new Frame(decal.getImage(), duration));
        return frames.size();
    }

    /**
     * Adds a Collection of multiple frames to the end of the animation
     *
     * @param frames a Collection of all the frames in the animation, each one
     * with its own image and duration
     * @return the number of frames in the animation
     */
    public int addFrames(Collection<Frame> frames) {
        this.frames.addAll(frames);
        return this.frames.size();
    }

    /**
     * Method to retrieve a collection of all the frames in the animation
     *
     * @return a collection of all the frames in the animation
     */
    public Collection<Frame> getFrames() {
        return frames;
    }

    /**
     * Returns the number of frames in the animation
     *
     * @return the number of frames in the animation
     */
    public int size() {
        return frames.size();
    }

    /**
     * Returns the current frame the animation is on
     *
     * @return the current frame the animation is on
     */
    public int getCurrentFrame() {
        return frameNum;
    }

    /**
     * Forces the animation to go to the specified frame
     *
     * @param frameNum the frame number to go to
     * @throws IndexOutOfBoundsException if the specified index is outside the
     * valid range
     */
    public void goToFrame(int frameNum) {
        if (frameNum < 0 || frameNum >= frames.size()) {
            throw new IndexOutOfBoundsException();
        }
        this.frameNum = frameNum;
    }

    /**
     * Updates the current frame, setting it to the next frame in the sequence
     * if appropriate
     */
    public void update() {
        if (timer >= frames.get(frameNum).duration) {
            timer = 0;
            if (++frameNum >= frames.size()) {
                frameNum = 0;
            }
        } else {
            timer++;
        }
    }

    @Override
    public void render(Graphics g, float offsetX, float offsetY) {
        g.drawImage(frames.get(frameNum).image, (int) offsetX, (int) offsetY, null);
    }

    /**
     * Resets the AnimatedGraphic to its initial position
     */
    public void reset() {
        this.timer = 0;
        this.frameNum = 0;
    }

    /**
     * Immutable wrapper for a single frame containing an image to render and
     * the duration to render it for, in frames
     */
    protected static class Frame {

        protected BufferedImage image;
        int duration;

        /**
         * Constructor
         *
         * @param image the image to render
         * @param duration the amount of frames the image should be rendered for
         */
        protected Frame(BufferedImage image, int duration) {
            this.duration = duration;
            this.image = image;
        }
    }

    /**
     * Method to break apart a spritesheet into its separate tiles
     *
     * @param spritesheet the spritesheet to break apart
     * @param width the width of each tile
     * @param height the height of each tile
     * @param spacing the amount of space between each tile. This is the number
     * of pixels between two respective tiles.
     * @param border the total border around the image. This is the distance
     * between the edge of the image and the edge of the closest tile.
     * @return A Collection of all the subimages in the spritesheet
     */
    public static Collection<BufferedImage> getFromSpritesheet(BufferedImage spritesheet, int width, int height, int spacing, int border) {
        // GET THE NUMBER OF TILES VERTICALLY AND HORIZONTALLY
        int horizCount = (spritesheet.getWidth() - 2 * border + spacing) / (width + spacing);
        int vertCount = (spritesheet.getHeight() - 2 * border + spacing) / (height + spacing);

        // MAKE A NEW ARRAYLIST TO HOLD IT ALL
        Collection<BufferedImage> images = new ArrayList(horizCount * vertCount);

        // AND ITERATE THROUGH ALL THE TILES
        for (int tileY = 0; tileY < vertCount; tileY++) {
            for (int tileX = 0; tileX < horizCount; tileX++) {
                // GET THE SUBIMAGE AND ADD IT TO THE COLLECTION
                int xLoc = border + tileX * (width + spacing);
                int yLoc = border + tileY * (height + spacing);
                images.add(spritesheet.getSubimage(xLoc, yLoc, width, height));
            }
        }

        return images;
    }
}
