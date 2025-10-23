<p>按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。</p>

<p><strong>n&nbsp;皇后问题</strong> 研究的是如何将 <code>n</code>&nbsp;个皇后放置在 <code>n×n</code> 的棋盘上，并且使皇后彼此之间不能相互攻击。</p>

<p>给你一个整数 <code>n</code> ，返回所有不同的&nbsp;<strong>n<em>&nbsp;</em>皇后问题</strong> 的解决方案。</p>

<div class="original__bRMd"> 
 <div> 
  <p>每一种解法包含一个不同的&nbsp;<strong>n 皇后问题</strong> 的棋子放置方案，该方案中 <code>'Q'</code> 和 <code>'.'</code> 分别代表了皇后和空位。</p> 
 </div>
</div>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2020/11/13/queens.jpg" style="width: 600px; height: 268px;" /> 
<pre>
<strong>输入：</strong>n = 4
<strong>输出：</strong>[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
<strong>解释：</strong>如上图所示，4 皇后问题存在两个不同的解法。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 1
<strong>输出：</strong>[["Q"]]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>1 &lt;= n &lt;= 9</code></li> 
</ul>

<details><summary><strong>Related Topics</strong></summary>数组 | 回溯</details><br>

<div>👍 2359, 👎 0<span style='float: right;'><span style='color: gray;'><a href='https://github.com/labuladong/fucking-algorithm/issues' target='_blank' style='color: lightgray;text-decoration: underline;'>bug 反馈</a> | <a href='https://labuladong.online/algo/fname.html?fname=jb插件简介' target='_blank' style='color: lightgray;text-decoration: underline;'>使用指南</a> | <a href='https://labuladong.online/algo/' target='_blank' style='color: lightgray;text-decoration: underline;'>更多配套插件</a></span></span></div>

<div id="labuladong"><hr>

**通知：为满足广大读者的需求，网站上架 [速成目录](https://labuladong.online/algo/intro/quick-learning-plan/)，如有需要可以看下，谢谢大家的支持~**



<p><strong><a href="https://labuladong.online/algo/practice-in-action/sudoku-nqueue/" target="_blank">⭐️labuladong 题解</a></strong></p>
<details><summary><strong>labuladong 思路</strong></summary>


<div id="labuladong_solution_zh">

## 基本思路

N 皇后问题就是一个决策问题：对于每一行，我应该选择在哪一列防止皇后呢？

这就是典型的回溯算法题目，回溯算法的框架如下：

```python
result = []
def backtrack(路径，选择列表):
    if 满足结束条件:
        result.add(路径)
        return

    for 选择 in 选择列表:
        做选择
        backtrack(路径，选择列表)
        撤销选择
```

回溯算法框架就是遍历决策树的过程：

![](https://labuladong.online/algo/images/backtracking/7.jpg)

关于回溯算法的详细讲解可以看 [46. 全排列](/problems/permutations) 或者详细题解。

**详细题解**：
  - [回溯算法实践：数独和 N 皇后问题](https://labuladong.online/algo/practice-in-action/sudoku-nqueue/)

</div>





<div id="solution">

## 解法代码



<div class="tab-panel"><div class="tab-nav">
<button data-tab-item="cpp" class="tab-nav-button btn " data-tab-group="default" onclick="switchTab(this)">cpp🤖</button>

<button data-tab-item="python" class="tab-nav-button btn " data-tab-group="default" onclick="switchTab(this)">python🤖</button>

<button data-tab-item="java" class="tab-nav-button btn active" data-tab-group="default" onclick="switchTab(this)">java🟢</button>

<button data-tab-item="go" class="tab-nav-button btn " data-tab-group="default" onclick="switchTab(this)">go🤖</button>

<button data-tab-item="javascript" class="tab-nav-button btn " data-tab-group="default" onclick="switchTab(this)">javascript🤖</button>
</div><div class="tab-content">
<div data-tab-item="cpp" class="tab-item " data-tab-group="default"><div class="highlight">

```cpp
// 注意：cpp 代码由 chatGPT🤖 根据我的 java 代码翻译。
// 本代码的正确性已通过力扣验证，如有疑问，可以对照 java 代码查看。

#include <vector>
#include <string>

class Solution {
public:
    // 输入棋盘边长 n，返回所有合法的放置
    std::vector<std::vector<std::string>> solveNQueens(int n) {
        // '.' 表示空，'Q' 表示皇后，初始化空棋盘。
        std::vector<std::string> board(n, std::string(n, '.'));
        backtrack(board, 0);
        return res;
    }

private:
    std::vector<std::vector<std::string>> res;

    // 路径：board 中小于 row 的那些行都已经成功放置了皇后
    // 选择列表：第 row 行的所有列都是放置皇后的选择
    // 结束条件：row 超过 board 的最后一行
    void backtrack(std::vector<std::string>& board, int row) {
        // 触发结束条件
        if (row == board.size()) {
            res.push_back(board);
            return;
        }

        int n = board[row].size();
        for (int col = 0; col < n; col++) {
            // 排除不合法选择
            if (!isValid(board, row, col)) {
                continue;
            }
            // 做选择
            board[row][col] = 'Q';
            // 进入下一行决策
            backtrack(board, row + 1);
            // 撤销选择
            board[row][col] = '.';
        }
    }

    // 是否可以在 board[row][col] 放置皇后？
    bool isValid(const std::vector<std::string>& board, int row, int col) {
        int n = board.size();
        // 检查列是否有皇后互相冲突
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q')
                return false;
        }
        // 检查右上方是否有皇后互相冲突
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q')
                return false;
        }
        // 检查左上方是否有皇后互相冲突
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q')
                return false;
        }
        return true;
    }
};
```

</div></div>

<div data-tab-item="python" class="tab-item " data-tab-group="default"><div class="highlight">

```python
# 注意：python 代码由 chatGPT🤖 根据我的 java 代码翻译。
# 本代码的正确性已通过力扣验证，如有疑问，可以对照 java 代码查看。

class Solution:
    def __init__(self):
        self.res = []

    # 输入棋盘边长 n，返回所有合法的放置
    def solveNQueens(self, n: int) -> List[List[str]]:
        # '.' 表示空，'Q' 表示皇后，初始化空棋盘。
        board = ["." * n for _ in range(n)]
        self.backtrack(board, 0)
        return self.res

    # 路径：board 中小于 row 的那些行都已经成功放置了皇后
    # 选择列表：第 row 行的所有列都是放置皇后的选择
    # 结束条件：row 超过 board 的最后一行
    def backtrack(self, board: List[str], row: int) -> None:
        # 触发结束条件
        if row == len(board):
            self.res.append(board[:])
            return
        
        n = len(board)
        for col in range(n):
            # 排除不合法选择
            if not self.isValid(board, row, col):
                continue
            # 做选择
            board[row] = board[row][:col] + 'Q' + board[row][col+1:]
            # 进入下一行决策
            self.backtrack(board, row + 1)
            # 撤销选择
            board[row] = board[row][:col] + '.' + board[row][col+1:]

    # 是否可以在 board[row][col] 放置皇后？
    def isValid(self, board: List[str], row: int, col: int) -> bool:
        n = len(board)
        # 检查列是否有皇后互相冲突
        for i in range(row):
            if board[i][col] == 'Q':
                return False
        # 检查右上方是否有皇后互相冲突
        for i, j in zip(range(row - 1, -1, -1), range(col + 1, n)):
            if board[i][j] == 'Q':
                return False
        # 检查左上方是否有皇后互相冲突
        for i, j in zip(range(row - 1, -1, -1), range(col - 1, -1, -1)):
            if board[i][j] == 'Q':
                return False
        return True
```

</div></div>

<div data-tab-item="java" class="tab-item active" data-tab-group="default"><div class="highlight">

```java
class Solution {
    private List<List<String>> res = new ArrayList<>();

    // 输入棋盘边长 n，返回所有合法的放置
    public List<List<String>> solveNQueens(int n) {
        // '.' 表示空，'Q' 表示皇后，初始化空棋盘。
        List<String> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            board.add(".".repeat(n));
        }
        backtrack(board, 0);
        return res;
    }

    // 路径：board 中小于 row 的那些行都已经成功放置了皇后
    // 选择列表：第 row 行的所有列都是放置皇后的选择
    // 结束条件：row 超过 board 的最后一行
    private void backtrack(List<String> board, int row) {
        // 触发结束条件
        if (row == board.size()) {
            res.add(new ArrayList<>(board));
            return;
        }

        int n = board.get(row).length();
        for (int col = 0; col < n; col++) {
            // 排除不合法选择
            if (!isValid(board, row, col)) {
                continue;
            }
            // 做选择
            char[] newRow = board.get(row).toCharArray();
            newRow[col] = 'Q';
            board.set(row, new String(newRow));
            // 进入下一行决策
            backtrack(board, row + 1);
            // 撤销选择
            newRow[col] = '.';
            board.set(row, new String(newRow));
        }
    }

    // 是否可以在 board[row][col] 放置皇后？
    private boolean isValid(List<String> board, int row, int col) {
        int n = board.size();
        // 检查列是否有皇后互相冲突
        for (int i = 0; i < row; i++) {
            if (board.get(i).charAt(col) == 'Q')
                return false;
        }
        // 检查右上方是否有皇后互相冲突
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board.get(i).charAt(j) == 'Q')
                return false;
        }
        // 检查左上方是否有皇后互相冲突
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board.get(i).charAt(j) == 'Q')
                return false;
        }
        return true;
    }
}
```

</div></div>

<div data-tab-item="go" class="tab-item " data-tab-group="default"><div class="highlight">

```go
// 注意：go 代码由 chatGPT🤖 根据我的 java 代码翻译。
// 本代码的正确性已通过力扣验证，如有疑问，可以对照 java 代码查看。

var res [][]string

// 输入棋盘边长 n，返回所有合法的放置
func solveNQueens(n int) [][]string {
    res = [][]string{}
    // '.' 表示空，'Q' 表示皇后，初始化空棋盘。
    board := make([]string, n)
    for i := 0; i < n; i++ {
        board[i] = strings.Repeat(".", n)
    }
    backtrack(board, 0)
    return res
}

// 路径：board 中小于 row 的那些行都已经成功放置了皇后
// 选择列表：第 row 行的所有列都是放置皇后的选择
// 结束条件：row 超过 board 的最后一行
func backtrack(board []string, row int) {
    // 触发结束条件
    if row == len(board) {
        temp := make([]string, len(board))
        copy(temp, board)
        res = append(res, temp)
        return
    }

    n := len(board[row])
    for col := 0; col < n; col++ {
        // 排除不合法选择
        if !isValid(board, row, col) {
            continue
        }
        // 做选择
        newRow := []rune(board[row])
        newRow[col] = 'Q'
        board[row] = string(newRow)
        // 进入下一行决策
        backtrack(board, row + 1)
        // 撤销选择
        newRow[col] = '.'
        board[row] = string(newRow)
    }
}

// 是否可以在 board[row][col] 放置皇后？
func isValid(board []string, row int, col int) bool {
    n := len(board)
    // 检查列是否有皇后互相冲突
    for i := 0; i < row; i++ {
        if board[i][col] == 'Q' {
            return false
        }
    }
    // 检查右上方是否有皇后互相冲突
    for i, j := row-1, col+1; i >= 0 && j < n; i, j = i-1, j+1 {
        if board[i][j] == 'Q' {
            return false
        }
    }
    // 检查左上方是否有皇后互相冲突
    for i, j := row-1, col-1; i >= 0 && j >= 0; i, j = i-1, j-1 {
        if board[i][j] == 'Q' {
            return false
        }
    }
    return true
}
```

</div></div>

<div data-tab-item="javascript" class="tab-item " data-tab-group="default"><div class="highlight">

```javascript
// 注意：javascript 代码由 chatGPT🤖 根据我的 java 代码翻译。
// 本代码的正确性已通过力扣验证，如有疑问，可以对照 java 代码查看。

var solveNQueens = function(n) {
    let res = [];

    // 输入棋盘边长 n，返回所有合法的放置
    var solveNQueens = function(n) {
        // '.' 表示空，'Q' 表示皇后，初始化空棋盘。
        let board = [];
        for (let i = 0; i < n; i++) {
            board.push(".".repeat(n));
        }
        backtrack(board, 0);
        return res;
    };

    // 路径：board 中小于 row 的那些行都已经成功放置了皇后
    // 选择列表：第 row 行的所有列都是放置皇后的选择
    // 结束条件：row 超过 board 的最后一行
    var backtrack = function(board, row) {
        // 触发结束条件
        if (row === board.length) {
            res.push([...board]);
            return;
        }

        let n = board[row].length;
        for (let col = 0; col < n; col++) {
            // 排除不合法选择
            if (!isValid(board, row, col)) {
                continue;
            }
            // 做选择
            let newRow = board[row].split('');
            newRow[col] = 'Q';
            board[row] = newRow.join('');
            // 进入下一行决策
            backtrack(board, row + 1);
            // 撤销选择
            newRow[col] = '.';
            board[row] = newRow.join('');
        }
    };

    // 是否可以在 board[row][col] 放置皇后？
    var isValid = function(board, row, col) {
        let n = board.length;
        // 检查列是否有皇后互相冲突
        for (let i = 0; i < row; i++) {
            if (board[i][col] === 'Q') return false;
        }
        // 检查右上方是否有皇后互相冲突
        for (let i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] === 'Q') return false;
        }
        // 检查左上方是否有皇后互相冲突
        for (let i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] === 'Q') return false;
        }
        return true;
    };

    return solveNQueens(n);
};
```

</div></div>
</div></div>

<hr /><details open hint-container details><summary style="font-size: medium"><strong>🥳🥳 算法可视化 🥳🥳</strong></summary><div id="data_n-queens"  category="leetcode" ></div><div class="resizable aspect-ratio-container" style="height: 100%;">
<div id="iframe_n-queens"></div></div>
</details><hr /><br />

</div>
</details>
</div>

