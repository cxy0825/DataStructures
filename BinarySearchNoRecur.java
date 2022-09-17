public class BinarySearchNoRecur {
    public static void main(String[] args) {
        int[] arr = {1, 2, 5, 7, 9, 11, 15, 16};
        System.out.println(binarySearch(arr, 100));
    }

    /**
     * 二分查找非递归实现
     *
     * @Param: arr 待查找的数组
     * @Param: target 需要查找的数
     * @Return: 返回对应的下标 -1表示没有
     */
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right);
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }

        }
        return -1;
    }
}
