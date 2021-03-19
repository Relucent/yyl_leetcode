package yyl.leetcode.p16;

import yyl.leetcode.util.Assert;

/**
 * <h3>设计停车系统</h3><br>
 * 请你给一个停车场设计一个停车系统。停车场总共有三种不同大小的车位：大，中和小，每种尺寸分别有固定数目的车位。<br>
 * 请你实现 ParkingSystem 类：<br>
 * ├ ParkingSystem(int big, int medium, int small) 初始化 ParkingSystem 类，三个参数分别对应每种停车位的数目。<br>
 * └ bool addCar(int carType) 检查是否有 carType 对应的停车位。 carType 有三种类型：大，中，小，分别用数字 1， 2 和 3 表示。一辆车只能停在 carType 对应尺寸的停车位中。如果没有空车位，请返回 false ，否则将该车停入车位并返回 true 。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入：
 * ["ParkingSystem", "addCar", "addCar", "addCar", "addCar"]
 * [[1, 1, 0], [1], [2], [3], [1]]
 * 输出：
 * [null, true, true, false, false]
 * 
 * 解释：
 * ParkingSystem parkingSystem = new ParkingSystem(1, 1, 0);
 * parkingSystem.addCar(1); // 返回 true ，因为有 1 个空的大车位
 * parkingSystem.addCar(2); // 返回 true ，因为有 1 个空的中车位
 * parkingSystem.addCar(3); // 返回 false ，因为没有空的小车位
 * parkingSystem.addCar(1); // 返回 false ，因为没有空的大车位，唯一一个大车位已经被占据了
 * </pre>
 * 
 * 提示：<br>
 * ├ 0 <= big, medium, small <= 1000<br>
 * ├ carType 取值为 1， 2 或 3<br>
 * └最多会调用 addCar 函数 1000 次<br>
 */
public class P1603_DesignParkingSystem {

    public static void main(String[] args) {
        ParkingSystem parkingSystem = new ParkingSystem(1, 1, 0);
        Assert.assertTrue(parkingSystem.addCar(1)); // 返回 true ，因为有 1 个空的大车位
        Assert.assertTrue(parkingSystem.addCar(2)); // 返回 true ，因为有 1 个空的中车位
        Assert.assertFalse(parkingSystem.addCar(3)); // 返回 false ，因为没有空的小车位
        Assert.assertFalse(parkingSystem.addCar(1)); // 返回 false ，因为没有空的大车位，唯一一个大车位已经被占据了
    }

    // 模拟
    // 为每种车维护一个计数器，初始值为车位的数目。此后，每来一辆车，就将对应类型的计数器减 1 。当计数器为 0 时，说明车位已满。
    // 时间复杂度：O(1)。
    // 空间复杂度：O(1)。
    static class ParkingSystem {

        private int[] limit;

        public ParkingSystem(int big, int medium, int small) {
            limit = new int[] { big, medium, small };
        }

        public boolean addCar(int carType) {
            if (limit[carType - 1] == 0) {
                return false;
            }
            limit[carType - 1]--;
            return true;
        }
    }
}
