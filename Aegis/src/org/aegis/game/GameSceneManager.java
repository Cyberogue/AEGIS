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

/**
 * Container class which holds all of the game's different scenes, switching
 * between them as required. This allows an user implementation to readily
 * switch between and execute different sets of runtime code.
 *
 * @author Rogue <Alice Q.>
 */
public class GameSceneManager implements RuntimeSystem {

    // MAP OF ALL THE DIFFERENT SCENES IN THE GAME
    private HashMap<String, GameScene> scenes;

    // THE CURRENT SCENE
    private GameScene currentScene;

    // THE NEXT SCENE IN LINE
    private GameScene nextScene;

    /**
     * Constructor
     */
    public GameSceneManager() {
        scenes = new HashMap();
    }

    @Override
    public final void update() {
       
    }
}
