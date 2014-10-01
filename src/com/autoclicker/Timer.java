package com.autoclicker;

public class Timer implements Runnable{
	public void run() {
		try {Thread.sleep((int) (5.5 * 60) * 60 * 1000);}
		catch (InterruptedException ie) {return;}
		
//		try {Thread.sleep(10000);}
//		catch (InterruptedException ie) {return;}
		
		if (AutoClicker.resetFlag) {AutoClicker.reset = true;}
		else {AutoClickerGUI.resetAll();}
	}
}
	