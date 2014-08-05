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

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import javax.imageio.ImageIO;
import org.aegis.game.AegisGame;
import org.aegis.ui.FixedRenderItem;
import org.aegis2d.Decal;
import org.aegis.ui.LayerStack;
import org.aegis.ui.RenderItem;
import org.aegis.ui.RenderList;
import org.aegis2d.AnimatedDecal;
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

    @Override
    public void onSceneEnter() {
        try {
            item = new AnimatedDecal();
            for (BufferedImage image : AnimatedDecal.getFromSpritesheet(
                    ImageIO.read(new File(".\\img\\AnimButtonSheet.png")), 64, 64, 1, 2)) {
                ((AnimatedDecal) item).addFrame(image, 5);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update() {
        // PRINT A MESSAGE OR SOMETHING
        System.out.println(this + "\t" + game.getTimeKeeper());

        game.getGraphics().addToRender(item);

    }
}
