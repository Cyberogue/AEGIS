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

/**
 * A custom GameScene which allows for two separate codes - one which is
 * asynchronous with the game and one which is synchronous. The two codes run in
 * parallel with the asynchronous code being executed once on load while the
 * synchronous code is executed once per game loop.
 *
 * @author Rogue <Alice Q>
 */
public abstract class AsyncGameScene extends GameScene implements Runnable {

    /**
     * Basic constructor
     *
     * @param game the AegisGame this scene corresponds to, used for data access
     * and retrieval
     * @param sceneID A string identifier to use for the scene
     */
    public AsyncGameScene(AegisGame game, String sceneID) {
        super(game, sceneID);
    }

    /**
     * Entry method which creates a new thread and executes the
     * AsyncGameScene.run() method parallel to the primary update method. If
     * this is overriden please be sure to leave a reference to the parent
     * method.
     */
    @Override
    public void onSceneEnter() {
        Thread thread = new Thread(this);
        thread.setPriority(Thread.NORM_PRIORITY);
        thread.setDaemon(true);
        thread.start();
    }
}
