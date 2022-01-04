package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    DrawingPlace drawingPlace;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawingPlace = new DrawingPlace(this);
        drawingPlace.setBackgroundColor(Color.WHITE);
        setContentView(drawingPlace);
        drawingPlace.requestFocus();
    }


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(new paintMedia(this));
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private static class paintMedia extends View {

        private Paint areaPaint = new Paint();

        public paintMedia(Context context) {
            super(context);

        }

        protected void onDraw(Canvas canvas) {

            Paint paint = areaPaint;
            int x, y;
            canvas.drawColor(Color.WHITE);
            paint.setColor(Color.MAGENTA);
            canvas.drawCircle(60, 60, 50, paint);
            paint.setColor(Color.BLUE);
            paint.setStrokeWidth(3);
            canvas.drawPoint(60, 60, paint);
            canvas.drawRect(180, 20, 260, 70, paint);
            paint.setStrokeWidth(1);
            paint.setColor(Color.RED);
            x = 0;
            canvas.drawLine(x + 10, 80, x + 10, 320, paint);
            y = 0;
            canvas.drawLine(0, y + 200, 400, y + 200, paint);
            paint.setColor(Color.BLUE);
            for (x = 0; x <= 360; x++) {
                y = (int) (Math.sin(x * Math.PI / 180) * 100);
                canvas.drawPoint(x + 10, y + 200, paint);
                if ((x % 90) == 0) {
                    canvas.drawText(Integer.toString(x), x + 10, 215, paint);
                    paint.setStrokeWidth(3);
                    canvas.drawPoint(x + 10, 200, paint);
                    paint.setStrokeWidth(1);
                }
            }
        }
    }
}