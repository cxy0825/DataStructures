public class HashTabDemo {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable(6);
        hashTable.add(new item(1, "lisi"));
        hashTable.add(new item(2, "zhangsan2"));
        hashTable.add(new item(3, "zhangsan3"));
        hashTable.add(new item(4, "zhangsan4"));
        hashTable.add(new item(7, "zhangsan7"));
        hashTable.list();
        hashTable.findByID(8);

    }
}

class HashTable {
    private int size;
    private HashTableLinkList[] hashTableLinkLists;

    public HashTable(int size) {
        this.size = size;
        hashTableLinkLists = new HashTableLinkList[size];
        //初始化头结点
        for (int i = 0; i < size; i++) {
            hashTableLinkLists[i] = new HashTableLinkList();
        }
    }

    //添加
    public void add(item item) {
        int no = hashFun(item.ID);
        //根据员工的id得到，改员工应该加到哪个节点
        hashTableLinkLists[no].add(item);
    }

    //遍历输出
    public void list() {
        for (int i = 0; i < size; i++) {
            hashTableLinkLists[i].list();
        }
    }

    //查找
    public void findByID(int ID) {
        hashTableLinkLists[hashFun(ID)].findByID(ID);
    }

    //哈希算法
    public int hashFun(int ID) {
        return ID % this.size;
    }
}

class HashTableLinkList {
    private item head;

    //添加到链表中
    //假定添加的id是递增的
    public void add(item item) {

        //如果头节点是空的就直接插入
        if (head == null) {
            head = item;
            return;
        }
        item currentItem = head;
        while (currentItem.next != null) {
            currentItem = currentItem.next;
        }
        currentItem.next = item;
    }

    //显示列表
    public void list() {
        if (head == null) {
            System.out.println("这个列表没有节点");
            return;
        }
        System.out.print("当前列表的信息为：");
        item currentItem = head;
        while (true) {
            System.out.print("ID:  " + currentItem.ID + " 名字：" + currentItem.name + "\t");
            if (currentItem.next == null) {
                System.out.println("");
                break;
            }
            currentItem = currentItem.next;
        }

    }

    //查找
    public void findByID(int ID) {
        if (head == null) {
            System.out.println("当前列表为空");
            return;
        }
        item currentItem = head;
        while (true) {

            if (currentItem.ID == ID) {
                System.out.println("找到了，ID为：" + currentItem.ID + "名字是：" + currentItem.name);
                return;
            }
            if (currentItem.next == null) {
                System.out.println("查无此人");
                return;
            }
            currentItem = currentItem.next;

        }
    }
}

class item {
    public String name;
    public int ID;
    public item next;

    public item(int ID, String name) {
        this.name = name;
        this.ID = ID;
    }
}