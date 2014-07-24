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
package org.aegis2d;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.aegis.game.AegisGame;
import org.aegis.game.RuntimeSystem;
import org.aegis.ui.GameGraphics;
import org.aegis.ui.RenderItem;
import org.aegis.ui.RenderList;

/**
 * Class which contains the game's main addToRender lists and uses a Swing
 * implementation for its primary addToRendering capabilities
 *
 * @author Rogue <Alice Q.>
 */
public class Aegis2DGraphics extends JPanel implements GameGraphics {

    // BACKGROUND ITEM
    private RenderItem background;

    // PRIMARY RENDERLIST CONTAINING ALL OF THE GAME'S RENder ITEMS
    private final RenderList renderlist;

    // THE MAIN GAME WINDOW
    private JFrame gameWindow;

    /**
     * Constructor
     *
     * @param title the title to display on the game window
     * @param initWidth the initial width
     * @param initHeight the initial height
     */
    public Aegis2DGraphics(String title, int initWidth, int initHeight) {
        renderlist = new RenderList();
        gameWindow = new GameWindow(title, initWidth, initHeight);
        gameWindow.add(this);
    }

    /**
     * Method to create a basic JFrame with EXIT_ON_CLOSE
     *
     * @param initWidth the initial width of the window
     * @param initHeight the initial height of the window
     * @param visible true in order to make the frame visible, otherwise false
     */
    /*public void initializeWindow(int initWidth, int initHeight, boolean visible) {
     gameWindow = new GameWindow(initWidth, initHeight);
     gameWindow.setSize(initWidth, initHeight);
     gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     gameWindow.add(this);
     gameWindow.setVisible(visible);
     }*/
    /**
     * Returns the game's window container
     *
     * @return the game's window container
     */
    public JFrame getWindow() {
        return gameWindow;
    }

    /**
     * Method to set the background RenderItem. This is the first item
     * addToRendered on-screen and should represent the background.
     *
     * @param bgColor the background color behind the item
     * @param bgDecal the RenderItem to use as the background
     */
    public void setBackground(Color bgColor, Decal bgDecal) {
        gameWindow.setBackground(bgColor);
        background = bgDecal;
    }

    /**
     * Queues an item for addToRendering in the next available pass
     *
     * @param item the RenderItem to add
     */
    public synchronized void addToRender(RenderItem item) {
        renderlist.add(item);
    }

    /**
     * Renders everything contained in the game and GUI addToRender lists, as
     * well as the background. The GUI addToRender list is always addToRendered
     * on top of the game addToRender list. Please note that this removes the
     * item from the addToRender lists, so a persistent item must be re-added
     * every frame
     */
    @Override
    public final void update() {
        if (gameWindow.isVisible()) {
            gameWindow.repaint();
        }
    }
    
    @Override
    public final synchronized void paintComponent(Graphics g) {
        if (background != null) {
            background.render(g, 0, 0);
        }
        for (RenderItem item : renderlist) {
            item.render(g, 0, 0);
        }
        renderlist.clear();
    }
}
