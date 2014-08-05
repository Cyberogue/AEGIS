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

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
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
public class TestScene extends org.aegis.game.GameScene {

    private final static java.util.Random rand = new java.util.Random();

    public TestScene(AegisGame game, String sceneID) {
        super(game, sceneID);
    }

    private Sprite2D item;

    @Override
    public void onSceneEnter() {
        try {
            item = new Sprite2D();

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

        game.getGraphics().getWindow().addKeyListener(new KeyListener() {
            @Override
            public void keyReleased(KeyEvent event) {
                switch (event.getKeyCode()) {
                    case KeyEvent.VK_SPACE:
                        item.pause(!item.isPaused());
                        break;
                    case KeyEvent.VK_1:
                        int frame = item.getCurrentAnimation().getCurrentFrame();
                        item.setCurrentAnimation(0);
                        item.getCurrentAnimation().goToFrame(frame);
                        break;
                    case KeyEvent.VK_2:
                        frame = item.getCurrentAnimation().getCurrentFrame();
                        item.setCurrentAnimation(1);
                        item.getCurrentAnimation().goToFrame(frame);
                        break;
                    case KeyEvent.VK_3:
                        frame = item.getCurrentAnimation().getCurrentFrame();
                        item.setCurrentAnimation(2);
                        item.getCurrentAnimation().goToFrame(frame);
                        break;
                    case KeyEvent.VK_W:
                        item.setWorldY(item.getWorldY() - 5);
                        break;
                    case KeyEvent.VK_A:
                        item.setWorldX(item.getWorldX() - 5);
                        break;
                    case KeyEvent.VK_S:
                        item.setWorldY(item.getWorldY() + 5);
                        break;
                    case KeyEvent.VK_D:
                        item.setWorldX(item.getWorldX() + 5);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void keyPressed(KeyEvent event) {

            }

            @Override
            public void keyTyped(KeyEvent event) {

            }
        });
    }

    @Override
    public void update() {
        // PRINT A MESSAGE OR SOMETHING
        System.out.println(this + "\t" + game.getTimeKeeper());

        game.getGraphics().addToRenderList(item);
    }
}
