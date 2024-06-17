package Main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import Data.Box;
import Data.Vector2D;
import Data.spriteInfo;
import FileIO.EZFileRead;
import logic.Control;
import timer.stopWatchX;

public class Main{
	// Fields (Static) below...

	public static Box pickle;	//Pickle Box
	
	public static ArrayList<spriteInfo> spritesRight = new ArrayList<>();	//Walking Right Sprite ArrayList
	public static ArrayList<spriteInfo> spritesLeft = new ArrayList<>();	//Walking Left Sprite ArrayList
	public static ArrayList<spriteInfo> spritesUp = new ArrayList<>();		//Walking Up Sprite ArrayList
	public static ArrayList<spriteInfo> spritesDown = new ArrayList<>();	//Walking Down Sprite ArrayList
	
	public static ArrayList<Box> screenBox = new ArrayList<>();	//Screen Box ArrayList
	public static ArrayList<Box> itemBoxes = new ArrayList<>();	//Item Box Arraylist
	public static ArrayList<Box> playerBox = new ArrayList<>();	//Player Box ArrayLis
	
	public static Box lastItem;	//Most Updated Player Box
	

	

	
	public static String Dir = "R"; //Initializes Player Looking Right
	
	
	public static int currentSpriteIndex = 200;	//Initial X position
	public static int currentSpriteIndexVertical = 200;	//Initial Y Position
	
	public static int xPos;
	public static int yPos;
	
	
//	public static HashMap<String, String> map = new HashMap<>();
	
	public static stopWatchX timer = new stopWatchX(5); //Lower = Faster/ Higher = Slower
	public static boolean isImageDrawn = false;
	
	
	public static String trigger = "";
	public static boolean isKeyDown = false;
	
	// End Static fields...
	
	public static void main(String[] args) {
		Control ctrl = new Control();				// Do NOT remove!
		ctrl.gameLoop();							// Do NOT remove!
	}
	
	/* This is your access to things BEFORE the game loop starts */
	public static void start(){
		// TODO: Code your starting conditions here...NOT DRAW CALLS HERE! (no addSprite or drawString)
		
		//Loads Walking  right sprites into arrayList
		for(int i=-100;i<1500;i+=4) {
			 spritesRight.add(new spriteInfo(new Vector2D(i,0),"p1"));
			 spritesRight.add(new spriteInfo(new Vector2D(i+1,0),"p2"));
			 spritesRight.add(new spriteInfo(new Vector2D(i+2,0),"p33"));
			 spritesRight.add(new spriteInfo(new Vector2D(i+3,0),"p4"));
			
		} 
		//Loads Walking left sprites into arrayList

		for(int i=-100;i<1500;i+=4) {
			 spritesLeft.add(new spriteInfo(new Vector2D(i,100),"pl1"));
			 spritesLeft.add(new spriteInfo(new Vector2D(i+1,100),"pl2"));
			 spritesLeft.add(new spriteInfo(new Vector2D(i+2,100),"pl33"));
			 spritesLeft.add(new spriteInfo(new Vector2D(i+3,100),"pl4"));
			 
		} 
		//Loads Walking down sprites into arrayList
		for(int j=-100;j<2000;j+=4) {
			 spritesDown.add(new spriteInfo(new Vector2D(0,j),"pd1"));
			 spritesDown.add(new spriteInfo(new Vector2D(0,j+1),"pd2"));
			 spritesDown.add(new spriteInfo(new Vector2D(0,j+2),"pd33"));
			 spritesDown.add(new spriteInfo(new Vector2D(0,j+3),"pd4"));

			 			 
		} 
		//Loads Walking up sprites into arrayList
		for(int j=-100;j<2000;j+=4) {
			 spritesUp.add(new spriteInfo(new Vector2D(0,j),"pu1"));
			 spritesUp.add(new spriteInfo(new Vector2D(0,j+1),"pu2"));
			 spritesUp.add(new spriteInfo(new Vector2D(0,j+2),"pu33"));
			 spritesUp.add(new spriteInfo(new Vector2D(0,j+3),"pu4"));

			 
			 
		} 

		//Bounding Box for Pickle item
		pickle = new Box(152,330,233,433);
		itemBoxes.add(pickle); //pickle`
		
		//Bounding Box for Screen
		screenBox.add(new Box(44,1360,660,700)); //bottom
		screenBox.add(new Box(44,1360,0,50)); //top
		screenBox.add(new Box(0,44,0,800)); //left Bound Box
		screenBox.add(new Box(1360,1550,0,800)); //right Bound Box
	}
	
	
	/* This is your access to the "game loop" (It is a "callback" method from the Control class (do NOT modify that class!))*/
	public static void update(Control ctrl) {
		
		
		// TODO: This is where you can code! (Starting code below is just to show you how it works)
		
		//Display background and 2 items (Pickle and Water)
		ctrl.addSpriteToFrontBuffer(0,0, "Background");			// Background
		ctrl.addSpriteToFrontBuffer(600,600, "water");       	//Water    
		ctrl.addSpriteToFrontBuffer(200, 300, "pickle");		//Pickle
		

		
		

		
		// If NO key is being pressed OR SPACEBAR is being pressed, Display sprite positioned correctly
		if(!isKeyDown || trigger == "Spacebar") {
		if(Dir == "R")  ctrl.addSpriteToFrontBuffer(spritesRight.get(currentSpriteIndex).getCoords().getX(),spritesUp.get(currentSpriteIndexVertical).getCoords().getY(),spritesRight.get(currentSpriteIndex).getTag()); 		//Sprite looking Right
		if(Dir == "L")  ctrl.addSpriteToFrontBuffer(spritesLeft.get(currentSpriteIndex).getCoords().getX(),spritesUp.get(currentSpriteIndexVertical).getCoords().getY(),spritesLeft.get(currentSpriteIndex).getTag());			//Sprite looking Left
		if(Dir == "U")  ctrl.addSpriteToFrontBuffer(spritesLeft.get(currentSpriteIndex).getCoords().getX(),spritesUp.get(currentSpriteIndexVertical).getCoords().getY(),spritesUp.get(currentSpriteIndexVertical).getTag());	//Sprite looking Up
		if(Dir == "D") ctrl.addSpriteToFrontBuffer(spritesLeft.get(currentSpriteIndex).getCoords().getX(),spritesUp.get(currentSpriteIndexVertical).getCoords().getY(),spritesDown.get(currentSpriteIndexVertical).getTag());	//Sprite looking Down
		}
		
		//If W,A,S,D are being pressed
		if(isKeyDown) {
			//Moving right
			if(trigger == "D"){
				
				xPos = spritesRight.get(currentSpriteIndex).getCoords().getX();	//Updated X position
				yPos = spritesUp.get(currentSpriteIndexVertical).getCoords().getY();  //Updated Y position
			
				ctrl.addSpriteToFrontBuffer(xPos,yPos,spritesRight.get(currentSpriteIndex).getTag());	//Display Sprite
				
				
				if(timer.isTimeUp()) {
					
					isImageDrawn = !isImageDrawn;
					currentSpriteIndex++;	//Update Sprite position X+1
				
					//Update Player Box
					playerBox.add(new Box(spritesRight.get(currentSpriteIndex).getCoords().getX() ,spritesUp.get(currentSpriteIndexVertical).getCoords().getY()));
					lastItem = playerBox.get(playerBox.size()-1);
				
					//Check for Collision on Pickle and Character Sprite
					if(Box.isCollision(pickle, lastItem)) {
							currentSpriteIndex--;	//Update Sprite Position X-1
					}
			
					//Check for Collision on Right Screen Border and Character Sprite
					if(Box.isCollision(lastItem,screenBox.get(3))) {
						currentSpriteIndex--;	//Update Sprite Position X-1
					}
				
				timer.resetWatch();
				Dir = "R";	// Direction = Sprite is looking to the right
				}
			}
			//Moving left
			if(trigger == "A") {

				xPos = spritesLeft.get(currentSpriteIndex).getCoords().getX();//Updated X position
				yPos = spritesUp.get(currentSpriteIndexVertical).getCoords().getY();  //Updated Y position
				ctrl.addSpriteToFrontBuffer(xPos,yPos,spritesLeft.get(currentSpriteIndex).getTag());//Display Sprite
				if(timer.isTimeUp()) {
					
					isImageDrawn = !isImageDrawn;
					currentSpriteIndex--;	//Update Sprite Position X-1
					
					//Update Player Box
					playerBox.add(new Box(spritesRight.get(currentSpriteIndex).getCoords().getX() ,spritesUp.get(currentSpriteIndexVertical).getCoords().getY()));
					lastItem = playerBox.get(playerBox.size()-1);
					
						//Check for Collision on Pickle and Character Sprite
						if(Box.isCollision(pickle, lastItem)) {
							currentSpriteIndex++; // Update Sprite Position X+1
						}
					

						//Check for Collision on Left Screen Border and Character Sprite
						if(Box.isCollision(lastItem,screenBox.get(2))) {
							currentSpriteIndex ++;	//Update Sprite Position X+1		
						} 
		
				timer.resetWatch();
				Dir = "L";	 //Direction = Sprite is looking to the left
				}
			


			}
			//Moving Down
			if(trigger == "S") {

				xPos = spritesRight.get(currentSpriteIndex).getCoords().getX(); //Updated X position
				yPos = spritesDown.get(currentSpriteIndexVertical).getCoords().getY(); //Updated Y position
				
				ctrl.addSpriteToFrontBuffer(xPos,yPos,spritesDown.get(currentSpriteIndexVertical).getTag());//Display Sprite
				if(timer.isTimeUp()) {
					
					isImageDrawn = !isImageDrawn;
					currentSpriteIndexVertical++;	//Update Sprite Position Y+1
					
					//Update Player Box
					playerBox.add(new Box(spritesRight.get(currentSpriteIndex).getCoords().getX() ,spritesUp.get(currentSpriteIndexVertical).getCoords().getY()));
					lastItem = playerBox.get(playerBox.size()-1);
					
					//Check for Collision on Pickle and Character Sprite
					if(Box.isCollision(pickle, lastItem))	{
						currentSpriteIndexVertical--;	//Update Sprite Position Y-1
					}
					
					//Check for Collision on Bottom Screen Border and Character Sprite
					if(Box.isCollision(lastItem,screenBox.get(0))) {
						currentSpriteIndexVertical--;	//Update Sprite Position Y-1
					}

				timer.resetWatch();
				Dir = "D"; 	//Direction = Sprite is looking down


				}
			}
			//Moving Up
			if(trigger == "W") {

					
				xPos = spritesRight.get(currentSpriteIndex).getCoords().getX();//Updated X position
				yPos = spritesUp.get(currentSpriteIndexVertical).getCoords().getY(); //Updated Y position
				ctrl.addSpriteToFrontBuffer(xPos,yPos,spritesUp.get(currentSpriteIndexVertical).getTag());//Display Sprite
				if(timer.isTimeUp()) {
					
					isImageDrawn = !isImageDrawn;
					currentSpriteIndexVertical--;	//Update Sprite Position Y-1
					
					//Update Player BoX
					playerBox.add(new Box(spritesRight.get(currentSpriteIndex).getCoords().getX() ,spritesUp.get(currentSpriteIndexVertical).getCoords().getY()));
					lastItem = playerBox.get(playerBox.size()-1);
					
					//Check for Collision on Pickle and Character Sprite
					if(Box.isCollision(pickle, lastItem)) {
						currentSpriteIndexVertical++;					
					}
				
					//Check for Collision on Top Screen Border and Character Sprite
					if(Box.isCollision(lastItem,screenBox.get(1))) {
						currentSpriteIndexVertical ++;
					}
					
				timer.resetWatch();
				Dir = "U"; // Direction = Sprite is looking up
				
				}
					
			}
			// PICKLE RICK ITEM INTERACTION
			//Coming from the left
			if(xPos == 151 && 437 >spritesUp.get(currentSpriteIndexVertical).getCoords().getY() && spritesUp.get(currentSpriteIndexVertical).getCoords().getY() > 234 && Dir == "R"&& trigger == "Spacebar") {
				ctrl.drawString(300, 300, "IM PICKLE RICK", Color.green);	//Display message
				ctrl.addSpriteToFrontBuffer(xPos,yPos,spritesRight.get(currentSpriteIndex).getTag());	//Display Sprite

			}
			//Coming from the right
			if(xPos == 331 && 437 >spritesUp.get(currentSpriteIndexVertical).getCoords().getY() && spritesUp.get(currentSpriteIndexVertical).getCoords().getY() > 234 && Dir == "L" && trigger == "Spacebar") {
				ctrl.drawString(300, 300, "IM PICKLE RICK", Color.green);
				ctrl.addSpriteToFrontBuffer(xPos,yPos,spritesLeft.get(currentSpriteIndex).getTag());
			}
			//Coming from the top
			if(spritesDown.get(currentSpriteIndexVertical).getCoords().getY() == 232 && 330 > xPos && xPos>150 && Dir == "D" && trigger == "Spacebar") {
				
				ctrl.drawString(300, 300, "IM PICKLE RICK", Color.green);
				ctrl.addSpriteToFrontBuffer(xPos,spritesDown.get(currentSpriteIndexVertical).getCoords().getY(),spritesDown.get(currentSpriteIndexVertical).getTag());
			}
			//Coming from the bottom
			if(spritesUp.get(currentSpriteIndexVertical).getCoords().getY()== 434 && 330 > xPos && xPos>150 && Dir == "U" && trigger == "Spacebar") {

				ctrl.drawString(300, 300, "IM PICKLE RICK", Color.green);
				ctrl.addSpriteToFrontBuffer(xPos,spritesUp.get(currentSpriteIndexVertical).getCoords().getY(),spritesUp.get(currentSpriteIndexVertical).getTag());
			}
			
			
			//WATER Item Interaction
			//Coming from the left
			if(xPos > 543 && xPos <655 && 587 >spritesUp.get(currentSpriteIndexVertical).getCoords().getY() && spritesUp.get(currentSpriteIndexVertical).getCoords().getY() > 504 && Dir == "R"&& trigger == "Spacebar") {
				ctrl.drawString(540, 550, "GREAT! NOW MY SHOES ARE WET", Color.blue);	//Display Message
				ctrl.addSpriteToFrontBuffer(xPos,yPos,spritesRight.get(currentSpriteIndex).getTag());	//Display Sprite

			}
			//Coming from the right
			if(xPos < 655 && xPos >543 && 587 >spritesUp.get(currentSpriteIndexVertical).getCoords().getY() && spritesUp.get(currentSpriteIndexVertical).getCoords().getY() > 504 && Dir == "L" && trigger == "Spacebar") {
				ctrl.drawString(540, 550, "GREAT! NOW MY SHOES ARE WET", Color.blue);
				ctrl.addSpriteToFrontBuffer(xPos,yPos,spritesLeft.get(currentSpriteIndex).getTag());
			}
			//Coming from the top
			if(spritesDown.get(currentSpriteIndexVertical).getCoords().getY() > 504 && spritesDown.get(currentSpriteIndexVertical).getCoords().getY() <587 && 655 > xPos && xPos>543 && Dir == "D" && trigger == "Spacebar") {
				
				ctrl.drawString(540, 550, "GREAT! NOW MY SHOES ARE WET", Color.blue);
				ctrl.addSpriteToFrontBuffer(xPos,spritesDown.get(currentSpriteIndexVertical).getCoords().getY(),spritesDown.get(currentSpriteIndexVertical).getTag());
			}
			//Coming from the bottom
			if(spritesUp.get(currentSpriteIndexVertical).getCoords().getY()< 587 && spritesDown.get(currentSpriteIndexVertical).getCoords().getY() > 504 && 655 > xPos && xPos>543 && Dir == "U" && trigger == "Spacebar") {

				ctrl.drawString(540, 550, "GREAT! NOW MY SHOES ARE WET", Color.blue);
				ctrl.addSpriteToFrontBuffer(xPos,spritesUp.get(currentSpriteIndexVertical).getCoords().getY(),spritesUp.get(currentSpriteIndexVertical).getTag());
			}
		}
		

		
				
		
	}
	
	
	
	// Additional Static methods below...(if needed)


	
}
