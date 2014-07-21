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
package org.aegis.game;

import java.util.HashMap;
import org.aegis.game.GameScene.SceneState;

/**
 * Managerial class which holds all of the game's different scenes, switching
 * between them as required. This allows an user implementation to readily
 * switch between and execute different sets of runtime code.
 *
 * @author Rogue <Alice Q.>
 */
public class GameSceneManager implements RuntimeSystem {

    // KEEP A REFERENCE TO THE RESPECTIVE GAME THIS IS FOR
    private AegisGame game;

    // MAP OF ALL THE DIFFERENT SCENES IN THE GAME
    private HashMap<String, GameScene> scenes;

    // THE CURRENT SCENE
    private GameScene currentScene;

    // THE NEXT SCENE IN LINE
    private GameScene nextScene;

    /**
     * Constructor
     *
     * @param game the AegisGame this system is for
     */
    public GameSceneManager(AegisGame game) {
        this.game = game;
        scenes = new HashMap();
    }

    /**
     * Main update method which simply passes the call onto the appropriate
     * scene and method
     */
    @Override
    public final void update() {

        switch (currentScene.state) {
            case ENTER:
                currentScene.onSceneEnter();
                currentScene.state = SceneState.RUNNING;
                break;
            case RUNNING:
                currentScene.update();
                break;
            case EXIT:
                currentScene.onSceneExit();
                currentScene.state = SceneState.STOPPED;

                currentScene = nextScene;
                currentScene.state = SceneState.ENTER;
                break;
            case PAUSED:
                currentScene.paused();
                break;
            case STOPPED:
                // THIS SHOULDN'T HAPPEN, SOMETHING WENT TERRIBLY WRONG
                throw new java.lang.IllegalStateException("A STOPPED SCENE SHOULD NOT RUN");
        }
    }

    /**
     * Adds a GameScene to the manager under an user-specified key
     *
     * @param key an unique key to identify the scene with
     * @param scene the GameScene to add
     */
    public void addScene(GameScene scene, String key) {
        if (currentScene == null) {
            currentScene = scene;
        }
        scenes.put(key, scene);
    }

    /**
     * Adds a GameScene to the manager using its SceneID as the key
     *
     * @param scene the GameScene to add
     */
    public void addScene(GameScene scene) {
        if (currentScene == null) {
            currentScene = scene;
        }
        scenes.put(scene.getSceneID(), scene);
    }

    /**
     * Readies the next GameScene for a standard transition between the two
     * which allows
     *
     * @param sceneID the ID of the next scene to go to
     */
    public void setNext(String sceneID) {
        currentScene.state = SceneState.EXIT;
        nextScene = scenes.get(sceneID);
    }

    /**
     * Forces the next GameScene on the next frame, starting at the
     * initialization method (Which defaults to the standard update method
     * unless it is overwritten)
     *
     * @param sceneID the ID of the next scene to go to
     */
    public void forceNext(String sceneID) {
        currentScene = scenes.get(sceneID);
        currentScene.state = SceneState.ENTER;
    }

    /**
     * Returns the currently active scene
     *
     * @return the currently active scene
     */
    public GameScene getCurrentScene() {
        return currentScene;
    }
}
