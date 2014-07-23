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

/**
 * Wrapper class for RenderItems which provides a constant fixed, immutable X
 * and Y position
 *
 * @author Rogue <Alice Q.>
 */
public class FixedRenderItem implements RenderItem {

    // X AND Y COORDINATES
    private final float x;
    private final float y;

    // THE RENDER ITEM TO WRAP AROUND
    private RenderItem item;

    /**
     * Basic constructor
     *
     * @param item the RenderItem to wrap around
     * @param windowX the fixed x position
     * @param windowY the fixed y position
     */
    public FixedRenderItem(RenderItem item, float windowX, float windowY) {
        this.x = windowX;
        this.y = windowY;
        this.item = item;
    }

    @Override
    public final void render(Graphics g, float offsetX, float offsetY) {
        item.render(g, x, y);
    }
}
