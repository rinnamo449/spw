package f2.spw;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

public class Hp extends Sprite{
	
	public Hp(int x, int y) {
		super(x, y, 20, 30);
		
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.blue);
		g.fillRect(x, y, width, height);
		
	}
}