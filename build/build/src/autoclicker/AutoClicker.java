package autoclicker;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.Random;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.mouse.NativeMouseEvent;

public class AutoClicker extends AutoClickerGUI implements Runnable{	
	public static int pick = 0;					// bot type
	
	public static int bankDelay = 1800;			// delay after banker is clicked
	public static int spaceDelay = 1500;		// delay before space is pressed
	public static int windowsDelay = 1000;		// delay after windows key is pressed
	public static int loadDelay = 10000;		// delay after client is started
	public static int loginDelay = 5000;		// delay after login
	public static int gameDelay = 20000;		// delay after starting game
	
	public static boolean runFlag = false;		// used to reset or quit
	public static boolean resetFlag = false;	// used to toggle resetting connection
	public static boolean reset = false;		// reset if true
	
	public static boolean setBanker = false;	// flags for setting coordinates
	public static boolean setItem1 = false;
	public static boolean setItem2 = false;
	public static boolean setDeposit = false;
	public static boolean setInv = false;
	public static boolean setPreset = false;
	public static boolean setCamera = false;
	
	public static int count = 0;				// multipurpose counter for iterations
	public static int itemNumber = 1;			// tracks item number

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
	
	public static int cameraX = 0;				// reset camera button
	public static int cameraY = 0;

	public static int closeWindowX = 200;		// close window button
	public static int closeWindowY = 0;
	
	public static int startGameX = 0;			// start game/world button
	public static int startGameY = 0;
	
	public static int item1Amount = 0;			// amount of each item
	public static int item2Amount = 0;
	
	public static Thread timer;					// timer thread
	public static String password = "";			// reset password
	
	private static int ivyX;
	private static int ivyY;
	
	private static int moveIndex = 0;			// index for camera movement
	private static int cycleCount = 0;			// count between random movement cycles
	private static int cameraDelay = 500;		// delay after clicking camera reset
	private static int optionBoxX = 40;			// location of option box relative to pointer
	private static int optionBoxY = 55;
	
	private static boolean depositFlag;			// controls banking
	private static boolean clickToggle; 		// controls ivy clicking
	
	private static double CIRCLE_CONST = 0.7071067;
	
	private static Robot robot;
	private static String pixelColor;
	private static Random rand = new Random();
	
	private enum Direction {LEFT, RIGHT, UP, DOWN}
	private static Direction ivyPosition = Direction.LEFT;

	private static int[] move = {NativeKeyEvent.VK_LEFT, NativeKeyEvent.VK_RIGHT, NativeKeyEvent.VK_UP, NativeKeyEvent.VK_DOWN};
	
	public void run() {
		try {
			robot = new Robot();
			depositFlag = true;
			clickToggle = true;
			count = 0;
			cycleCount = 0;
			
			// start new timer thread
			timer = new Thread(new Timer());
			timer.start();

			if (pick == 6) {
				item1Amount /= 14;
				item2Amount /= 14;
			}
			
			if (pick == 8) {
				ivyX = item1X;
				ivyY = item1Y;
				
				// set ivy pixel direction
				ivyPosition = Direction.LEFT;
			}
		   
		    while (true) {
		    	if (reset) {
		    		try {
		    			Thread.sleep(4000);
		    			resetClient();
			    		resetCamera();
			    		
			    		if (pick == 8) {
			    			robot.keyPress(NativeKeyEvent.VK_UP);
			    			Thread.sleep(2000);
			    			robot.keyRelease(NativeKeyEvent.VK_UP);
			    		}
			        } catch (InterruptedException ie) {}
		            
		            
		            // restart timer thread
		            reset = false;
		            timer = new Thread(new Timer());
		            timer.start();
		            
		            depositFlag = true;
		    	}
		    	
		    	switch (pick) {
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
	    					depositFlag = true;
		    			} 
		    			
		    			if (itemNumber == 2 && count >= item2Amount) {
	    					presetX -= 40;
	    					robot.keyPress(NativeKeyEvent.VK_ESCAPE);
	    		            try {Thread.sleep(300);}
	    		            catch (InterruptedException ie) {}
	    		            robot.keyRelease(NativeKeyEvent.VK_ESCAPE);
	    		            
		    				robot.keyPress(NativeKeyEvent.VK_BACK_QUOTE);
		    				robot.keyRelease(NativeKeyEvent.VK_BACK_QUOTE);
		    				
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
		    		case 9:
		    			for (int i = 0; i < 2; i++) {
		    				enchantBolts(i);
		    				if (!runFlag) {return;}
		    			}
		    			break;
		    		case 10:
		    			for (int i = 0; i < 4; i++) {
		    				superheat(i);
		    				if(!runFlag) {return;}
		    			}
		    			break;
		    		case 11:
		    			fishing();
		    			if (!runFlag) {return;}
		    			break;
		    		case 12:
		    			int save = gameDelay;
		    			gameDelay = 2000;
		    			
		    			resetClient();
		    			gameDelay = save;
		    			
		    			robot.keyPress(NativeKeyEvent.VK_BACK_QUOTE);
	    				robot.keyRelease(NativeKeyEvent.VK_BACK_QUOTE);
	    				
		    			return;
		    	}
		    }
		} catch (AWTException e) {}	
	}
	
	private static void chocolatePowder(int i) {
		try {
        	switch(i) {
        		case 0:
        			if (depositFlag) {
	        			clickBanker();
	        			pixelColor = robot.getPixelColor(item1X, item1Y).toString();
			            depositFlag = false;
        			}
		            break;
        		case 1:
        			checkInBank(item1X, item1Y);
        			
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
	       			
		            pressESC();
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
	
	private static void headlessArrows(int i) {
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
		            robot.keyRelease(NativeKeyEvent.VK_SPACE);
		            Thread.sleep(1000);
		            
		            randomMoveCamera(1, 900);
		            
		            Thread.sleep(10000);
		            break;
        	}
		} catch (InterruptedException ex) {}
	}
	
	private static void alchemy(int i) {
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
		            
		            if (cycleCount >= 21) {
		            	randomMoveCamera(1.5, 1000);
		            	
			            cycleCount = 0;
		            } else {
		            	Thread.sleep((long) (Math.random() + 1.5) * 1000);
		            	cycleCount++;
		            }
		            
		            Thread.sleep(1050);
		            break;
        	}
 		} catch (InterruptedException ex) {}
	}
	
	private static void arrowShafts(int i) {
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

	       			pressESC();
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
		            robot.keyRelease(NativeKeyEvent.VK_SPACE);
		            Thread.sleep(50000);
		            break;
        	}
        } catch (InterruptedException ex) {}
	}
	
	private static void cleanHerbs(int i) {
		try {
        	switch(i) {
        		case 0:
        			if (depositFlag) {
	        			clickBanker();
	        			pixelColor = robot.getPixelColor(item1X, item1Y - 50).toString();
			            depositFlag = false;
        			}
		            break;
        		case 1:
        			checkInBank(item1X, item1Y - 50);
        			
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
	       			
		            pressESC();
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
		            robot.keyRelease(NativeKeyEvent.VK_SPACE);
		            
		            if (cycleCount >= 19) {
		            	randomMoveCamera(1.2, 1000);
		            }
		            cycleCount++;
		            
		            Thread.sleep(19000);
		            break;
        		case 5:
        			if (cycleCount >= 20) {
        				resetCamera();
    		            cycleCount = 0;
        			}
        			
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
	
	private static void potions(int i) {
		try {
        	switch(i) {
        		case 0:
        			if (depositFlag) {
	        			clickBanker();
	        			pixelColor = robot.getPixelColor(presetX, presetY).toString();
			            depositFlag = false;
        			}
		            break;
        		case 1:
        			checkInBank(presetX, presetY);
        			
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
		            robot.keyRelease(NativeKeyEvent.VK_SPACE);
		            
		            if (cycleCount >= 13) {
		            	randomMoveCamera(1.5, 1000);
		            }
		            cycleCount++;
		            
		            Thread.sleep(18000);
		            break;
        		case 5:
        			if (cycleCount >= 14) {
        				resetCamera();
    		            cycleCount = 0;
        			}
        			
        			robot.mouseMove(bankerX, bankerY);
        			robot.mousePress(InputEvent.BUTTON1_MASK);
		            robot.mouseRelease(InputEvent.BUTTON1_MASK);
		            Thread.sleep(bankDelay);
		            break;
        	}
        } catch (InterruptedException ex) {}
	}
	
	private static void shieldbows(int i) {
		try {
        	switch(i) {
        		case 0:
        			if (depositFlag) {
	        			clickBanker();
	        			pixelColor = robot.getPixelColor(item1X, item1Y).toString();
			            depositFlag = false;
        			}
		            break;
        		case 1:
        			checkInBank(item1X, item1Y);
        			
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

	       			pressESC();
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
		            robot.keyRelease(NativeKeyEvent.VK_SPACE);
		            
		            if (cycleCount >= 9) {
		            	randomMoveCamera(1.5, 1000);
		            }
		            cycleCount++;
		            
		            Thread.sleep(52000);
		            break;
        		case 5:
        			if (cycleCount >= 10) {
        				resetCamera();
    		            cycleCount = 0;
        			}
        			
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
	
	private static void ivy() {

		// if current ivy is left
		if (ivyPosition == Direction.LEFT) {
			// check if there is ivy
			if (checkNeighbors(ivyX, ivyY, 1, false)) {
	            // click every 15 seconds
	    		try {
	    			if (clickToggle) {
	    				robot.mouseMove(ivyX, ivyY);
	        			robot.mousePress(InputEvent.BUTTON1_MASK);
	    	            robot.mouseRelease(InputEvent.BUTTON1_MASK);
	    	            
	    				clickToggle = false;
	    			} else {
	    				if (!runFlag) {return;}
	    				
	    				Thread.sleep(15000);
	    				clickToggle = true;
	    			}
	    		} catch (InterruptedException ex) {}
			} else {
				// check neighbors for ivy and click if found
				if (!checkNeighbors(ivyX + 200, ivyY, 8, true)) {
					if (!checkNeighbors(ivyX + 200, ivyY, 4, true)) {
						try {
							if (cycleCount < 4) {
								cycleCount++;
								Thread.sleep(4000);
							} else {
								robot.keyPress(NativeKeyEvent.VK_DOWN);
				    			Thread.sleep(1500);
				    			robot.keyRelease(NativeKeyEvent.VK_DOWN);
								
								robot.keyPress(NativeKeyEvent.VK_UP);
				    			Thread.sleep(2500);
				    			robot.keyRelease(NativeKeyEvent.VK_UP);
				    			
								cycleCount = 0;
							}
			    		} catch (InterruptedException ex) {}
						
						return;
					}
				}	
	            
				// wait for player to move	
	            try {
	    			Thread.sleep(2000);
	    		} catch (InterruptedException ex) {}
	            
	            // update direction
	            ivyPosition = Direction.RIGHT;
	            
	            try {
	    			Thread.sleep(5000);
	    		} catch (InterruptedException ex) {}
			}
		} else {
			// same as above
			if (checkNeighbors(ivyX, ivyY, 1, false)) {
	            try {
	            	if (clickToggle) {
	    				robot.mouseMove(ivyX, ivyY);
	        			robot.mousePress(InputEvent.BUTTON1_MASK);
	    	            robot.mouseRelease(InputEvent.BUTTON1_MASK);
	    	            
	    				clickToggle = false;
	    			} else {
	    				if (!runFlag) {return;}
	    				
	    				Thread.sleep(15000);
	    				clickToggle = true;
	    			}
	    		} catch (InterruptedException ex) {}
			} else {
				if (!checkNeighbors(ivyX - 100, ivyY, 2, true)) {
					if (!checkNeighbors(ivyX - 100, ivyY, 1, true)) {
						try {
							if (cycleCount < 4) {
								cycleCount++;
								Thread.sleep(4000);
							} else {
								robot.keyPress(NativeKeyEvent.VK_DOWN);
				    			Thread.sleep(1500);
				    			robot.keyRelease(NativeKeyEvent.VK_DOWN);
								
								robot.keyPress(NativeKeyEvent.VK_UP);
				    			Thread.sleep(2500);
				    			robot.keyRelease(NativeKeyEvent.VK_UP);
				    			
								cycleCount = 0;
							}
			    		} catch (InterruptedException ex) {}
						
						return;
					}
				}	
				
	            try {
	    			Thread.sleep(2000);
	    		} catch (InterruptedException ex) {}
	            
	            ivyPosition = Direction.LEFT;
	            
	            try {
	    			Thread.sleep(5000);
	    		} catch (InterruptedException ex) {}
			}
		}
	}
	
	private static void enchantBolts(int i) {
		try {
        	switch(i) {
        		case 0:
        			robot.mouseMove(item1X, item1Y);
        			robot.mousePress(InputEvent.BUTTON1_MASK);
		            robot.mouseRelease(InputEvent.BUTTON1_MASK);
		            Thread.sleep(spaceDelay);
		            break;
        		case 1:
        			robot.keyPress(NativeKeyEvent.VK_SPACE);
		            robot.keyRelease(NativeKeyEvent.VK_SPACE);
		            
		            if (cycleCount >= 2) {
		            	randomMoveCamera(1.5, 1000);
		            	
			            cycleCount = 0;
		            } else {
		            	Thread.sleep((long) (Math.random() + 1.5) * 1000);
		            	cycleCount++;
		            }
		            
		            Thread.sleep(20500);
		            break;
        	}
 		} catch (InterruptedException ex) {}
	}
	
	private static void superheat(int i) {
		try {
        	switch(i) {
        		case 0:
        			if (depositFlag) {
	        			clickBanker();
	        			pixelColor = robot.getPixelColor(presetX, presetY).toString();
			            depositFlag = false;
        			}
		            break;
        		case 1:
        			checkInBank(presetX, presetY);
        			
        			robot.mouseMove(presetX, presetY);
        			robot.mousePress(InputEvent.BUTTON1_MASK);
		            robot.mouseRelease(InputEvent.BUTTON1_MASK);
		            Thread.sleep(1500);
		            break;
        		case 2:
		            if (cycleCount >= 13) {
		            	randomMoveCamera(1.2, 1000);
		            }
		            cycleCount++;
		            
        			while (count < 9) {
        				if (!runFlag) {return;}
        				
	        			robot.mouseMove(item1X, item1Y);
	        			robot.mousePress(InputEvent.BUTTON1_MASK);
			            robot.mouseRelease(InputEvent.BUTTON1_MASK);
			            Thread.sleep(500);
			            
			            robot.mousePress(InputEvent.BUTTON1_MASK);
			            robot.mouseRelease(InputEvent.BUTTON1_MASK);
			            Thread.sleep(2000);
			            
			            count++;
        			}
		            
        			count = 0; 
		            break;
        		case 3:
        			if (cycleCount >= 14) {
        				resetCamera();
    		            cycleCount = 0;
        			}
        			
        			robot.mouseMove(bankerX, bankerY);
        			robot.mousePress(InputEvent.BUTTON1_MASK);
		            robot.mouseRelease(InputEvent.BUTTON1_MASK);
		            Thread.sleep(bankDelay);
		            break;
        	}
        } catch (InterruptedException ex) {}
	}
	
	private static void fishing() {
		int searchOffsetY = 0;
		
		try {
			if (checkOptionBox(item1X, item1Y, optionBoxX, optionBoxY)) {
				if (clickToggle) {
    				robot.mouseMove(item1X, item1Y);
        			robot.mousePress(InputEvent.BUTTON1_MASK);
    	            robot.mouseRelease(InputEvent.BUTTON1_MASK);
    	            Thread.sleep(500);
    	            
    				clickToggle = false;
    			} else {
    				randomMoveCamera(1, 1000);
    				Thread.sleep(500);
		            resetCamera();
    				clickToggle = true;
    			}
				
				if (checkOptionBox(item2X, item2Y, 0, 30)) {
					dropFish(10);
					clickToggle = true;
					
					return;
				} else {
					if (clickToggle) {
						dropFish(3);
					} 
				}
				
				if (!runFlag) {return;}
			} else {				
				if (count > 4) {
					robot.keyPress(NativeKeyEvent.VK_DOWN);
					Thread.sleep(1000);
			        robot.keyRelease(NativeKeyEvent.VK_DOWN);
			        
			        resetCamera();
			        count = 0;
				}
				
				while (searchOffsetY < 160) {
					searchOffsetY += 40;
					
					if (checkNeighbors(item1X, item1Y + searchOffsetY, 5, true) 
							|| checkNeighbors(item1X, item1Y - searchOffsetY, 5, true)) {
						clickToggle = true;
						count = 0;
						
						resetCamera();
						Thread.sleep(2000);
						return;
					}
					
					if (!runFlag) {return;}
				}
				count++;
			}
			Thread.sleep(15000);
		} catch (InterruptedException ie) {}
	}
	
	private static void dropFish (int dropCount) {
		try {
			Thread.sleep(500);
			robot.keyPress(NativeKeyEvent.VK_SPACE);
	        robot.keyRelease(NativeKeyEvent.VK_SPACE);
	        Thread.sleep(500);
	        
			for (int i = 0; i < dropCount; i++) {		        
				robot.keyPress(NativeKeyEvent.VK_1);
				Thread.sleep(100);
		        robot.keyRelease(NativeKeyEvent.VK_1);
		        robot.keyPress(NativeKeyEvent.VK_2);
		        Thread.sleep(100);
		        robot.keyRelease(NativeKeyEvent.VK_2);
		        robot.keyPress(NativeKeyEvent.VK_3);
		        Thread.sleep(100);
		        robot.keyRelease(NativeKeyEvent.VK_3);
		        Thread.sleep(100);
			}
		} catch (InterruptedException ie) {}
	}
	
	private static void clickBanker() {
		try {
			robot.mouseMove(bankerX, bankerY);
			Thread.sleep(500);
			
			robot.mousePress(InputEvent.BUTTON1_MASK);
	        robot.mouseRelease(InputEvent.BUTTON1_MASK);
	        Thread.sleep(bankDelay);
		} catch (InterruptedException ie) {}
	}
	
	private static void pressESC() {
		try {
			robot.keyPress(NativeKeyEvent.VK_ESCAPE);
	        Thread.sleep(300);
	        robot.keyRelease(NativeKeyEvent.VK_ESCAPE);
	        Thread.sleep(1500);
		} catch (InterruptedException ie) {}
	}
	
	private static void resetCamera() {
		try {
			robot.mouseMove(cameraX, cameraY);
	        Thread.sleep(200);
			robot.mousePress(InputEvent.BUTTON1_MASK);
	        robot.mouseRelease(InputEvent.BUTTON1_MASK);
	        Thread.sleep(cameraDelay);
		} catch (InterruptedException ie) {}
	}
	
	private static void randomMoveCamera(double timeOffset, int timeMultiplier) {
		moveIndex = rand.nextInt(4);
        robot.keyPress(move[moveIndex]);
        
        try {Thread.sleep((long) (Math.random() + timeOffset) * timeMultiplier);}
        catch (InterruptedException ie) {}
        
        robot.keyRelease(move[moveIndex]);
	}
	
	private static void checkInBank(int x, int y) {
		if (!robot.getPixelColor(x, y).toString().equals(pixelColor)) {
			try {Thread.sleep(2000);}
			catch (InterruptedException ie) {}
			
			pressESC();
			resetCamera();
			if (bankDelay < 2500) {bankDelay += 200;}
			clickBanker();
		}
		
		if (!robot.getPixelColor(x, y).toString().equals(pixelColor)) {
			robot.keyPress(NativeKeyEvent.VK_BACK_QUOTE);
			robot.keyRelease(NativeKeyEvent.VK_BACK_QUOTE);
		}
	}
	
	private static boolean checkNeighbors(int centerX, int centerY, int offset, boolean click) {
		double circleOffset = offset * CIRCLE_CONST;
		
		int xCoords[] = {centerX, centerX + 2,  centerX, centerX - 2, centerX,
				(int)(centerX + circleOffset), (int)(centerX + circleOffset), (int)(centerX - circleOffset), (int)(centerX - circleOffset)};
		
		int yCoords[] = {centerY, centerY, centerY - 2, centerY, centerY + 2,
				(int)(centerY + circleOffset), (int)(centerY - circleOffset), (int)(centerY - circleOffset), (int)(centerY + circleOffset)};
		
		for (int i = 0; i < xCoords.length; i++) {
			if (checkOptionBox(xCoords[i], yCoords[i], optionBoxX, optionBoxY)) {
				if (click) {
					robot.mousePress(InputEvent.BUTTON1_MASK);
		            robot.mouseRelease(InputEvent.BUTTON1_MASK);
				}
	            return true;
			}
		}
		
		return false;
	}
	
	private static boolean checkOptionBox(int coordX, int coordY, int offsetX, int offsetY) {
		robot.mouseMove(coordX, coordY);
		
		try {Thread.sleep(500);}
		catch (InterruptedException ie) {}
		
		Color temp = robot.getPixelColor(coordX + offsetX, coordY + offsetY);
		
		if (temp.getRed() > 2 || temp.getBlue() > 2 || temp.getBlue() > 2) {return false;}
		else {return true;}
	}
	
	private static void resetClient() {
		try {
			for (int i = 0; i < 7; i++) {
				if (!runFlag) {return;}
				
	        	switch(i) {
	        		case 1:
	        			robot.mouseMove(closeWindowX, closeWindowY);
	        			robot.mousePress(InputEvent.BUTTON1_MASK);
			            robot.mouseRelease(InputEvent.BUTTON1_MASK);
			            Thread.sleep(2000);
			            break;
	        		case 2:
	        			robot.keyPress(NativeKeyEvent.VK_WINDOWS);
			            robot.keyRelease(NativeKeyEvent.VK_WINDOWS);
			           
			            Thread.sleep(windowsDelay);
			            break;
	        		case 3:
	        			typeString("runescape");
	        			Thread.sleep(2000);
	        			
	        			robot.keyPress(NativeKeyEvent.VK_ENTER);
	        			robot.keyRelease(NativeKeyEvent.VK_ENTER);
	        			
	        			Thread.sleep(5000);
	        			break;
	        		case 4:
	        			robot.keyPress(NativeKeyEvent.VK_WINDOWS);
	        			robot.keyPress(NativeKeyEvent.VK_RIGHT);
	        			robot.keyRelease(NativeKeyEvent.VK_RIGHT);
	        			robot.keyRelease(NativeKeyEvent.VK_WINDOWS);
	        			
	        			Thread.sleep(loadDelay);
	        			break;
	        		case 5:
	        			typeString(password);
	        			Thread.sleep(1000);
	        			
	        			robot.keyPress(NativeKeyEvent.VK_ENTER);
	        			robot.keyRelease(NativeKeyEvent.VK_ENTER);
	        			
	        			Thread.sleep(loginDelay);
	        			break;
	        		case 6:
	        			robot.mouseMove(startGameX, startGameY);
	        			robot.mousePress(InputEvent.BUTTON1_MASK);
			            robot.mouseRelease(InputEvent.BUTTON1_MASK);
			            
			            Thread.sleep(gameDelay);
			            break;
	        	}
			}
 		} catch (InterruptedException ex) {}
	}
	
	private static void typeString(String str) {		
		for (int i = 0; i < str.length(); i++) {
			typeChar(str.charAt(i));
		}
	}
	
	private static void typeChar(char character) {
		switch (character) {
	        case 'a': pressKey(NativeKeyEvent.VK_A, false); break;
	        case 'b': pressKey(NativeKeyEvent.VK_B, false); break;
	        case 'c': pressKey(NativeKeyEvent.VK_C, false); break;
	        case 'd': pressKey(NativeKeyEvent.VK_D, false); break;
	        case 'e': pressKey(NativeKeyEvent.VK_E, false); break;
	        case 'f': pressKey(NativeKeyEvent.VK_F, false); break;
	        case 'g': pressKey(NativeKeyEvent.VK_G, false); break;
	        case 'h': pressKey(NativeKeyEvent.VK_H, false); break;
	        case 'i': pressKey(NativeKeyEvent.VK_I, false); break;
	        case 'j': pressKey(NativeKeyEvent.VK_J, false); break;
	        case 'k': pressKey(NativeKeyEvent.VK_K, false); break;
	        case 'l': pressKey(NativeKeyEvent.VK_L, false); break;
	        case 'm': pressKey(NativeKeyEvent.VK_M, false); break;
	        case 'n': pressKey(NativeKeyEvent.VK_N, false); break;
	        case 'o': pressKey(NativeKeyEvent.VK_O, false); break;
	        case 'p': pressKey(NativeKeyEvent.VK_P, false); break;
	        case 'q': pressKey(NativeKeyEvent.VK_Q, false); break;
	        case 'r': pressKey(NativeKeyEvent.VK_R, false); break;
	        case 's': pressKey(NativeKeyEvent.VK_S, false); break;
	        case 't': pressKey(NativeKeyEvent.VK_T, false); break;
	        case 'u': pressKey(NativeKeyEvent.VK_U, false); break;
	        case 'v': pressKey(NativeKeyEvent.VK_V, false); break;
	        case 'w': pressKey(NativeKeyEvent.VK_W, false); break;
	        case 'x': pressKey(NativeKeyEvent.VK_X, false); break;
	        case 'y': pressKey(NativeKeyEvent.VK_Y, false); break;
	        case 'z': pressKey(NativeKeyEvent.VK_Z, false); break;
	        case 'A': pressKey(NativeKeyEvent.VK_A, true); break;
	        case 'B': pressKey(NativeKeyEvent.VK_B, true); break;
	        case 'C': pressKey(NativeKeyEvent.VK_C, true); break;
	        case 'D': pressKey(NativeKeyEvent.VK_D, true); break;
	        case 'E': pressKey(NativeKeyEvent.VK_E, true); break;
	        case 'F': pressKey(NativeKeyEvent.VK_F, true); break;
	        case 'G': pressKey(NativeKeyEvent.VK_G, true); break;
	        case 'H': pressKey(NativeKeyEvent.VK_H, true); break;
	        case 'I': pressKey(NativeKeyEvent.VK_I, true); break;
	        case 'J': pressKey(NativeKeyEvent.VK_J, true); break;
	        case 'K': pressKey(NativeKeyEvent.VK_K, true); break;
	        case 'L': pressKey(NativeKeyEvent.VK_L, true); break;
	        case 'M': pressKey(NativeKeyEvent.VK_M, true); break;
	        case 'N': pressKey(NativeKeyEvent.VK_N, true); break;
	        case 'O': pressKey(NativeKeyEvent.VK_O, true); break;
	        case 'P': pressKey(NativeKeyEvent.VK_P, true); break;
	        case 'Q': pressKey(NativeKeyEvent.VK_Q, true); break;
	        case 'R': pressKey(NativeKeyEvent.VK_R, true); break;
	        case 'S': pressKey(NativeKeyEvent.VK_S, true); break;
	        case 'T': pressKey(NativeKeyEvent.VK_T, true); break;
	        case 'U': pressKey(NativeKeyEvent.VK_U, true); break;
	        case 'V': pressKey(NativeKeyEvent.VK_V, true); break;
	        case 'W': pressKey(NativeKeyEvent.VK_W, true); break;
	        case 'X': pressKey(NativeKeyEvent.VK_X, true); break;
	        case 'Y': pressKey(NativeKeyEvent.VK_Y, true); break;
	        case 'Z': pressKey(NativeKeyEvent.VK_Z, true); break;
	        case '`': pressKey(NativeKeyEvent.VK_BACK_QUOTE, false); break;
	        case '0': pressKey(NativeKeyEvent.VK_0, false); break;
	        case '1': pressKey(NativeKeyEvent.VK_1, false); break;
	        case '2': pressKey(NativeKeyEvent.VK_2, false); break;
	        case '3': pressKey(NativeKeyEvent.VK_3, false); break;
	        case '4': pressKey(NativeKeyEvent.VK_4, false); break;
	        case '5': pressKey(NativeKeyEvent.VK_5, false); break;
	        case '6': pressKey(NativeKeyEvent.VK_6, false); break;
	        case '7': pressKey(NativeKeyEvent.VK_7, false); break;
	        case '8': pressKey(NativeKeyEvent.VK_8, false); break;
	        case '9': pressKey(NativeKeyEvent.VK_9, false); break;
	        case '-': pressKey(NativeKeyEvent.VK_MINUS, false); break;
	        case '=': pressKey(NativeKeyEvent.VK_EQUALS, false); break;
	        case '~': pressKey(NativeKeyEvent.VK_BACK_QUOTE, true); break;
	        case '!': pressKey(NativeKeyEvent.VK_EXCLAMATION_MARK, false); break;
	        case '@': pressKey(NativeKeyEvent.VK_AT, false); break;
	        case '#': pressKey(NativeKeyEvent.VK_NUMBER_SIGN, false); break;
	        case '$': pressKey(NativeKeyEvent.VK_DOLLAR, false); break;
	        case '%': pressKey(NativeKeyEvent.VK_5, true); break;
	        case '^': pressKey(NativeKeyEvent.VK_CIRCUMFLEX, false); break;
	        case '&': pressKey(NativeKeyEvent.VK_AMPERSAND, false); break;
	        case '*': pressKey(NativeKeyEvent.VK_ASTERISK, false); break;
	        case '(': pressKey(NativeKeyEvent.VK_LEFT_PARENTHESIS, false); break;
	        case ')': pressKey(NativeKeyEvent.VK_RIGHT_PARENTHESIS, false); break;
	        case '_': pressKey(NativeKeyEvent.VK_UNDERSCORE, false); break;
	        case '+': pressKey(NativeKeyEvent.VK_PLUS, false); break;
	        case '\t': pressKey(NativeKeyEvent.VK_TAB, false); break;
	        case '\n': pressKey(NativeKeyEvent.VK_ENTER, false); break;
	        case '[': pressKey(NativeKeyEvent.VK_OPEN_BRACKET, false); break;
	        case ']': pressKey(NativeKeyEvent.VK_CLOSE_BRACKET, false); break;
	        case '\\': pressKey(NativeKeyEvent.VK_BACK_SLASH, false); break;
	        case '{': pressKey(NativeKeyEvent.VK_OPEN_BRACKET, true); break;
	        case '}': pressKey(NativeKeyEvent.VK_CLOSE_BRACKET, true); break;
	        case '|': pressKey(NativeKeyEvent.VK_BACK_SLASH, true); break;
	        case ';': pressKey(NativeKeyEvent.VK_SEMICOLON, false); break;
	        case ':': pressKey(NativeKeyEvent.VK_COLON, false); break;
	        case '\'': pressKey(NativeKeyEvent.VK_QUOTE, false); break;
	        case '"': pressKey(NativeKeyEvent.VK_QUOTEDBL, false); break;
	        case ',': pressKey(NativeKeyEvent.VK_COMMA, false); break;
	        case '<': pressKey(NativeKeyEvent.VK_LESS, false); break;
	        case '.': pressKey(NativeKeyEvent.VK_PERIOD, false); break;
	        case '>': pressKey(NativeKeyEvent.VK_GREATER, false); break;
	        case '/': pressKey(NativeKeyEvent.VK_SLASH, false); break;
	        case '?': pressKey(NativeKeyEvent.VK_SLASH, true); break;
	        case ' ': pressKey(NativeKeyEvent.VK_SPACE, false); break;
		}
	}
	
	private static void pressKey(int code, boolean shift) {
		if (shift) {robot.keyPress(NativeKeyEvent.VK_SHIFT);}
		
		robot.keyPress(code);
		robot.keyRelease(code);
		
		if (shift) {robot.keyRelease(NativeKeyEvent.VK_SHIFT);}
	}
	
	public static void nativeKeyPressedHandler(NativeKeyEvent e) {
        System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));

        if (e.getKeyCode() == NativeKeyEvent.VK_BACK_QUOTE){
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
		} else if (setCamera) {
			cameraX = e.getX();
			cameraY = e.getY();
			setCamera = false;
			AutoClickerGUI.resetButton(SetButton.CAMERA);
		}
         
         System.out.println("X: " + e.getX() + " Y: " + e.getY());
	 }
}