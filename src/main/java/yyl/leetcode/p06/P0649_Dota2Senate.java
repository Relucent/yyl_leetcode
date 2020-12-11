package yyl.leetcode.p06;

import java.util.ArrayDeque;
import java.util.Queue;

import yyl.leetcode.util.Assert;

/**
 * <h3>Dota2 参议院</h3><br>
 * Dota2 的世界里有两个阵营：Radiant(天辉)和 Dire(夜魇)<br>
 * Dota2 参议院由来自两派的参议员组成。现在参议院希望对一个 Dota2 游戏里的改变作出决定。他们以一个基于轮为过程的投票进行。在每一轮中，每一位参议员都可以行使两项权利中的一项：<br>
 * 1、 禁止一名参议员的权利：<br>
 * └ 参议员可以让另一位参议员在这一轮和随后的几轮中丧失所有的权利。<br>
 * 2、 宣布胜利：<br>
 * └ 如果参议员发现有权利投票的参议员都是同一个阵营的，他可以宣布胜利并决定在游戏中的有关变化。<br>
 * 给定一个字符串代表每个参议员的阵营。字母 “R” 和 “D” 分别代表了 Radiant（天辉）和 Dire（夜魇）。然后，如果有 n 个参议员，给定字符串的大小将是 n。<br>
 * 以轮为基础的过程从给定顺序的第一个参议员开始到最后一个参议员结束。这一过程将持续到投票结束。所有失去权利的参议员将在过程中被跳过。<br>
 * 假设每一位参议员都足够聪明，会为自己的政党做出最好的策略，你需要预测哪一方最终会宣布胜利并在 Dota2 游戏中决定改变。输出应该是 Radiant 或 Dire。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入："RD"
 * 输出："Radiant"
 * 解释：第一个参议员来自 Radiant 阵营并且他可以使用第一项权利让第二个参议员失去权力，因此第二个参议员将被跳过因为他没有任何权利。然后在第二轮的时候，第一个参议员可以宣布胜利，因为他是唯一一个有投票权的人
 * 
 * 示例 2：
 * 输入："RDD"
 * 输出："Dire"
 * 解释：
 * 第一轮中,第一个来自 Radiant 阵营的参议员可以使用第一项权利禁止第二个参议员的权利
 * 第二个来自 Dire 阵营的参议员会被跳过因为他的权利被禁止
 * 第三个来自 Dire 阵营的参议员可以使用他的第一项权利禁止第一个参议员的权利
 * 因此在第二轮只剩下第三个参议员拥有投票的权利,于是他可以宣布胜利
 * </pre>
 * 
 * <br>
 * 提示：<br>
 * └ 给定字符串的长度在 [1, 10,000] 之间.<br>
 */

public class P0649_Dota2Senate {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals("Radiant", solution.predictPartyVictory("RD"));
        Assert.assertEquals("Dire", solution.predictPartyVictory("RDD"));
    }

    // 队列模拟 + 贪心
    // 为保准权利能有效利用，应该贪心地挑选按照投票顺序的下一名对方的议员。（如果挑选了对方其它较晚投票的议员，下一个对方可以行使权利使本方议员丧失权利）
    // 使用两个队列 radiant 和 dire 分别按照投票顺序存储天辉方和夜魇方每一名议员的投票时间，之开始模拟整个投票的过程。
    // ├ 如果 radiant 或者 dire 均不为空，那么比较这两个队列的首元素，就可以确定当前行使权利的是哪一名议员：
    // │ ├ 如果 radiant 的首元素较小，那说明轮到天辉方的议员行使权利，其会挑选 dire 的首元素对应的那一名议员。因此，将 dire的首元素永久地弹出，并将 radiant的首元素弹出之后再重新放回队列，即表示该议员会参与下一轮的投票。
    // │ └ 如果 dire 的首元素较小，那说明轮到夜魇方的议员行使权利，那么将 radiant的首元素永久地弹出，并将 dire的首元素弹出之后再重新放回队列
    // └ 如果 radiant 或者 dire 为空，那么就可以宣布另一方获得胜利；
    // 时间复杂度：O(n)，其中 n 是字符串 senate 的长度。在模拟整个投票过程的每一步，我们进行的操作的时间复杂度均为 O(1)，并且会弹出一名天辉方或夜魇方的议员。由于议员的数量为 n ，因此模拟的步数不会超过 n，时间复杂度即为 O(n)。
    // 空间复杂度：O(n)，即为两个队列需要使用的空间。
    static class Solution {
        public String predictPartyVictory(String senate) {
            int n = senate.length();
            Queue<Integer> radiantIdxs = new ArrayDeque<>();
            Queue<Integer> direIdxs = new ArrayDeque<>();
            int index = 0;
            while (index < n) {
                char c = senate.charAt(index);
                if (c == 'R') {
                    radiantIdxs.offer(index);
                } else {
                    direIdxs.offer(index);
                }
                index++;
            }
            while (!radiantIdxs.isEmpty() && !direIdxs.isEmpty()) {
                int radiantIdx = radiantIdxs.poll();
                int direIdx = direIdxs.poll();
                if (radiantIdx < direIdx) {
                    radiantIdxs.offer(index);
                } else {
                    direIdxs.offer(index);
                }
                index++;
            }
            return direIdxs.isEmpty() ? "Radiant" : "Dire";
        }
    }
}
