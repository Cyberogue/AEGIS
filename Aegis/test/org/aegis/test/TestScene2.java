/*
 * Copyright (C) 2014 Rogue <Alice Q>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.aegis.test;

/**
 *
 * @author Rogue <Alice Q>
 */
public class TestScene2 extends org.aegis.GameScene {

    public TestScene2(String sceneID) {
        super(sceneID);
    }

    @Override
    public void onSceneEnter() {
        System.out.println("Enter 2");
    }

    @Override
    public void update() {
        System.out.println("Update 2");
    }

    @Override
    public void onSceneExit() {
        System.out.println("Exit 2");
    }

    @Override
    public void paused() {
        System.out.println("Paused 2");
    }

}
