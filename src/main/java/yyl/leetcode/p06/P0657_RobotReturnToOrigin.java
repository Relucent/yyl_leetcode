package yyl.leetcode.p06;

import yyl.leetcode.util.Assert;

/**
 * <h3>机器人能否返回原点</h3><br>
 * 在二维平面上，有一个机器人从原点 (0, 0) 开始。给出它的移动顺序，判断这个机器人在完成移动后是否在 (0, 0) 处结束。<br>
 * 移动顺序由字符串表示。字符 move[i] 表示其第 i 次移动。机器人的有效动作有 R（右），L（左），U（上）和 D（下）。如果机器人在完成所有动作后返回原点，则返回 true。否则，返回 false。<br>
 * 注意：机器人“面朝”的方向无关紧要。 “R” 将始终使机器人向右移动一次，“L” 将始终向左移动等。此外，假设每次移动机器人的移动幅度相同。<br>
 * 
 * <pre>
 * 示例 1:
 * 输入: "UD"
 * 输出: true
 * 解释：机器人向上移动一次，然后向下移动一次。所有动作都具有相同的幅度，因此它最终回到它开始的原点。因此，我们返回 true。
 * 
 * 示例 2:
 * 输入: "LL"
 * 输出: false
 * 解释：机器人向左移动两次。它最终位于原点的左侧，距原点有两次 “移动” 的距离。我们返回 false，因为它在移动结束时没有返回原点。
 * </pre>
 */
public class P0657_RobotReturnToOrigin {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertTrue(solution.judgeCircle("UD"));
        Assert.assertFalse(solution.judgeCircle("LL"));
    }

    // 模拟法
    // 算法思路
    // 只需按指令模拟机器人移动的坐标即可。起始时机器人的坐标为 (0,0)，在遍历完所有指令并对机器人进行移动之后，判断机器人的坐标是否为 (0,0) 即可。
    // 1、如果指令是 U ，则令 y=y−1
    // 2、如果指令是 D ，则令 y=y+1
    // 3、如果指令是 L ，则令 x=x−1
    // 4、如果指令是 R ，则令 x=x+1
    // 5、最后判断 (x,y) 是否为 (0,0) 即可。
    // 时间复杂度： O(N)，其中 N 表示 moves指令串的长度。只需要遍历一遍字符串即可。
    // 空间复杂度： O(1)。只需要常数的空间来存放若干变量。
    static class Solution {
        public boolean judgeCircle(String moves) {
            int n = moves.length();
            int x = 0;
            int y = 0;
            for (int i = 0; i < n; i++) {
                char c = moves.charAt(i);
                if (c == 'U') {
                    y--;
                } else if (c == 'D') {
                    y++;
                } else if (c == 'L') {
                    x--;
                } else if (c == 'R') {
                    x++;
                }
            }
            return x == 0 && y == 0;
        }
    }

    // 计数法
    // 如果需要返回原点，指令U和D、L和R必须是成对出现的
    // 设 U、D、L、R 的个数分别为 u,d,l,r
    // 如果 u==d && l==r 那么说明可以返回原点，则返回 true；否则，返回 false。
    // 时间复杂度： O(N)，其中 N 表示 moves指令串的长度。只需要遍历一遍字符串即可。
    // 空间复杂度： O(1)。只需要常数的空间来存放若干变量。
    static class Solution1 {
        public boolean judgeCircle(String moves) {
            int[] letters = new int[26 + 'A'];
            moves.chars().forEach(c -> letters[c]++);
            return letters['U'] == letters['D'] && letters['L'] == letters['R'];
        }
    }
}
