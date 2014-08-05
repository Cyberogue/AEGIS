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
package org.aegis2d.test;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.aegis.game.AegisGame;
import org.aegis2d.AnimatedGraphic;
import org.aegis2d.Sprite2D;

/**
 * Simple scene used for running test cases and nothing else
 *
 * @author Rogue <Alice Q>
 */
public class TestScene extends org.aegis.game.AsyncGameScene {

    private final static java.util.Random rand = new java.util.Random();

    public TestScene(AegisGame game, String sceneID) {
        super(game, sceneID);
        item = new Sprite2D();
    }

    private Sprite2D item;

    @Override
    public void onSceneEnter() {
        super.onSceneEnter();
        try {
            AnimatedGraphic ag = new AnimatedGraphic();
            ag.loadFromSpritesheet(ImageIO.read(new File(".\\img\\BlueTimerSheet.png")), 64, 64, 1, 2, 0);
            item.addState(ag);

            ag = new AnimatedGraphic();
            ag.loadFromSpritesheet(ImageIO.read(new File(".\\img\\GreenTimerSheet.png")), 64, 64, 1, 2, 5);
            item.addState(ag);

            ag = new AnimatedGraphic();
            ag.loadFromSpritesheet(ImageIO.read(new File(".\\img\\RedTimerSheet.png")), 64, 64, 1, 2, 10);
            item.addState(ag);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(1000);
                item.setCurrentAnimation(1);

                Thread.sleep(1000);
                item.setCurrentAnimation(2);

                Thread.sleep(1000);
                item.setCurrentAnimation(0);
            }
        } catch (InterruptedException e) {

        }
    }

    @Override
    public void update() {
        // PRINT A MESSAGE OR SOMETHING
        System.out.println(this + "\t" + game.getTimeKeeper());

        game.getGraphics().addToRenderList(item);
    }
}
