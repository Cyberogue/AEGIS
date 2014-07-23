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
import org.aegis.ui.RenderItem;
import org.aegis.ui.RenderList;
import org.aegis.ui.Viewport;

/**
 *
 * @author Rogue <Alice Q>
 */
public class DebugScene extends org.aegis.game.GameScene {
    
    private final static java.util.Random rand = new java.util.Random();
    
    public DebugScene(AegisGame game, String sceneID) {
        super(game, sceneID);
    }

    // TWO WAYS OF STORING ITEMS FOR RENDERING
    private int debugItemID;
    private RenderItem debugList;
    
    private Viewport vp;
    
    @Override
    public void onSceneEnter() {
        // THIS IS JUST AN EXAMPLE OF HOW SCENE-SPECIFIC RENDERING STUFF WORKS

        // LOADING ITEMS INTO THE SCENE IS SLOW BY COMPARISON SO WE ONLY WANT TO DO THIS ONCE
        // YOU CAN STORE THIS IN ONE OF TWO WAYS - AS AN INDEX OR THROUGH DIRECT REFERENCE
        debugItemID = game.getResources().getRenderItems().indexOf("ITEM");
        debugList = game.getResources().getRenderItems().get("LIST");
        ((RenderList) debugList).setPersistence(true);

        // VIEWPORT TESTING
        vp = new Viewport(1920, 1080);
        vp.limitOffset(-500, 5000, -1500, 1500);
        
        System.out.println(this + "\t" + game.getTimeKeeper());
    }
    
    @Override
    public void update() {
        // PRINT A MESSAGE OR SOMETHING
        //System.out.println(this + "\t" + game.getTimeKeeper());

        // THESE TWO RENDER METHODS ARE FAST ENOUGH FOR RUNTIME USAGE, AS LONG AS YOU RETRIEVE OBJECTS USING AN INTEGER AND NOT A STRING
        //addToRenderList(game.getResources().getRenderItems().get(debugItemID));  // THIS USES AN ID
        //addToRenderList(debugList);  // THIS USES DIRECT REFERENCE
        // !!!! DO NOT USE STRING KEYS AT RUNTIME TO FETCH ITEMS !!!!!
        // addToRenderList(game.getResources().getRenderItems().get("LIST"));
        System.out.println(vp);
        vp.moveX(500.0f);
        vp.moveY(500 - rand.nextInt(1000));
    }
}
