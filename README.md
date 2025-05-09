# APCSA_BasketRandom
Our project is Dance Party, where you and a friend can control your characters and dance around a map that is randomly generated each time.
The characters on the left are control by WASD and the characters on the right are controlled by the arrow keys. Only one person is controlled at a time, and the controlled character can be switched by S or down respectively.
Classes included are the superclass Entity, which is inherited by Arm, Body, and Ball, all of the objects present in the game panel, which has its own class. All of the movement is calculated by the Vector class. All keys presses are detected by the KeyHandler class and used by other classes. Additionally, the Background class uses an ArrayList to change the background to a random image either every 10 seconds or when P is pressed.
Imported classes include JFrame, Color, and Dimension for the game panel, Graphics and Graphics2D for drawing entities in the panel, and BufferedImage for adding sprites to the entities.
While this project serves as a good foundation, it doesn't have much of a game-like aspect. In the future, more classes could be added for a variety of games.
