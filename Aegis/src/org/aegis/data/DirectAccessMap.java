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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Data structure which acts as an extension of a Map using key-index pairs
 * which allow for either direct access through indeces or indirect access
 * through keys
 *
 * @param <E> the type of object to store in the map
 * @author Rogue <Alice Q.>
 */
public class DirectAccessMap<E> {

    // ACTUAL STORAGE LIST OF ALL OF THE ITEMS
    private List<E> items;

    // STORAGE MAP WHICH STORES THE INDECES OF COMMON KEYS
    private Map<String, Integer> keys;

    /**
     * Basic constructor
     */
    public DirectAccessMap() {
        items = new ArrayList();
        keys = new HashMap();
    }

    /**
     * Adds the specified value to the internal data structure and associates
     * the specified value with the specified key in this map
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the index assigned to the value
     */
    public int add(String key, E value) {
        int index = items.size();
        keys.put(key, index);
        items.add(value);
        return index;
    }

    /**
     * Adds the specified value to the internal data structure, assigning it to
     * a specified index but without assigning it to a specified key.
     *
     * @param value value to be associated with the specified key
     * @return the index assigned to the value
     */
    public int add(E value) {
        int index = items.size();
        items.add(value);
        return index;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if there
     * is none
     *
     * This method is exponentially slower than the similar getDirect method
     * which is why it should be used sparingly, only when performance isn't an
     * issue
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or null if there
     * is none
     */
    public E get(String key) {
        Integer index = keys.get(key);
        if (index == null || index >= items.size()) {
            return null;
        }
        return items.get(index);
    }

    /**
     * Returns the element corresponding to the specified index in this
     * structure
     *
     * @param index index of the element to return
     * @return the element assigned to the specified index
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0
     * || index >= size())
     */
    public E get(int index) {
        return items.get(index);
    }

    /**
     * Removes all of the elements from this data structure
     */
    public void clear() {
        items.clear();
        keys.clear();
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    public int size() {
        return items.size();
    }

    /**
     * Returns a Set view of the keys contained in this map. Please note that
     * not every loaded resource may have a key so the actual number of loaded
     * resources may be greater than the number of entries.
     *
     * @return Returns a Set view of the keys contained in this map.
     */
    public Set<String> keySet() {
        return keys.keySet();
    }

    /**
     * Utility method used to find the index associated with a key
     *
     * @param key key with which a value is associated
     * @return the index assigned to the value associated with the key, or -1 if
     * none exists
     */
    public int indexOf(String key) {
        Integer i = keys.get(key);
        return (i == null ? -1 : i);
    }
}
