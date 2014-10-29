AutoClicker-Win
===============

Basic auto clicker/typer with custom macros.  
Keyboard and mouse capture functions do not work on Windows.  
Basic click macros untested on other operating systems.

2.1 -------------------------------------------------------------------------------------------------------------------------
- Added properties file, will save your deposit, inventory, and camera coordinates 
automatically after running any bot with the new coordinates once.  Keep in the same 
folder as the jar.  The program will still work without it but you won't be able to reset 
connection or save/load coordinates. A (P) after a requirement means those coordinates 
are stored inthe properties file and won't need to be loaded again.

-  Added automatic connection reset.  Only works if you have the client installed.  You will 
have to manually fill out some coordinates in the properties file:

	- closeWindow is the button to close the existing client window.  
	- startGame is the button you use after you login to start game or you can set it 
	to be one of the favorite worlds.
	- windowsDelay is the delay after the windows button is pressed.
	- loadDelay is the delay after the client has been started up.
	- loginDelay is the delay after login has been pressed.
	- gameDelay is the delay after the start game or favorite world has been 
	pressed.
	- password is your password, make sure your username is saved.

To use the reset just click the Reset Connection box under Toggles.  There's also a new 
Test Reset option you can use to see if the reset actually works.  You should probably do
this to make sure it works.  I have the settings that work for me in the file already and 
those might work for you.  

- New anti-ban stuff.  Random camera movements and timers.  If there's a camera under
the requirements, make sure you press the reset camera before you set the banker
coordinates so the bot can find the banker later when it moves the camera around.

- Pressing r or q does nothing now.  Reset hotkey changed to ` and just close it as you 
normally would.




