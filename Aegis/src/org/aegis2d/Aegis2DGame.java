/*
 * The MIT License
 *
 * Copyright 2014 Rogue <Alice Q.>.
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
package org.aegis2d;

import org.aegis.data.GameResourceManager;
import org.aegis.game.AegisGame;
import org.aegis.game.GameSceneManager;
import org.aegis.game.TimeKeeper;
import org.aegis.ui.GameInputMonitor;

/**
 * Wrapper for the AegisGame which adds simple constructors and additional awt
 * and swing functionality
 *
 * @author Rogue <Alice Q.>
 */
public class Aegis2DGame extends AegisGame {

    public Aegis2DGame(String name, int width, int height) {
        this(name, width, height, TimeKeeper.FRAMERATE_HIGH);
    }

    public Aegis2DGame(String name, int width, int height, float targetFramerate) {
        super(name, targetFramerate);

        //a2dg.initializeWindow(width, height, true);
        set(new Aegis2DGraphics(name, width, height));
        set(new GameInputMonitor());
        set(new GameResourceManager());
        set(new GameSceneManager(this));
    }

    @Override
    public Aegis2DGraphics getGraphics() {
        return super.getGraphics();
    }
}
