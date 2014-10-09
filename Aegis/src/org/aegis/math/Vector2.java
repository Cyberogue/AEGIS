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
 * Immutable class containing two respective x and y float values, allowing to
 * transformations between them
 *
 * @author Rogue <Alice Q.>
 */
public class Vector2 {

    public static final Vector2 ZERO = new Vector2(0.0f, 0.0f);
    public static final Vector2 ONE = new Vector2(1.0f, 1.0f);

    private float x;
    private float y;

    /**
     * Default constructor which creates a Vector2 set to ZERO
     */
    public Vector2() {
        this.x = 0.0f;
        this.y = 0.0f;
    }

    /**
     * Constructor which sets both Vector2 values to the same value
     *
     * @param f the value to give both x and y
     */
    public Vector2(float f) {
        this.x = f;
        this.y = f;
    }

    /**
     * Constructor which allows for two separate values at initialization
     *
     * @param x the value to set for x
     * @param y the value to set for y
     */
    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructor which returns a new instance cloned from an existing Vector2
     *
     * @param v the Vector2 to copy values from
     */
    public Vector2(Vector2 v) {
        this.x = v.x;
        this.y = v.y;
    }

    /**
     * Returns the x value of the Vector2
     *
     * @return the x value of the Vector2
     */
    public float x() {
        return x;
    }

    /**
     * Returns the y value of the Vector2
     *
     * @return the y value of the Vector2
     */
    public float y() {
        return y;
    }

    /**
     * Returns a Vector2 representation of the sum of two Vector2 instances
     *
     * @param v The Vector2 containing the values to add
     * @return a Vector2 representation of the sum of two Vector2 instances
     */
    public Vector2 add(Vector2 v) {
        return new Vector2(x + v.x, y + v.y);
    }

    /**
     * Returns a Vector2 representation of the difference of two Vector2
     * instances
     *
     * @param v The Vector2 containing the values to subtract
     * @return a Vector2 representation of the difference of two Vector2
     * instances
     */
    public Vector2 subtract(Vector2 v) {
        return new Vector2(x - v.x, y - v.y);
    }

    /**
     * Returns a Vector2 representation of the product of two Vector2 instances
     *
     * @param v The Vector2 containing the values to multiply with
     * @return a Vector2 representation of the product of two Vector2 instances
     */
    public Vector2 multiply(Vector2 v) {
        return new Vector2(x * v.x, y * v.y);
    }

    /**
     * Returns a Vector2 representation of the quotient of two Vector2 instances
     *
     * @param v The Vector2 containing the values to divide by
     * @return a Vector2 representation of the quotient of two Vector2 instances
     */
    public Vector2 divide(Vector2 v) {
        return new Vector2(x / v.x, y / v.y);
    }

    /**
     * Returns a Vector2 representation of the sum between a Vector2 and 2
     * floats
     *
     * @param x The value to add to x
     * @param y The value to add to y
     * @return a Vector2 representation of the sum between a Vector2 and 2
     * floats
     */
    public Vector2 add(float x, float y) {
        return new Vector2(this.x + x, this.y + y);
    }

    /**
     * Returns a Vector2 representation of the difference between a Vector2 and
     * 2 floats
     *
     * @param x The value to subtract from x
     * @param y The value to subtract from y
     * @return a Vector2 representation of the difference between a Vector2 and
     * 2 floats
     */
    public Vector2 subtract(float x, float y) {
        return new Vector2(this.x - x, this.y - y);
    }

    /**
     * Returns a Vector2 representation of the product between a Vector2 and 2
     * floats
     *
     * @param x The value to multiply with x
     * @param y The value to multiply with y
     * @return a Vector2 representation of the product between a Vector2 and 2
     * floats
     */
    public Vector2 multiply(float x, float y) {
        return new Vector2(this.x * x, this.y * y);
    }

    /**
     * Returns a Vector2 representation of the quotient between a Vector2 and 2
     * floats
     *
     * @param x The value to divide x by
     * @param y The value to divide y by
     * @return a Vector2 representation of the quotient between a Vector2 and 2
     * floats
     */
    public Vector2 divide(float x, float y) {
        return new Vector2(this.x / x, this.y / y);
    }

    /**
     * Returns the length of the vector
     *
     * @return the length of the vector
     */
    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    /**
     * Returns the angle of the vector in radians
     *
     * @return the angle of the vector in radians
     */
    public double angle() {
        return Math.atan2(y, x);
    }

    /**
     * Returns the angle of the vector in degrees
     *
     * @return the angle of the vector in degrees
     */
    public double angleDegrees() {
        return Math.atan2(y, x) * 57.2957795131;  // 180/pi
    }

    /**
     * Returns a normalized Vector2 of magnitude 1.0f
     *
     * @return a normalized Vector2 of magnitude 1
     */
    public Vector2 normalize() {
        float magnitude = (float) Math.sqrt(x * x + y * y);
        return new Vector2(x / magnitude, y / magnitude);
    }

    /**
     * APproximates a normalization of the Vector2 using the Quake fast inverse
     * square root algorithm. Note that since this is an approximation, it does
     * not guarantee perfect normalization but should be close to it.
     *
     * @return an approximated normalized Vector2 of magnitude 1
     */
    public Vector2 fastNormalize() {
        float fsqrt = Vector2.q_rsqst(x * x + y * y);
        return new Vector2(x * fsqrt, y * fsqrt);
    }

    /**
     * Returns the dot product of a Vector2 with this Vector2. If both Vector2
     * instances are normalized, this is equivalent to the cosine of the angle
     * between them.
     *
     * @param v The Vector2 to dot product with
     * @return the dot product result
     */
    public float dot(Vector2 v) {
        return x * v.x + y * v.y;
    }

    /**
     * Returns true if both values are equal to zero
     *
     * @return true if both values are equal to zero
     */
    public boolean isZero() {
        return (x == 0f && y == 0f);
    }

    /**
     * Returns true if the magnitude of the Vector2 is 1.0f
     *
     * @return true if the magnitude of the Vector2 is 1.0f
     */
    public boolean isNormalized() {
        if (x > 1.0f || x < -1.0f || y > 1.0f || y < -1.0f) {    // Fast check
            return false;
        }
        return (this.magnitude() == 1.0f);
    }

    /**
     * Quake quick inverse square root algorithm
     *
     * @param number The number to take the inv_sqrt of
     * @return a floating point approximation of the inv_sqrt
     */
    public static float q_rsqst(float number) {
        int i;
        float x2, y;

        x2 = number * 0.5f;
        i = Float.floatToRawIntBits(number);  // evil floating point bit level hacking
        i = 0x5f3759df - (i >> 1);            // what the fuck?
        y = Float.intBitsToFloat(i);
        y = y * (1.5f - (x2 * y * y));        // 1st iteration
        // y = y * (1.5f - (x2 * y * y));     // 2nd iteration, this can be removed

        return y;
    }

    @Override
    public String toString() {
        return "{" + x + ',' + y + '}';
    }

    /**
     * Returns true if both Vector2 instances are equal
     *
     * @param v the Vector2 to compare to
     * @return true if both Vector2 instances are equal
     */
    public boolean equals(Vector2 v) {
        return (x == v.x && y == v.y);
    }
}
