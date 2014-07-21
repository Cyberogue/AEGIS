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
 * Decorative class meant to run a Runnable object at a specified rate and
 * maintain performance statistics
 *
 * @author Rogue <Alice Q>
 */
public class TimeKeeper implements java.lang.Runnable {

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

    // SEMAPHORE FOR PAUSING
    private int sem_pause = 0;

    // STATISTICS
    private float performance;  // STORES THE PERFORMANCE RATING AS A PERCENTAGE BETWEEN TARGET AND ACTUAL FRAME RATES
    private long lastRuntime;   // USED TO STORE THE TIME BETWEEN TWO SUBSEQUENT CALLS IN MILLIS
    private long averageRuntime;   // USED TO STORE A RUNNING AVERAGE OF THE RUNTIME IN MILLIS
    private long framenumber = 1;

    // SELF-EXPANATORY
    private boolean isRunning;

    /**
     * Simple constructor set to FRAMERATE_LOW
     *
     * @param syncedObject The Synchronizable object to run at a synchronized
     * rate
     */
    public TimeKeeper(Runnable syncedObject) {
        this(syncedObject, FRAMERATE_LOW);
    }

    /**
     * Constructor for a target frame rate
     *
     * @param syncedObject The Synchronizable object to run at a synchronized
     * rate
     * @param targetFramerate The child frame rate
     */
    public TimeKeeper(Runnable syncedObject, float targetFramerate) {
        if (targetFramerate <= 0.0f) {
            throw new IllegalArgumentException("Framerate must be positive and non-zero");
        }

        child = syncedObject;

        this.targetFramerate = targetFramerate;
        this.targetMillis = (long) (1000 / targetFramerate);
        this.unlockedFramerate = false;
        this.isRunning = false;
    }
    // GETTERS
    // - getTargetFramerate
    // - isUnlocked
    // - getChild
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
     * @return The child object that is being observed
     */
    public Runnable getChild() {
        return child;
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

    /**
     * @return The total number of frames that have been elapsed
     */
    public long getFramenumber() {
        return framenumber;
    }

    // MAIN USAGE METHODS
    // - requestPause
    // - releasePause
    // - start
    // - end
    // - lockFramerate
    // - unlockFramerate
    // - isPaused
    // - isRunning
    // TODO rewrite requestPause and requestUnpause methods
    /**
     * Method to start the timer running
     */
    public void start() {
        isRunning = true;
        Thread thread = new Thread(this);
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
    }

    /**
     * Method which allows the timing system to safely end the next time it can
     */
    public void endAfterFrame() {
        isRunning = false;
    }

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

    // TODO rerwite pause shit and isPaused()
    // OVERWRITTEN METHODS
    // - run()
    // - toString()
    /**
     * Main method which serves as a wrapper for runSynced() and simply
     * initializes the initial values and provides an unsynchronized point to
     * suspend on pause statements
     */
    @Override
    public final void run() {
        // SET SOME INITIAL VALUES IN CASE OF READ
        lastRuntime = targetMillis;
        averageRuntime = lastRuntime;
        performance = 1.0f;

        // AND RUN MAIN
        while (isRunning) {
            // SUSPEND AT THIS CALL IF PAUSED
            runSynced();
        }
    }

    private synchronized void runSynced() {
        // WAIT UNTIL SEM_PAUSE IS 0 - THAT IS, THERE ARE NO PAUSE REQUESTS ON THIS THREAD
        while (sem_pause > 0) {
            try {
                wait();
            } catch (InterruptedException ex) {

            }
        }

        // GET THE START TIME
        long startTime = System.currentTimeMillis();

        // PASS ON THE METHOD TO THE CHILD WHILE OBSERVING STATISTICS
        child.run();

        // GET THE DELTA TIME AND FIND THE WAIT TIME
        long stopTime = System.currentTimeMillis();
        long delta = targetMillis - (stopTime - startTime);

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
        performance = (float) runtime / targetMillis;
        lastRuntime = runtime;
        framenumber++;
    }

    /**
     * @return A string representation of the timekeeper's statistics
     */
    @Override
    public String toString() {
        return String.format("[%06d]\t[%4.2f%c\t% 8.2f\t%.3f]", framenumber, targetFramerate, (unlockedFramerate ? '+' : ' '), 1000.0f / averageRuntime, performance);
    }
}
