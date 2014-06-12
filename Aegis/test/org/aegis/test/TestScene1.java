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

import org.aegis.Timekeeper;

/**
 *
 * @author Rogue <Alice Q>
 */
public class TestScene1 extends org.aegis.GameScene {

    Timekeeper tk;

    public TestScene1(String sceneID, Timekeeper tk) {
        super(sceneID);
        this.tk = tk;
    }

    @Override
    public void onSceneEnter() {
        System.out.println("Enter 1");
    }

    @Override
    public void update() {
        System.out.println("Update 1 " + tk.toString());
    }

    @Override
    public void onSceneExit() {
        System.out.println("Exit 1");
    }
}
