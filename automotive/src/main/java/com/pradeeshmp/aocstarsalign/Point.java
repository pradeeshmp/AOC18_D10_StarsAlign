/*
 * *
 *  * Created by Pradeesh on 27/09/22, 12:29 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 27/09/22, 12:45 PM
 *
 */

package com.pradeeshmp.aocstarsalign;

class Point {
    public Point(int x, int y, int dx, int dy) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
    }

    public int x;
    public int y;
    public int dx;
    public int dy;

    // Method for updating the coordinates based on its velocity
    public void updateLocation() {
        this.x += dx;
        this.y += dy;
    }
}