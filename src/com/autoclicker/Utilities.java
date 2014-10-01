package com.autoclicker;

import org.apache.commons.lang.exception.NestableException;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.ConversionException;
import org.apache.commons.configuration.PropertiesConfiguration;

@SuppressWarnings("unused")
public class Utilities {
	public static PropertiesConfiguration config;

	// retrieve properties from properties file
	public static int getProperties() {
		try {
			config = new PropertiesConfiguration("autoclicker.properties");
		} catch (ConfigurationException ce) {return -1;}
		
		try {
			AutoClicker.depositX = config.getInt("depositX");
			AutoClicker.depositY = config.getInt("depositY");
			
			AutoClicker.invX = config.getInt("inventoryX");
			AutoClicker.invY = config.getInt("inventoryY");
			
			AutoClicker.cameraX = config.getInt("cameraX");
			AutoClicker.cameraY = config.getInt("cameraY");
			
			AutoClicker.closeWindowX = config.getInt("closeWindowX");
			AutoClicker.closeWindowY = config.getInt("closeWindowY");
			
			AutoClicker.startGameX = config.getInt("startGameX");
			AutoClicker.startGameY = config.getInt("startGameY");
			
			AutoClicker.windowsDelay = config.getInt("windowsDelay");
			AutoClicker.loadDelay = config.getInt("loadDelay");
			AutoClicker.loginDelay = config.getInt("loginDelay");
			AutoClicker.gameDelay = config.getInt("gameDelay");
			
			AutoClicker.password = config.getString("password");
		} catch (ConversionException ce) {return -1;}
		
		return 0;
	}
	
	// set properties in properties file
	public static void setProperties() {
		config.setProperty("depositX", AutoClicker.depositX);
		config.setProperty("depositY", AutoClicker.depositY);
		
		config.setProperty("inventoryX", AutoClicker.invX);
		config.setProperty("inventoryY", AutoClicker.invY);
		
		config.setProperty("cameraX", AutoClicker.cameraX);
		config.setProperty("cameraY", AutoClicker.cameraY);
		
		try {
			config.save("autoclicker.properties");
		} catch (ConfigurationException ce) {}
	}
}
