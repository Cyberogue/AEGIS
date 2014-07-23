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
import java.util.ArrayList;
import java.util.Collection;

/**
 * Object which manages multiple different layers of RenderItems, able to render
 * them in order of lowest to highest
 *
 * @author Rogue <Alice Q.>
 */
public class LayerStack implements RenderItem {

    // THE LAYERS
    private final ArrayList<RenderList> layers;

    /**
     * Constructor which also creates an initial number of layers
     *
     * @param initSize the initial number of layers
     */
    public LayerStack(int initSize) {
        layers = new ArrayList(initSize);
        for (int i = 0; i < initSize; i++) {
            layers.add(new RenderList());
        }
    }

    /**
     * Constructor for an innitially empty LayerManager
     */
    public LayerStack() {
        layers = new ArrayList();
    }

    /**
     * Creates a new layer and adds it on top of the stack of layers
     *
     * @return the index of the newly created layer
     */
    public int createNewLayer() {
        RenderList rl = new RenderList();
        layers.add(rl);
        return layers.size() - 1;
    }

    /**
     * Returns the contents of one of the layers
     *
     * @param layerNum the layer index to return contents for
     * @return a Collection with all the RenderItems in the given layer
     */
    public Collection<RenderItem> getLayerContents(int layerNum) {
        return layers.get(layerNum);
    }

    /**
     * Adds a RenderItem to the top of one of the layers
     *
     * @param layerNum the layer index to add to
     * @param item the item to add
     */
    public void addToLayer(int layerNum, RenderItem item) {
        layers.get(layerNum).add(item);
    }

    /**
     * Returns the number of layers in the stack
     *
     * @return the number of layers in the stack
     */
    public int size() {
        return layers.size();
    }

    /**
     * Clears each of the layers in the layer stack, preserving the layers
     * themselves
     */
    public void clear() {
        for (RenderList list : layers) {
            list.clear();
        }
    }

    /**
     * Deletes all the layers from the layer stack
     */
    public void clearAll() {
        layers.clear();
    }

    @Override
    public void render(Graphics g, float offsetX, float offsetY) {
        for (RenderList list : layers) {
            list.render(g, offsetX, offsetY);
        }
    }
}
