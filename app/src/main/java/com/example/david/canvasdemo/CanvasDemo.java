package com.example.david.canvasdemo;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.ViewGroup;

//Following the youtube video "Graphics in android/android app development tutorial for beginners" second half ie from about 20mins in.
//unfortunately it didnt work! Something to do with the "onTouch" and the "onTouchListener" but I followed the tutorial exactly.
public class CanvasDemo extends AppCompatActivity implements View.OnTouchListener {
//had to add "implements ontouchlistener" and then click on the light bulb to add methods which put in the public boolean onTouch below.

    //declare some variables including bitmap
    private Bitmap bitmap;
    private float x;
    private float y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_demo);

        //the MyCanvas class is made further below, but here we now create (in onCreate ?) a "MyCanvas class object", called myCanvas.
        MyCanvas myCanvas = new MyCanvas(this);

        //set layout parameter to view group
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
        addContentView(myCanvas, layoutParams); //is this making the myCanvas a "view"? The FILL PARENT might mean fill the screen or the activity box.

        //set ontouchlistener to myCanvas
        myCanvas.setOnTouchListener(this);

        //set drawing cache to enabled True
        myCanvas.setDrawingCacheEnabled(true);
        //call get drawing cache
        bitmap = myCanvas.getDrawingCache();

    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {



        //add the following bitmap logic into this ontouch method (also have bitmap logic in the draw method below)
            x = event.getX();   //notice that here I am using the word "event" which comes from the line above, but the video uses "arg1".
            y = event.getY();

            bitmap = v.getDrawingCache();
            v.invalidate(); //ie refresh the view, v.
            return true; //changed from the default of false to true

    }




    //create class "mycanvas"; I typed this; I think you can also do it by using Add new java class, but not sure. Notice how the "MyCanvas" stays light grey until the class is "used"; this occurs when the MyClass object is created in OnCreate near top of this page.
    private class MyCanvas extends View{
        public MyCanvas(Context context){   //I had to add these 2 lines because there was no automatic "add constructor" option as on the video
            super(context);

        }



        //start typing dra and select public void draw.. to add the following (NB video tells to delete the "super.draw(canvas)" line, then create paint object, but I got red underline when I deleted it.)
        @Override
        public void draw(Canvas canvas) {
            super.draw(canvas);

            Paint paint = new Paint();    //set the colour and stroke width in next 2 lines
            paint.setColor(Color.YELLOW);
            paint.setStrokeWidth(50);

            //write bitmap logic in draw method:
            if(bitmap != null){
                bitmap.setPixel((int)x,(int)y, Color.BLUE); //set pixels for bitmap. notice that the x and y have to be changed to integer here.
                //call draw bitmap on canvas
                canvas.drawBitmap(bitmap, x, y, paint);
            }
        }
    }

}
