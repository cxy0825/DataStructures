import java.util.Arrays;

public class Sort {
    public static void main(String[] args) {
        int arrLength = 8;
        int[] arr = new int[arrLength];
//        int[] arr = {
//                2, 1, 3, 1, 1, 2, 1, 3
//        };
        for (int i = 0; i < arrLength; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }

//        System.out.println(Arrays.toString(arr));
        Long timeStart = System.currentTimeMillis();
//        bubbleSort(arr);
//        selectSort(arr);
//        insertSort(arr);
//        shellSort(arr);
        quickSort(arr, 0, arr.length - 1);
        Long timeEnd = System.currentTimeMillis();
//        System.out.println("冒泡排序：共花费" + (timeEnd - timeStart) + "ms");
        System.out.println("选择排序：共花费" + (timeEnd - timeStart) + "ms  " + Arrays.toString(arr));

    }

    //冒泡排序
    public static void bubbleSort(int[] arr) {
        int temp;
        //用来判断一趟排序中有没有交换
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }

            }
            //一次也没交换说明已经这个数组有序了就直接退出
            if (!flag) {
                break;
            }
        }
    }

    //选择排序
    public static void selectSort(int[] arr) {
        int minIndex;
        int temp;
        for (int i = 0; i < arr.length - 1; i++) {
            minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[i]) {
                    minIndex = j;
                }
            }
//            交换位置
            if (minIndex != i) {
                temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }


    // 插入排序
    public static void insertSort(int[] arr) {
        //要进行插入的数据应该从1开始，默认一个数字就是有序的
        int insertValue;
        //要插入的位置 默认从 insertValue -1开始；
        int insertIndex;
        //从索引为1开始，因为第0个默认有序
        for (int i = 1; i < arr.length; i++) {
            insertValue = arr[i];
            insertIndex = i - 1;
            //从后面往前面比较
            //如果要插入的数字比前面有序列表的最后一个还要大就说明找到了就交换
//            {33,25}
            while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            //这个循环结束后j就是这个数字的索引

            arr[insertIndex + 1] = insertValue;
        }
    }

    //希尔排序
    public static void shellSort(int[] arr) {
        int temp;
        //一共多少趟
        for (int inc = arr.length / 2; inc > 0; inc /= 2) {
            for (int i = inc; i < arr.length; i++) {
                temp = arr[i];
                for (int j = i; j >= i && arr[j] < arr[j - i]; j -= i) {
                    arr[j] = arr[j - i];
                    arr[j - i] = temp;
                }

            }


        }
    }

    //快速排序
    public static void quickSort(int[] arr, int left, int right) {
        //说明数组中就一个元素
        if (right - left <= 1) {
            return;
        }
        int l = left;//左边指针
        int r = right;//右边指针
        int povit = arr[(l + r) / 2];//中轴
        int temp;
        while (l < r) {
            //在左边找到一个比中轴更大的值就退出
            while (arr[l] < povit) {
                l++;
            }
            //在右边找到一个比中轴更小的值就退出
            while (arr[r] > povit) {
                r--;
            }
//            如果l>=r说明两边的值全部按照左边小于中轴右边大于中轴排列
            if (l >= r) {
                break;
            }
            //交换两边的数值
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
//            例子
//            {123,123,123}
//            如果有重复数值交换后，就需要移动l和r 要不然会一直死循环
            if (arr[l] == povit) {
                r--;
            }
            if (arr[r] == povit) {
                l++;
            }
            //这样移动后可能会出现l=r，必须要l++和r--交换，这样才能正确的分成左右两边
            //l++后就变成了右边数的左轴left
            //r--后就变成左边数的右轴right
//            一直递归
            //如果左边的下标比第一个要大说明没有排完
            if (r > left) {
                quickSort(arr, 0, r);
            }
            //如果右边的下标比最后一个要小说明没有排完
            if (right > l) {
                quickSort(arr, l, right);
            }

        }


    }

}
