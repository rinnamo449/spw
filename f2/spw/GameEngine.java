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

	int stop = 1;
	int attack = 0;
	int lifeboss = 40;
	GamePanel gp;
		
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();	
	private ArrayList<Enemy2> enemies2 = new ArrayList<Enemy2>();
	private ArrayList<Enemy3> enemies3 = new ArrayList<Enemy3>();
	private ArrayList<Gun> gun = new ArrayList<Gun>();
	private ArrayList<Gun> gun2 = new ArrayList<Gun>();
	private ArrayList<Gunboss> gunboss = new ArrayList<Gunboss>();
	private SpaceShip v;	
	
	private Timer timer;
	
	private long score = 0;
	private double difficulty = 0.1;
	public int dead = 9;
	
	
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
		
		if(score > 2000 && stop >0){
		Enemy3 e3 = new Enemy3(0, 100);
		gp.sprites.add(e3);
		enemies3.add(e3);
		stop--; }
		
		if(attack == 1 ){
			Gun g = new Gun(v.getX()+5,v.getY());
			gp.sprites.add(g);
			gun.add(g);
			}
			
		if(attack == 2 ){
			Gun g2 = new Gun(v.getX()-15,v.getY());
			gp.sprites.add(g2);
			gun.add(g2);
			
			Gun g3 = new Gun(v.getX()+25,v.getY());
			gp.sprites.add(g3);
			gun.add(g3);
			}
		//want gun shoot by boss
		if( score > 2000 ){
		for(int i=0; i<200 ;i++){
		Gunboss gunb = new Gunboss(v.getX(),100);
			gp.sprites.add(gunb);
			gunboss.add(gunb); }}
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
		
				
		Iterator<Enemy3> e_iter3 = enemies3.iterator();
		while(e_iter3.hasNext()){
			Enemy3 e3 = e_iter3.next();
			e3.proceed();
			
		if(!e3.isAlive()){
				e_iter3.remove();
				gp.sprites.remove(e3);
				score += 500;
			}
		}
		
		Iterator<Gun> e_iter_gun = gun.iterator();
		while(e_iter_gun.hasNext()){
			Gun g = e_iter_gun.next();
			g.proceed();
			
		if(!g.isAlive()){
				e_iter_gun.remove();
				gp.sprites.remove(g);
				score += 100;
			}
		}
		
		
		Iterator<Gun> e_iter_gun2 = gun.iterator();
		while(e_iter_gun2.hasNext()){
			Gun g2 = e_iter_gun2.next();
			g2.proceed();
			
		if(!g2.isAlive()){
				e_iter_gun2.remove();
				gp.sprites.remove(g2);
				score += 100;
			}
		}
		
		Iterator<Gunboss> e_iter_gunboss = gunboss.iterator();
		while(e_iter_gunboss.hasNext()){
			Gunboss gunb = e_iter_gunboss.next();
			gunb.proceed();
			
		if(!gunb.isAlive()){
				e_iter_gunboss.remove();
				gp.sprites.remove(gunb);
				score += 100;
			}
		}
		
		
		
		
		gp.updateGameUI(this);
		
		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double er;
		Rectangle2D.Double er2;
		Rectangle2D.Double er3;
		Rectangle2D.Double gr1;
		Rectangle2D.Double gr2;
		Rectangle2D.Double gbs;
		
		for(Enemy e : enemies){
			er = e.getRectangle();
			
				for(Gun g : gun){
				gr1 = g.getRectangle();
				if(gr1.intersects(er)){
					score += 100;
					gp.sprites.remove(e);
					return;
				}
			}
			
			if(er.intersects(vr)){
			    gp.sprites.remove(e);
				dead--;
				return;
			}
				
		if(dead <= 0){
		  die();
		 }
		}
		
		for(Enemy2 e2 : enemies2){
			er2 = e2.getRectangle();
			if(er2.intersects(vr) && dead <=19 ){
				gp.sprites.remove(e2);
				dead += 1;
				score += 100;
				return;
			}
		}
		
		for(Enemy3 e3 : enemies3){
			er3 = e3.getRectangle();
			
			for(Gun g : gun){
				gr1 = g.getRectangle();
				if(gr1.intersects(er3)){
					score += 100;
					if(lifeboss > 0)
					lifeboss--;
					
					if(lifeboss <= 0)
					gp.sprites.remove(e3);
					
					return;
				}
			}
			
			if(er3.intersects(vr)){
				dead -= 2;
				return;
			}
		}
	
	
		for(Gun g : gun){
			gr1 = g.getRectangle();
			if(gr1.intersects(vr)){
				score += 100;
				gp.sprites.remove(g);
				return;
			}
		}
		
		for(Gun g2 : gun2){
			gr2 = g2.getRectangle();
			if(gr2.intersects(vr)){
				score += 100;
				gp.sprites.remove(g2);
				return;
			}
		}
		
		for(Gunboss gunb : gunboss){
			gbs = gunb.getRectangle();
			
			for(Gun g : gun){
				gr1 = g.getRectangle();
				
				if(gr1.intersects(gbs)){
					dead -= 1;
					gp.sprites.remove(gunb);
					return;
				}
			}
			
			if(gbs.intersects(vr)){
			    gp.sprites.remove(gunb);
				dead--;
				return;
			}
		}
	
		
	}
	
	public int getDead(){
	  return dead;	
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
			 
        case KeyEvent.VK_P:
			timer.stop();
			break;
		case KeyEvent.VK_S:
			timer.start();
			break;		 

		case KeyEvent.VK_Q:
			 {attack = 1;}
			 break;
		case KeyEvent.VK_W:
			 {attack = 2;}
			 break;
		
		}
	}

	public long getScore(){
        return score;	}
	
	public int getLifeboss(){
	    return lifeboss; }
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
