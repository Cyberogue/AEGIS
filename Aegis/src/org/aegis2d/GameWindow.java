/*
 * The MIT License
 *
 * Copyright 2014 Rogue <Alice Q>.
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

import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import org.aegis.Synchronizer;

/**
 * Custom JFrame to contain the main game window
 *
 * @author Rogue <Alice Q>
 */
public class GameWindow extends JFrame {

    // STORAGE OBJECT CONTAINING THE WINDOW'S CONFIGURATION
    private WindowConfig config;

    // REFERENCE TO THE SYNCHRONIZER DOING THE GRAPHICS UPDATING
    private Synchronizer timer;

    // GRAPHICS DEVICE
    private GraphicsDevice gd;

    /**
     * Basic constructor
     *
     * @param timer The Synchronizer object used to update the window rendering
     * @param title The title to display on the game's window
     * @param width The initial width
     * @param height The initial height
     */
    public GameWindow(String title, int width, int height, Synchronizer timer) {
        super(title);

        setSize(width, height);
        setVisible(true);
        setDefaultCloseOperation(GameWindow.EXIT_ON_CLOSE);

        this.gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        this.timer = timer;

        config = new WindowConfig(width, height);
    }

    /**
     * Changes the window's dimensions
     *
     * @param width The new width
     * @param height The new height
     */
    public void setDimensions(int width, int height) {
        config.setDimensions(width, height);
    }

    /**
     * Sets whether or not the window is fullscreen
     *
     * @param active A boolean value
     */
    public void setFullscreen(boolean active) {
        config.setFullscreen(active);
    }

    /**
     * Sets whether or not the window is borderless
     *
     * @param active A boolean value
     */
    public void setBorderless(boolean active) {

    }

    /**
     * @return The game window's width
     */
    public int getWindowWidth() {
        return config.width;
    }

    /**
     * @return The game window's height
     */
    public int getWindowHeight() {
        return config.height;
    }

    /**
     * @return True if the current window is fullscreen
     */
    public boolean isFullstreen() {
        return config.fullscreen;
    }

    /**
     * @return True if the current window is borderless
     */
    public boolean isBorderless() {
        return config.borderless;
    }

    /**
     * Applies any changes set into the game window's configuration
     */
    public void applyChanges() {
        // HIDE THE WINDOW TO APPLY CHANGES
        setVisible(false);
        
        // PAUSE THE TIMER TO PREVENT UNWANTED UPDATES
        timer.suspend();

        // MAKE THE WINDOW FULLSCREEN IF REQUIRED
        if (config.isFullscreen() && gd.isFullScreenSupported()) {
            gd.setFullScreenWindow(this);
        } else if (!config.isFullscreen()) {
            gd.setFullScreenWindow(null);
        }

        // CHANGE THE WINDOW SIZE
        setSize(config.getWidth(), config.getHeight());

        // SHOW THE WINDOW
        setVisible(true);

    }

    /**
     * Data-only class containing all the config for a display window
     */
    protected static class WindowConfig {

        // STORAGE VARIABLES
        private int width;
        private int height;

        // DEFAULT VARIABLES
        private boolean fullscreen = false;
        private boolean borderless = false;
        private ImageIcon icon = null;

        // TOOLKIT
        private final static Toolkit tk = Toolkit.getDefaultToolkit();

        /**
         * Basic constructor
         *
         * @param width The window's initial width
         * @param height The window's initial height
         */
        public WindowConfig(int width, int height) {
            this.width = width;
            this.height = height;
        }

        //
        public void setWindowIcon(String path) {
            icon = new ImageIcon(path);
        }

        public void setFullscreen(boolean bool) {
            fullscreen = bool;
        }

        public void setBorderless(boolean bool) {
            this.borderless = bool;
        }

        public void setDimensions(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public int getWidth() {
            if (fullscreen) {
                return (int) tk.getScreenSize().getWidth();
            } else {
                return width;
            }
        }

        public int getHeight() {
            if (fullscreen) {
                return (int) tk.getScreenSize().getHeight();
            } else {
                return height;
            }
        }

        public boolean isFullscreen() {
            return fullscreen;
        }

        public boolean isBorderless() {
            return borderless;
        }

        public ImageIcon getIcon() {
            return icon;
        }
    }
}
