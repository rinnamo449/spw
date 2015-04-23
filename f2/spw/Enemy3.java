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
	
	if( bossmove > 0 && bossmove <= 115){
		x = x + step;
		bossmove++;
	}
	else if(bossmove > 115 && bossmove <= 230){
	    x = x - step;
		bossmove++;
		if( bossmove == 230 )
		bossmove = 1;
	}
	
	/*public int getX_e3(){
		return x;
		}
	
	public int getY_e3(){
		return y;
		}*/
	
	
		
		if(y > Y_TO_DIE){
			alive = false;
		}
	}
	
	public boolean isAlive(){
		return alive;
	}
}