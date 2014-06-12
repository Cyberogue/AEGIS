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
public class Program extends GameScene {

    private static final java.util.Random rand = new java.util.Random();
    private static Timekeeper tk;

    @Override
    public void initialize() {
        System.out.println("Start run (15FPS)");
    }

    @Override
    public void main() {
        try {
            // SIMULATE A RANDOM LOOP DURATION
            int sleepFor = rand.nextInt(150) + 50;

            Thread.sleep(sleepFor);

            // PRINT STATISTICS
            System.out.println(tk.toString() + "\tDelay:" + sleepFor);
        } catch (Exception ex) {
            System.out.println("Error in run " + ex.toString());
        }
    }

    public void terminate() {
        System.out.println("Exit now?");
        tk.suspend();
    }

    public Program(String sceneID) {
        super(sceneID);
    }

    public static void main(String[] args) {
        try {
            // INITIALIZE TIMEKEEPER TO LOOOW FPS
            tk = new Timekeeper(new Program("Testprog"), 15.0f);
            tk.start();

            Thread.sleep(5000);
            
            ((GameScene) tk.getChild()).end();
        } catch (Exception ex) {
            // PRINT ERROR
            System.out.println("Error in main " + ex.toString());
        }
    }
}
