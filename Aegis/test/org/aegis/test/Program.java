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
package org.aegis.test;

import org.aegis.*;

/**
 * Simple program used to test different functions of the project
 *
 * @author Rogue <Alice Q>
 */
public class Program implements java.lang.Runnable {

    private static final java.util.Random rand = new java.util.Random();
    private static Timekeeper tk;

    @Override

    public void run() {
        try {
            // SIMULATE A RANDOM LOOP DURATION
            Thread.sleep(50 + rand.nextInt(100));

            // PRINT STATISTICS
            System.out.println(tk.toString());
        } catch (Exception ex) {
            System.out.println("Error in run " + ex.toString());
        }
    }

    public static void main(String[] args) {
        try {
            // INITIAL MESSAGE
            System.out.println("Start run");

            // INITIALIZE TIMEKEEPER TO LOOOW FPS
            tk = new Timekeeper(new Program(), 2.0f);
            tk.start();

            System.out.println("Testing at 2FPS");
            Thread.sleep(3000);

            // SET THE NEW FRAMERATE TO CAUSE CLIPPING
            tk.lockFramerate(7.0f);
            System.out.println("Testing at 7FPS");
            Thread.sleep(3000);

            // SET THE NEW FRAMERATE TO SOMETHING HIGHER
            tk.lockFramerate(30.0f);
            System.out.println("Testing at 30FPS");
            Thread.sleep(3000);

            // SET THE NEW FRAMERATE TO SOMETHING HIGHER
            tk.unlockFramerate(60.0f);
            System.out.println("Testing at unlocked framerate");
            Thread.sleep(3000);

            // EXIT OTHERWISE THE THREAD WILL RUN ENDLESSLY
            System.out.println("End run");
            tk.pause();

            Thread.sleep(1000);
            System.out.println("Should exit now?");
            tk.suspend();
        } catch (Exception ex) {
            // PRINT ERROR
            System.out.println("Error in main " + ex.toString());
        }
    }
}
