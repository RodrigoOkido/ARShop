package com.arshop.model;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


/**
 * Class responsible to deal with the Drawing in the user screen.
 */
public class ClickableDraw extends Drawable {

    private final Paint paint = new Paint();
    private boolean enabled;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled (boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        float cx = canvas.getWidth()/2;
        float cy = canvas.getHeight()/2;
        if (enabled) {
            paint.setColor(Color.GREEN);
            canvas.drawCircle(cx, cy, 10, paint);
        } else {
            paint.setColor(Color.GRAY);
            canvas.drawText("X", cx, cy, paint);
        }
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @SuppressLint("WrongConstant")
    @Override
    public int getOpacity() {
        return 0;
    }

}
