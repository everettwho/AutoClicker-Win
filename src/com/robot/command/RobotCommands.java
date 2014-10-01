package com.robot.command;

import java.awt.Color;
import java.awt.Robot;
import java.awt.AWTException;
import java.awt.event.InputEvent;
import java.util.Random;

import org.jnativehook.keyboard.NativeKeyEvent;

public class RobotCommands {
	private Robot robot;
	private Random rand;
	private int optionBoxX = 40;			// location of option box relative to pointer
	private int optionBoxY = 55;
	private double CIRCLE_CONST = 0.7071067;
	
	private Color pixelColor;
	
	private int[] move = {NativeKeyEvent.VK_LEFT, NativeKeyEvent.VK_RIGHT, NativeKeyEvent.VK_UP, NativeKeyEvent.VK_DOWN};
	
	public RobotCommands() {
		try {
			robot = new Robot();
			rand = new Random();
		} catch(AWTException awte) {}
	}
	
	public void leftClick(int x, int y, int sleepTime) throws InterruptedException {
		robot.mouseMove(x, y);
		Thread.sleep(sleepTime);
		robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	
	public void rightClick(int x, int y, int sleepTime) throws InterruptedException{
		robot.mouseMove(x, y);
		Thread.sleep(sleepTime);
		robot.mousePress(InputEvent.BUTTON3_MASK);
        robot.mouseRelease(InputEvent.BUTTON3_MASK);
	}
	
	public void typeString(String str) {		
		for (int i = 0; i < str.length(); i++) {
			typeChar(str.charAt(i));
		}
	}
	
	public void pressKeyDelay(int code, int delay) throws InterruptedException {
		robot.keyPress(code);
		Thread.sleep(delay);
		robot.keyRelease(code);
	}
	
	public void getPixelColor(int x, int y) {
		pixelColor = robot.getPixelColor(x, y);
	}
	
	public boolean checkPixelColor(int x, int y) {
		return robot.getPixelColor(x, y).equals(pixelColor);
	}
	
	public void randomMoveCamera(int timeOffset, int timeMultiplier) throws InterruptedException {
		timeOffset /= 10;
		int moveIndex = rand.nextInt(4);
        robot.keyPress(move[moveIndex]);
        
        Thread.sleep((long) (Math.random() + timeOffset) * timeMultiplier);
        
        robot.keyRelease(move[moveIndex]);
	}
	
	public boolean checkNeighbors(int centerX, int centerY, int offset, boolean click) throws InterruptedException {
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
	
	public boolean checkOptionBox(int coordX, int coordY, int offsetX, int offsetY) throws InterruptedException {
		robot.mouseMove(coordX, coordY);
		
		Thread.sleep(500);
		
		Color temp = robot.getPixelColor(coordX + offsetX, coordY + offsetY);
		
		if (temp.getRed() > 2 || temp.getBlue() > 2 || temp.getBlue() > 2) {return false;}
		else {return true;}
	}
	
	private void pressKey(int code, boolean shift) {
		if (shift) {robot.keyPress(NativeKeyEvent.VK_SHIFT);}
		
		robot.keyPress(code);
		robot.keyRelease(code);
		
		if (shift) {robot.keyRelease(NativeKeyEvent.VK_SHIFT);}
	}
	
	private void typeChar(char character) {
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
}
