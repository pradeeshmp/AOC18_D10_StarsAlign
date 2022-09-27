/*
 * *
 *  * Created by Pradeesh on 27/09/22, 3:25 AM
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 27/09/22, 3:14 AM
 *
 */

package com.pradeeshmp.aocstarsalign;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class StarView extends View {
    private Paint paint;
    List<Point> points = new ArrayList<>();

    public StarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(getResources().getColor(R.color.colorPoleThemeText, null));
    }

    public void plotMyStars(List<Point> points) {
        this.points = points;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int sizeBandwidth = 15;
        int size = 5;
        for(Point p: points){
            canvas.drawCircle(p.x * sizeBandwidth, p.y * sizeBandwidth, size, paint);
        }
    }
}