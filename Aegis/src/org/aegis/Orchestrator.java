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
package org.aegis;

import java.util.HashMap;

/**
 * Class meant to maintain and switch between multiple different GameScenes at
 * runtime
 *
 * @author Rogue <Alice Q>
 */
public class Orchestrator implements java.lang.Runnable {

    // CONTAINER FOR ALL THE DIFFERENT SCENES
    HashMap<String, GameScene> scenes;

    // CURRENT AND PREVIOUS SCENE STATES
    SceneState currentState = SceneState.INITIALIZING;
    SceneState previousState = SceneState.STOPPED;

    // CURRENT SCENE
    GameScene currentScene = null;
    GameScene nextScene = null;

    /**
     * Basic constructor
     */
    public Orchestrator() {
        scenes = new HashMap();
    }

    /**
     * @return The number of loaded scenes
     */
    public int size() {
        return scenes.size();
    }

    /**
     * Adds a GameScene under a specified key
     *
     * @param scene The GameScene to add
     * @param name A string name to store the GameScene under
     */
    public void add(String name, GameScene scene) {
        if (name == null || scenes.containsKey(name)) {
            throw new InvalidSceneIDException();
        }

        scenes.put(name, scene);

        if (currentScene == null) {
            currentScene = scene;
        }
    }

    /**
     * Adds a GameScene under its sceneID
     *
     * @param scene The GameScene to add
     */
    public void add(GameScene scene) {
        add(scene.getSceneID(), scene);
    }

    /**
     * Starts the sequence to switch scenes
     *
     * @param name The name of the scene to switch to
     */
    public void switchTo(String name) {
        if (!scenes.containsKey(name)) {
            throw new InvalidSceneIDException();
        }
        nextScene = scenes.get(name);
        previousState = currentState;
        currentState = SceneState.TERMINATING;
    }

    /**
     * Exits the current scene without entering another scene
     */
    public void terminate() {
        previousState = currentState;
        currentState = SceneState.TERMINATING;
    }

    /**
     * Switches to the paused state
     */
    public void pause() {
        previousState = currentState;
        currentState = SceneState.PAUSED;
    }

    /**
     * Resumes from being paused to wherever it left off
     */
    public void unpause() {
        SceneState temp = currentState;
        currentState = previousState;
        previousState = temp;
    }

    /**
     * Main overwritten method which switches between and runs GameScene code.
     * DO NOT OVERWRITE THIS METHOD
     */
    @Override
    public final void run() {
        switch (currentState) {
            case INITIALIZING:
                // RUN ENTRY CODE
                currentScene.onSceneEnter();

                // ENTER MAIN LOOP
                previousState = currentState;
                currentState = SceneState.RUNNING;
                break;
            case RUNNING:
                // MAIN UPDATE LOOP
                currentScene.update();
                break;
            case TERMINATING:
                // RUN EXIT CODE
                currentScene.onSceneExit();

                if (nextScene == null) {
                    // RESET THE SCENE STATE
                    previousState = currentState;
                    currentState = SceneState.STOPPED;
                } else {
                    // SWITCH SCENES
                    currentScene = nextScene;
                    nextScene = null;

                    // RESET THE SCENE STATE
                    previousState = currentState;
                    currentState = SceneState.INITIALIZING;
                }
                break;
            case PAUSED:
                // ENTER ALTERNATE MAIN LOOP
                currentScene.paused();
                break;
            case STOPPED:
                // DO... NOTHING?
                break;
        }
    }

    /**
     * Exception thrown when an invalid scene is added
     */
    public static class InvalidSceneIDException extends RuntimeException {

    }
}
