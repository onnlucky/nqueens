import java.util.BitSet;

import org.junit.Test;
import static org.junit.Assert.*;

public class NQueensTest {

    String markedToString(int N, BitSet marked) {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                sb.append(marked.get(row * N + col) ? "x" : " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Test
    public void markQueen() {
        int N = 4;
        int[] board = new int[N];
        BitSet marked = new BitSet(N * N);
        int row = 1;
        int col = 1;
        NQueens.markQueen(board, marked, row, col);

        assertTrue(marked.get(row * N + col));
        assertTrue(marked.get(N * N - 1));
        assertFalse(marked.get(N * N - 2));
        assertEquals(markedToString(N, marked),
                "xxx \n"+
                "xxxx\n" +
                "xxx \n" +
                " x x\n");
    }

    @Test
    public void markLines() {
        int N = 4;
        int[] board = new int[N];
        BitSet marked = new BitSet(N * N);
        int row = 1;
        int col = 1;
        NQueens.markLines(board, marked, row, col);
        assertEquals(markedToString(N, marked),
                "    \n" +
                "    \n" +
                "  x \n" +
                "   x\n");
    }
}