package com.example.myapplication;

import android.app.AppComponentFactory;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author Kavya Mandla
 */
public class notepadSurfaceView  extends View {
    Paint paint;

    Path path;

    /**
     * External Citation:
     * Person Commenting: Kavya Mandla
     * 5 December 2019
     * Problem: wanted to implement a free hand drawing ability to strategize moves
     * Resource: https://www.youtube.com/watch?v=Tsg0HDUeiAs
     * Solution: used the code in this video to help me with this.
     **/


    /**
     * @param context
     * @param attrs
     */
    public notepadSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        path = new Path();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5f);

    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.drawPath(path, paint);
        canvas.save();
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent){
        float x = motionEvent.getX();
        float y = motionEvent.getY();

        switch(motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x,y);
                return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(x, y);
                break;
            case MotionEvent.ACTION_UP:
                break;
                default:
                    return false;
        }
        this.invalidate();
        return true;
    }

}
