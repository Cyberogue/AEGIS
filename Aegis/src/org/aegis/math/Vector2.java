/*
 * The MIT License
 *
 * Copyright 2014 Rogue <Alice Q.>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
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
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.aegis.math;

/**
 *
 * @author Rogue <Alice Q.>
 */
public class Vector2 {

    public static final Vector2 ZERO = new Vector2(0.0f, 0.0f);
    public static final Vector2 ONE = new Vector2(1.0f, 1.0f);

    public static final Vector2 UP = new Vector2(0.0f, -1.0f);
    public static final Vector2 DOWN = new Vector2(0.0f, 1.0f);
    public static final Vector2 LEFT = new Vector2(-1.0f, 0.0f);
    public static final Vector2 RIGHT = new Vector2(1.0f, 0.0f);

    protected float x;
    protected float y;

    public Vector2() {
        this.x = 0.0f;
        this.y = 0.0f;
    }

    public Vector2(float f) {
        this.x = f;
        this.y = f;
    }

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2(Vector2 v) {
        this.x = v.x;
        this.y = v.y;
    }

    public float x() {
        return x;
    }

    public float y() {
        return y;
    }

    public Vector2 add(Vector2 v) {
        return new Vector2(x + v.x, y + v.y);
    }

    public Vector2 subtract(Vector2 v) {
        return new Vector2(x - v.x, y - v.y);
    }

    public Vector2 multiply(Vector2 v) {
        return new Vector2(x * v.x, y * v.y);
    }

    public Vector2 divide(Vector2 v) {
        return new Vector2(x / v.x, y / v.y);
    }

    public Vector2 add(float x, float y) {
        return new Vector2(this.x + x, this.y + y);
    }

    public Vector2 subtract(float x, float y) {
        return new Vector2(this.x - x, this.y - y);
    }

    public Vector2 multiply(float x, float y) {
        return new Vector2(this.x * x, this.y * y);
    }

    public Vector2 divide(float x, float y) {
        return new Vector2(this.x / x, this.y / y);
    }

    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    public double angle() {
        return Math.atan2(y, x);
    }

    public Vector2 normalized() {
        float magnitude = (float) Math.sqrt(x * x + y * y);
        return new Vector2(x / magnitude, y / magnitude);
    }

    public float dot(Vector2 v) {
        return x * v.x + y * v.y;
    }

    @Override
    public String toString() {
        return "{" + x + ',' + y + '}';
    }

    public boolean equals(Vector2 v) {
        return (x == v.x && y == v.y);
    }
}
