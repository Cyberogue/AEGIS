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
 * Abstract class which represents a single game scene with its own loading and
 * run methods
 *
 * @author Rogue <Alice Q>
 */
public abstract class GameScene implements java.lang.Runnable {

    // SCENE NAME FOR IDENTIFICATION
    private String sceneID;

    // SCENE STATE
    private SceneState currentState;
    private SceneState lastState;

    public GameScene(String name) {
        sceneID = name;
        currentState = SceneState.INIT;
    }

    /**
     * Main overriden method which runs a different hook based on the state, DO
     * NOT TOUCH
     */
    @Override
    public void run() {
        switch (currentState) {
            case INIT:
                initialize();
                currentState = SceneState.RUN;
                break;
            case RUN:
                main();
                break;
            case END:
                terminate();
                currentState = SceneState.COMPLETED;
                break;
            case PAUSED:
                pause();
                break;
        }
    }

    /**
     * Switches the current state. Note that changes take place on the next game
     * loop.
     *
     * @param state The state to switch to
     */
    public void switchToState(SceneState state) {
        currentState = state;
    }

    /**
     * Switches to the PAUSED state
     */
    public void pause() {
        lastState = currentState;
        currentState = SceneState.PAUSED;
    }

    /**
     * Resumes from the PAUSED state to whatever state it was formerly in
     */
    public void resume() {
        currentState = lastState;
    }

    /**
     * Switches to the scene termination state
     */
    public void end() {
        currentState = SceneState.END;
    }

    /**
     * @return The current state the scene is in
     */
    public SceneState getCurrentState() {
        return currentState;
    }

    /**
     * @return The name of the string
     */
    public String getSceneID() {
        return sceneID;
    }

    /**
     * @return The name and state of the scene in String form
     */
    @Override
    public String toString() {
        return sceneID + " [" + currentState.toString() + "]";
    }

    // HOOK METHODS
    /**
     * Hook method for scene initialization, override for single-run
     * initialization method
     */
    protected abstract void initialize();

    /**
     * Hook method for the game loop, override with the main scene loop
     */
    protected abstract void main();

    /**
     * Hook method for cleanup before stopping the scene, override for
     * single-run end method
     */
    protected abstract void terminate();

    /**
     * Hook method for pausing the scene, override with paused code
     */
    protected void paused() {

    }

    /**
     * All the states a scene can be in
     */
    public enum SceneState {

        INIT, RUN, PAUSED, END, COMPLETED
    }
}
