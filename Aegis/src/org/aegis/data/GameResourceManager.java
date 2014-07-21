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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.aegis.ui.RenderItem;

/**
 *
 * @author Rogue <Alice Q.>
 */
public class GameResourceManager {

    // LIST OF ALL THE PRELOADED RENDERITEMS. THESE ARE ARRANGED BY INDEX FOR FAST ACCESS
    private List<RenderItem> items;

    // MAP OF ALL THE KEY-VALUE PAIRINGS. THESE MUST REMAIN SYNCHRONIZED WITH ITEMS
    private Map<String, Integer> keys;

    /**
     * Constructor
     */
    public GameResourceManager() {
        items = new ArrayList();
        keys = new TreeMap();
    }

    /**
     * Method to load a RenderItem into memory under a given key
     *
     * @param key the identifying key to put the item under
     * @param item the item to load
     * @return the index the item was loaded under
     */
    public int loadResource(String key, RenderItem item) {
        int index = items.size();
        items.add(index, item);
        keys.put(key, index);
        return index;
    }

    /**
     * Data retrieval method to get a RenderItem assigned to a String key.
     * However, this method is much slower than get(int index), so this should
     * only be used sparingly
     *
     * @param key the key the item is under
     * @return the RenderItem stored under the key
     */
    public RenderItem get(String key) {
        int index = keys.get(key);
        if (index < 0) {
            return null;
        } else {
            return items.get(index);
        }
    }

    /**
     * Fast data retrieval method which retrieves an item at a specified index
     *
     * @param index the index the item is under
     * @return the RenderItem stored at the provided index
     */
    public RenderItem get(int index) {
        return items.get(index);
    }

    /**
     * Searches for the corresponding index for a given key. Data retrieval
     * using keys takes longer than direct retrieval so only use this when
     * performance time isn't of high priority.
     *
     * @param key the key to find the corresponding index for
     * @return the index belonging to the key, or -1 if none exists
     */
    public int keyToIndex(String key) {
        Integer i = keys.get(key);
        if (i == null) {
            return -1;
        } else {
            return i;
        }
    }

    /**
     * @return the number of currently loaded items
     */
    public int size() {
        return items.size();
    }
}
