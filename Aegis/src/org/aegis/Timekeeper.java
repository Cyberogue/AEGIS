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
 * Decorative class meant to run a Runnable object at a specified rate and
 * maintain performance statistics
 *
 * @author Rogue <Alice Q>
 */
public class Timekeeper extends java.lang.Thread {

    // DEFAULT FRAMERATES
    public final static float FRAMERATE_ONE = 1.0f;
    public final static float FRAMERATE_LOW = 30.0f;
    public final static float FRAMERATE_HIGH = 60.0f;
    public final static float FRAMERATE_ULTRA = 120.0f;

    // SYNCHRONIZED OBJECT
    private Runnable child;

    // TARGETS
    private float targetFramerate;
    private long targetMillis;
    private boolean unlockedFramerate;

    private boolean running = true;

    // STATISTICS
    private float performance;  // STORES THE PERFORMANCE RATING AS A PERCENTAGE BETWEEN TARGET AND ACTUAL FRAME RATES
    private long lastRuntime;   // USED TO STORE THE TIME BETWEEN TWO SUBSEQUENT CALLS IN MILLIS
    private long averageRuntime;   // USED TO STORE A RUNNING AVERAGE OF THE RUNTIME IN MILLIS

    /**
     * Simple constructor set to FRAMERATE_LOW
     *
     * @param syncedObject The Synchronizable object to run at a synchronized
     * rate
     */
    public Timekeeper(Runnable syncedObject) {
        this(syncedObject, FRAMERATE_LOW);
    }

    /**
     * Constructor for a target frame rate
     *
     * @param syncedObject The Synchronizable object to run at a synchronized
     * rate
     * @param targetFramerate The child frame rate
     */
    public Timekeeper(Runnable syncedObject, float targetFramerate) {
        if (targetFramerate <= 0.0f) {
            throw new IllegalArgumentException("Framerate must be positive and non-zero");
        }

        child = syncedObject;

        this.targetFramerate = targetFramerate;
        this.targetMillis = (long) (1000 / targetFramerate);
        this.unlockedFramerate = false;
    }

    // SETTERS
    // - unlockFramerate
    // - lockFramerate
    // - pause
    // - unpause
    /**
     * Used to lock the framerate
     *
     * @param newFramerate The new framerate
     */
    public void lockFramerate(float newFramerate) {
        if (newFramerate <= 0.0f) {
            throw new IllegalArgumentException("Framerate must be positive and non-zero");
        }

        this.targetFramerate = newFramerate;
        this.targetMillis = (long) (1000 / newFramerate);
        this.unlockedFramerate = false;
    }

    /**
     * Used to unlock the framerate. Note that a target framerate is still
     * required.
     *
     * @param framerate A framerate to use for calculating statistics
     */
    public void unlockFramerate(float framerate) {
        this.targetFramerate = framerate;
        this.targetMillis = (long) (1000 / targetFramerate);
        this.unlockedFramerate = true;
    }

    /**
     * Pauses the Timekeeper
     */
    public void pause() {
        this.running = false;
    }

    /**
     * Resumes the Timekeeper
     */
    public void unpause() {
        this.running = true;
    }

    // GETTERS
    // - getTargetFramerate
    // - isUnlocked
    // - isPaused
    /**
     * @return The child frame rate
     */
    public float getTargetFramerate() {
        return targetFramerate;
    }

    /**
     * @return Whether or not the frame rate is unlocked
     */
    public boolean isUnlocked() {
        return unlockedFramerate;
    }

    /**
     * @return Whether or not the Timekeeper is paused
     */
    public boolean isPaused() {
        return !running;
    }

    // STATISTICS
    // getPerformance
    // getLastRuntime
    // getSmoothedRuntime
    /**
     * @return The performance index as a ratio between the expected runtime and
     * the actual runtime
     */
    public float getPerformance() {
        return performance;
    }

    /**
     * @return The total runtime of the previous call
     */
    public long getLastRuntime() {
        return lastRuntime;
    }

    /**
     * @return The average runtime
     */
    public long getAverageRuntime() {
        return averageRuntime;
    }

    /**
     * @return The average framerate taken as a function of the calculated
     * runtime
     */
    public float getAverageFramerate() {
        return (float) 1000 / averageRuntime;
    }

    // Main running method
    /**
     * Method which calls run() on the Runnable in a loop while updating
     * statistics on each subsequent run
     */
    @Override
    public void run() {
        while (true) {
            // SET SOME INITIAL VALUES IN CASE OF READ
            lastRuntime = targetMillis;
            averageRuntime = lastRuntime;
            performance = 1.0f;

            // AND RUN MAIN
            while (running) {
                // GET THE START TIME
                long startTime = System.currentTimeMillis();

                // PASS ON THE METHOD TO THE CHILD WHILE OBSERVING STATISTICS
                child.run();

                // GET THE DELTA TIME AND FIND THE WAIT TIME
                long delta = startTime + targetMillis - System.currentTimeMillis();

                // SLEEP FOR SOME AMOUNT OF TIME IF NEEDED
                if (delta > 0 && !unlockedFramerate) {
                    try {
                        Thread.sleep(delta);
                    } catch (InterruptedException ex) {
                    }
                }

                // CALCULATE STATISTICS AND FINISH UP
                long runtime = System.currentTimeMillis() - startTime;

                averageRuntime = (lastRuntime + runtime) / 2;
                performance = (float) targetMillis / runtime;

                lastRuntime = runtime;
            }
        }
    }

    /**
     * @return A string of the format [child.toString] <target
     * framerate>:<calculated framerate>:<performance index>
     */
    @Override
    public String toString() {
        return "[" + child.toString() + "]\tT:" + (unlockedFramerate ? "U" : targetFramerate) + "\tC:" + ((float) 1000 / averageRuntime) + "\tP:" + performance;
    }
}
