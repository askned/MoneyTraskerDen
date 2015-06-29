package com.example.rf1.myapplication2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

/**
 * Created by 1 on 22.06.2015.
 */
public class PieChartView extends View {
    private Paint slicePaint;
    private RectF rectF;
    private float[] datapoints;

    public PieChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        slicePaint = new Paint();
        slicePaint.setAntiAlias(true);
        slicePaint.setDither(true);

        slicePaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.datapoints != null) {
            Random r;
            int startTop = 0;
            int startLeft = 0;
            int endBottom = getWidth();
            int endRight = endBottom;

            rectF = new RectF(startLeft, startTop, endRight, endBottom);

            float[] scaledValues = scale();
            float sliceStartPoint = 0;

            for (float scaledValue : scaledValues) {
                r = new Random();
                int color = Color.argb(100, r.nextInt(256), r.nextInt(256), r.nextInt(256));
                slicePaint.setColor(color);
                canvas.drawArc(rectF, sliceStartPoint, scaledValue, true, slicePaint);
                sliceStartPoint += scaledValue;
            }
        }
    }

    public void setDatapoints(float[] datapoints) {
        this.datapoints = datapoints;
        invalidate();
    }

    private float[] scale() {
        float[] scaledValues = new float[this.datapoints.length];
        float total = getTotal();

        for (int i = 0; i < this.datapoints.length; i++) {
            scaledValues[i] = (this.datapoints[i] / total) * 360;
        }
        return scaledValues;
    }

    private float getTotal() {
        float total = 0;
        for (float val : this.datapoints) {
            total += val;
        }
        return total;
    }

}