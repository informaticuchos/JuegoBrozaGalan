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
import android.widget.EditText;
import android.widget.FrameLayout;
        import android.widget.ImageView;
        import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends Activity implements View.OnTouchListener{

    private Random r;
    private ImageView v;
    private ArrayList<RotoTwo> images;
    private TextView score;
    private int scoreInt = 0;
    private boolean gameRunning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        r           = new Random();
        images      = new ArrayList<>();
        gameRunning = true;
        //Changing to fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final View background = this.getWindow().getDecorView();
        background.setBackgroundColor(Color.RED);
        setContentView(R.layout.activity_main);
        score       = (TextView)findViewById(R.id.puntuacion);
        final FrameLayout myLayout = (FrameLayout)findViewById(R.id.layout);
        FrameLayout.LayoutParams params;
        //this.score.append(" "+this.scoreInt);

        for(int i = 0; i < 10; i++){

            RotoTwo image      = new RotoTwo(this);
            params             = new FrameLayout.LayoutParams(100,100);
            image              .setOnTouchListener(this);
            myLayout           .addView(image, params);
            images             .add(image);
        }

        Thread hilomovedor = new Thread(new Runnable() {
            @Override
            public void run() {
                while(gameRunning) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    for (RotoTwo i : images) {

                        i.mover();

                    }

                }
            }
        });
        hilomovedor.start();





    }

    @Override
    protected void onPause() {
        super.onPause();
        gameRunning=false;
    }




    @Override
    public boolean onTouch(View v, MotionEvent event) {


//        v.bringToFront();
//        ((View)v.getParent()).invalidate();
        //Code for moving the image

           /* if(event.getAction() == MotionEvent.ACTION_MOVE){

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



            }*/



            if(event.getAction() == MotionEvent.ACTION_UP) {
                this.scoreInt++;
                ((ImageView) v).setImageResource(0);
                images.remove(images.indexOf((RotoTwo)v));
                String text = this.score.getText().toString().split(" ")[0];
                text += " " + scoreInt;
                this.score.setText(text);
            }


            return true;


    }
}


