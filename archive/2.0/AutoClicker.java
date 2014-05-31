package AutoClicker;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.awt.event.InputEvent;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.mouse.NativeMouseEvent;

public class AutoClicker extends AutoClickerGUI implements Runnable{	
	public static int pick = 0;					// bot type
	
	public static int bankDelay = 1500;			// delay after banker is clicked
	public static int spaceDelay = 1500;		// delay before space is pressed
	
	public static boolean runFlag = false;		// used to reset or quit
	
	public static boolean setBanker = false;	// flags for setting coordinates
	public static boolean setItem1 = false;
	public static boolean setItem2 = false;
	public static boolean setDeposit = false;
	public static boolean setInv = false;
	public static boolean setPreset = false;
	
	public static int count = 0;				// counter for iterations
	public static int itemNumber = 1;			// tracks item number
	public static int move_idx = 0;				// index for camera movement

	public static int bankerX = 0;
	public static int bankerY = 0;
	
	public static int item1X = 0;
	public static int item1Y = 0;
	
	public static int item2X = 0;
	public static int item2Y = 0;
	
	public static int depositX = 0;
	public static int depositY = 0;
	
	public static int invX = 0;
	public static int invY = 0;

	public static int presetX = 0;
	public static int presetY = 0;
	
	public static int item1Amount = 0;			// amount of each item
	public static int item2Amount = 0;
	
	private enum Direction {LEFT, RIGHT, UP, DOWN}
	private static Direction ivyPosition = Direction.LEFT;
	
	private static int ivyX;
	private static int ivyY;
	
	private  static boolean depositFlag;		// controls banking
	
	private static Color ivyColor;

	private static int[] move = {NativeKeyEvent.VK_LEFT, NativeKeyEvent.VK_RIGHT, NativeKeyEvent.VK_UP, NativeKeyEvent.VK_DOWN};
	
	public static Robot robot;
	

	
	public void run() {
		try {
			robot = new Robot();
			depositFlag = true;
			
			if (pick == 6) {
				item1Amount /= 14;
				item2Amount /= 14;
			}
			
			if (pick == 8) {
				ivyX = item1X;
				ivyY = item1Y;
				
				ivyColor = robot.getPixelColor(ivyX, ivyY);
				
				ivyPosition = Direction.LEFT;
			}
		   
		    while (true) {
		    	switch(pick) {
		    		case 0:
				    	for (int i = 0; i < 9; i++) {
				    		chocolatePowder(i);
				    		if (!runFlag) {return;}
				    	}
				    	break;
		    		case 1:
			    		for (int i = 0; i < 3; i++) {
			    			headlessArrows(i);
			    			if (!runFlag) {return;}
			    		} 
			    		break;
		    		case 2:
			    		for (int i = 0; i < 2; i++) {
			    			alchemy(i);
			    			if (!runFlag) {return;}
			    		}
			    		break;
		    		case 3: 
		    			for (int i = 0; i < 5; i++) {
		    				arrowShafts(i);
		    				if (!runFlag) {return;}
		    			}
		    			break;
		    		case 4:
				    	for (int i = 0; i < 8; i++) {
				    		cleanHerbs(i);
				    		if (!runFlag) {return;}
				    	}
				    	break;
		    		case 5:
		    			for (int i = 0; i < 6; i++) {
		    				potions(i);
		    				if (!runFlag) {return;}
		    			}
		    			break;
		    		case 6:
		    			if (itemNumber == 1 && count >= item1Amount) {
	    					presetX += 40;
	    					count = 0;
	    					itemNumber++;
		    			} 
		    			
		    			if (itemNumber == 2 && count >= item2Amount) {
		    				try {
		    					presetX -= 40;
		    					robot.keyPress(NativeKeyEvent.VK_ESCAPE);
		    		            Thread.sleep(300);
		    		            robot.keyRelease(NativeKeyEvent.VK_ESCAPE);
		    		            
			    				robot.keyPress(NativeKeyEvent.VK_R);
					            Thread.sleep(300);
					            robot.keyRelease(NativeKeyEvent.VK_R);
		    				} catch (InterruptedException ie) {}
		    				
		    				return;
				        }

		    			for (int i = 0; i < 6; i++) {
		    				potions(i);
		    				if (!runFlag) {return;}
		    			}
		    			
		    			count++;
		    			break;
		    		case 7:
		    			for (int i = 0; i < 8; i++) {
				    		shieldbows(i);
				    		if (!runFlag) {return;}
				    	}
				    	break;
		    		case 8:
		    			ivy();
		    			if (!runFlag) {return;}
		    			break;
		    	}
		    }
		} catch (AWTException e) {}	
	}
	
	public static void chocolatePowder(int i) {
		try {
        	switch(i) {
        		case 0:
        			if (depositFlag) {
	        			robot.mouseMove(bankerX, bankerY);
	        			robot.mousePress(InputEvent.BUTTON1_MASK);
			            robot.mouseRelease(InputEvent.BUTTON1_MASK);
			            Thread.sleep(bankDelay);
			            depositFlag = false;
        			}
		            break;
        		case 1:
        			robot.mouseMove(item1X, item1Y);
        			robot.mousePress(InputEvent.BUTTON3_MASK);
		            robot.mouseRelease(InputEvent.BUTTON3_MASK);
		            Thread.sleep(500);
		            break;
        		case 2:
        			robot.mouseMove(item1X, item1Y + 110);
        			robot.mousePress(InputEvent.BUTTON1_MASK);
		            robot.mouseRelease(InputEvent.BUTTON1_MASK);
	       			Thread.sleep(1000);
		            robot.keyPress(NativeKeyEvent.VK_ESCAPE);
		            Thread.sleep(300);
		            robot.keyRelease(NativeKeyEvent.VK_ESCAPE);
		            Thread.sleep(1500);
		            break;
        		case 3:
        			robot.mouseMove(invX, invY);
        			Thread.sleep(500);
        			robot.mousePress(InputEvent.BUTTON3_MASK);
		            robot.mouseRelease(InputEvent.BUTTON3_MASK);
		            Thread.sleep(500);
		            break;
        		case 4:
        			robot.mouseMove(invX, invY + 55);
        			robot.mousePress(InputEvent.BUTTON1_MASK);
		            robot.mouseRelease(InputEvent.BUTTON1_MASK);
		            Thread.sleep(spaceDelay);
        			break;
        		case 5:
        			robot.keyPress(NativeKeyEvent.VK_SPACE);
		            Thread.sleep(300);
		            robot.keyRelease(NativeKeyEvent.VK_SPACE);
		            Thread.sleep(19000);
		            break;
        		case 6:
        			robot.mouseMove(bankerX, bankerY);
        			robot.mousePress(InputEvent.BUTTON1_MASK);
		            robot.mouseRelease(InputEvent.BUTTON1_MASK);
		            Thread.sleep(bankDelay);
		            break;
        		case 7:
        			robot.mouseMove(depositX, depositY);
        			robot.mousePress(InputEvent.BUTTON3_MASK);
		            robot.mouseRelease(InputEvent.BUTTON3_MASK);
		            Thread.sleep(800);
		            break;
        		case 8:
        			robot.mouseMove(depositX, depositY + 110);
        			robot.mousePress(InputEvent.BUTTON1_MASK);
		            robot.mouseRelease(InputEvent.BUTTON1_MASK);
		            Thread.sleep(1000);
		            break;
        	}
        } catch (InterruptedException ex) {}
	}
	
	public static void headlessArrows(int i) {
		try {
        	switch(i) {
        		case 0:
        			robot.mouseMove(invX, invY);
        			robot.mousePress(InputEvent.BUTTON1_MASK);
		            robot.mouseRelease(InputEvent.BUTTON1_MASK);
		            Thread.sleep(700);
		            break;
        		case 1:
        			robot.mouseMove(invX + 45, invY);
        			robot.mousePress(InputEvent.BUTTON1_MASK);
		            robot.mouseRelease(InputEvent.BUTTON1_MASK);
		            Thread.sleep(spaceDelay);
		            break;
        		case 2:
        			robot.keyPress(NativeKeyEvent.VK_SPACE);
		            Thread.sleep(300);
		            robot.keyRelease(NativeKeyEvent.VK_SPACE);
		            
		            Thread.sleep(1000);
		            
		            if (move_idx >= 4) {
		            	move_idx = 0;
		            }
		            robot.keyPress(move[move_idx]);
		            Thread.sleep(1000);
		            robot.keyRelease(move[move_idx]);
		            move_idx++;
		            Thread.sleep(10000);
		            break;
        	}
		} catch (InterruptedException ex) {}
	}
	
	public static void alchemy(int i) {
		 try {
        	switch(i) {
        		case 0:
        			robot.mouseMove(item1X, item1Y);
        			robot.mousePress(InputEvent.BUTTON1_MASK);
		            robot.mouseRelease(InputEvent.BUTTON1_MASK);
		            Thread.sleep(500);
		            break;
        		case 1:
        			robot.mouseMove(item1X, item1Y);
        			robot.mousePress(InputEvent.BUTTON1_MASK);
		            robot.mouseRelease(InputEvent.BUTTON1_MASK);
		            Thread.sleep(2000);
		            break;
        	}
 		} catch (InterruptedException ex) {}
	}
	
	public static void arrowShafts(int i) {
		try {
        	switch(i) {
        		case 0:
        			robot.mouseMove(bankerX, bankerY);
        			robot.mousePress(InputEvent.BUTTON1_MASK);
		            robot.mouseRelease(InputEvent.BUTTON1_MASK);
		            Thread.sleep(bankDelay);
		            break;
        		case 1:
        			robot.mouseMove(item1X, item1Y);
        			robot.mousePress(InputEvent.BUTTON3_MASK);
		            robot.mouseRelease(InputEvent.BUTTON3_MASK);
		            Thread.sleep(500);
		            break;
        		case 2:
        			robot.mouseMove(item1X, item1Y + 110);
        			robot.mousePress(InputEvent.BUTTON1_MASK);
		            robot.mouseRelease(InputEvent.BUTTON1_MASK);
	       			Thread.sleep(1000);
		            robot.keyPress(NativeKeyEvent.VK_ESCAPE);
		            Thread.sleep(300);
		            robot.keyRelease(NativeKeyEvent.VK_ESCAPE);
		            Thread.sleep(1500);
		            break;
        		case 3:
        			robot.mouseMove(invX, invY);
        			Thread.sleep(500);
        			robot.mousePress(InputEvent.BUTTON1_MASK);
		            robot.mouseRelease(InputEvent.BUTTON1_MASK);
		            Thread.sleep(spaceDelay);
		            break;
        		case 4:
        			robot.keyPress(NativeKeyEvent.VK_SPACE);
		            Thread.sleep(300);
		            robot.keyRelease(NativeKeyEvent.VK_SPACE);
		            Thread.sleep(50000);
		            break;
        	}
        } catch (InterruptedException ex) {}
	}
	
	public static void cleanHerbs(int i) {
		try {
        	switch(i) {
        		case 0:
        			if (depositFlag) {
	        			robot.mouseMove(bankerX, bankerY);
	        			robot.mousePress(InputEvent.BUTTON1_MASK);
			            robot.mouseRelease(InputEvent.BUTTON1_MASK);
			            Thread.sleep(bankDelay);
			            depositFlag = false;
        			}
		            break;
        		case 1:
        			robot.mouseMove(item1X, item1Y);
        			robot.mousePress(InputEvent.BUTTON3_MASK);
		            robot.mouseRelease(InputEvent.BUTTON3_MASK);
		            Thread.sleep(500);
		            break;
        		case 2:
        			robot.mouseMove(item1X, item1Y + 110);
        			robot.mousePress(InputEvent.BUTTON1_MASK);
		            robot.mouseRelease(InputEvent.BUTTON1_MASK);
	       			Thread.sleep(1000);
		            robot.keyPress(NativeKeyEvent.VK_ESCAPE);
		            Thread.sleep(300);
		            robot.keyRelease(NativeKeyEvent.VK_ESCAPE);
		            Thread.sleep(1500);
		            break;
        		case 3:
        			robot.mouseMove(invX, invY);
        			Thread.sleep(500);
        			robot.mousePress(InputEvent.BUTTON1_MASK);
		            robot.mouseRelease(InputEvent.BUTTON1_MASK);
		            Thread.sleep(spaceDelay);
		            break;
        		case 4:
        			robot.keyPress(NativeKeyEvent.VK_SPACE);
		            Thread.sleep(300);
		            robot.keyRelease(NativeKeyEvent.VK_SPACE);
		            Thread.sleep(19000);
		            break;
        		case 5:
        			robot.mouseMove(bankerX, bankerY);
        			robot.mousePress(InputEvent.BUTTON1_MASK);
		            robot.mouseRelease(InputEvent.BUTTON1_MASK);
		            Thread.sleep(bankDelay);
		            break;
        		case 6:
        			robot.mouseMove(depositX, depositY);
        			robot.mousePress(InputEvent.BUTTON3_MASK);
		            robot.mouseRelease(InputEvent.BUTTON3_MASK);
		            Thread.sleep(800);
		            break;
        		case 7:
        			robot.mouseMove(depositX, depositY + 110);
        			robot.mousePress(InputEvent.BUTTON1_MASK);
		            robot.mouseRelease(InputEvent.BUTTON1_MASK);
		            Thread.sleep(1000);
		            break;
        	}
        } catch (InterruptedException ex) {}
	}
	
	public static void potions(int i) {
		try {
        	switch(i) {
        		case 0:
        			if (depositFlag) {
	        			robot.mouseMove(bankerX, bankerY);
	        			robot.mousePress(InputEvent.BUTTON1_MASK);
			            robot.mouseRelease(InputEvent.BUTTON1_MASK);
			            Thread.sleep(bankDelay);
			            depositFlag = false;
        			}
		            break;
        		case 1:
        			robot.mouseMove(presetX, presetY);
        			robot.mousePress(InputEvent.BUTTON1_MASK);
		            robot.mouseRelease(InputEvent.BUTTON1_MASK);
		            Thread.sleep(1500);
		            break;
        		case 2:
        			robot.mouseMove(invX, invY);
        			Thread.sleep(500);
        			robot.mousePress(InputEvent.BUTTON1_MASK);
		            robot.mouseRelease(InputEvent.BUTTON1_MASK);
		            Thread.sleep(700);
		            break;
        		case 3:
        			robot.mouseMove(invX + 45, invY);
        			Thread.sleep(500);
        			robot.mousePress(InputEvent.BUTTON1_MASK);
		            robot.mouseRelease(InputEvent.BUTTON1_MASK);
		            Thread.sleep(spaceDelay);
		            break;
        		case 4:
        			robot.keyPress(NativeKeyEvent.VK_SPACE);
		            Thread.sleep(300);
		            robot.keyRelease(NativeKeyEvent.VK_SPACE);
		            Thread.sleep(18000);
		            break;
        		case 5:
        			robot.mouseMove(bankerX, bankerY);
        			robot.mousePress(InputEvent.BUTTON1_MASK);
		            robot.mouseRelease(InputEvent.BUTTON1_MASK);
		            Thread.sleep(bankDelay);
		            break;
        	}
        } catch (InterruptedException ex) {}
	}
	
	public static void shieldbows(int i) {
		try {
        	switch(i) {
        		case 0:
        			if (depositFlag) {
	        			robot.mouseMove(bankerX, bankerY);
	        			robot.mousePress(InputEvent.BUTTON1_MASK);
			            robot.mouseRelease(InputEvent.BUTTON1_MASK);
			            Thread.sleep(bankDelay);
			            depositFlag = false;
        			}
		            break;
        		case 1:
        			robot.mouseMove(item1X, item1Y);
        			robot.mousePress(InputEvent.BUTTON3_MASK);
		            robot.mouseRelease(InputEvent.BUTTON3_MASK);
		            Thread.sleep(500);
		            break;
        		case 2:
        			robot.mouseMove(item1X, item1Y + 110);
        			robot.mousePress(InputEvent.BUTTON1_MASK);
		            robot.mouseRelease(InputEvent.BUTTON1_MASK);
	       			Thread.sleep(1000);
		            robot.keyPress(NativeKeyEvent.VK_ESCAPE);
		            Thread.sleep(300);
		            robot.keyRelease(NativeKeyEvent.VK_ESCAPE);
		            Thread.sleep(1500);
		            break;
        		case 3:
        			robot.mouseMove(invX, invY);
        			Thread.sleep(500);
        			robot.mousePress(InputEvent.BUTTON1_MASK);
		            robot.mouseRelease(InputEvent.BUTTON1_MASK);
		            Thread.sleep(spaceDelay);
		            break;
        		case 4:
        			robot.keyPress(NativeKeyEvent.VK_SPACE);
		            Thread.sleep(300);
		            robot.keyRelease(NativeKeyEvent.VK_SPACE);
		            Thread.sleep(52000);
		            break;
        		case 5:
        			robot.mouseMove(bankerX, bankerY);
        			robot.mousePress(InputEvent.BUTTON1_MASK);
		            robot.mouseRelease(InputEvent.BUTTON1_MASK);
		            Thread.sleep(bankDelay);
		            break;
        		case 6:
        			robot.mouseMove(depositX, depositY);
        			robot.mousePress(InputEvent.BUTTON3_MASK);
		            robot.mouseRelease(InputEvent.BUTTON3_MASK);
		            Thread.sleep(800);
		            break;
        		case 7:
        			robot.mouseMove(depositX, depositY + 110);
        			robot.mousePress(InputEvent.BUTTON1_MASK);
		            robot.mouseRelease(InputEvent.BUTTON1_MASK);
		            Thread.sleep(1000);
		            break;
        	}
        } catch (InterruptedException ex) {}
	}
	
	public static void ivy() {
		// if current ivy is left
		if (ivyPosition == Direction.LEFT) {
			// check if pixel has changed
			if (robot.getPixelColor(ivyX, ivyY).toString().equals(ivyColor.toString())) {
				robot.mouseMove(ivyX, ivyY);
    			robot.mousePress(InputEvent.BUTTON1_MASK);
	            robot.mouseRelease(InputEvent.BUTTON1_MASK);
	            
	    		try {
	    			Thread.sleep(10000);
	    		} catch (InterruptedException ex) {}
			} else {
				int tempX = ivyX + 200;		// temporary x, y for ivy search
				int tempY = ivyY;
				Color temp = robot.getPixelColor(ivyX + 200, ivyY);
				
				// check all neighboring pixels for ivy
				while(true) {
					if (!(temp.getRed() > 110 || temp.getBlue() > 150 || temp.getBlue() > 80)) {break;}
					
					temp = robot.getPixelColor(tempX + 2, tempY);
					if (!(temp.getRed() > 110 || temp.getBlue() > 150 || temp.getBlue() > 80)) {
						tempX += 2;
						break;
					}
					
					temp = robot.getPixelColor(tempX, tempY - 2);
					if (!(temp.getRed() > 110 || temp.getBlue() > 150 || temp.getBlue() > 80)) {
						tempY -= 2;
						break;
					}
					
					temp = robot.getPixelColor(tempX - 2, tempY);
					if (!(temp.getRed() > 110 || temp.getBlue() > 150 || temp.getBlue() > 80)) {
						tempX -= 2;
						break;
					}
					
					temp = robot.getPixelColor(tempX, tempY + 2);
					if (!(temp.getRed() > 110 || temp.getBlue() > 150 || temp.getBlue() > 80)) {
						tempY += 2;
						break;
					}
					
					try {
		    			Thread.sleep(4000);
		    		} catch (InterruptedException ex) {}
					
					return;
				}
				
				// click if ivy is found
				robot.mouseMove(tempX, tempY);
    			robot.mousePress(InputEvent.BUTTON1_MASK);
	            robot.mouseRelease(InputEvent.BUTTON1_MASK);
	            
	            try {
	    			Thread.sleep(2000);
	    		} catch (InterruptedException ex) {}
	            
	            // retrieve new pixel color and update direction
	            ivyColor = robot.getPixelColor(ivyX, ivyY);
	            ivyPosition = Direction.RIGHT;
	            
	            try {
	    			Thread.sleep(13000);
	    		} catch (InterruptedException ex) {}
			}
		} else {
			// same as above
			if (robot.getPixelColor(ivyX, ivyY).toString().equals(ivyColor.toString())) {
				robot.mouseMove(ivyX, ivyY);
    			robot.mousePress(InputEvent.BUTTON1_MASK);
	            robot.mouseRelease(InputEvent.BUTTON1_MASK);
	            
	            try {
	    			Thread.sleep(10000);
	    		} catch (InterruptedException ex) {}
			} else {
				int tempX = ivyX - 135;
				int tempY = ivyY;
				Color temp = robot.getPixelColor(ivyX - 135, ivyY);
				
				while(true) {
					if (!(temp.getRed() > 110 || temp.getBlue() > 150 || temp.getBlue() > 80)) {break;}
					
					temp = robot.getPixelColor(tempX + 2, tempY);
					if (!(temp.getRed() > 110 || temp.getBlue() > 150 || temp.getBlue() > 80)) {
						tempX += 2;
						break;
					}
					
					temp = robot.getPixelColor(tempX, tempY - 2);
					if (!(temp.getRed() > 110 || temp.getBlue() > 150 || temp.getBlue() > 80)) {
						tempY -= 2;
						break;
					}
					
					temp = robot.getPixelColor(tempX - 2, tempY);
					if (!(temp.getRed() > 110 || temp.getBlue() > 150 || temp.getBlue() > 80)) {
						tempX -= 2;
						break;
					}
					
					temp = robot.getPixelColor(tempX, tempY + 2);
					if (!(temp.getRed() > 110 || temp.getBlue() > 150 || temp.getBlue() > 80)) {
						tempY += 2;
						break;
					}
					
					try {
		    			Thread.sleep(4000);
		    		} catch (InterruptedException ex) {}
					
					return;
				}
				
				robot.mouseMove(tempX, tempY);
    			robot.mousePress(InputEvent.BUTTON1_MASK);
	            robot.mouseRelease(InputEvent.BUTTON1_MASK);
	            
	            try {
	    			Thread.sleep(2000);
	    		} catch (InterruptedException ex) {}
	            
	            ivyColor = robot.getPixelColor(ivyX, ivyY);
	            ivyPosition = Direction.LEFT;
	            
	            try {
	    			Thread.sleep(13000);
	    		} catch (InterruptedException ex) {}
			}
		}
	}
	
	public static void nativeKeyPressedHandler(NativeKeyEvent e) {
        System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));

        if (e.getKeyCode() == NativeKeyEvent.VK_Q) {
            System.exit(0);
        }
        
        if (e.getKeyCode() == NativeKeyEvent.VK_R){
        	AutoClickerGUI.resetAll();
        }
	}
	
	public static void nativeKeyReleasedHandler(NativeKeyEvent e) {
        System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
	}
	
	public static void nativeMouseClickedHandler(NativeMouseEvent e) {		
		if (setBanker) {
			bankerX = e.getX();
			bankerY = e.getY();
			setBanker = false;
			AutoClickerGUI.resetButton(SetButton.BANKER);
		} else if (setInv) {
			invX = e.getX();
			invY = e.getY();
			setInv = false;
			AutoClickerGUI.resetButton(SetButton.INV);
		} else if (setItem1) {
			item1X = e.getX();
			item1Y = e.getY();
			setItem1 = false;
			AutoClickerGUI.resetButton(SetButton.ITEM1);
		} else if (setItem2) {
			item2X = e.getX();
			item2Y = e.getY();
			setItem2 = false;
			AutoClickerGUI.resetButton(SetButton.ITEM2);
		} else if (setDeposit) {
			depositX = e.getX();
			depositY = e.getY();
			setDeposit = false;
			AutoClickerGUI.resetButton(SetButton.DEPOSIT);
		} else if (setPreset) {
			presetX = e.getX();
			presetY = e.getY();
			setPreset = false;
			AutoClickerGUI.resetButton(SetButton.PRESET);
		}
         
         System.out.println("X: " + e.getX() + " Y: " + e.getY());
	 }
}