import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;

public class AutoClickerGUI extends Application implements NativeKeyListener, NativeMouseInputListener{
	public static int mouseX = 0;
	public static int mouseY = 0;
	
	public static int param_flag = 0;
	public static int start_flag = 0;
	
	public static Thread bot;
	
	public static final Button btn = new Button("Start");
	public static final Text pressed = new Text();
	public static final Text message = new Text();
	
	public static final TextField bankDelayField = new TextField(Integer.toString(AutoClicker.bankDelay));
	public static final TextField spaceDelayField = new TextField(Integer.toString(AutoClicker.spaceDelay));
	
	public static final ChoiceBox<String> cb = new ChoiceBox<String>(FXCollections.observableArrayList(
			"Chocolate Powder ",
			"Headless Arrows ",
			"Alchemy ",
			"Arrow Shafts ",
			"Clean Herbs ",
			"Potions")
			);
	
	public static void getParams() {
		AutoClicker.pick = cb.getSelectionModel().getSelectedIndex();
		
		if (AutoClicker.pick != -1) {
			param_flag = 1;
			
			switch (AutoClicker.pick) {
				case 0: 
					message.setText("Banker       \n" +
									"Chocolate    \n" +
									"Deposit      \n" +
									"Inventory    \n");
					break;
				case 1:
					message.setText("Below Shafts \n");
					break;
				case 2:
					message.setText("Alchemy Skill\n");
					break;
				case 3: 
					message.setText("Banker       \n" +
									"Logs         \n" +
									"Inventory    \n");
					break;
				case 4:
					message.setText("Banker       \n" +
									"Herb	      \n" +
									"Deposit      \n" +
									"Inventory    \n");
					break;
				case 5:
					message.setText("Banker       \n" +
									"Preset	      \n" +
									"Inventory    \n");
					break;
			}
		}
		
		AutoClicker.bankDelay = Integer.parseInt(bankDelayField.getText());
	}
	
	public static void reset() {
		AutoClicker.run_flag = false;
		AutoClicker.wait_flag = 0;
		AutoClicker.idx = 0;
		AutoClicker.deposit_flag = 0;
		param_flag = 2;
		
		if (start_flag == 1) {
			try {bot.join();}
			catch (InterruptedException ie) {}
		}
		
		param_flag = 0;
		
		Platform.runLater(new Runnable() {
        	public void run() {
        		btn.setText("Start");
        	}
        });
	}
	
	@Override
	public void start(Stage primaryStage) {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		
//		grid.setGridLinesVisible(true);
		
		Scene scene = new Scene(grid, 355, 480);
		
		Text scenetitle = new Text("AutoClicker 1.0");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 24));
		grid.add(scenetitle, 0, 0, 2, 1);
		
		Text selectFunc = new Text("Select Function");
		selectFunc.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
		grid.add(selectFunc, 0, 2, 2, 1);
		
		grid.add(message, 5, 2, 4, 4);
		message.setFill(Color.FIREBRICK);
		message.setText("                     ");
		
		grid.add(cb, 0, 3, 6, 1);
		
		Text delayAdjust = new Text("Delay Adjustments");
		delayAdjust.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
		grid.add(delayAdjust, 0, 5, 2, 1);
		
		Label bankDelay = new Label("Bank : ");
		grid.add(bankDelay, 0, 6);
		
		bankDelayField.setMaxWidth(60);
		grid.add(bankDelayField, 1, 6);
		
		Label spaceDelay = new Label("Space : ");
		grid.add(spaceDelay, 0, 7);
		
		spaceDelayField.setMaxWidth(60);
		grid.add(spaceDelayField, 1, 7);
		
		grid.add(pressed, 1, 10);
        pressed.setFill(Color.FIREBRICK);
        pressed.setText("Pressed\nX : " + mouseX + "\nY : " + mouseY);
		
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_LEFT);
		hbBtn.getChildren().add(btn);
		grid.add(hbBtn, 1, 12);
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
            	if (param_flag == 0) {
	            	getParams();
	            	
	            	if(param_flag == 0) {return;}
	            	AutoClicker.wait_flag = 1;
	            	btn.setText("Continue");
            	} else if (param_flag == 1){
            		AutoClicker.run_flag = true;
            		AutoClicker.wait_flag = 0;
            		
            		start_flag = 1;
            		
            		bot = new Thread(new AutoClicker());
            		bot.setDaemon(true);
            		bot.start();
            	}
            }
        });
		
		primaryStage.setTitle("AutoClicker");
		primaryStage.setScene(scene);
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent event) {
				System.exit(0);
			}
		});
		primaryStage.show();
	}
	
	public static void main(String[] args) {
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
        
		launch(AutoClickerGUI.class, args);
	}
	
	public void nativeKeyPressed(NativeKeyEvent e) {
		AutoClicker.nativeKeyPressedHandler(e);
	}
	
	public void nativeKeyReleased(NativeKeyEvent e) {
        AutoClicker.nativeKeyReleasedHandler(e);
	}
	
	public void nativeMouseClicked(NativeMouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();

		Platform.runLater(new Runnable() {
        	public void run() {
        		 pressed.setText("Pressed\nX : " + mouseX + "\nY : " + mouseY);
        	}
        });
		
		AutoClicker.nativeMouseClickedHandler(e);
	 }
	
	public void nativeKeyTyped(NativeKeyEvent e) {}	
	public void nativeMousePressed(NativeMouseEvent e) {}
	public void nativeMouseReleased(NativeMouseEvent e) {}
	public void nativeMouseMoved(NativeMouseEvent e) {}
	public void nativeMouseDragged(NativeMouseEvent e) {}
}
