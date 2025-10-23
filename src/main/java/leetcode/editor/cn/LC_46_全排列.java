package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

class LC_46_全排列 {
    //2025-10-23 19:48:12
    //全排列
    //编号：[46]

    public static void main(String[] args) {
        Solution solution = new LC_46_全排列().new Solution();
        // TO TEST
        int[] nums = new int[]{1, 2, 3};
        List<List<Integer>> results = solution.permute(nums);
        for (int i = 0; i < results.size(); i++) {
            System.out.println(results.get(i).stream().map(String::valueOf).collect(Collectors.joining(",")));
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        List<List<Integer>> results;
        boolean[] used;
        LinkedList<Integer> track;

        public List<List<Integer>> permute(int[] nums) {
            results = new ArrayList<>();
            used = new boolean[nums.length];
            track = new LinkedList<>();
            backtrack(nums);
            return results;
        }

        public void backtrack(int[] nums) {
            if (nums.length == track.size()) {
                results.add(new ArrayList<>(track));
                return;
            }

            for (int i = 0; i < nums.length; i++) {
                if (!used[i]) {
                    track.addLast(nums[i]);
                    used[i] = true;
                    backtrack(nums);
                    track.removeLast();
                    used[i] = false;
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}