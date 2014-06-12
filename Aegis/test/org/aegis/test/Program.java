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

import org.aegis.*;

/**
 *
 * @author Rogue <Alice Q>
 */
public class Program {

    public static void main(String[] args) {
        try {
            Orchestrator o = new Orchestrator();
            Timekeeper tk = new Timekeeper(o, 10.0f);

            tk.setDaemon(true); //TEMP
            
            o.add(new TestScene1("Scene 1", tk));
            o.add(new TestScene2("Scene 2", tk));

            tk.start();
            Thread.sleep(3000);

            o.switchTo("Scene 2");
            Thread.sleep(2500);

            o.pause();
            Thread.sleep(2500);

            o.unpause();
            Thread.sleep(1000);

            o.terminate();

            Thread.sleep(500);
        } catch (Exception ex) {

        }
    }
}
