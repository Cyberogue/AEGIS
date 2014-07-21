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

import java.util.LinkedList;
import org.aegis.game.RuntimeSystem;

/**
 *
 * Managerial class which handles the game's windows and rendering RenderItems
 * at runtime
 *
 * @author Rogue <Alice Q.>
 */
public class GameGraphics implements RuntimeSystem {

    // BACKGROUND
    private RenderItem background;

    // GAME RENDER LIST
    private LinkedList<RenderItem> gameRenderList;
    // GUI RENDER LIST
    private LinkedList<RenderItem> guiRenderList;

    /**
     * Constructor
     */
    public GameGraphics() {
        gameRenderList = new LinkedList();
        guiRenderList = new LinkedList();
    }

    /**
     * Method to set the background RenderItem. This is the first item rendered
     * on-screen and should represent the background.
     *
     * @param bg the RenderItem to use as the background
     */
    public void setBackground(RenderItem bg) {
        background = bg;
    }

    /**
     * Adds a RenderItem to the end of the GUI render list
     *
     * @param item the item to queue for rendering
     */
    public void addToGUIRenderList(RenderItem item) {
        guiRenderList.push(item);
    }

    /**
     * Adds a RenderItem to the end of the game render list
     *
     * @param item the item to queue for rendering
     */
    public void addToGameRenderList(RenderItem item) {
        gameRenderList.push(item);
    }

    public void removeFromGUIRenderList(RenderItem item) {
        guiRenderList.remove(item);
    }

    /**
     * Renders everything contained in the game and GUI render lists, as well as
     * the background. The GUI render list is always rendered on top of the game
     * render list. Please note that this removes the item from the render
     * lists, so a persistent item must be re-added every frame
     */
    @Override
    public final void update() {
        if (background != null) {
            background.render(this);
        }
        for (int i = 0; i < gameRenderList.size(); i++) {
            gameRenderList.removeFirst().render(this);
        }
        for (int i = 0; i < guiRenderList.size(); i++) {
            guiRenderList.removeFirst().render(this);
        }
    }
}
