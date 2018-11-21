/**
 * Created by Zhenqi in 2016
 * 
 */
package Pacman;

public class Fantome extends Personnage {
	private String name;
//	private boolean isVunerable = false;

	public Fantome(int x, int y, String name ) {
		this.x= x;
		this.y =y;
		this.name = name;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	/*
	public boolean getIsVunerable(){
		return isVunerable;
	}
	
	public void setIsVunerable(boolean isVunerable){
		this.isVunerable = isVunerable;
	}
	*/
	
	public void turnAround(){
		if((x==13||x==14)&&y==11){
			if(state !=1 || state !=3)
				state = 1;
			else if(state == 1)
				state = 1;
			else if (state == 3)
				state = 3;
		}
		else if((x==11||x==12) && (y==12||y==13||y==14)) // quand les fantômes sont dans le prison
			state = 3;
		else if((x==13||x==14) && (y==12||y==13||y==14)) // quand les fantômes sont dans le prison
			state = 2 ;
		else if((x==15||x==16) && (y==12||y==13||y==14)) // quand les fantômes sont dans le prison
			state = 1 ;
		else if(x==0 && state==1)
			state=1;
		else if(x==27 && state==3)
			state=3;
		else {
			switch (state){
			case 1:
				if(Labyrinthe.walls[y][x-1]==0 && Labyrinthe.walls[y-1][x]!=0 && Labyrinthe.walls[y+1][x]==0)
					state = 2;
				else if(Labyrinthe.walls[y][x-1]==0 && Labyrinthe.walls[y-1][x]==0 && Labyrinthe.walls[y+1][x]!=0)
					state = 4;
				else if(Labyrinthe.walls[y][x-1]!=0 && Labyrinthe.walls[y-1][x]==0 && Labyrinthe.walls[y+1][x]!=0){
					if(Math.random()<0.5)
						state = 1;
					else
						state = 4;
						
				}else if(Labyrinthe.walls[y][x-1]==0 && Labyrinthe.walls[y-1][x]!=0 && Labyrinthe.walls[y+1][x]!=0){
					if(Math.random()<0.5)
						state = 2;
					else
						state = 4;
				}else if(Labyrinthe.walls[y][x-1]!=0 && Labyrinthe.walls[y-1][x]!=0 && Labyrinthe.walls[y+1][x]==0){
					if(Math.random()<0.5)
						state = 1;
					else
						state = 2;
				}else if(Labyrinthe.walls[y][x-1]!=0 && Labyrinthe.walls[y-1][x]!=0 && Labyrinthe.walls[y+1][x]!=0){
					if(Math.random()<0.33)
						state = 1;
					else if (Math.random() > 0.66)
						state = 4;
					else
						state = 2;
				}
				break;
			case 2:
				if(Labyrinthe.walls[y][x-1]!=0 && Labyrinthe.walls[y][x+1]==0 && Labyrinthe.walls[y-1][x]==0)
					state = 1;
				else if(Labyrinthe.walls[y][x-1]==0 && Labyrinthe.walls[y][x+1]!=0 && Labyrinthe.walls[y-1][x]==0)
					state = 3;
				else if(Labyrinthe.walls[y][x-1]!=0 && Labyrinthe.walls[y][x+1]!=0 && Labyrinthe.walls[y-1][x]==0){
					if(Math.random()<0.5)
						state = 1;
					else
						state = 3;
				}else if(Labyrinthe.walls[y][x-1]!=0 && Labyrinthe.walls[y][x+1]==0 && Labyrinthe.walls[y-1][x]!=0){
					if(Math.random()<0.5)
						state = 2;
					else
						state = 1;
				}else if(Labyrinthe.walls[y][x-1]==0 && Labyrinthe.walls[y][x+1]!=0 && Labyrinthe.walls[y-1][x]!=0){
					if(Math.random()<0.5)
						state = 2;
					else
						state = 3;
				}
				else if(Labyrinthe.walls[y][x-1]==0 && Labyrinthe.walls[y][x+1]!=0 && Labyrinthe.walls[y-1][x]!=0){
					if(Math.random()<0.33)
						state = 1;
					else if (Math.random() > 0.66)
						state = 3;
					else
						state = 2;
				}
				break;
			
			case 3:
				if(Labyrinthe.walls[y][x+1]==0 && Labyrinthe.walls[y-1][x]!=0 && Labyrinthe.walls[y+1][x]==0)
					state = 2;
				else if(Labyrinthe.walls[y][x+1]==0 && Labyrinthe.walls[y-1][x]==0 && Labyrinthe.walls[y+1][x]!=0)
					state = 4;
				else if(Labyrinthe.walls[y][x+1]!=0 && Labyrinthe.walls[y-1][x]==0 && Labyrinthe.walls[y+1][x]!=0){
					if(Math.random()<0.5)
						state = 3;
					else
						state = 4;
				}else if(Labyrinthe.walls[y][x+1]==0 && Labyrinthe.walls[y-1][x]!=0 && Labyrinthe.walls[y+1][x]!=0){
					if(Math.random()<0.5)
						state = 2;
					else
						state = 4;
				}else if(Labyrinthe.walls[y][x+1]!=0 && Labyrinthe.walls[y-1][x]!=0 && Labyrinthe.walls[y+1][x]==0){
					if(Math.random()<0.5)
						state = 3;
					else
						state = 2;
				}else if(Labyrinthe.walls[y][x+1]!=0 && Labyrinthe.walls[y-1][x]!=0 && Labyrinthe.walls[y+1][x]!=0){
					if(Math.random()<0.33)
						state = 3;
					else if (Math.random() > 0.66)
						state = 4;
					else
						state = 2;
				}
				break;	
				
			case 4:
				if(Labyrinthe.walls[y][x-1]!=0 && Labyrinthe.walls[y][x+1]==0 && Labyrinthe.walls[y+1][x]==0)
					state = 1;
				else if(Labyrinthe.walls[y][x-1]==0 && Labyrinthe.walls[y][x+1]!=0 && Labyrinthe.walls[y+1][x]==0)
					state = 3;
				else if(Labyrinthe.walls[y][x-1]!=0 && Labyrinthe.walls[y][x+1]!=0 && Labyrinthe.walls[y+1][x]==0){
					if(Math.random()<0.5)
						state = 1;
					else
						state = 3;
				}else if(Labyrinthe.walls[y][x-1]!=0 && Labyrinthe.walls[y][x+1]==0 && Labyrinthe.walls[y+1][x]!=0){
					if(Math.random()<0.5)
						state = 4;
					else
						state = 1;
				}else if(Labyrinthe.walls[y][x-1]==0 && Labyrinthe.walls[y][x+1]!=0 && Labyrinthe.walls[y+1][x]!=0){
					if(Math.random()<0.5)
						state = 4;
					else
						state = 3;
				}
				else if(Labyrinthe.walls[y][x-1]==0 && Labyrinthe.walls[y][x+1]!=0 && Labyrinthe.walls[y+1][x]!=0){
					if(Math.random()<0.33)
						state = 1;
					else if (Math.random() > 0.66)
						state = 3;
					else
						state = 4;
				}
				break;
			default: break;
			}	
		}
	}
}
