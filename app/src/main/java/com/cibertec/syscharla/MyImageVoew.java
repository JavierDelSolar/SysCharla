package com.cibertec.syscharla;

import android.content.Context;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.view.GestureDetectorCompat;

public class MyImageVoew extends AppCompatImageView {

    private GestureDetectorCompat scrollGestureDetector;
    private ScaleGestureDetector scaleGestureDetector;

    public MyImageVoew(Context context) {
        super(context);
        init();
    }

    public MyImageVoew(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyImageVoew(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    float scrollx = getScrollX();
    float scrolly = getScrollY();
    // vamos a crear un gestureDetectorLstener para poder usar
    private class SrollListener extends GestureDetector.SimpleOnGestureListener
    {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            scrollx = scrollx + distanceX;
            scrolly = scrolly + distanceY;

            scrollx = Math.max(-10000f,Math.min(scrollx, getDrawable().getIntrinsicWidth()));
            scrolly = Math.max(-10000f,Math.min(scrolly, getDrawable().getIntrinsicHeight()));

            scrollTo((int)scrollx, (int)scrolly);
            return true;
        }
    }
    private  void init(){
        setScaleType(ScaleType.MATRIX);
        scrollGestureDetector = new GestureDetectorCompat(getContext(), new SrollListener());

        scaleGestureDetector = new ScaleGestureDetector(getContext(),
                new ScaleListener());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        scrollGestureDetector.onTouchEvent(event);
        scaleGestureDetector.onTouchEvent(event);
        return true;
    }
    private float scaleFactor = 1f;

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener
    {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scaleFactor = scaleFactor * detector.getScaleFactor();

            //No permitir que el objeto se vuelva muy pequeño o muy gigante
            scaleFactor = Math.max(0.1f,Math.min(scaleFactor,5.0f));
            Matrix matrix = new Matrix(getImageMatrix());
            matrix.setScale(scaleFactor, scaleFactor, detector.getFocusX(),
                    detector.getFocusY());
            setImageMatrix(matrix);
            return true;
        }
    }
}
