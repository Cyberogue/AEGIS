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

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Container for RenderItems which itself functions as a RenderItem, rendering
 * everything inside it sequentially in order of it being added.
 *
 * @author Rogue <Alice Q.>
 */
public class RenderList implements RenderItem, java.util.Queue<RenderItem> {

    // THE ACTUAL DATA CONTAINER
    private LinkedList<RenderItem> renderList;

    // THE MAXIMUM LIST SIZE
    private int maxSize;

    // PERSISTANCE
    private boolean persistent;

    /**
     * Constructor for a RenderList with no upper limit and no persistence
     */
    public RenderList() {
        this(0, false);
    }

    /**
     * Constructor for a RenderList with a specified upper limit and no
     * persistence
     *
     * @param maxSize the maximum amount of items allowable in the RenderList
     */
    public RenderList(int maxSize) {
        this(maxSize, false);
    }

    /**
     * Constructor for a RenderList with a specified upper limit and specified
     * persistence
     *
     * @param maxSize maxSize the maximum amount of items allowable in the
     * RenderList
     * @param persistent when true, the RenderList has persistence and will not
     * remove items as it renders them. When false (the default), rendered items
     * will only be rendered once then removed.
     */
    public RenderList(int maxSize, boolean persistent) {
        this.maxSize = maxSize;
        this.renderList = new LinkedList();
        this.persistent = persistent;
    }

    @Override
    public boolean add(RenderItem item) {
        if (maxSize > 0 && renderList.size() >= maxSize) {
            throw new IllegalStateException("Render list is full");
        }
        renderList.addLast(item);
        return true;
    }

    @Override
    public RenderItem element() {
        return renderList.peekFirst();
    }

    @Override
    public boolean offer(RenderItem item) {
        if (maxSize > 0 && renderList.size() >= maxSize) {
            return false;
        }
        renderList.addLast(item);
        return true;
    }

    @Override
    public RenderItem peek() {
        return renderList.peekFirst();
    }

    @Override
    public RenderItem poll() {
        if (renderList.isEmpty()) {
            return null;
        } else {
            return renderList.removeFirst();
        }
    }

    @Override
    public RenderItem remove() {
        return renderList.remove();
    }

    @Override
    public int size() {
        return renderList.size();
    }

    public int maxSize() {
        return maxSize;
    }

    @Override
    public boolean isEmpty() {
        return renderList.isEmpty();
    }

    @Override
    public void clear() {
        renderList.clear();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return renderList.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return renderList.removeAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends RenderItem> c) {
        return renderList.addAll(c);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return renderList.containsAll(c);
    }

    @Override
    public boolean remove(Object item) {
        return renderList.remove(item);
    }

    @Override
    public Object[] toArray() {
        return renderList.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return renderList.toArray(a);
    }

    @Override
    public Iterator iterator() {
        return renderList.iterator();
    }

    @Override
    public boolean contains(Object o) {
        return renderList.contains(o);
    }

    @Override
    public void render(GameGraphics g, float offsetX, float offsetY) {
        for (RenderItem item : renderList) {
            item.render(g, offsetX, offsetY);
        }
        if (!persistent) {
            renderList.clear();
        }
    }

    /**
     * Sets the persistece of items in the list. When the list is persistent,
     * items will not be removed from the list after rendering, otherwise the
     * list will be emptied regularly.
     *
     * @param on true in order to prevent items from being removed as they are
     * rendered
     */
    public void setPersistence(boolean on) {
        persistent = on;
    }
}
