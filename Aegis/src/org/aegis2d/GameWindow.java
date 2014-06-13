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
import java.awt.Image;
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

    // TOOLKIT
    private Toolkit tk;

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

        this.gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        this.tk = Toolkit.getDefaultToolkit();

        this.timer = timer;
        this.config = new WindowConfig(width, height);

        setSize(width, height);
        setDefaultCloseOperation(GameWindow.EXIT_ON_CLOSE);

        setVisible(true);
    }

    /**
     * Changes the window's dimensions
     *
     * @param width The new width
     * @param height The new height
     */
    public void setDimensions(int width, int height) {
        config.width = width;
        config.height = height;
    }

    /**
     * Changes the window's dimensions to the native resolution
     */
    public void fitToScreen() {
        config.width = tk.getScreenSize().width;
        config.height = tk.getScreenSize().height;
    }

    /**
     * Sets whether or not the window is fullscreen
     *
     * @param active A boolean value
     */
    public void setFullscreen(boolean active) {
        config.fullscreen = active;
        config.borderless = active;
    }

    /**
     * Sets whether or not the window is borderless
     *
     * @param active A boolean value
     */
    public void setBorderless(boolean active) {
        config.borderless = active;
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
        // PAUSE THE TIMER TO PREVENT UNWANTED UPDATES
        timer.requestPause();

        // HIDE THE WINDOW TO APPLY CHANGES
        setVisible(false);

        // PRINT THE GRAPHICAL CONFIG
        System.out.println(config.toString());

        // MAKE THE WINDOW FULLSCREEN IF REQUIRED
        if (config.isFullscreen() && gd.isFullScreenSupported()) {
            System.out.println("FFF");
            setSize(tk.getScreenSize().width, tk.getScreenSize().height);     // CHANGE THE WINDOW SIZE
            gd.setFullScreenWindow(this);       // REQUEST NATIVE FULLSCREEN

        } else if (!config.isFullscreen()) {
            System.out.println("WWW");
            gd.setFullScreenWindow(null);   // EXIT FULLSCREEN
            setSize(config.width, config.height);    // RESUME TO ORIGINAL SIZE
        }

        // SET THE NEW ICON IF IT'S NOT NULL
        if (config.icon != null) {
            setIconImage(config.icon.getImage());
        }

        // BORDERLESS MAYBE?
        dispose();
        setUndecorated(config.isBorderless());

        // THERE REALLY ISN'T MUCH NEED FOR THIS, BUT IT MAKES THE GAME LOOK LIKE IT'S DOING SOMETHING. I GUESS IT ALSO ALLOWS SOME TIME FOR CHANGES TO TAKE EFFECT
        try {
            java.lang.Thread.sleep(500);
        } catch (InterruptedException ex) {

        }

        // UNPAUSE THE TIMER
        timer.releasePause();

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
            return width;
        }

        public int getHeight() {
            return height;
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

        @Override
        public String toString() {
            return width + "x" + height + (fullscreen ? " -F" : "") + (borderless ? " -B" : "") + (icon == null ? "" : " " + icon.toString());
        }
    }
}
