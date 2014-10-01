package autoclicker;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import javafx.application.Application;

public class Launcher {

	public static void main(String[] args) {
		// register hook to mouse events
		try {
            GlobalScreen.registerNativeHook();
	    }
	    catch (NativeHookException ex) {
	            System.err.println("There was a problem registering the native hook");
	            System.err.println(ex.getMessage());
	
	            System.exit(1);
	    }
		
		if (Utilities.getProperties() == 0) {AutoClickerGUI.propertiesFlag = true;}
		
	    // construct the example object and initialize native hooks
	    GlobalScreen.getInstance().addNativeKeyListener(new AutoClicker());
	    GlobalScreen.getInstance().addNativeMouseListener(new AutoClicker());
        GlobalScreen.getInstance().addNativeMouseMotionListener(new AutoClicker());
        
        // launch GUI
		Application.launch(AutoClickerGUI.class, args);
	}
}
