package autoclicker;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
	public static int mouseX = 0;			// last mouse click coordinates
	public static int mouseY = 0;
	
	public static int paramFlag = 0;				// flag for parameter error checking
	public static boolean startFlag = false;		// used to determine if thread is running
	public static boolean propertiesFlag = false;	// flag for property loading
	
	public static int sceneWidth = 355;		// scene dimensions
	public static int sceneHeight = 680;
	public static int fieldMaxWidth = 60;	// maximum input field width
	
	public static Thread bot;
	
	// button declarations
	public static final Button startButton = new Button("Start");
	public static final Button bankButton = new Button("Banker");
	public static final Button item1Button = new Button ("Item 1");
	public static final Button item2Button = new Button ("Item 2");
	public static final Button depositButton = new Button ("Deposit");
	public static final Button invButton = new Button ("Inventory");
	public static final Button presetButton = new Button ("Preset");
	public static final Button cameraButton = new Button ("Camera");
	
	
	// coordinate and required text fields
	public static final Text pressedX = new Text();
	public static final Text pressedY = new Text();
	public static final Text message = new Text();
	
	// input fields
	public static final TextField bankDelayField = new TextField(Integer.toString(AutoClicker.bankDelay));
	public static final TextField spaceDelayField = new TextField(Integer.toString(AutoClicker.spaceDelay));
	public static final TextField amountField1 = new TextField(Integer.toString(AutoClicker.item1Amount));
	public static final TextField amountField2 = new TextField(Integer.toString(AutoClicker.item2Amount));
	
	// toggles
	public static final CheckBox resetToggle = new CheckBox("Reset Connection");
	
	public enum SetButton {BANKER, ITEM1, ITEM2, DEPOSIT, INV, PRESET, CAMERA}
	
	// drop down menu declaration
	public static final ChoiceBox<String> cb = new ChoiceBox<String>(FXCollections.observableArrayList(
			"Chocolate Powder ",
			"Headless Arrows ",
			"Alchemy ",
			"Arrow Shafts ",
			"Clean Herbs ",
			"Potions (1)",
			"Potions (2)",
			"Shieldbows",
			"Ivy",
			"Enchant Bolts ",
			"Superheat Item", 
			"Test Reset")
			);

	@Override
	public void start(Stage primaryStage) {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		
//		grid.setGridLinesVisible(true);
		
		Scene scene = new Scene(grid, sceneWidth, sceneHeight);
		
		Text scenetitle = new Text("AutoClicker 2.1");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 24));
		grid.add(scenetitle, 0, 0, 2, 1);
		
		// add drop down menu
		Text selectFunc = new Text("Select Function");
		selectFunc.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
		
		// add delay input fields
		Text delayAdjust = new Text("Delay Adjustments");
		delayAdjust.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
		
		Label bankDelay = new Label("Bank :    ");
		Label spaceDelay = new Label("Space :  ");
		
		bankDelayField.setMaxWidth(fieldMaxWidth);
		spaceDelayField.setMaxWidth(fieldMaxWidth);
		
		// horizontal aligned boxes for delay inputs
		HBox bankDelayBox = new HBox(2);
		bankDelayBox.setAlignment(Pos.BASELINE_LEFT);
		bankDelayBox.getChildren().addAll(bankDelay, bankDelayField);
		
		HBox spaceDelayBox = new HBox(2);
		spaceDelayBox.setAlignment(Pos.BASELINE_LEFT);
		spaceDelayBox.getChildren().addAll(spaceDelay, spaceDelayField);
		
		// add item amount input fields
		Text itemAmounts = new Text("Item Amounts");
		delayAdjust.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
		
		Label item1Amount = new Label("Item 1 :  ");
		Label item2Amount = new Label("Item 2 :  ");
		
		amountField1.setMaxWidth(fieldMaxWidth);
		amountField2.setMaxWidth(fieldMaxWidth);
		
		// horizontal aligned boxes for item amounts
		HBox item1Box = new HBox(2);
		item1Box.setAlignment(Pos.BASELINE_LEFT);
		item1Box.getChildren().addAll(item1Amount, amountField1);
		
		HBox item2Box = new HBox(2);
		item2Box.setAlignment(Pos.BASELINE_LEFT);
		item2Box.getChildren().addAll(item2Amount, amountField2);
		
		// add toggles field
		Text toggles = new Text("Toggles");
		toggles.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
		
		resetToggle.setIndeterminate(false);
	
		// add mouse press coordinate display field
		Text pressedCoords = new Text("Pressed");
		pressedCoords.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
		
		Label xPos = new Label("X-Pos : ");
		xPos.setTextFill(Color.FIREBRICK);
		
		Label yPos = new Label("Y-Pos : ");
		yPos.setTextFill(Color.FIREBRICK);

		pressedX.setFill(Color.FIREBRICK);
		pressedY.setFill(Color.FIREBRICK);
		
		pressedX.setText(Integer.toString(mouseX));
		pressedY.setText(Integer.toString(mouseY));
		
		// horizontal aligned boxes for mouse coordinates
		HBox xPosBox = new HBox(2);
		xPosBox.setAlignment(Pos.TOP_LEFT);
		xPosBox.getChildren().addAll(xPos, pressedX);
		
		HBox yPosBox = new HBox(2);
		yPosBox.setAlignment(Pos.TOP_LEFT);
		yPosBox.getChildren().addAll(yPos, pressedY);
		
		// add and reserve space for required coordinate field
		Text required = new Text("Required");
		required.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
		
		message.setFill(Color.FIREBRICK);
		if (!propertiesFlag) {message.setText("Failed to open properties file \n" +
												"or invaild properties. \n\n\n");} 
		else {message.setText(" \n\n\n\n");}
		
		VBox left = new VBox(10);
		left.setSpacing(10);
		left.setAlignment(Pos.TOP_LEFT);
		left.getChildren().addAll(selectFunc, 
				cb, 
				delayAdjust,
				bankDelayBox,
				spaceDelayBox,
				itemAmounts,
				item1Box,
				item2Box,
				toggles,
				resetToggle,
				pressedCoords,
				xPosBox,
				yPosBox,
				required,
				message
			);
		
		grid.add(left, 0, 2, 5, 10);
		
		// vertical box for buttons
		Text setCoords = new Text("Set Coordinates");
		setCoords.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
		
		startButton.setMaxWidth(Double.MAX_VALUE);
		bankButton.setMaxWidth(Double.MAX_VALUE);
		item1Button.setMaxWidth(Double.MAX_VALUE);
		item2Button.setMaxWidth(Double.MAX_VALUE);
		depositButton.setMaxWidth(Double.MAX_VALUE);
		invButton.setMaxWidth(Double.MAX_VALUE);
		presetButton.setMaxWidth(Double.MAX_VALUE);
		cameraButton.setMaxWidth(Double.MAX_VALUE);
		
		Text spacer = new Text(" \n\n");
		
		VBox verticalBox = new VBox(10);
		verticalBox.setSpacing(10);
		verticalBox.setAlignment(Pos.TOP_LEFT);
		verticalBox.getChildren().addAll(setCoords, bankButton, item1Button, item2Button, depositButton, invButton, presetButton, cameraButton, spacer, startButton);
		grid.add(verticalBox, 5, 2, 5, 10);
		
        // button action functions
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
            	if (paramFlag == 0) {
            		displayRequirements();
            		
	            	if(paramFlag == 0) {return;}
	            	startButton.setText("Continue");
            	} else if (paramFlag == 1){
            		AutoClicker.runFlag = true;
            		startFlag = true;
            		
	            	getParams();
	        		if (propertiesFlag) {Utilities.setProperties();}
            		startButton.setText("Working");
            		
            		bot = new Thread(new AutoClicker());
            		bot.setDaemon(true);
            		bot.start();
            	}
            }
        });
        
        bankButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
            	if (AutoClicker.runFlag) {return;}
            	
            	if (!checkSetFlags()) {
            		bankButton.setText("Waiting");
            		AutoClicker.setBanker = true;
            	}
            }
        });
        
        item1Button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
            	if (AutoClicker.runFlag) {return;}
            	
            	if (!checkSetFlags()) {
            		item1Button.setText("Waiting");
            		AutoClicker.setItem1 = true;
            	}
            }
        });
        
        item2Button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
            	if (AutoClicker.runFlag) {return;}
            	
            	if (!checkSetFlags()) {
            		item2Button.setText("Waiting");
            		AutoClicker.setItem2 = true;
            	}
            }
        });
        
        depositButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
            	if (AutoClicker.runFlag) {return;}
            	
            	if (!checkSetFlags()) {
            		depositButton.setText("Waiting");
            		AutoClicker.setDeposit = true;
            	}
            }
        });
        
        invButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
            	if (AutoClicker.runFlag) {return;}
            	
            	if (!checkSetFlags()) {
            		invButton.setText("Waiting");
            		AutoClicker.setInv = true;
            	}
            }
        });
        
        presetButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
            	if (AutoClicker.runFlag) {return;}
            	
            	if (!checkSetFlags()) {
            		presetButton.setText("Waiting");
            		AutoClicker.setPreset = true;
            	}
            }
        });
        
        cameraButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
            	if (AutoClicker.runFlag) {return;}
            	
            	if (!checkSetFlags()) {
            		cameraButton.setText("Waiting");
            		AutoClicker.setCamera = true;
            	}
            }
        });
		
        // set up primary stage and display
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
		// register hook to mouse events
		try {
            GlobalScreen.registerNativeHook();
	    }
	    catch (NativeHookException ex) {
	            System.err.println("There was a problem registering the native hook.");
	            System.err.println(ex.getMessage());
	
	            System.exit(1);
	    }
		
		if (Utilities.getProperties() == 0) {propertiesFlag = true;}
		
	    // construct the example object and initialize native hooks
	    GlobalScreen.getInstance().addNativeKeyListener(new AutoClicker());
	    GlobalScreen.getInstance().addNativeMouseListener(new AutoClicker());
        GlobalScreen.getInstance().addNativeMouseMotionListener(new AutoClicker());
        
        // launch GUI
		launch(AutoClickerGUI.class, args);
	}
	
	// displays requirements based on user choice
	public static void displayRequirements() {
		AutoClicker.pick = cb.getSelectionModel().getSelectedIndex();
		
		if (AutoClicker.pick != -1) {
			paramFlag = 1;
			
			switch (AutoClicker.pick) {
				case 0: 
					message.setText("Banker        \n" +
									"Chocolate (I1)\n" +
									"Deposit (P)   \n" +
									"Inventory (P) \n");
					break;
				case 1:
					message.setText("Inventory (P) \n\n\n\n");
					break;
				case 2:
					message.setText("Alchemy (I1)  \n\n\n\n");
					break;
				case 3: 
					message.setText("Banker			\n" +
									"Logs (I1)		\n" +
									"Inventory (P)  \n\n");
					break;
				case 4:
					message.setText("Banker       	\n" +
									"Herb (I1)    	\n" +
									"Deposit (P)    \n" +
									"Inventory (P)  \n" +
									"Camera (P)     ");
					break;
				case 5:
					message.setText("Banker       	\n" +
									"Preset	      	\n" +
									"Inventory (P)  \n" +
									"Camera (P)     \n");
					break;
				case 6:
					message.setText("Banker       	\n" +
									"Preset 1	  	\n" +
									"Inventory (P)  \n" +
									"Camera (P)     \n");
					break;
				case 7: 
					message.setText("Banker			\n" +
									"Logs (I1)		\n" +
									"Deposit (P)    \n" +
									"Inventory (P)  \n" +
									"Camera (P)     ");
					break;
				case 8: 
					message.setText("Ivy (I1)		\n\n\n\n");
					break;
				case 9:
					message.setText("Enchant (I1)   \n\n\n\n");
					break;
				case 10:
					message.setText("Banker         \n" +
									"Preset         \n" +
									"Superheat (I1) \n" +
									"Deposit (P)    \n" +
									"Camera (P)     ");
			}
		}
	}
	
	// retrieves parameters from input fields and toggles
	public static void getParams() {
		AutoClicker.bankDelay = Integer.parseInt(bankDelayField.getText());
		AutoClicker.spaceDelay = Integer.parseInt(spaceDelayField.getText());
		AutoClicker.item1Amount = Integer.parseInt(amountField1.getText());
		AutoClicker.item2Amount = Integer.parseInt(amountField2.getText());
		
		if (!propertiesFlag) {AutoClicker.resetFlag = false;}
		else {AutoClicker.resetFlag = resetToggle.isSelected();}
	}
	
	// reset set buttons
	public static void resetButton(SetButton sb) {
		switch(sb) {
			case BANKER:
				Platform.runLater(new Runnable() {
		        	public void run() {
		        		bankButton.setText("Banker");
		        	}
		        });
				break;
			case ITEM1:
				Platform.runLater(new Runnable() {
		        	public void run() {
		        		item1Button.setText("Item 1");
		        	}
		        });
				break;
			case ITEM2:
				Platform.runLater(new Runnable() {
		        	public void run() {
		        		item2Button.setText("Item 2");
		        	}
		        });
				break;
			case DEPOSIT:
				Platform.runLater(new Runnable() {
		        	public void run() {
		        		depositButton.setText("Deposit");
		        	}
		        });
				break;
			case INV:
				Platform.runLater(new Runnable() {
		        	public void run() {
		        		invButton.setText("Inventory");
		        	}
		        });
				break;
			case PRESET:
				Platform.runLater(new Runnable() {
		        	public void run() {
		        		presetButton.setText("Preset");
		        	}
		        });
				break;
			case CAMERA:
				Platform.runLater(new Runnable() {
		        	public void run() {
		        		cameraButton.setText("Camera");
		        	}
		        });
				break;
		}
	}
	
	// reset all flags and running thread
	public static void resetAll() {
		AutoClicker.runFlag = false;
		AutoClicker.count = 0;
		AutoClicker.itemNumber = 1;
		AutoClicker.reset = false;
		
		paramFlag = 2;
		
		// end timer thread
		AutoClicker.timer.interrupt();
		
		// wait for running thread to end
		if (startFlag == true) {
			try {bot.join();}
			catch (InterruptedException ie) {}
			
			startFlag = false;
		}
		
		paramFlag = 0;
		
		Platform.runLater(new Runnable() {
        	public void run() {
        		startButton.setText("Start");
        	}
        });
	}
	
	public static boolean checkSetFlags() {
		return (AutoClicker.setBanker && AutoClicker.setItem1 && AutoClicker.setItem2 && AutoClicker.setDeposit && AutoClicker.setInv && AutoClicker.setPreset && AutoClicker.setCamera);
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

		// update mouse clicked field
		Platform.runLater(new Runnable() {
        	public void run() {
        		 pressedX.setText(Integer.toString(mouseX));
        		 pressedY.setText(Integer.toString(mouseY));
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
