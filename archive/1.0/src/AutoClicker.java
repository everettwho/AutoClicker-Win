import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.*;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.mouse.NativeMouseEvent;

public class AutoClicker extends AutoClickerGUI implements Runnable{	
	// Pick Options
	// 0 - Chocolate Powder
	// 1 - Headless Arrows
	// 2 - Alchemy
	// 3 - Arrow Shafts
	public static int pick = 0;
	
	public static int bankDelay = 1500;
	public static int spaceDelay = 1500;
	
	public static int wait_flag = 0;
	public static boolean run_flag = false;
	
	public  static int idx = 0;
	public static int move_idx = 0;
	public static int deposit_flag = 0;
	
	private static int[] posX = new int[10];
	private static int[] posY = new int[10];
	private static int[] move = {NativeKeyEvent.VK_LEFT, NativeKeyEvent.VK_RIGHT, NativeKeyEvent.VK_UP, NativeKeyEvent.VK_DOWN};
	
	public void run() {
		try {
		    Robot robot = new Robot();
		   
		    while (true) {
		    	if (pick == 0) {
			    	for (int i = 0; i < 9; i++) {
				        try {
				        	switch(i) {
				        		case 0:
				        			if (deposit_flag == 0) {
					        			robot.mouseMove(posX[0], posY[0]);
					        			robot.mousePress(InputEvent.BUTTON1_MASK);
							            robot.mouseRelease(InputEvent.BUTTON1_MASK);
							            Thread.sleep(bankDelay);
							            deposit_flag++;
				        			}
						            break;
				        		case 1:
				        			robot.mouseMove(posX[1], posY[1]);
				        			robot.mousePress(InputEvent.BUTTON3_MASK);
						            robot.mouseRelease(InputEvent.BUTTON3_MASK);
						            Thread.sleep(500);
						            break;
				        		case 2:
				        			robot.mouseMove(posX[1], posY[1] + 110);
				        			robot.mousePress(InputEvent.BUTTON1_MASK);
						            robot.mouseRelease(InputEvent.BUTTON1_MASK);
					       			Thread.sleep(1000);
						            robot.keyPress(NativeKeyEvent.VK_ESCAPE);
						            Thread.sleep(300);
						            robot.keyRelease(NativeKeyEvent.VK_ESCAPE);
						            Thread.sleep(1500);
						            break;
				        		case 3:
				        			robot.mouseMove(posX[3], posY[3]);
				        			Thread.sleep(500);
				        			robot.mousePress(InputEvent.BUTTON3_MASK);
						            robot.mouseRelease(InputEvent.BUTTON3_MASK);
						            Thread.sleep(500);
						            break;
				        		case 4:
				        			robot.mouseMove(posX[3], posY[3] + 55);
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
				        			robot.mouseMove(posX[0], posY[0]);
				        			robot.mousePress(InputEvent.BUTTON1_MASK);
						            robot.mouseRelease(InputEvent.BUTTON1_MASK);
						            Thread.sleep(bankDelay);
						            break;
				        		case 7:
				        			robot.mouseMove(posX[2], posY[2]);
				        			robot.mousePress(InputEvent.BUTTON3_MASK);
						            robot.mouseRelease(InputEvent.BUTTON3_MASK);
						            Thread.sleep(800);
						            break;
				        		case 8:
				        			robot.mouseMove(posX[2], posY[2] + 110);
				        			robot.mousePress(InputEvent.BUTTON1_MASK);
						            robot.mouseRelease(InputEvent.BUTTON1_MASK);
						            Thread.sleep(1000);
						            break;
				        	}
			            } catch (InterruptedException ex) {}
				        
				        if (!run_flag) {return;}
			    	}
		    	} else if (pick == 1) {
		    		for (int i = 0; i < 3; i++) {
				        try {
				        	switch(i) {
				        		case 0:
				        			robot.mouseMove(posX[0], posY[0] - 35);
				        			robot.mousePress(InputEvent.BUTTON1_MASK);
						            robot.mouseRelease(InputEvent.BUTTON1_MASK);
						            Thread.sleep(700);
						            break;
				        		case 1:
				        			robot.mouseMove(posX[0] - 45, posY[0] - 35);
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
				        
				        if (!run_flag) {return;}
		    		} 
		    	} else if (pick == 2) {
		    		for (int i = 0; i < 2; i++) {
				        try {
				        	switch(i) {
				        		case 0:
				        			robot.mouseMove(posX[0], posY[0]);
				        			robot.mousePress(InputEvent.BUTTON1_MASK);
						            robot.mouseRelease(InputEvent.BUTTON1_MASK);
						            Thread.sleep(500);
						            break;
				        		case 1:
				        			robot.mouseMove(posX[0], posY[0]);
				        			robot.mousePress(InputEvent.BUTTON1_MASK);
						            robot.mouseRelease(InputEvent.BUTTON1_MASK);
						            Thread.sleep(2000);
						            break;
				        	}
		        		} catch (InterruptedException ex) {}
				        
				        if (!run_flag) {return;}
		    		}
	    		} else if (pick == 3) {
	    			for (int i = 0; i < 5; i++) {
	    				 try {
				        	switch(i) {
				        		case 0:
				        			robot.mouseMove(posX[0], posY[0]);
				        			robot.mousePress(InputEvent.BUTTON1_MASK);
						            robot.mouseRelease(InputEvent.BUTTON1_MASK);
						            Thread.sleep(bankDelay);
						            break;
				        		case 1:
				        			robot.mouseMove(posX[1], posY[1]);
				        			robot.mousePress(InputEvent.BUTTON3_MASK);
						            robot.mouseRelease(InputEvent.BUTTON3_MASK);
						            Thread.sleep(500);
						            break;
				        		case 2:
				        			robot.mouseMove(posX[1], posY[1] + 110);
				        			robot.mousePress(InputEvent.BUTTON1_MASK);
						            robot.mouseRelease(InputEvent.BUTTON1_MASK);
					       			Thread.sleep(1000);
						            robot.keyPress(NativeKeyEvent.VK_ESCAPE);
						            Thread.sleep(300);
						            robot.keyRelease(NativeKeyEvent.VK_ESCAPE);
						            Thread.sleep(1500);
						            break;
				        		case 3:
				        			robot.mouseMove(posX[2], posY[2]);
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

	    				 if (!run_flag) {return;}
	    			}
	    		} else if (pick == 4) {
			    	for (int i = 0; i < 8; i++) {
				        try {
				        	switch(i) {
				        		case 0:
				        			if (deposit_flag == 0) {
					        			robot.mouseMove(posX[0], posY[0]);
					        			robot.mousePress(InputEvent.BUTTON1_MASK);
							            robot.mouseRelease(InputEvent.BUTTON1_MASK);
							            Thread.sleep(bankDelay);
							            deposit_flag++;
				        			}
						            break;
				        		case 1:
				        			robot.mouseMove(posX[1], posY[1]);
				        			robot.mousePress(InputEvent.BUTTON3_MASK);
						            robot.mouseRelease(InputEvent.BUTTON3_MASK);
						            Thread.sleep(500);
						            break;
				        		case 2:
				        			robot.mouseMove(posX[1], posY[1] + 110);
				        			robot.mousePress(InputEvent.BUTTON1_MASK);
						            robot.mouseRelease(InputEvent.BUTTON1_MASK);
					       			Thread.sleep(1000);
						            robot.keyPress(NativeKeyEvent.VK_ESCAPE);
						            Thread.sleep(300);
						            robot.keyRelease(NativeKeyEvent.VK_ESCAPE);
						            Thread.sleep(1500);
						            break;
				        		case 3:
				        			robot.mouseMove(posX[3], posY[3]);
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
				        			robot.mouseMove(posX[0], posY[0]);
				        			robot.mousePress(InputEvent.BUTTON1_MASK);
						            robot.mouseRelease(InputEvent.BUTTON1_MASK);
						            Thread.sleep(bankDelay);
						            break;
				        		case 6:
				        			robot.mouseMove(posX[2], posY[2]);
				        			robot.mousePress(InputEvent.BUTTON3_MASK);
						            robot.mouseRelease(InputEvent.BUTTON3_MASK);
						            Thread.sleep(800);
						            break;
				        		case 7:
				        			robot.mouseMove(posX[2], posY[2] + 110);
				        			robot.mousePress(InputEvent.BUTTON1_MASK);
						            robot.mouseRelease(InputEvent.BUTTON1_MASK);
						            Thread.sleep(1000);
						            break;
				        	}
			            } catch (InterruptedException ex) {}
				        
				        if (!run_flag) {return;}
			    	}
		    	} else if (pick == 5) {
			    	for (int i = 0; i < 6; i++) {
				        try {
				        	switch(i) {
				        		case 0:
				        			if (deposit_flag == 0) {
					        			robot.mouseMove(posX[0], posY[0]);
					        			robot.mousePress(InputEvent.BUTTON1_MASK);
							            robot.mouseRelease(InputEvent.BUTTON1_MASK);
							            Thread.sleep(bankDelay);
							            deposit_flag++;
				        			}
						            break;
				        		case 1:
				        			robot.mouseMove(posX[1], posY[1]);
				        			robot.mousePress(InputEvent.BUTTON1_MASK);
						            robot.mouseRelease(InputEvent.BUTTON1_MASK);
						            Thread.sleep(1500);
						            break;
				        		case 2:
				        			robot.mouseMove(posX[2], posY[2]);
				        			Thread.sleep(500);
				        			robot.mousePress(InputEvent.BUTTON1_MASK);
						            robot.mouseRelease(InputEvent.BUTTON1_MASK);
						            Thread.sleep(700);
						            break;
				        		case 3:
				        			robot.mouseMove(posX[2] + 45, posY[2]);
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
				        			robot.mouseMove(posX[0], posY[0]);
				        			robot.mousePress(InputEvent.BUTTON1_MASK);
						            robot.mouseRelease(InputEvent.BUTTON1_MASK);
						            Thread.sleep(bankDelay);
						            break;
				        	}
			            } catch (InterruptedException ex) {}
				        
				        if (!run_flag) {return;}
			    	}
		    	}
		    }
		} catch (AWTException e) {}	
	}
	
	public static void nativeKeyPressedHandler(NativeKeyEvent e) {
        System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));

        if (e.getKeyCode() == NativeKeyEvent.VK_Q) {
            System.exit(0);
        }
        
        if (e.getKeyCode() == NativeKeyEvent.VK_R){
        	AutoClickerGUI.reset();
        }
	}
	
	public static void nativeKeyReleasedHandler(NativeKeyEvent e) {
        System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
	}
	
	public static void nativeMouseClickedHandler(NativeMouseEvent e) {
		if (wait_flag == 1) {
	         posX[idx] = e.getX();
	         posY[idx] = e.getY();
	         
	         if (idx < 9) {
	        	 idx++;
	         }
		}
         
         System.out.println("X: " + e.getX() + " Y: " + e.getY());
	 }
}