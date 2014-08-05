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
package org.aegis.debugtools;

import java.awt.Color;
import java.awt.Graphics;
import org.aegis.ui.RenderItem;

/**
 * Simple RenderItem used for debugging which simply prints its name to output
 * when rendered
 *
 * @author Rogue <Alice Q.>
 */
public class DebugRenderItem implements RenderItem {

    private String name;

    public DebugRenderItem() {
        this.name = toString();
    }

    public DebugRenderItem(String name) {
        this.name = name;
    }

    @Override
    public void render(Graphics g, float offsetX, float offsetY) {
        g.setColor(Color.red);
        g.fillRect((int) offsetX, (int) offsetY, 50, 50);

        System.out.println("RENDER " + name);
    }
}
