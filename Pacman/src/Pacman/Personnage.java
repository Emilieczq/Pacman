/**
 * Created by Zhenqi in 2016
 * 
 */
package Pacman;

public abstract class Personnage {
	protected int x;
	protected int y;
	protected int state = 1;
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getState(){
		return state;
	}
	public void setState(int state){
		this.state = state;
	}
}


