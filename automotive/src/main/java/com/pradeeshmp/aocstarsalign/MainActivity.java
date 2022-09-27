/*
 * *
 *  * Created by Pradeesh on 27/09/22, 01:21 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 27/09/22, 02:09 PM
 *
 */

package com.pradeeshmp.aocstarsalign;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    List<Point> points = new ArrayList<>();
    int maxX,minX,maxY,minY = 0;
    // Area used as comparison for the next loop to figureout seconds taken
    int area = 0;
    // Total seconds used
    int seconds = 0;
    //Canvas view for better presentation
    String FILE_NAME = "d10.txt";
    StarView starView;
    TextView secondsTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        starView = findViewById(R.id.starView);
        secondsTv = findViewById(R.id.secondsTv);
        try {
            parseInput();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void parseInput() throws IOException {
        //TODO Add loader here
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(getAssets().open(FILE_NAME)))) {
            String mLine;
            while ((mLine = bufferedReader.readLine()) != null) {
                String[] firstSplit = mLine.split("<");
                int x = Integer.parseInt(firstSplit[1].split(",")[0].trim());
                int y = Integer.parseInt(firstSplit[1].split(",")[1].split(">")[0].trim());

                int dx = Integer.parseInt(firstSplit[2].split(",")[0].trim());
                int dy = Integer.parseInt(firstSplit[2].split(",")[1].split(">")[0].trim());

                // FInd min max points for x and y
                if (x > maxX) {
                    maxX = x;
                } else if (x < minX) {
                    minX = x;
                }

                if (y > maxY) {
                    maxY = y;
                } else if (y < minY) {
                    minY = y;
                }
                // Add the point to the list of points
                this.points.add(new Point(x, y, dx, dy));
            }
            this.area = maxX + (minX * -1) * maxY + (minY * -1);

        } catch (IOException e) {
            e.printStackTrace();
        }
        this.letsStartTheLOOP();
    }

    private void letsStartTheLOOP() {
        while (true) {
            // Add another second for every loop
            this.seconds++;
            // Reset points
            this.maxX = this.points.get(0).x;
            this.minX = this.points.get(0).x;
            this.maxY = this.points.get(0).y;
            this.minY = this.points.get(0).y;
            for (Point point : this.points) {
                // Update the point's location
                point.updateLocation();
                // Check if this points is an extremal point
                if (point.x > maxX) {
                    this.maxX = point.x;
                } else if (point.x < minX) {
                    this.minX = point.x;
                }

                if (point.y > maxY) {
                    this.maxY = point.y;
                } else if (point.y < minY) {
                    this.minY = point.y;
                }
            }

            // Convert negative points to positive to use as comparison
            if (this.minX < 0) {
                this.minX = minX * -1;
            }
            if (this.minY < 0) {
                this.minY = minY * -1;
            }

            // Calculate new area and check if it still shrinks
            int newArea = (this.maxX + this.minX) * (this.maxY + this.minY);
            if (this.area > 0 && newArea > this.area) {
                break;
            } else {
                this.area = newArea;
            }
        }
        for(Point point : this.points){
            point.x -= point.dx;
            point.y -= point.dy;
        }
        givePointsToCanvasView();
    }

    private void givePointsToCanvasView() {
        for (Point point : points) {
            point.x = point.x - minX;
            point.y = point.y - minY;
        }
        starView.plotMyStars(points);
        Log.d(this.getClass().getName(), "Seconds Taken :"+this.seconds);
        secondsTv.setText("Took "+this.seconds+" seconds to align");
    }
}
