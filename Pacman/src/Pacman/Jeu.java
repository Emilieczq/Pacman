/**
 * Created by Zhenqi in 2016
 * 
 */

package Pacman;

import java.awt.event.KeyEvent;

import edu.princeton.cs.introcs.StdDraw;

public class Jeu{
	private static Joueur J = new Joueur(14,23,1);
	private static Fantome[] F = {new Fantome(13,11,"Blinky"),new Fantome(13,14,"Pinky"),new Fantome(11,14, "Inky"),new Fantome(15,14, "Clyde")};
	private static int[][] walls = new int [31][28];
	private static int numFromages = 244;
	public static boolean isStart = true;
	
	
    private final static double SPACE = 20.0;
    private final static double SPACE_BODER = 20.0;
	private final static double SPACE_SCORE = 40.0;
	
	private final static double WIN_WIDTH = SPACE_BODER*2+walls[0].length*SPACE;
    private final static double WIN_HEIGHT = SPACE_SCORE + walls.length*SPACE + SPACE_BODER*2;
	
	
	
    Jeu(){
    	J = new Joueur(14,23,1);
    	F = new Fantome[] {new Fantome(13,11,"Blinky"),new Fantome(13,14,"Pinky"),new Fantome(11,14, "Inky"),new Fantome(15,14, "Clyde")};   
    
    }
	
    public static void drawWalls(){
    	StdDraw.picture(SPACE_BODER+SPACE*14,SPACE_SCORE+SPACE*16, "images/maze.jpg",SPACE*29,SPACE*32);
    }
    
    
	public static void drawFromage(){
		for (int i=0; i<walls.length; i++){
        	for(int j=0; j<walls[0].length;j++){
        		if(walls[i][j]==1){
        			if(i==J.getY() && j==J.getX()){ 
        				walls[i][j]=2; // Si les positions du joueur et d'un petit fromage sont même, 
        				               // l'état de cette position est changé à 2 (= vide),
        				numFromages -=1; // et le nombre des fromages restes est moins 1
        				
        				J.setScore(J.getScore()+10); // Si le joueur mange un petit fromage, il gagne 10 points.
        			}else{
	        			StdDraw.setPenColor(StdDraw.WHITE);
	        			StdDraw.filledCircle(j*SPACE+SPACE/2+SPACE_BODER, SPACE_SCORE+(walls.length-i)*SPACE, 2);
        			}      		
        		}else if(walls[i][j]==4){
        			if(i==J.getY() && j==J.getX()){
        				walls[i][j]=2; // même principe avec le petit fromage
        				numFromages -=1;
        				J.setScore(J.getScore()+50); // Si le joueur mange un grand fromage, il gagne 50 points.
        				
        			}else{
	        			StdDraw.setPenColor(StdDraw.WHITE);
	        			StdDraw.filledCircle(j*SPACE+SPACE/2+SPACE_BODER, SPACE_SCORE+(walls.length-i)*SPACE, 7.5);
        			}
        		}
        	}
		}
	}
	
	public static void drawJoueur(){
		int x = J.getX();
		int y = J.getY();
		int degre = 0; // Ce paramètre permet de choisir le degré de rotation de l'image.
		switch(J.getState()){ // Le joueur a quatre états : 1 = face à gauche, 2 = face en haut, 3 = face à droite, 4 = face en bas
		case 1: degre = 180; break; 
		case 2: degre = 90; break;
		case 3: degre = 0; break; // L'état initial de l'image est à gauche.
		case 4: degre = 270; break;
		
		}
		StdDraw.picture(x*SPACE+SPACE/2+SPACE_BODER, 
				SPACE_SCORE+SPACE_SCORE+(walls.length-y-1)*SPACE-SPACE/2-10, 
				"images/joueur.png",  SPACE*1.5, SPACE*1.5, degre);
	}
	
	public static void drawFantome(Fantome F){
		int x = F.getX();
		int y = F.getY();
		String image;
		if (F.getName()=="Blinky"){
			image = "images/blinky.png";
		}else if(F.getName()=="Pinky"){
			image = "images/pinky.png";
		}else if(F.getName()=="Inky"){
			image = "images/inky.png";
		}else{
			image = "images/clyde.png";
		}
		StdDraw.picture(x*SPACE+SPACE/2+SPACE_BODER, 
				SPACE_SCORE+SPACE_SCORE+(walls.length-y-1)*SPACE-SPACE/2-10, 
				image, SPACE*1.5, SPACE*1.5, 0);
	}
	
	public static void drawScore(){
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.text(80, 20, "Score " );
		StdDraw.setPenColor(StdDraw.YELLOW);
		StdDraw.text(140, 20, ""+J.getScore());
	}
	
	public static void drawLives(){
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.text(300, 20, "Lives " );
		if (J.getLives()>0){ // Si le joueur a au moin une vie, on dessine une vie.
			StdDraw.picture(350, 20,"images/joueur.png", 30, 30, 180);
			if(J.getLives()>1){ // S'il a au moin deux vies, on dessine une deuxième vie.
				StdDraw.picture(390, 20,"images/joueur.png", 30, 30, 180);
				if(J.getLives()>2) // S'il a trois vies, on dessine une dernière vie.
					StdDraw.picture(430, 20,"images/joueur.png", 30, 30, 180);
			}
		}
	}
	
	public static void move(Personnage personnage){
		switch (personnage.getState()){ // 4 états
		case 1 : 
			if(personnage.getX()==0)
				personnage.setX(27); // Dans ce cas, le personnage peut traverser de gauche à droit
			else if(Labyrinthe.walls[personnage.getY()][personnage.getX()-1] != 0){
				personnage.setX(personnage.getX()-1); 
			}
			break;
		case 2 : 
			if(Labyrinthe.walls[personnage.getY()-1][personnage.getX()] != 0){
				personnage.setY(personnage.getY()-1); 
			}
			break;
		case 3 : 
			if(personnage.getX()==27)
				personnage.setX(0); // Dans ce cas, le personnage peut traverser de droit à gauche
			else if(Labyrinthe.walls[personnage.getY()][personnage.getX()+1] != 0){
				personnage.setX(personnage.getX()+1); 
			}
			break;
		case 4 : 
			if(Labyrinthe.walls[personnage.getY()+1][personnage.getX()] != 0){
				personnage.setY(personnage.getY()+1); 
			}
			break;
		}
	}
	
	
	public static void jeu(){
		StdDraw.clear(StdDraw.BLACK);
		drawWalls();
		drawFromage();
		drawScore();
		drawLives();
		if(isStart){
			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.text(WIN_WIDTH/2, WIN_HEIGHT/2, "GET READY!");
			isStart = false;
		}
		
		J.turnAround();
		move(J);
		drawJoueur();
		
		/*
		 * Quand le joueur est mangé par un fantôme
		 */
		boolean isEaten = false;
		for (int i = 0; i< F.length;i++){
			if(F[i].getX()==J.getX() && F[i].getY()==J.getY()){
				isEaten = true;
				break;
			}
		}
		if(isEaten){
			J.setLives(J.getLives()-1);
			
			isStart = true;
			/*
			 * initialiser les positions des fantômes et du joueur
			 */
			J.setX(14);
			J.setY(23);
			J.setState(1);
			F[0].setX(13);
			F[0].setY(12);
			F[1].setX(14);
			F[1].setY(15);
			F[2].setX(12);
			F[2].setY(15);
			F[3].setX(16);
			F[3].setY(15);
		}
		for(int h=0; h<F.length; h++){
			F[h].turnAround();
			move(F[h]);
			drawFantome(F[h]);
		}
		isEaten = false;
		for (int j = 0; j< F.length;j++){
			if(F[j].getX()==J.getX() && F[j].getY()==J.getY()){
				isEaten = true;
				break;
			}
		}
		if(isEaten){
			J.setLives(J.getLives()-1);
			isStart = true;		
			
			J.setX(14);
			J.setY(23);
			J.setState(1);
			
			F[0].setX(13);
			F[0].setY(12);
			F[1].setX(14);
			F[1].setY(15);
			F[2].setX(12);
			F[2].setY(15);
			F[3].setX(16);
			F[3].setY(15);			
			J.state_temp = 1;
		}
		
		StdDraw.show(120); // Les personnages s'avancent chaque 120 ms.
		
		if(numFromages==0){ // s'il n'y a plus de fromage, le joueur gagne.
			do{
				StdDraw.clear(StdDraw.BLACK);
				StdDraw.setPenColor(StdDraw.WHITE);
				StdDraw.picture(WIN_WIDTH/2,  WIN_HEIGHT/2+80, "images/youWin.jpeg");
				StdDraw.text(WIN_WIDTH/2, WIN_HEIGHT/2-20, "Your score : " + J.getScore());
				StdDraw.text(WIN_WIDTH/2, WIN_HEIGHT/2-60, "Press SPACE to try again !" );
				StdDraw.show(20);
			}while(!StdDraw.isKeyPressed(KeyEvent.VK_SPACE));
			J.state_temp = 1;
			numFromages = 244;
			for (int a=0; a<Labyrinthe.walls[0].length;a++){
				for (int b=0; b<Labyrinthe.walls.length; b++){
					walls[b][a] = Labyrinthe.walls[b][a];
				}
			}
			new Jeu();
		}
		
		if(J.getLives()==0){ // si le joueur n'a plus de vie, il mort.
			do{
				StdDraw.clear(StdDraw.BLACK);
				StdDraw.setPenColor(StdDraw.WHITE);
				StdDraw.picture(WIN_WIDTH/2,  WIN_HEIGHT/2+80, "images/gameOver.jpeg");
				StdDraw.text(WIN_WIDTH/2, WIN_HEIGHT/2-50, "Your score : " + J.getScore());
				StdDraw.text(WIN_WIDTH/2, WIN_HEIGHT/2-90, "Press SPACE to try again !" );
				StdDraw.show(20);
			}while(!StdDraw.isKeyPressed(KeyEvent.VK_SPACE));
			J.state_temp = 1;
			numFromages = 244;
			for (int a=0; a<Labyrinthe.walls[0].length;a++){
				for (int b=0; b<Labyrinthe.walls.length; b++){
					walls[b][a] = Labyrinthe.walls[b][a];
				}
			}
			new Jeu();
		}
		
	}
	/*
	 * main function
	 */
	public static void main(String [] args) {
		StdDraw.setXscale(-50, WIN_WIDTH+50);
		StdDraw.setYscale(0, WIN_HEIGHT);
		do{
			StdDraw.clear(StdDraw.BLACK);
			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.picture(WIN_WIDTH/2,  WIN_HEIGHT/2+80, "images/logo.png");
			StdDraw.text(WIN_WIDTH/2, WIN_HEIGHT/2-40, "Press SPACE to start !" );
			StdDraw.show(20);
		}while(!StdDraw.isKeyPressed(KeyEvent.VK_SPACE));
		
		for (int i=0; i<Labyrinthe.walls[0].length;i++){
			for (int j=0; j<Labyrinthe.walls.length; j++){
				walls[j][i] = Labyrinthe.walls[j][i];
			}
		}
		StdDraw.clear(StdDraw.BLACK);
		drawWalls();
		drawFromage();
		drawScore();
		drawLives();
		if(isStart){
			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.text(WIN_WIDTH/2, WIN_HEIGHT/2, "GET READY!");
			isStart = false;
		}
		for(int i=0; i<F.length; i++){
			drawFantome(F[i]);
		}		
		drawJoueur();
		StdDraw.show(20);
		StdDraw.pause(3000);
		while (true){
			jeu();
		}
	}
}
