/*
 * The MIT License
 *
 * Copyright 2014 Rogue <Alice Q>.
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
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.aegis.test;

import org.aegis.game.AegisGame;
import org.aegis.game.TimeKeeper;
import org.aegis.ui.RenderItem;

/**
 *
 * @author Rogue <Alice Q>
 */
public class DebugScene extends org.aegis.game.GameScene {
    
    private final static java.util.Random rand = new java.util.Random();
    
    public DebugScene(AegisGame game, String sceneID) {
        super(game, sceneID);
    }
    
    @Override
    public void update() {
        System.out.println(this + "\t" + game.getTimeKeeper());
        
        RenderItem debugItem = game.getResources().getRenderItems().get(0);
        game.getGraphics().addToGameRenderList(debugItem);
    }
}
