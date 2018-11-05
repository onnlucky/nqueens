import java.util.Arrays;
import java.util.BitSet;

public class NQueens {
    public static void main(String[] args) {
        queens(8);
    }

    static void queens(int N) {
        int[] board = new int[N];
        BitSet marked = new BitSet(N * N);
        queenForRow(board, marked, 0);
    }

    // always 1 queen per row (or col) so we try all positions in a row, recursing to the next row and backtracking
    // we mark off all impossible positions
    static void queenForRow(int[] board, BitSet marked, int row) {
        int N = board.length;
        BitSet nextMarked = new BitSet(marked.length());

        for (int col = 0; col < N; col++) {
            if (marked.get(row * N + col)) continue;

            board[row] = col;

            // last row
            if (row == N - 1) {
                System.out.println(Arrays.toString(board));
                continue;
            }


            // make a copy of the marked off board
            nextMarked.clear();
            nextMarked.or(marked);

            // mark queen and line rules
            markQueen(board, nextMarked, row, col);
//            markLines(board, nextMarked, row, col);

            // move to queen on next row
            queenForRow(board, nextMarked, row + 1);
        }
    }

    static void markQueen(int[] board, BitSet taken, int row, int col) {
        int N = board.length;

        for (int i = 0; i < N; i++) {
            taken.set(N * row + i); // mark full row
            taken.set(N * i + col); // mark full column

            // mark 4 directions of cross
            int colSub = col - i;
            int colAdd = col + i;
            int rowSub = row - i;
            int rowAdd = row + i;
            if (colSub >= 0) {
                if (rowSub >= 0) taken.set(rowSub * N + colSub);
                if (rowAdd < N) taken.set(rowAdd * N + colSub);
            }
            if (colAdd < N) {
                if (rowSub >= 0) taken.set(rowSub * N + colAdd);
                if (rowAdd < N) taken.set(rowAdd * N + colAdd);
            }
        }
    }

    static void markLines(int[] board, BitSet taken, int row, int col) {
        int N = board.length;

        // project lines from other queens
        for (int i = 0; i < row; i++) {
            int delta_row = row - i;
            int delta_col = col - board[i];
            for (int j = 1; j < N; j++) {
                int r = row + delta_row * j;
                int c = col + delta_col * j;
                if (r < 0 || r >= N) break;
                if (c < 0 || c >= N) break;
                taken.set(r * N + c);
            }
        }
    }
}
