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
