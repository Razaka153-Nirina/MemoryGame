# 🎮 Memory Game

## About This Project

This is a **Memory Game** made with Java Swing. This was one of my **first projects** with graphical interfaces during my **first year** of computer science studies.

I learned how to use JavaSwing to create windows, buttons, and interactive games. This project helped me understand how to work with GUI (Graphical User Interface) in Java.

e52652u@sd-lx012-16:~/Documents$ git clone https://github.com/schmit721u/assovote_RAZAKANIRINA_SCHMITT.git
Clonage dans 'assovote_RAZAKANIRINA_SCHMITT'...
Username for 'https://github.com': Razaka153-Nirina
Password for 'https://Razaka153-Nirina@github.com': 
remote: Invalid username or token. Password authentication is not supported for Git operations.
fatal: Échec d'authentification pour 'https://github.com/schmit721u/assovote_RAZAKANIRINA_SCHMITT.git/'
e52652u@sd-lx012-16:~/Documents$ 
e52652u@sd-lx012-16:~/Documents$ git clone https://github.com/schmit721u/assovote_RAZAKANIRINA_SCHMITT.git^C
e52652u@sd-lx012-16:~/Documents$ 

## What is Memory Game?

Memory is a card matching game. The goal is simple:
- Find all the pairs of cards
- Click on two cards to flip them
- If they match, they stay visible
- If they don't match, they flip back
- Try to find all pairs with the minimum number of moves!

## Game Features

✨ **Welcome Screen**: Choose your game mode
- Play **without timer** (relaxed mode)
- Play **with timer** (challenge mode)

🎯 **Game Board**: 
- 20 cards (10 pairs)
- Click on cards to flip them
- Score counter shows your moves
- Pairs counter shows your progress

⏱️ **Timer Mode**: 
- See how fast you can finish
- Time is displayed in minutes and seconds

🏆 **End Screen**:
- Shows your final results
- Displays number of moves, pairs found, and time
- Options to play again or quit

## How to Play

1. Run the program
2. Choose your game mode (with or without timer)
3. Click on a card to flip it
4. Click on another card to find its pair
5. Match all 10 pairs to win!
6. Try to use fewer moves for a better score!

## Project Structure

The project is organized in **4 files**:

- `MemoryGame.java` - Main file to start the game
- `WelcomeDialog.java` - Welcome screen with game mode selection
- `EndGameDialog.java` - End screen with results
- `FrameDessin.java` - Main game window with the card board

## How to Run

### Requirements
- Java JDK 8 or higher
- Image files in an `images/` folder

### Steps
1. Put all `.java` files in the same folder
2. Create an `images/` folder with your card images:
   - `im1.png` to `im10.png` (card images)
   - `fond.png` (back of cards)
3. Open terminal/command prompt
4. Compile: `javac *.java`
5. Run: `java MemoryGame`

## What I Learned

Through this project, I learned:
- How to create windows with Java Swing
- How to use buttons and labels
- How to handle mouse clicks
- How to use timers in Java
- How to organize code in multiple files
- How to create dialog boxes
- How to display images in a GUI

## Technologies Used

- **Language**: Java
- **GUI Library**: Java Swing
- **Development**: First year computer science project

---

*This is a learning project from my first year of computer science. It's one of my first experiences with graphical interfaces!* 🎓
