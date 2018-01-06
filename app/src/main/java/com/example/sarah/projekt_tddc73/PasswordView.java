package com.example.sarah.projekt_tddc73;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


public class PasswordView extends View {

    Paint p = new Paint();
    int x = 10;
    int y = 10;
    int radius = 10;


    public PasswordView(Context context) {
        super(context);
    }

    public PasswordView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PasswordView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PasswordView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        p.setColor(Color.YELLOW);
        canvas.drawCircle(x, y, radius, p);
    }
}
