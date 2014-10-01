package com.robot.command;

import java.util.List;

import org.jnativehook.keyboard.NativeKeyEvent;

public class RobotController {
	private RobotCommands command;

	public RobotController() {
		command = new RobotCommands();
	}
	
	public boolean executeClick(CommandKey key, int x, int y, int sleepTime) throws InterruptedException {
		switch (key) {
			case LEFT_CLICK:
				command.leftClick(x, y, sleepTime);
				break;
			case RIGHT_CLICK:
				command.rightClick(x, y, sleepTime);
				break;
			default:
				return false;
		}
		return true;
	}
	
	public void executeTypeString(String string) {
		command.typeString(string);
	}
	
	public boolean executeSpecial(CommandKey key, List<Integer> params, boolean bool) throws InterruptedException {
		switch (key) {
			case PRESS_UP:
				if (params.size() < 1) {
					return false;
				}
				command.pressKeyDelay(NativeKeyEvent.VK_UP, params.get(0));
			
			case PRESS_DOWN:
				if (params.size() < 1) {
					return false;
				}
				command.pressKeyDelay(NativeKeyEvent.VK_DOWN, params.get(0));
			
			case PRESS_LEFT:
				if (params.size() < 1) {
					return false;
				}
				command.pressKeyDelay(NativeKeyEvent.VK_LEFT, params.get(0));
				
			case PRESS_RIGHT:
				if (params.size() < 1) {
					return false;
				}
				command.pressKeyDelay(NativeKeyEvent.VK_UP, params.get(0));
			
			case PRESS_ESC:
				command.pressKeyDelay(NativeKeyEvent.VK_ESCAPE, 500);
			
			case CHECK_OPTION_BOX:
				if (params.size() < 4) {
					return false;
				}
				return command.checkOptionBox(params.get(0), params.get(1), params.get(2), params.get(3));
				
			case CHECK_NEIGHBORS:
				if (params.size() < 3) {
					return false;
				}
				return command.checkNeighbors(params.get(0), params.get(1), params.get(2), bool);
				
			case RANDOM_MOVE_CAMERA:
				if (params.size() < 2) {
					return false;
				}
				command.randomMoveCamera(params.get(0), params.get(1));
				return true;
			
			case SAVE_PIXEL_COLOR:
				if (params.size() < 2) {
					return false;
				}
				command.getPixelColor(params.get(0), params.get(1));
				return true;
				
			case CHECK_PIXEL_COLOR:
				if (params.size() < 2) {
					return false;
				}
				return command.checkPixelColor(params.get(0), params.get(1));
				
			default:
				return false;
		}
	}
}
