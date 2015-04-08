package nozacutanymore.com.juegobrozauno;

import android.content.Context;
import android.widget.ImageView;

import java.util.Random;


public class RotoTwo extends ImageView{
    private int dx;
    private int dy;
    private Random r = new Random();

    public RotoTwo(Context context) {
        super(context);
        this.setImageResource(R.drawable.rotodoscon);

        this.dx = 0;
        this.dy = r.nextInt(25)+5;
        this.setX(r.nextFloat()*1000);
        this.setY(0f);

    }

    public void mover(){
        this.setY(this.getY()+dy);
    }
}
