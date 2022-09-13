public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        CircleArrayQueue circleArrayQueue = new CircleArrayQueue(4);
        circleArrayQueue.add(1);
        circleArrayQueue.add(2);
        circleArrayQueue.add(3);
        circleArrayQueue.add(3);
        circleArrayQueue.showQueue();
        System.out.println("队列头是：" + circleArrayQueue.showQueueHeader());
        System.out.println("出队列");
        System.out.println(circleArrayQueue.getQueue());
        System.out.println("出队列后的队列");
        circleArrayQueue.showQueue();

    }
}

class CircleArrayQueue {
    public int front;//头
    public int rear;//尾
    public int maxSize;//最大容量
    public int[] arr;//存放数据

    public CircleArrayQueue(int arrMaxSize) {
        this.maxSize = arrMaxSize;
        this.arr = new int[maxSize];
    }

    //是否为空
    public boolean hasEmpty() {
        return this.rear == this.front;
    }

    //是否满了
    public boolean hasFull() {
        return (this.rear + 1) % this.maxSize == this.front;
    }


    //显示队列
    public void showQueue() {
        if (hasEmpty()) {
            System.out.println("空的，没有数据");
            return;
        }
        for (int i = this.front; i < this.front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % this.maxSize, arr[i % this.maxSize]);
        }
    }

    //实际的个数
    public int size() {
        return (this.rear - this.front + this.maxSize) % this.maxSize;
    }

    //添加
    public void add(int item) {
        if (hasFull()) {
            System.out.println("队列满了，不能添加");
            return;
        }
        this.arr[this.rear] = item;
        this.rear = (this.rear + 1) % this.maxSize;
    }

    //出去
    public int getQueue() {
        if (this.hasEmpty()) {
            throw new RuntimeException("空的，你不能出去");

        }
        int value = this.arr[this.front];
        this.front = (this.front + 1) % this.maxSize;
        return value;
    }

    //显示队列头
    public int showQueueHeader() {
        if (this.hasEmpty()) {
            throw new RuntimeException("空的，你不能显示");
        }
        return this.arr[this.front];
    }
}