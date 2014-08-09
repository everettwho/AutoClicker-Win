import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.awt.image.BufferedImage;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;

//import java.util.Random; 

public class AutoClicker implements NativeKeyListener, NativeMouseInputListener{	
	
	public static int pick =3;
	public static int idx = 0;
	
	public static int exp =0;
	public static int failure=0;
	public static int knockout=0;
	public static long elapsedTime=0;
	public static int idxSave=0;

	public static int[] posX = new int[20];
	public static int[] posY = new int[20];
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
		    System.out.println("Press C to continue...");
		    while(true) {
		    	if (wait.get() == 1) {break;}
		    }
            /*************MAIN***************/   
		    while (true) {
		    	Thread.sleep(500);
		    	//firemaking();
		    	//mixPot(730,2);
		    	//mixPot(650,1);
		    	jewels();
		    	
		    	cooking(5500);
		    	//botExit();
		    	reset();
		    	leftMouse(1420,66,200,2000);
		    	leftMouse(1592,56,200,1000);
		    	leftMouse(1592,56,200,1000);
		    	key(NativeKeyEvent.VK_UP);
		    	key(NativeKeyEvent.VK_UP);
		    	key(NativeKeyEvent.VK_UP);
		    	key(NativeKeyEvent.VK_UP);
		    	//jewels();
                //coshing();
            }
            /*************MAIN***************/		    				 
		}catch(AWTException e){}catch(InterruptedException e1){}
	}

    public static void firemaking(){
        for(int is =0;is<85;is++){
        //preset
        leftMouse(posX[0], posY[0],300,1300);
        //fireplace
        leftMouse(posX[1], posY[1],780,2500);
        //craft
        leftMouse(posX[2], posY[2],400,1895);
        //tinder
        leftMouse(posX[3], posY[3],500,2800);
        //craft
        leftMouse(posX[4], posY[4],400,1354);
        //bonfire
        leftMouse(posX[5], posY[5],400,96000);
        //bank
        leftMouse(posX[6], posY[6],400,4500);
        /***************again**********************/
        //preset
        leftMouse(posX[0], posY[0],300,1300);
        //fireplace
        leftMouse(posX[1], posY[1],780,2500);
        //craft
        leftMouse(posX[2], posY[2],400,1895);
        //tinder
        leftMouse(posX[3], posY[3],500,2800);
        //craft
        leftMouse(posX[4], posY[4],400,1354);
        //bonfire
        leftMouse(posX[5], posY[5],400,96000);
        //bank 2
        leftMouse(posX[8], posY[8],400,4800);
        System.out.println("Iteration:"+is);
        }
    }

    public static void cooking(int number){
    	try{
    	for(int i =0;i<(number/28+1);i++){
    	//preset
    	leftMouse(posX[0],posY[0],200,1800);
    	//ladder
    	leftMouse(posX[1],posY[1],200,9000);
    	//range
    	leftMouse(posX[2],posY[2],200,200);
    	leftMouse(posX[2],posY[2],200,3000);
    	key(NativeKeyEvent.VK_SPACE);
    	key(NativeKeyEvent.VK_SPACE);
    	Thread.sleep(66000);
    	//ladder
    	leftMouse(posX[3],posY[3],200,200);
    	leftMouse(posX[3],posY[3],200,5000);
    	//bank
    	leftMouse(posX[4],posY[4],200,6000);
    	}}catch(InterruptedException e){}
    }

	public static void coshing(){
		try {
		Robot robot = new Robot();
      	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
     	int screenWidth = (int)screenSize.getWidth();
     	int screenHeight = (int)screenSize.getHeight();
     	int red=0;int rgb=0;
     	Rectangle screenCap = new Rectangle(0,0,screenWidth,screenHeight);
     	BufferedImage screenShot = robot.createScreenCapture(screenCap);
     	leftMouse(posX[2],posY[2],100,300);
     	
        for(int th=0;th<2100;th++){
       	rightMouse(posX[0],posY[0],200,500);
       	leftMouse(posX[0],posY[0]+60,200,500);
     	
            for(int space=0;space<5;space++){
       	        key(NativeKeyEvent.VK_SPACE);
                Thread.sleep(200);}
       	    rightMouse(posX[0],posY[0],200,500);
       	    leftMouse(posX[0],posY[0]+70,200,500);
       	    Thread.sleep(400);
       	        screenShot = robot.createScreenCapture(screenCap);     	      
                rgb = screenShot.getRGB(posX[1],posY[1]);		              
                red  = (rgb >> 16 ) & 0xFF;
            if(red<200){
                for(int smash=0;smash<4;smash++){
                rightMouse(posX[0],posY[0],200,200);
                leftMouse(posX[0],posY[0]+40,200,200);   
                }
                Thread.sleep(2000);  
                knockout++;
                exp+=177*2;		  
            }else{
		  	    failure++;
		  	    Thread.sleep(5000);
            }
		
        }
		reset();
	    leftMouse(posX[2],posY[2],100,300);
	   	leftMouse(posX[2],posY[2],100,2000);
	    } catch (InterruptedException ex) {}catch(AWTException e1){}
	}

	public static void jewels(){
		try {
		int exp =0;

		for(int smelt=0;smelt<550;smelt++){
		leftMouse(1626,330,200,5500);
		key(NativeKeyEvent.VK_SPACE);
		key(NativeKeyEvent.VK_SPACE);
		Thread.sleep(26000);	
		leftMouse(230,845,200,6000);
		leftMouse(759,810,200,1200);	
		exp+=60*14;
		System.out.println("Total exp thus far:" +exp);
		}

		reset();
		leftMouse(1274,116,200,100);
		leftMouse(1274,116,100,3000);
		leftMouse(898,68,100,100);
		leftMouse(898,68,100,2000);
		} catch (InterruptedException ex) {}
	}

	public static void cleanHerb(int number, int preset){
		try{
			for(int w = 0;w<(number/28+1);w++){
			//bank
			leftMouse(posX[0],posY[0],400,500);
			leftMouse(posX[0],posY[0],200,1200);
			//preset
			leftMouse(posX[1]+(preset-1)*35,posY[1],400,1400);
			//click on herb
			leftMouse(posX[2],posY[2],500,1400);
			//confirm
			key(NativeKeyEvent.VK_SPACE); 
			Thread.sleep(17000);   			
		}
		}catch(InterruptedException e){}
	}

	public static void changePreset(int column, int row){
		try{
			//bank
			leftMouse(posX[0],posY[0],100,200);
			leftMouse(posX[0],posY[0],100,1200);
			leftMouse(posX[1]-35,posY[1],200,1200);
			leftMouse(810+(column-1)*20,303+(row-1)*28,200,1400);
			key(NativeKeyEvent.VK_ESCAPE);
			Thread.sleep(600);
			key(NativeKeyEvent.VK_ESCAPE);
			Thread.sleep(600);
		}catch(InterruptedException e){}
	}

	public static void mixPot(int number, int preset){
		try{
		for(int z = 0;z<(number*1.1111)/14;z++){
			//bank
			leftMouse(posX[0],posY[0],400,200);
			leftMouse(posX[0],posY[0],100,1200);
			//preset
			leftMouse(posX[1]+(preset-1)*35,posY[1],400,1200);
	        //click on potions
	        leftMouse(posX[2],posY[2],300,600);
	        leftMouse(posX[2],posY[2]+42,500,1200);
	   		key(NativeKeyEvent.VK_SPACE);    	
	   		Thread.sleep(16500);
		}
		}catch(InterruptedException e){}
	}

	public static boolean checkcolor(int hue, int lower, int upper, int x, int y){
		boolean checking=false;
		if(lower<0){lower=0;}
		if(upper>255){upper=255;}
		try{
		Robot robot = new Robot();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (int)screenSize.getWidth();
		int screenHeight = (int)screenSize.getHeight();
		Rectangle screenCap = new Rectangle(0,0,screenWidth,screenHeight);
		BufferedImage screenShot = robot.createScreenCapture(screenCap);
		int rgb =0;int color =0;int shift=0;
		rgb = screenShot.getRGB(x,y);
		switch(hue){case 0:shift=16; case 1:shift=8; case 2:shift=0;}
		color  = (rgb >> shift ) & 0xFF;
		if(color<lower || color > upper){checking = false;}else{checking = true;}
		}catch (AWTException e) {}
		return checking;
	}
	public static void reset(){
		//lets go people
		try {
        Robot robot = new Robot();
		leftMouse(1447,65,300,10000);
		//open
		leftMouse(1006,1064,300,55000);
		//password
		leftMouse(1047,441,200,200);
		leftMouse(1047,441,200,600);
		key(NativeKeyEvent.VK_1);
		key(NativeKeyEvent.VK_4);
		key(NativeKeyEvent.VK_3);
		key(NativeKeyEvent.VK_3);
		key(NativeKeyEvent.VK_G);
		key(NativeKeyEvent.VK_A);
		key(NativeKeyEvent.VK_M);
		key(NativeKeyEvent.VK_M);
		key(NativeKeyEvent.VK_A);
		key(NativeKeyEvent.VK_ENTER);
		Thread.sleep(25000);
		//world
		leftMouse(1151,609,200,100);
		leftMouse(1151,609,100,25000);
		} catch (InterruptedException e){}catch(AWTException e1){}
	}
	
	public static void botExit(){
		System.out.println("taskquit");
		GlobalScreen.unregisterNativeHook();
		System.exit(0);
	}
	
	public static void leftMouse(int posX, int posY, int delay, int sleep){
		try {
        Robot robot = new Robot();
		robot.mouseMove(posX, posY);
		Thread.sleep(delay);
    	robot.mousePress(InputEvent.BUTTON1_MASK);
    	robot.mouseRelease(InputEvent.BUTTON1_MASK);
		Thread.sleep(sleep);
		}catch(InterruptedException e){}catch(AWTException e1){}
	}
	public static void rightMouse(int posX, int posY, int delay, int sleep){
		try {
        Robot robot = new Robot();
		robot.mouseMove(posX, posY);
		Thread.sleep(delay);
    	robot.mousePress(InputEvent.BUTTON3_MASK);
    	robot.mouseRelease(InputEvent.BUTTON3_MASK);
		Thread.sleep(sleep);
		}catch(InterruptedException e){}catch(AWTException e1){}
	}
	
	public static void key(int input){
		try{ 
        Robot robot = new Robot();
		robot.keyPress(input);
        Thread.sleep(140);
        robot.keyRelease(input);
        Thread.sleep(210);
		}catch(InterruptedException e){}catch(AWTException e1){}
	}
	
	
	public void nativeKeyPressed(NativeKeyEvent e) {
        if(wait.get()==0){
		System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
        }

        switch(e.getKeyCode()){
        	case NativeKeyEvent.VK_Q: 
        		botExit();
        		break;
        	case NativeKeyEvent.VK_C:
        		wait.incrementAndGet();
        		idxSave=idx;
        		break;
        	case NativeKeyEvent.VK_R:
        		System.out.println("F:K - "+failure+":"+knockout);
        		break;
        	case NativeKeyEvent.VK_E:
        		System.out.println("exp: "+exp);
        		break;
        	case NativeKeyEvent.VK_M:
        		long hours = elapsedTime/(3600*1000);
        		long minutes = (elapsedTime-hours*3600*1000)*1000*60;
        		System.out.println("time: "+hours+":"+minutes);
        		break;	
        	default:
        		break;
       }
	}
	
	public void nativeKeyReleased(NativeKeyEvent e) {
        if(wait.get()==0){
		System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
        }
    }
	
	 public void nativeMouseClicked(NativeMouseEvent e) {
         posX[idx] = e.getX();
         posY[idx] = e.getY();
         
         if(wait.get()==0){
        	 System.out.println("X: " + e.getX() + " Y: " + e.getY());
         }
         if (idx < 19) {
        	 idx++;
         }
	 }
	
	 public void nativeKeyTyped(NativeKeyEvent e) {}	
	 public void nativeMousePressed(NativeMouseEvent e) {}
	 public void nativeMouseReleased(NativeMouseEvent e) {}
	 public void nativeMouseMoved(NativeMouseEvent e) {}
	 public void nativeMouseDragged(NativeMouseEvent e) {}
}
