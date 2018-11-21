/**
 * Created by Zhenqi in 2016
 * 
 */
package Pacman;

import java.awt.event.KeyEvent;

import edu.princeton.cs.introcs.StdDraw;

public class Joueur extends Personnage {
	private int score;
	private int lives = 3;
	public int state_temp;
	
	public Joueur(int x, int y, int state) {
		super();
		this.x=x;
		this.y=y;
		this.state = state;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score){
		this.score = score;
	}
	
	public int getLives(){
		return lives;
	}
	public void setLives(int lives){
		this.lives = lives;
	}
	
	
	public void turnAround(){
		if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN)) {
			state_temp = 4;
        }else if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) {
        	state_temp = 1;
        }else if (StdDraw.isKeyPressed(KeyEvent.VK_UP)) {
        	state_temp = 2;
		}
		else if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) {
			state_temp = 3;
		}
		
		switch(state_temp){
		case 1:
			if(x!=0){
				if(Labyrinthe.walls[y][x-1]!=0)
	        		state = 1;
			}else
					state = 1; // x = 0 est la situation spéciale : il peut se déplacer de x = 0 à 27 directement
			break;
		case 2:
			if(Labyrinthe.walls[y-1][x]!=0){
        		state = 2;
			}
			break;
		case 3:
			if (x!=27){
        		if(Labyrinthe.walls[y][x+1]!=0)
	        		state = 3;
			}else
				state = 3; // x = 27 est la situation spéciale : il peut se déplacer de x = 27 à 0 directement
			break;
		case 4:
			if(Labyrinthe.walls[y+1][x]!=0 && Labyrinthe.walls[y+1][x]!=3)
				state = 4;
			break;
		}
	}
}
