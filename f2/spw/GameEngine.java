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
	int i = 0,j = 0,k = 0;
	int lifeboss;
	int ultimate = 1;
	int quick = 1;
	int skillE = 0;
	int skillR = 0;
	int stopgun = 1;
	GamePanel gp;
		
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();	
	private ArrayList<Enemy2> enemies2 = new ArrayList<Enemy2>();
	private ArrayList<Enemy3> enemies3 = new ArrayList<Enemy3>();
	private ArrayList<Gun> gun = new ArrayList<Gun>();
	private ArrayList<Gunstun> gunstun = new ArrayList<Gunstun>();
	private ArrayList<Gunboss> gunboss = new ArrayList<Gunboss>();
	private ArrayList<Item> item = new ArrayList<Item>();
	private ArrayList<Item2> item2 = new ArrayList<Item2>();
	private SpaceShip v;	
	
	private Timer timer;
	
	private long score = 0;
	private double difficulty = 0.05;
	public int dead = 9;
	public Enemy3 e3;
	public Item i1;
	public Item2 i2;
	
	
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
		if(score > 2000 && score % 2000 == 0 && stop == 1){
			e3 = new Enemy3(0, 100);
			gp.sprites.add(e3);
			enemies3.add(e3);
			stop = 0; 
			lifeboss = e3.getLife();
		}
		
	}
	private void generateItem(){
			{	if((int)(Math.random()*100) < 1){
				i1 = new Item((int)(Math.random()*400),100);
				gp.sprites.add(i1);
				item.add(i1);
				quick = 0; 
				}		
				quick = 1;	}
			
			{	if((int)(Math.random()*100) < 1){
				i2 = new Item2((int)(Math.random()*400),100);
				gp.sprites.add(i2);
				item2.add(i2);
				ultimate = 0; 
				}		
				ultimate = 1;	}
				
			
	}
	
	
	private void generateWeapon(){
	
		if(attack == 1 ){
			if(j%10 == 0){
				Gun g = new Gun(v.getX()+5,v.getY());
				gp.sprites.add(g);
				gun.add(g);
			}
			j++;
		}
			
		if(attack == 2 ){
			if(j%10 == 0){
			Gun g2 = new Gun(v.getX()-15,v.getY());
			gp.sprites.add(g2);
			gun.add(g2);
			
			Gun g3 = new Gun(v.getX()+25,v.getY());
			gp.sprites.add(g3);
			gun.add(g3);
			}
			j++;
		}
		
		if(attack == 3 && skillE > 0){
			if(j%2 == 0){
			Gun g = new Gun(v.getX()+5,v.getY());
			gp.sprites.add(g);
			gun.add(g);
			
			Gun g2 = new Gun(v.getX()-15,v.getY());
			gp.sprites.add(g2);
			gun.add(g2);
			
			Gun g3 = new Gun(v.getX()+25,v.getY());
			gp.sprites.add(g3);
			gun.add(g3);
			
			skillE--;
			}
			j++;
		}
		
		if(attack == 4 && skillR > 0){
			if(k%10 == 0){
				Gunstun gs = new Gunstun(v.getX()+5,v.getY());
			    gp.sprites.add(gs);
			    gunstun.add(gs);
				
				skillR--;
			}
			k++;
		}
	
	
	}
	
	
	private void process(){
		if(Math.random() < difficulty){
			generateEnemy();
		}
		
		generateWeapon();
		generateItem();
		
		Iterator<Enemy> e_iter = enemies.iterator();
		while(e_iter.hasNext()){
			Enemy e = e_iter.next();
			e.proceed();
			
			if(!e.isAlive()){
				e.die();
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
				e3.die();
				e_iter3.remove();
				gp.sprites.remove(e3);
				score += 500;
				stop = 1;
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
				gunb.die();
				e_iter_gunboss.remove();
				gp.sprites.remove(gunb);
				score += 100;
			}
		}
		
		
		//want gun shoot by boss
		if(e3 != null){
			if(e3.isAlive()){
				if(i%20 == 0 && stopgun != 0){
					Gunboss gunb = new Gunboss(e3.getXboss()+15,137);
					gp.sprites.add(gunb);
					gunboss.add(gunb); 
				}
				i++;
			}
			else{
				i = 0;
			}
		}
		
		Iterator<Item> e_iter_item = item.iterator();
		while(e_iter_item.hasNext()){
			Item i1 = e_iter_item.next();
			i1.proceed();
			
			if(!i1.isAlive()){
				e_iter_item.remove();
				gp.sprites.remove(i1);
				score += 300;
			}
		}
		
		Iterator<Item2> e_iter_item2 = item2.iterator();
		while(e_iter_item2.hasNext()){
			Item2 i2 = e_iter_item2.next();
			i2.proceed();
			
			if(!i2.isAlive()){
				e_iter_item2.remove();
				gp.sprites.remove(i2);
				score += 300;
			}
		}
		
		Iterator<Gunstun> e_iter_stun = gunstun.iterator();
		while(e_iter_stun.hasNext()){
			Gunstun gs = e_iter_stun.next();
			gs.proceed();
			
			if(!gs.isAlive()){
				e_iter_stun.remove();
				gp.sprites.remove(gs);
				score += 300;
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
		Rectangle2D.Double itm;
		Rectangle2D.Double itm2;
		Rectangle2D.Double gstr;
		
		for(Enemy e : enemies){
			er = e.getRectangle();
			
				for(Gun g : gun){
				gr1 = g.getRectangle();
				if(gr1.intersects(er)){
					e.die();
					g.die();
					score += 100;
					gp.sprites.remove(e);
					gp.sprites.remove(g);
					return;
					}
				}
			
				for(Gunstun gs : gunstun){
				gstr = gs.getRectangle();
				if(gstr.intersects(er)){
					e.die();
					gs.die();
					score += 100;
					gp.sprites.remove(e);
					gp.sprites.remove(gs);
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
					//e3.die();
					g.die();
					gp.sprites.remove(g);
					score += 100;
					if(e3.isAlive()){
						e3.loseLife();
					}
					else{
						gp.sprites.remove(e3);
					}
					lifeboss = e3.getLife();
					return;
				}
			}
			
			for(Gunstun gs : gunstun){
				gstr = gs.getRectangle();

				if(gstr.intersects(er3)){
					//e3.die();
					gs.die();
					e3.stunt();
					stopgun = 0;
					gp.sprites.remove(gs);
					score += 100;
					if(e3.isAlive()){
						e3.loseLife();
					}
					else{
						gp.sprites.remove(e3);
					}
					lifeboss = e3.getLife();
					return;
				}
			}
			
			if(er3.intersects(vr)){
				dead -= 2;
				return;
			}
		}
		
		for(Gunboss gunb : gunboss){
			gbs = gunb.getRectangle();
			
			for(Gun g : gun){
				gr1 = g.getRectangle();
				
				if(gr1.intersects(gbs)){
				    g.die();
					gunb.die();
					dead -= 1;
					gp.sprites.remove(g);
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
		
		for(Item i1 : item){
			itm = i1.getRectangle();
			if(itm.intersects(vr) && skillE < 30){
				gp.sprites.remove(i1);
				skillE += 1;
				score += 100;
				return;
			}
		}
		
		for(Item2 i2 : item2){
			itm2 = i2.getRectangle();
			if(itm2.intersects(vr) && skillR < 1){
				gp.sprites.remove(i2);
				skillR += 1;
				score += 100;
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
	
	public int getSkillE(){
		return skillE;
	}
	
	public int getSkillR(){
		return skillR;
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
	    case KeyEvent.VK_E:
			 {attack = 3;}
			 break;
		case KeyEvent.VK_R:
			 {attack = 4;}
			 break;
		
		}
	}

	public long getScore(){
        return score;	}
	public int getStop(){
        return stop;	
	}
	public int getStopgun(){
        return stopgun;	
	}
	
	
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
