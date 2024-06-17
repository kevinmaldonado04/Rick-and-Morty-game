/* This will handle the "Hot Key" system. */

package Main;



import java.util.ArrayList;

import Data.Box;
import logic.Control;
import timer.stopWatchX;

public class KeyProcessor{
	// Static Fields
	private static char last = ' ';			// For debouncing purposes
	private static stopWatchX sw = new stopWatchX(25);
	
	
	
	
	
	// Static Method(s)
	public static void processKey(char key){
		if(key == ' ') {
			Main.isKeyDown = false;
			return;
		}
		// Debounce routine below...
		if(key == last)
			if(sw.isTimeUp() == false)			return;
		last = key;
		sw.resetWatch();
		
		/* TODO: You can modify values below here! */
		switch(key){
		
		case '%':								// ESC key
			System.exit(0);
			break;
			
		case 'w':	
			
			Main.trigger = "W";	
			Main.isKeyDown = true;
			
			break;
			
		case 'a':
			
			Main.trigger = "A";
			Main.isKeyDown = true;
			
			break;
			
		case 'd':
			
			Main.trigger = "D";
			Main.isKeyDown = true;
		
			break;
			
		case 's':
			
			Main.trigger = "S";
			Main.isKeyDown = true;
			
			break;
			
		case '$':
			Main.trigger = "Spacebar";
			Main.isKeyDown = true;
			break;
			
		case 'm':
			// For mouse coordinates
			Control.isMouseCoordsDisplayed = !Control.isMouseCoordsDisplayed;
			break;
		
			}
	}
}