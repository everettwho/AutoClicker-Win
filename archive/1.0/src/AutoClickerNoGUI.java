import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.*;
import java.util.concurrent.atomic.AtomicInteger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;

public class AutoClickerNoGUI extends AutoClickerGUI implements NativeKeyListener, NativeMouseInputListener{	
	// Pick Options
	// 0 - Chocolate Powder
	// 1 - Headless Arrows
	// 2 - Alchemy
	// 3 - Arrow Shafts
	public static int pick = 0;
	
	public static int idx = 0;
	public static int move_idx = 0;
	public static int deposit_flag = 0;
	
	public static int buttonX = 1550;
	public static int buttonY = 750;
	
	public static int[] posX = new int[10];
	public static int[] posY = new int[10];
	public static int[] move = {NativeKeyEvent.VK_LEFT, NativeKeyEvent.VK_RIGHT, NativeKeyEvent.VK_UP, NativeKeyEvent.VK_DOWN};

	public static AtomicInteger wait = new AtomicInteger(0);
	
	public static void main (String[] args) {
		try {
            GlobalScreen.registerNativeHook();
	    }
	    catch (NativeHookException ex) {
	            System.err.println("There was a problem registering the native hook.");
	            System.err.println(ex.getMessage());
	
	            System.exit(1);
	    }
		
	    // construct the example object and initialize native hooks
	    GlobalScreen.getInstance().addNativeKeyListener(new AutoClicker());
	    GlobalScreen.getInstance().addNativeMouseListener(new AutoClicker());
        GlobalScreen.getInstance().addNativeMouseMotionListener(new AutoClicker());
        
		try {
		    Robot robot = new Robot();
		    
		    if (pick == 0) {
	    		System.out.println("Banker");
		        System.out.println("Chocolate");
		        System.out.println("Deposit");
		        System.out.println("Inventory");
		    } else if (pick == 1) {
	    		System.out.println("Below Shafts");
		    } else if (pick == 2) {
	    		System.out.println("Alchemy Skill");
		    } else if (pick == 3) {
	    		System.out.println("Banker");
	    		System.out.println("Logs");
	    		System.out.println("Inventory");
		    }
		    
		    System.out.println("Press C to continue...");
		    
		    while(true) {
		    	if (wait.get() == 1) {break;}
		    }

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
							            Thread.sleep(1000);
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
						            Thread.sleep(1000);
				        			break;
				        		case 5:
				        			robot.mouseMove(buttonX, buttonY);
				        			robot.mousePress(InputEvent.BUTTON1_MASK);
						            robot.mouseRelease(InputEvent.BUTTON1_MASK);
						            Thread.sleep(19000);
						            break;
				        		case 6:
				        			robot.mouseMove(posX[0], posY[0]);
				        			robot.mousePress(InputEvent.BUTTON1_MASK);
						            robot.mouseRelease(InputEvent.BUTTON1_MASK);
						            Thread.sleep(1200);
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
						            Thread.sleep(1000);
						            break;
				        		case 2:
				        			robot.mouseMove(buttonX, buttonY);
				        			robot.mousePress(InputEvent.BUTTON1_MASK);
						            robot.mouseRelease(InputEvent.BUTTON1_MASK);
						            
						            if (move_idx >= 4) {
						            	move_idx = 0;
						            }
						            robot.keyPress(move[move_idx]);
						            Thread.sleep(1000);
						            robot.keyRelease(move[move_idx]);
						            move_idx++;
						            Thread.sleep(11000);
						            break;
				        	}
		        		} catch (InterruptedException ex) {}
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
		    		}
	    		} else if (pick == 3) {
	    			for (int i = 0; i < 5; i++) {
	    				 try {
					        	switch(i) {
					        		case 0:
					        			robot.mouseMove(posX[0], posY[0]);
					        			robot.mousePress(InputEvent.BUTTON1_MASK);
							            robot.mouseRelease(InputEvent.BUTTON1_MASK);
							            Thread.sleep(1000);
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
							            Thread.sleep(1000);
							            break;
					        		case 4:
					        			robot.mouseMove(buttonX, buttonY);
					        			robot.mousePress(InputEvent.BUTTON1_MASK);
							            robot.mouseRelease(InputEvent.BUTTON1_MASK);
							            Thread.sleep(50000);
							            break;
					        	}
				            } catch (InterruptedException ex) {}
	    			}
	    		}
		    }
		} catch (AWTException e) {}	
	}
	
	public void nativeKeyPressed(NativeKeyEvent e) {
        System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));

        if (e.getKeyCode() == NativeKeyEvent.VK_Q) {
            System.exit(0);
        } else if (e.getKeyCode() == NativeKeyEvent.VK_C) {
            wait.incrementAndGet();
        }
	}
	
	public void nativeKeyReleased(NativeKeyEvent e) {
        System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
	}
	
	 public void nativeMouseClicked(NativeMouseEvent e) {
         posX[idx] = e.getX();
         posY[idx] = e.getY();
         
         System.out.println("X: " + e.getX() + " Y: " + e.getY());
         
         if (idx < 9) {
        	 idx++;
         }
	 }
	
	 public void nativeKeyTyped(NativeKeyEvent e) {}	
	 public void nativeMousePressed(NativeMouseEvent e) {}
	 public void nativeMouseReleased(NativeMouseEvent e) {}
	 public void nativeMouseMoved(NativeMouseEvent e) {}
	 public void nativeMouseDragged(NativeMouseEvent e) {}
}