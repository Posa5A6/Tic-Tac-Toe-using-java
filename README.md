A Tic-Tac-Toe game is a classic two-player game where the players take turns marking either "X" or "O" in a 3x3 grid. The objective of the game is for a player to place three of their marks in a horizontal, vertical, or diagonal row to win. If all nine cells are filled without a winner, the game is considered a draw.

Here’s a breakdown of the code components for a simple Tic-Tac-Toe game in Java:

1. Game Board Initialization
A 3x3 array is used to represent the grid. Each cell can be marked by an "X", "O", or left empty.
The board is displayed after every move so players can see the current state.
2. Player Turns
The program alternates turns between Player X and Player O.
Players are prompted to select a cell by entering a row and column number.
Input validation ensures that players can ojnly select empty cells and that their choices are within bounds.
3. Win and Draw Conditions
The program checks for a win condition after each move. A player wins if they complete a row, column, or diagonal with their mark.
If no win is detected and all cells are filled, the game ends in a draw.
4. Restart Option
Players may be prompted to restart the game after a win or draw.
