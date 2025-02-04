# MINIMAX Game AI – Cube Removal Strategy Game

## Overview
This project implements a **two-player strategy game** where the computer (**MAX**) plays optimally against a human opponent (**MIN**) using the **Minimax algorithm**. The game is played with **white (M1) and black (M2) cubes**, and players take turns removing cubes based on defined rules.

## Game Rules
- The game starts with **M1 white** and **M2 black** cubes on a table
- On each turn, a player must remove **one of the following**:
  1. **Remove 1 cube** (either color)
  2. **Remove 2 white + 1 black cube**
  3. **Remove 2 black + 1 white cube**
- The game **ends** when all cubes are removed
- The **winner** is the player who makes the last move **if at least one white cube is removed**
- If no white cube is removed in the final move, the game is a **draw**
- **MAX (AI)** always plays first and selects the optimal move using the **Minimax algorithm**

## Features
- **Minimax Algorithm Implementation**  
  - The AI player evaluates the best possible move recursively
- **Turn-Based Gameplay**  
  - Alternating moves between the AI and the human player
- **Game State Evaluation**  
  - Determines **win, loss, or draw** based on the final move
- **Recursive Search for Best Move**  
  - Explores the entire game tree for optimal decision-making

## Technologies Used
- **Programming Language**: Java  
- **Algorithm**: MINIMAX (Recursive Implementation)  
- **Data Structures**: Tree Search for Move Evaluation

## Credits
Eleni Mouzaki

Panagiotis Papaioannou
