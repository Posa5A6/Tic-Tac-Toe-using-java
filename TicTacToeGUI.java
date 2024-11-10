import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI extends JFrame implements ActionListener {
    private JButton[][] buttons = new JButton[3][3];
    private char currentPlayer = 'X';
    private JLabel statusLabel;
    private JButton startButton, restartButton, exitButton;
    private boolean gameStarted = false;

    public TicTacToeGUI() {
        setTitle("Tic-Tac-Toe Game");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create status label
        statusLabel = new JLabel("Click 'Start' to play", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(statusLabel, BorderLayout.NORTH);

        // Create game board
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3));
        Font buttonFont = new Font("Arial", Font.BOLD, 60);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton(" ");
                buttons[i][j].setFont(buttonFont);
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].setEnabled(false); // Disable buttons until game starts
                buttons[i][j].addActionListener(this);
                boardPanel.add(buttons[i][j]);
            }
        }
        
        add(boardPanel, BorderLayout.CENTER);

        // Create control panel for start, restart, and exit buttons
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(1, 3));

        startButton = new JButton("Start");
        startButton.addActionListener(e -> startGame());
        controlPanel.add(startButton);

        restartButton = new JButton("Restart");
        restartButton.addActionListener(e -> restartGame());
        restartButton.setEnabled(false); // Disable restart until game starts
        controlPanel.add(restartButton);

        exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));
        controlPanel.add(exitButton);

        add(controlPanel, BorderLayout.SOUTH);
    }

    // Start the game
    private void startGame() {
        gameStarted = true;
        currentPlayer = 'X';
        statusLabel.setText("Player X's turn");
        enableButtons(true);
        startButton.setEnabled(false); // Disable start after game has begun
        restartButton.setEnabled(true); // Enable restart during game
    }

    // Restart the game
    private void restartGame() {
        currentPlayer = 'X';
        statusLabel.setText("Player X's turn");
        clearBoard();
        enableButtons(true);
    }

    // Clear the board for a new game
    private void clearBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText(" ");
                buttons[i][j].setEnabled(true);
            }
        }
    }

    // Enable or disable all buttons
    private void enableButtons(boolean enable) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(enable);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameStarted) return; // Prevent moves before the game starts

        JButton buttonClicked = (JButton) e.getSource();
        
        // Check if the button is already used
        if (!buttonClicked.getText().equals(" ")) {
            return;
        }

        // Set the player's mark (X or O) and disable the button
        buttonClicked.setText(String.valueOf(currentPlayer));

        // Check if the player has won or if it is a draw
        if (checkWin()) {
            statusLabel.setText("Player " + currentPlayer + " wins!");
            enableButtons(false); // Disable buttons after a win
        } else if (checkDraw()) {
            statusLabel.setText("It's a draw!");
        } else {
            // Switch player and update status
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            statusLabel.setText("Player " + currentPlayer + "'s turn");
        }
    }

    // Function to check for a win
    private boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[i][1].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[i][2].getText().equals(String.valueOf(currentPlayer))) {
                return true;
            }
            if (buttons[0][i].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[1][i].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[2][i].getText().equals(String.valueOf(currentPlayer))) {
                return true;
            }
        }
        if (buttons[0][0].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[2][2].getText().equals(String.valueOf(currentPlayer))) {
            return true;
        }
        if (buttons[0][2].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[2][0].getText().equals(String.valueOf(currentPlayer))) {
            return true;
        }
        return false;
    }

    // Function to check for a draw
    private boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TicTacToeGUI game = new TicTacToeGUI();
            game.setVisible(true);
        });
    }
}
