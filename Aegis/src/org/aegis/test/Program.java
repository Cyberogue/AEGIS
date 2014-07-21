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

import org.aegis.data.GameResourceManager;
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

        game.set(new GameSceneManager());
        game.getScenes().addScene(new DebugScene("DEBUG", game.getTimeKeeper()));

        game.set(new GameResourceManager());
        for (int i = 0; i < 50; i++) {
            game.getResources().loadResource("ITEM" + i, new DebugRenderItem());
        }
        int index = game.getResources().loadResource("DEBUGITEM", new DebugRenderItem());

        System.out.println(index + "\t" + game.getResources().size());

        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            game.getResources().get("ITEM2");
        }
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            game.getResources().get(2);
        }
        System.out.println(System.currentTimeMillis() - start);

        //   game.start();
    }
}
