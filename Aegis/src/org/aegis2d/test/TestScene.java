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
import org.aegis.game.AegisGame;
import org.aegis.ui.FixedRenderItem;
import org.aegis2d.Decal;
import org.aegis.ui.LayerStack;
import org.aegis.ui.RenderItem;
import org.aegis.ui.RenderList;
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

    private RenderItem item;
    private RenderList list;
    private LayerStack stack;
    private Sprite2D sprite;

    private int direction;
    private float vY;

    @Override
    public void onSceneEnter() {
        try {
            // DECALS ARE EXTENSIONS OF BUFFEREDIMAGES SUITED FOR USE WITH AEGIS
            Decal helloTile = new Decal(new File(".\\img\\HelloWorld.png"));
            Decal blueTile = new Decal(new File(".\\img\\BlueBUtton.png"));

            // A SIMPLE RENDER ITEM 
            item = new FixedRenderItem(blueTile, 50, 100);

            // A RENDER LIST
            list = new RenderList();
            for (int i = 0; i < 3; i++) {
                list.add(new FixedRenderItem(blueTile, 50 + i * 50, 200 + i * 10));
                list.add(new FixedRenderItem(helloTile, 75 + i * 50, 205 + i * 10));
            }

            // A LAYER STACK
            stack = new LayerStack(3);
            for (int i = 0; i < 5; i++) {
                stack.addToLayer(0, new FixedRenderItem(blueTile, 50 + i * 50, 300 + i * 5));
                stack.addToLayer(1, new FixedRenderItem(helloTile, 75 + i * 50, 325 + i * 5));
                stack.addToLayer(2, new FixedRenderItem(blueTile, 100 + i * 50, 350 + i * 5));
            }

            // AND A SIMPLE SPRITE
            sprite = new Sprite2D(helloTile, 250, 500);
            stack.addToLayer(1, sprite);
            direction = 1; // SIMPLE BOUNCING CODE
            vY = 1;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update() {
        // PRINT A MESSAGE OR SOMETHING
        System.out.println(this + "\t" + game.getTimeKeeper());

        game.getGraphics().render(item);
        game.getGraphics().render(list);
        game.getGraphics().render(stack);

        // SIMPLE BOUNCING CODE
        if (sprite.x() > 1024 - sprite.getDecal().getWidth()) {
            direction = -1;
        } else if (sprite.x() < 0) {
            direction = 1;
        }
        vY += 1;
        if (sprite.y() > 768 - sprite.getDecal().getHeight()) {
            vY = -10 + rand.nextFloat() * -30;
        }
        sprite.move(direction * 5 * game.getTimeKeeper().getPerformance(), vY);
        // NO NEED TO RENDER THE SPRITE SINCE IT'S PART OF THE STACK
    }
}
