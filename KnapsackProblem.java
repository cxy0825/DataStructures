public class KnapsackProblem {
    public static void main(String[] args) {
        int[] w = {1, 4, 3};//物品的重量
        int[] val = {1500, 3000, 2000};//物品价值
        int m = 4;//背包容量
        int n = val.length;//物品个数

        //创建二位数组
        //+1是为了预留出0个的位置
        int[][] v = new int[n + 1][m + 1];
        //记录放入商品的情况
        int[][] path = new int[n + 1][m + 1];
//        初始化第一行第一列
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;

        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;
        }
        //动态规划
        //i表示有多少件物品
        //j表示当前背包有多少的权重
        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[i].length; j++) {
                //如果当前物品的重量大于当前
                if (w[i - 1] > j) {
                    v[i][j] = v[i - 1][j];
                } else {
//                    v[i][j] = Math.max(v[i - 1][j], v[i - 1][j - w[i - 1]] + val[i - 1]);
                    //为了记录商品存放到背包的情况我们要自己写if else使用
                    if (v[i - 1][j] < v[i - 1][j - w[i - 1]] + val[i - 1]) {
                        v[i][j] = v[i - 1][j - w[i - 1]] + val[i - 1];
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();

        }
        //最后输出我们放入那些物品
        //思路是反着来，我们最后一个格子表示的是我们放着 i号产品
        //所以拿着i号产品的重量减去总重量，就可以得到剩余的重量里最有价值的
        //这时候如果该格的path是1，则说明最后那种商品是在里面的（这里是电脑），除了电脑包容量只剩4-电脑，可以放的东西只有前面那两个了
        int i = path.length - 1;
        int j = path[0].length - 1;
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                System.out.println("放入了第" + i + "号物品");
                //获得剩余的重量，只要前面有1这个重量就是最优的组成部分之一
                j -= w[i - 1];
                //如果j是0说明已经找完了
            }
            //每次扫描每一行的最后一个格子
            //最后一列才是我们要找的重量的格子
            i--;
        }
    }
}
