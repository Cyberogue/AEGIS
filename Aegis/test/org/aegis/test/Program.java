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

import org.aegis2d.GameWindow;
import org.aegis.*;

import java.awt.event.*;

/**
 *
 * @author Rogue <Alice Q>
 */
public class Program {

    public static void main(String[] args) {

        final Orchestrator o = new Orchestrator();
        final Synchronizer timer = new Synchronizer(o, 10.0f);
        final GameWindow w = new GameWindow("My Game", 1280, 768, timer);

        o.add(new TestScene("Test"));
        o.add(new DebugScene("Debug", timer));

        w.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyChar()) {
                    case '1':
                        w.setDimensions(1024, 768);
                        w.applyChanges();
                        break;
                    case '2':
                        w.setDimensions(1280, 768);
                        w.applyChanges();
                        break;
                    case '3':
                        w.setDimensions(1920, 1080);
                        w.applyChanges();
                        break;
                    case '4':
                        w.fitToScreen();
                        w.applyChanges();
                        break;
                    case '5':
                        w.fitToScreen();
                        w.setBorderless(true);
                        w.applyChanges();
                        break;
                    //-----------     
                    case 'f':
                        w.setFullscreen(!w.isFullstreen());
                        w.applyChanges();
                        break;
                    case 'b':
                        w.setBorderless(!w.isBorderless());
                        w.applyChanges();
                        break;
                    //-----------
                    case '+':
                    case '=':
                        timer.lockFramerate(timer.getTargetFramerate() * 0.8f);
                        System.out.println("Framerate " + (timer.getTargetFramerate() * 0.8f));
                        break;
                    case '-':
                        timer.lockFramerate(timer.getTargetFramerate() * 1.2f);
                        System.out.println("Framerate " + (timer.getTargetFramerate() * 1.2f));
                        break;
                    case 'u':
                        timer.unlockFramerate(10.0f);
                        System.out.println("Framerate Unlocked (10FPS)");
                        break;
                    //-----------
                    case 'd':
                        o.switchTo("Debug");
                        System.out.println("Entered debug scene");
                        break;
                    case 't':
                        o.switchTo("Test");
                        System.out.println("Entered test scene");
                        break;
                    //-----------
                    default:
                        break;
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }
        });

        timer.start();
    }
}
