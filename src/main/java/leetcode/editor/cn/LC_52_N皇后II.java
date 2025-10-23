package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class LC_52_N皇后II {
    //2025-10-23 20:32:49
    //N 皇后 II
    //编号：[52]

    public static void main(String[] args) {
        Solution solution = new LC_52_N皇后II().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int totalNQueens(int n) {
            int[][] chessboard = new int[n][n];
            backtrack(chessboard, 0);
            return count;
        }

        int count;

        public void backtrack(int[][] chessboard, int curQueues) {
            if (curQueues == chessboard[0].length) {
                count++;
            }

            for (int i = 0; i < chessboard[0].length; i++) {
                if (test(chessboard, curQueues, i)) {
                    chessboard[curQueues][i] = 1;
                    backtrack(chessboard, curQueues + 1);
                    chessboard[curQueues][i] = 0;
                }
            }
        }

        public boolean test(int[][] chessboard, int x, int y) {
            // 同一横线：不可能存在
            // 同一竖线
            for (int i = 0; i < x; i++) {
                if (chessboard[i][y] == 1) {
                    return false;
                }
            }
            // 左斜线
            for (int i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--) {
                if (chessboard[i][j] == 1) {
                    return false;
                }
            }
            // 右斜线
            for (int i = x - 1, j = y + 1; i >= 0 && j < chessboard[0].length; i--, j++) {
                if (chessboard[i][j] == 1) {
                    return false;
                }
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}