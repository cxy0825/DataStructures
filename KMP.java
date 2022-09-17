import java.util.Arrays;

public class KMP {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        int[] next = kmpNext("ABCDABD");
        System.out.println("next" + Arrays.toString(next));

    }

    //获得kmp的next数组
    public static int[] kmpNext(String dest) {
        int[] next = new int[dest.length()];
        //第一个字符的next值一定是0
        next[0] = 0;
        for (int i = 1, j = 0;
             i < dest.length();
             i++) {
            //部分匹配值就+1

            //当deft.charAt(i) ！= deft.charAt(j)时，我们需要从next[j-1]中获取新的j值
            //直到我们发现有deft.charAt(i) == deft.charAt(j）才推出
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];

            }
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;

        }
        return next;
    }
    //kmp匹配算法

    /**
     * @Param: str1 源字符串
     * @Param: str2 要匹配的字符串
     * @Param: next next数组
     * @Return: int 如果是-1表示没有匹配到，否则返回匹配的位置
     */
    public static int kmpSearch(String str1, String str2, int[] next) {

        for (int i = 0, j = 0; i < str1.length(); i++) {
            //需要处理str1.charAt(i)!=str2.charAt(j)
            //KMP算法的核心点
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) {
                return i - j + 1;
            }

        }

        return -1;
    }
}
