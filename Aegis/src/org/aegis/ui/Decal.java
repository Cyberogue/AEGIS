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

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Immutable wrapper class for a BufferedImage allowing for RenderItem
 * capabilities, which can be used to reduce memory usage by using single
 * instances across multiple resources
 *
 * @author Rogue <Alice Q.>
 */
public class Decal implements RenderItem {

    // THE IMAGE TO WRAP AROUND
    private final BufferedImage image;

    /**
     * Basic constructor which creates an Decal wrapped around a BufferedImage
     *
     * @param image the BufferedImage to wrap around
     */
    public Decal(BufferedImage image) {
        this.image = image;
    }

    /**
     * Constructor which creates a Decal with the image data loaded from a file
     *
     * @param file a file containing data about the image
     * @throws IOException when there is a problem reading the file, or the file
     * contains invalid data
     */
    public Decal(File file) throws IOException {
        this.image = ImageIO.read(file);
    }

    /**
     * Returns the BufferedImage this is wrapped around
     *
     * @return the BufferedImage this is wrapped around
     */
    public BufferedImage getImage() {
        return image;
    }

    @Override
    public void render(Graphics g, float offsetX, float offsetY) {
        g.drawImage(image, (int) offsetX, (int) offsetY, null);
    }
}
