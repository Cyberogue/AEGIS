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

/**
 * Abstract class providing the framework for separate game code for a single
 * scene
 *
 * @author Rogue <Alice Q>
 */
public abstract class GameScene {

    // THE ID WHICH THE SCENE WILL USE
    private String sceneID;

    /**
     * Basic constructor
     *
     * @param sceneID A string identifier to use for the scene
     */
    public GameScene(String sceneID) {
        this.sceneID = sceneID;
    }

    /**
     * @return The string identifier for the scene
     */
    public String getSceneID() {
        return sceneID;
    }

    /**
     * Hook method for scene entry. Use this for things such as initializations
     * and GUI loading
     */
    public abstract void onSceneEnter();

    /**
     * Hook method for the main runnable code. This code is called once a game
     * loop
     */
    public abstract void update();

    /**
     * Hook method for scene exit. This method is called before the scene is
     * swapped out
     */
    public abstract void onSceneExit();

    /**
     * Hook method for paused runnable code. This code is called once a game
     * loop when the game is paused.
     */
    public void paused() {

    }
}
