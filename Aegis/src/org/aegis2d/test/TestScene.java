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
import org.aegis.ui.Decal;
import org.aegis.ui.LayerStack;
import org.aegis.ui.RenderItem;
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

    private RenderItem renderItem;
    private Sprite2D testSprite;

    @Override
    public void onSceneEnter() {
        try {
            Decal helloTile = new Decal(new File(".\\img\\HelloWorld.png"));
            Decal blueTile = new Decal(new File(".\\img\\BlueBUtton.png"));

            renderItem = new LayerStack(2);
            ((LayerStack) renderItem).addToLayer(0, new Sprite2D(blueTile));
            ((LayerStack) renderItem).addToLayer(0, new Sprite2D(blueTile, 100, 100));
            ((LayerStack) renderItem).addToLayer(1, new Sprite2D(helloTile, 50, 50));
            ((LayerStack) renderItem).addToLayer(0, new Sprite2D(blueTile, 200, 100));
            ((LayerStack) renderItem).addToLayer(1, new Sprite2D(helloTile, 100, 200));
            ((LayerStack) renderItem).addToLayer(1, new Sprite2D(helloTile, 200, 200));

            testSprite = new Sprite2D(helloTile, 250, 250);
            ((LayerStack) renderItem).addToLayer(1, testSprite);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update() {
        // PRINT A MESSAGE OR SOMETHING
        System.out.println(this + "\t" + game.getTimeKeeper());

        game.getGraphics().render(renderItem);
        testSprite.move(10 - rand.nextFloat() * 20, 10 - rand.nextFloat() * 20);
    }
}
