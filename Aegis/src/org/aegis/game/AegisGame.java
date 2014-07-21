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
package org.aegis.game;

import org.aegis.data.GameResourceManager;
import org.aegis.ui.GameGraphics;
import org.aegis.ui.GameInputMonitor;

/**
 * Class which orchestrates how all of a game's subsystems run and in what order
 *
 * @author Rogue <Alice Q>
 */
public class AegisGame implements java.lang.Runnable {

    // GLOBAL STATIC INSTANCE. THIS IS SIMPLY FOR THE USER TO USE IF DESIRED AND HAS NO EFFECT ON THE OVERALL GAME, BUT CAN BE USED FOR EASY GLOBAL ACCESS
    public static AegisGame GLOBAL;

    // THE GAME'S NAME
    private String name;

    // TIMEKEEPER OBJECT
    protected TimeKeeper timer;

    // GAMEGRAPHICS OBJECT
    protected GameGraphics graphics;

    // GAMESCENEMANAGER OBJECT
    protected GameSceneManager scenemanager;

    // GAMEINPUTMONITOR OBJECT
    protected GameInputMonitor input;

    // RESOURCE MANAGER
    protected GameResourceManager resources;

    /**
     * Constructor which initializes the game using the default framerate
     *
     * @param name the game's title
     */
    public AegisGame(String name) {
        this.timer = new TimeKeeper(this);
        this.name = name;
    }

    /**
     * Constructor which initializes the game with a set maximum framerate. This
     * does not however guarantee 100% accuracy
     *
     * @param name the game's title
     * @param targetFramerate the target framerate
     */
    public AegisGame(String name, int targetFramerate) {
        this.timer = new TimeKeeper(this, targetFramerate);
        this.name = name;
    }

    /**
     * Method to set a GameGraphics system for the game
     *
     * @param graphics the graphics system to set
     */
    public void set(GameGraphics graphics) {
        this.graphics = graphics;
    }

    /**
     * Method to set a GameSceneManager system for the game
     *
     * @param scenemanager the scene manager to set
     */
    public void set(GameSceneManager scenemanager) {
        this.scenemanager = scenemanager;
    }

    /**
     * Method to set a GameInputMonitor system for the game
     *
     * @param inputmonitor the input monitor to set
     */
    public void set(GameInputMonitor inputmonitor) {
        this.input = inputmonitor;
    }

    /**
     * Method to set a GameResourceManager system for the game
     *
     * @param resources the game's global resource manager to set
     */
    public void set(GameResourceManager resources) {
        this.resources = resources;
    }

    /**
     * Method to start the game after the vital systems have been initialized
     */
    public void start() {
        if (graphics == null) {
            graphics = new GameGraphics();
        }
        if (scenemanager == null) {
            scenemanager = new GameSceneManager();
        }
        if (input == null) {
            input = new GameInputMonitor();
        }
        if (resources == null) {
            resources = new GameResourceManager();
        }
        timer.start();
    }

    @Override
    public final void run() {
        System.out.println(timer);
        // FIRST RUN THE INPUT MONITORS TO CHECK FOR NEW INPUTS
        input.update();
        // THEN RUN CUSTOM GAME CODE
        scenemanager.update();
        // FOLLOWED BY ANY INTERNAL SYSTEMS SUCH AS PHYSICS (TODO)
        // AND GRAPHICS ARE THE LAST THING THAT SHOULD BE UPDATED
        graphics.update();
    }

    /**
     * Runtime exception to be thrown whenever one of the game's vital systems
     * is either non-existent or missing
     */
    public class SystemMissingException extends RuntimeException {

        SystemMissingException(String message) {
            super(message);
        }

        SystemMissingException() {
            super();
        }
    }
}
