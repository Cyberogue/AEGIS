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

import org.aegis.*;

/**
 *
 * @author Rogue <Alice Q>
 */
public class Program {

    public static void main(String[] args) {
        try {
            Orchestrator o = new Orchestrator();
            Timekeeper tk = new Timekeeper(o, 10.0f);

            //tk.setDaemon(true); //TEMP
            
            o.add(new TestScene1("Scene 1", tk));
            o.add(new TestScene2("Scene 2", tk));

            tk.start();/*
            Thread.sleep(3000);

            o.switchTo("Scene 2");
            Thread.sleep(2500);

            o.pause();
            Thread.sleep(2500);

            o.unpause();
            Thread.sleep(1000);

            o.terminate();

            Thread.sleep(500);*/
        } catch (Exception ex) {

        }
    }
}
