package f2.spw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Timer;


public class GameEngine implements KeyListener, GameReporter{
	GamePanel gp;
		
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();	
	private ArrayList<Enemy2> enemies2 = new ArrayList<Enemy2>();
	private SpaceShip v;	
	
	private Timer timer;
	
	private long score = 0;
	private double difficulty = 0.1;
	private int dead = 9;
	
	
	public GameEngine(GamePanel gp, SpaceShip v) {
		this.gp = gp;
		this.v = v;		
		
		gp.sprites.add(v);
		
		timer = new Timer(50, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				process();
			}
		});
		

		
		timer.setRepeats(true);
		
	}
	
	public void start(){
		timer.start();
	}
	
	private void generateEnemy(){
		Enemy e = new Enemy((int)(Math.random()*400), 100);
		gp.sprites.add(e);
		enemies.add(e);
		
		Enemy2 e2 = new Enemy2((int)(Math.random()*400), 100);
		gp.sprites.add(e2);
		enemies2.add(e2);
		}
	
	
	private void process(){
		if(Math.random() < difficulty){
			generateEnemy();
		}
		
		Iterator<Enemy> e_iter = enemies.iterator();
		while(e_iter.hasNext()){
			Enemy e = e_iter.next();
			e.proceed();
			
			if(!e.isAlive()){
				e_iter.remove();
				gp.sprites.remove(e);
				score += 200;
			}
			
		Iterator<Enemy2> e_iter2 = enemies2.iterator();
		while(e_iter2.hasNext()){
			Enemy2 e2 = e_iter2.next();
			e2.proceed();
			
		if(!e2.isAlive()){
				e_iter2.remove();
				gp.sprites.remove(e2);
				score += 300;
			}
		}
		}
		
		gp.updateGameUI(this);
		
		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double er;
		for(Enemy e : enemies){
			er = e.getRectangle();
			if(er.intersects(vr)){
				dead--;
				return;
			}
		if(dead == 0){
		  die();
		 }
		}
		
		for(Enemy2 e2 : enemies2){
			er = e2.getRectangle();
			if(er.intersects(vr)){
				//die();
				return;
			}
		}
		
	}
	
	public void die(){
		timer.stop();
	}
	
	void controlVehicle(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_NUMPAD4:
			v.moveX(-1);
			break;
		case KeyEvent.VK_NUMPAD6:
			v.moveX(1);
			break;
        case KeyEvent.VK_NUMPAD2:
            v.moveY(1);
            break;
        case KeyEvent.VK_NUMPAD8:
            v.moveY(-1);
            break;
			
		case KeyEvent.VK_NUMPAD7:
		    {v.moveY(-1);
			 v.moveX(-1);}
			 break;
		case KeyEvent.VK_NUMPAD9:
		    {v.moveY(-1);
			 v.moveX(1);}
			 break;
		case KeyEvent.VK_NUMPAD1:
		    {v.moveY(1);
			 v.moveX(-1);}
			 break;	 
        case KeyEvent.VK_NUMPAD3:
		    {v.moveY(1);
			 v.moveX(1);}
			 break;
			 
        case KeyEvent.VK_D:
			difficulty += 0.1;
			break;
   
		}
	}

	public long getScore(){
        return score;	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		controlVehicle(e);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//do nothing
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//do nothing		
	}
}
