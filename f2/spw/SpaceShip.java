package f2.spw;

import java.awt.Color;
import java.awt.Graphics2D;

public class SpaceShip extends Sprite{

	int step = 12;
	
	public SpaceShip(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(x, y, width, height);
		g.fillRect(x,y,20,40);
		g.fillRect(x-20,y+5,30,10);
		g.fillRect(x+10,y+5,30,10);
		g.fillRect(x-10,y+30,40,10);
		g.fillRect(x-15,y,5,23);
		g.fillRect(x+30,y,5,23);
		g.fillRect(x+2,y-3,15,5);
		g.fillRect(x+5,y-6,10,5);
		
		
	}

	public void moveX(int direction){
		x += (step * direction);
		if(x < 0)
			x = 0;
		if(x > 400 - width)
			x = 400 - width;
           }
     
          
     public void moveY(int direction){
          y += (step * direction);
		if(y < 0)
			y = 0;
		if(y > 600 - height)
                y = 600 - height;
 
	      }

    }

