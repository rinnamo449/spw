package f2.spw;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

public class Enemy3 extends Sprite{
    int bossmove=1;
	public static final int Y_TO_FADE = 400;
	public static final int Y_TO_DIE = 600;
	
	private int step = 3;
	private boolean alive = true;
	
	public Enemy3(int x, int y) {
		super(x, y, 40, 40);
		
	}

	@Override
	public void draw(Graphics2D g) {
		if(y < Y_TO_FADE)
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		else{
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 
					(float)(Y_TO_DIE - y)/(Y_TO_DIE - Y_TO_FADE)));
		}
		g.setColor(Color.blue);
		g.fillRect(x, y, width, height);
		
	}

	public void proceed(){
	
	if( bossmove > 0 && bossmove <= 100){
		x = x + step;
		bossmove++;
	}
	else if(bossmove > 100 && bossmove <= 200){
	    x = x - step;
		bossmove++;
		if( bossmove == 200 )
		bossmove = 1;
	}
		if(y > Y_TO_DIE){
			alive = false;
		}
	}
	
	public boolean isAlive(){
		return alive;
	}
	
	public int getXboss(){
		return x;
		}
	
	public int getYboss(){
		return y;
		}
}