package com.example.v_cuyi.sensorandroid_10_24;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.view.View;
import android.hardware.SensorEventListener;

/**
 * Created by v-cuyi on 10/25/2016.
 */

public class ArrowView extends View implements SensorEventListener {
    private Bitmap comp = null;
    private float[] allValue;

    public ArrowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setBackgroundColor(Color.WHITE);
        comp= BitmapFactory.decodeResource(this.getResources(),R.drawable.lion);
        SensorManager manager=(SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
        manager.registerListener(this,manager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),SensorManager.SENSOR_DELAY_GAME);




    }

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
        Paint p = new Paint();
        if (allValue != null) {
            float x = allValue[0];
            float y = allValue[1];
            canvas.restore();
            canvas.translate(getWidth() / 2, getHeight() / 2);
            if (y == 0 && x > 0) {
                canvas.rotate(90);
            } else if (y == 0 && x < 0) {
                canvas.rotate(270);
            } else {
                if (y >= 0) {
                    canvas.rotate((float) Math.tanh(x / y) * 90);
                } else {
                    canvas.rotate(180 + (float) Math.tanh(x / y) * 90);
                }
            }
        } else {
        }
        canvas.drawBitmap(comp, -comp.getWidth() / 2, -comp.getHeight() / 2, p);


    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            float[] value = event.values;
            allValue = value;
            postInvalidate();
        } else {
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
