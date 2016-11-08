package com.example.v_cuyi.sensorandroid_10_24;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.view.View;
import android.hardware.SensorEventListener;
import android.widget.Toast;


/**
 * Created by v-cuyi on 10/24/2016.
 */

public class BallView extends View implements SensorEventListener {
    private Bitmap ball = null;
    private float[] allValue;
    private Point point = new Point(0, 0);
    private int xSpeed = 0;
    private int ySpeed = 0;

    public BallView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setBackgroundColor(Color.WHITE);
        ball = BitmapFactory.decodeResource(this.getResources(), R.drawable.lion);
        SensorManager manager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        manager.registerListener(this, manager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_ORIENTATION) {
            float[] value = event.values;
            allValue = value;
           // System.out.println(allValue[2]+"              "+allValue[1]);
            super.postInvalidate();
        } else {
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        /// /super.onDraw(canvas);
        Paint p = new Paint();
        if (allValue != null) {
            xSpeed = (int) -allValue[2];
            ySpeed = (int) -allValue[1];
        } else {
        }
        point.x += xSpeed;
        point.y += ySpeed;
        if (point.x < 0) {
            point.x = 0;
        } else {
        }
        if (point.y < 0) {
            point.y = 0;
        } else {
        }
        if (point.x > super.getWidth() - ball.getWidth()) {
            point.x = super.getWidth() - ball.getWidth();
        } else {
        }
        if (point.y > super.getHeight() - ball.getHeight()) {
            point.y = super.getHeight() - ball.getHeight();
        } else {
        }
        canvas.drawBitmap(ball, point.x, point.y, p);
    }
}
