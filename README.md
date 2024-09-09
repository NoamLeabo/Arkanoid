# 🧱 Arkanoid Game - OOP Java Edition

Welcome to **Arkanoid Game**, a fun and classic brick-breaking game implemented in Java. This project showcases the use of Object-Oriented Programming (OOP) principles while delivering an interactive and challenging game experience with multiple levels and power-ups.

## 🎮 Game Features

- **3 Challenging Levels**: Navigate through progressively harder levels with blocks that require varying strategies.
- **Grey Blocks**: These blocks need to be hit multiple times to change color. Only when they become color blocks can they be popped.
- **Candy Power-ups**: Some blocks drop candies when popped, with different effects:
  - **Blue Candy**: Increases the size of the paddle.
  - **Pink Candy**: Adds an extra ball.
  - **Green Candy**: Decreases the size of the paddle.
- **Multiple Balls**: Keep track of your active balls through the heart indicators at the top of the screen. More balls mean more fun, but also more challenges!

## 🛠️ Technologies Used

- **Java:** Core programming language
- **Design Patterns:** Massive use of Listener-Notifier in order to have a smooth reactions to events without having a too-tight class-relations 
- **OOP Concepts:** Classes, inheritance, encapsulation, and polymorphism are at the heart of the game’s structure.

## 🚀 How to Run

1. Make sure you have **Java JDK 8** or higher installed on your system.
2. Download the **GAME.jar** file from the repository
3. You can now simply click on the download file anf play! 
4. Another option - Open a terminal or command prompt and navigate to the directory containing the **GAME.jar** file.
5. Run the following command:

   ```bash
   java -jar GAME.jar
6. The game window should open, and you can start playing!

## 🕹️ Controls
- **Arrow keys** - Control the paddle’s movement.
- **Spacebar** - Launch and resume the game.
- **P** - Pause the game.

## 📸 Screenshots

### Main Menu
![צילום מסך 2024-09-09 194245](https://github.com/user-attachments/assets/f1f85efd-cfd5-4384-bd25-84d7174ca408)

### Gameplay
![צילום מסך 2024-09-09 194449](https://github.com/user-attachments/assets/ac37bd61-b671-49dd-a7b6-e3227426d10a)

![צילום מסך 2024-09-09 195048](https://github.com/user-attachments/assets/ab1a4df4-b3ca-4fa5-a509-725f5f27361a)

![צילום מסך 2024-09-09 195406](https://github.com/user-attachments/assets/187da6be-f526-4810-b3c0-936e8cf09508)

### End Screens
![צילום מסך 2024-09-09 200318](https://github.com/user-attachments/assets/59f37b9e-415b-4f1e-9dbf-568fa1a4c8d2)

![צילום מסך 2024-09-09 195643](https://github.com/user-attachments/assets/fcb31226-0c79-44b7-ab49-3a2472c847cf)


## 🧑‍💻 Project Structure
- **src/**: Contains the Java source code for the game.
- **resources/**: Stores images and other assets like block and paddle graphics.
- **GAME.jar**: The executable JAR file that launches the game.

## 💡 Key OOP Concepts Explored
- **Encapsulation:** Game entities such as the ball, paddle, and blocks are encapsulated into separate classes to manage their properties and behaviors.
- **Inheritance:** Common features and behaviors are inherited by different game objects, such as different types of blocks.
- **Polymorphism:** Methods for handling collisions and game logic use polymorphism to manage various types of interactions.
- **Abstraction:** The game's core mechanics and rendering logic are abstracted from the user interface to ensure a clean and manageable codebase.

## 🙌 Notes
- **Graphics:** Custom game assets created for a unique visual style.
- **Development Tools:** Java, Swing, IntelliJ IDEA

## 🚧 Future Improvements
- Add more levels and challenges.
- Introduce new power-ups and block types.
- Implement sound effects and background music for an enhanced gaming experience.

Enjoy playing Arkanoid! 🧱🎮

