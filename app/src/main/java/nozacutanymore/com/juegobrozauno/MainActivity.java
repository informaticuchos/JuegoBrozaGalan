package nozacutanymore.com.juegobrozauno;

import android.app.Activity;
        import android.graphics.Color;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.MotionEvent;
        import android.view.View;
        import android.view.WindowManager;
        import android.view.Window;
        import android.widget.FrameLayout;
        import android.widget.ImageView;
        import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends Activity implements View.OnTouchListener{

    private Random r;
    private ImageView v;
    private ArrayList<ImageView> images;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        r = new Random();
        images = new ArrayList<>();
        //Changing to fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        View background = this.getWindow().getDecorView();

        background.setBackgroundColor(Color.RED);
        setContentView(R.layout.activity_main);

        v = (ImageView)findViewById(R.id.imageView);
        v.setImageResource(R.drawable.rotodoscon);
        FrameLayout myLayout = (FrameLayout)findViewById(R.id.layout);
        FrameLayout.LayoutParams params;
        v.setOnTouchListener(this);

        for(int i = 0; i < 10; i++){
            ImageView image = new ImageView(this);

            image.setX(r.nextFloat() * 300 + 150);
            image.setY(r.nextFloat() * 300 + 150);
            image.setImageResource(R.drawable.rotodoscon);
            params = new FrameLayout.LayoutParams(100,100);
            image.setOnTouchListener(this);
            myLayout.addView(image,params);
        }





    }



    @Override
    public boolean onTouch(View v, MotionEvent event) {


        v.bringToFront();
        ((View)v.getParent()).invalidate();
            if(event.getAction() == MotionEvent.ACTION_MOVE){

                if(v.getX()>=0 && v.getY()>= 0) {
                    v.setX(v.getX() + (int) event.getX() - v.getWidth() / 2);
                    v.setY(v.getY() + (int) event.getY() - v.getHeight() / 2);
                }
                else{
                    v.setX(0.1f);
                    v.setY(v.getY()+0.1f);
                }

            }
            if(event.getAction() == MotionEvent.ACTION_UP){
                v.setX(v.getX() + (int) event.getX() - v.getWidth() / 2);
                v.setY(v.getY() + (int) event.getY() - v.getHeight() / 2);



            }


            return true;


    }
}


