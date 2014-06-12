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

import org.aegis.Timekeeper;

/**
 *
 * @author Rogue <Alice Q>
 */
public class TestScene2 extends org.aegis.GameScene {

    private Timekeeper tk;
    private final static java.util.Random rand = new java.util.Random();

    public TestScene2(String sceneID, Timekeeper tk) {
        super(sceneID);
        this.tk = tk;
    }

    @Override
    public void onSceneEnter() {
        System.out.println("Enter 2");
    }

    @Override
    public void update() {
        int sleepFor = rand.nextInt(100) + 50;
        System.out.print("[" + sleepFor + "]\tUpdate 2\t");
        try {
            Thread.sleep(sleepFor);
        } catch (Exception ex) {

        }
        System.out.println(tk.toString());
    }

    @Override
    public void onSceneExit() {
        System.out.println("Exit 2");
    }

    @Override
    public void paused() {
        System.out.println("Paused 2");
    }

}
