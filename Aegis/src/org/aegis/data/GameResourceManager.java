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
package org.aegis.data;

import org.aegis.ui.RenderItem;

/**
 * Class responsible for loading and fetching all of the game's custom resources
 *
 * @author Rogue <Alice Q.>
 */
public class GameResourceManager {

    // MAP OF ALL THE RENDER ITEMS
    private DirectAccessMap<RenderItem> renderItems;

    /**
     * Constructor
     */
    public GameResourceManager() {
        renderItems = new DirectAccessMap();
    }

    /**
     * Method to load a RenderItem onto storage, assigning it to a specified
     * unique key
     *
     * @param key the String key associated with the RenderItem
     * @param item the RenderItem to load
     * @return the index assigned to the RenderItem
     */
    public int load(String key, RenderItem item) {
        return renderItems.add(key, item);
    }

    /**
     * Method to load a RenderItem onto storage
     *
     * @param item the RenderItem to load
     * @return the index assigned to the RenderItem
     */
    public int load(RenderItem item) {
        return renderItems.add(item);
    }

    /**
     * @return a DirectAccessMap of loaded RenderItems
     */
    public DirectAccessMap<RenderItem> getRenderItems() {
        return renderItems;
    }
}
