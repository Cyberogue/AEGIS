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
package org.aegis.test;

import org.aegis.game.AegisGame;
import org.aegis.game.GameSceneManager;

/**
 * Simple test program for running simple test cases
 *
 * @author Rogue <Alice Q>
 */
public class Program {

    public static void main(String[] args) {
        AegisGame game = new AegisGame("My Game", 1.0f);

        try {
            game.set(new GameSceneManager());
            game.getScenes().addScene(new DebugScene("DEBUG1", game.getTimeKeeper()));
            game.getScenes().addScene(new DebugScene("DEBUG2", game.getTimeKeeper()));
            game.getScenes().addScene(new DebugScene("DEBUG3", game.getTimeKeeper()));
            game.getScenes().addScene(new DebugScene("DEBUG4", game.getTimeKeeper()));
            game.start();

            while (true) {
                Thread.sleep(5000);
                game.getScenes().setNext("DEBUG2");
                Thread.sleep(5000);
                game.getScenes().setNext("DEBUG3");
                Thread.sleep(5000);
                game.getScenes().setNext("DEBUG4");
                Thread.sleep(5000);
                game.getScenes().setNext("DEBUG1");
            }

        } catch (InterruptedException ex) {

        }
    }
}
