/*
 * Copyright (C) 2014 Rogue <Alice Q>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
