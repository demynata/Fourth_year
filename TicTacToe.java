import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToe {

    private char[][] board;
    private char currentPlayer;
    private Scanner scanner;

    // Конструктор: ініціалізує гру
    public TicTacToe() {
        board = new char[3][3];
        currentPlayer = 'X';
        scanner = new Scanner(System.in);
        initializeBoard();
    }

    // Ініціалізація поля
    public void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    // Друк ігрового поля в консоль
    public void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    // Зміна поточного гравця
    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    // Отримання та підтвердження ходу гравця
    public boolean makeMove() {
        int row, col;
        while (true) {
            System.out.println("Гравець " + currentPlayer + ", введiть ваш хiд (рядок та стовпець, 0-2): ");
            try {
                row = scanner.nextInt();
                col = scanner.nextInt();

                // Чи координати в межах (0, 1, 2)?
                if (row >= 0 && row < 3 && col >= 0 && col < 3) {
                    // Чи клітинка вільна?
                    if (board[row][col] == '-') {
                        board[row][col] = currentPlayer; // Робимо хід
                        return true; // Успішний хід
                    } else {
                        System.out.println("Ця клiтинка вже зайнята. Спробуйте ще раз.");
                    }
                } else {
                    System.out.println("Невiрнi координати. Введiть числа вiд 0 до 2.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Невiрний ввiд. Введiть два числа, роздiлені пробiлом.");
                scanner.nextLine(); // Очистка буфера сканера
            }
        }
    }

    // Перевірка на перемогу
    private boolean checkWin() {
        // Перевірка рядків
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
        }
        // Перевірка стовпців
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == currentPlayer && board[1][j] == currentPlayer && board[2][j] == currentPlayer) {
                return true;
            }
        }
        // Перевірка діагоналей
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            return true;
        }
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
            return true;
        }
        return false;
    }

    // Перевірка на нічию (якщо поле заповнене)
    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false; // Знайшли порожню клітинку, гра триває
                }
            }
        }
        return true; // Порожніх клітинок немає
    }

    // Головний ігровий цикл
    public void play() {
        System.out.println("Хрестики-Нулики");
        printBoard();

        boolean gameActive = true;

        while (gameActive) {
            makeMove(); // Гравець робить хід
            printBoard(); // Показуємо оновлене поле

            // чи виграв поточний гравець
            if (checkWin()) {
                System.out.println("Гравець " + currentPlayer + " перемiг");
                gameActive = false;
            } else if (isBoardFull()) {
                System.out.println("Гра закiнчилась в нiчию!");
                gameActive = false;
            } else {
                switchPlayer();
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.play();
    }
}