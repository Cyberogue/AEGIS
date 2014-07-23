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

import org.aegis.ui.RenderItem;

/**
 * Abstract class providing the framework for separate game code for a single
 * scene
 *
 * @author Rogue <Alice Q>
 */
public abstract class GameScene {

    // THE ID WHICH THE SCENE WILL USE
    private String sceneID;

    // A REFERENCE TO THE CORRESPONDING GAME OBJECT
    protected AegisGame game;

    // THE SCENE'S CURRENT STATE
    protected SceneState state;

    /**
     * Basic constructor
     *
     * @param game the AegisGame this scene corresponds to, used for data access
     * and retrieval
     * @param sceneID A string identifier to use for the scene
     */
    public GameScene(AegisGame game, String sceneID) {
        this.sceneID = sceneID;
        this.state = SceneState.ENTER;
        this.game = game;
    }

    /**
     * @return The string identifier for the scene
     */
    public final String getSceneID() {
        return sceneID;
    }

    /**
     * Hook method for scene entry. Use this for things such as initializations
     * and GUI loading. By default (as in, when not overriden) this method
     * simply passes the call to the update() method
     *
     *
     */
    public void onSceneEnter() {
        update();
    }

    /**
     * Hook method for the main runnable code. This code is called once a game
     * loop
     *
     */
    public abstract void update();

    /**
     * Hook method for scene exit. This method is called before the scene is
     * swapped out. By default (as in, when not overriden) this method simply
     * passes the call to the update() method
     *
     */
    public void onSceneExit() {
        update();
    }

    /**
     * Hook method for paused runnable code. This code is called once a game
     * loop when the game is paused. By default (as in, when not overriden) this
     * method simply passes the call to the update() method
     *
     */
    public void paused() {
        update();
    }

    /**
     * Wrapper method which adds a RenderItem to the game's primary addToRenderList list
     *
     * @param item
     */
    public void addToRenderList(RenderItem item) {
        game.getGraphics().addToGameRenderList(item);
    }

    @Override
    public String toString() {
        return sceneID + '[' + state + ']';
    }

    /**
     * The different states a GameScene can be in
     *
     * @author Rogue <Alice Q>
     */
    public enum SceneState {

        ENTER, RUNNING, EXIT, PAUSED, STOPPED
    }
}
