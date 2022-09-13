/*
 *单链表
 *@Param:
 *@Return:
 */

public class Liebiao {
    public static void main(String[] args) {
        HeroNode heroNode1 = new HeroNode(1, "小A", "aa");
        HeroNode heroNode2 = new HeroNode(2, "小B", "bb");
        HeroNode heroNode3 = new HeroNode(3, "小C", "cc");
        HeroNode heroNode4 = new HeroNode(4, "小D", "dd");
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        //添加
        singleLinkedList.addByOrder(heroNode1);
        singleLinkedList.addByOrder(heroNode4);
        singleLinkedList.addByOrder(heroNode2);
        singleLinkedList.addByOrder(heroNode3);
        //显示
        singleLinkedList.list();
        System.out.println("---------------");
        //更新节点
        singleLinkedList.updateNode(new HeroNode(2, "更新B", "更新b"));
        //显示
        singleLinkedList.list();
        System.out.println("---------------");
        singleLinkedList.del(4);
        singleLinkedList.list();

//        求链表中有几个有效节点
        System.out.println("有效节点个数" + singleLinkedList.getLength());

        //求列表中倒数第k个节点
        System.out.println(singleLinkedList.findLastIndexNode(3));
        //单链表反转
        System.out.println("反转列表");
        singleLinkedList.revise();
        singleLinkedList.list();
    }
}

class SingleLinkedList {
    public HeroNode head = new HeroNode(0, "", "");

    //添加节点
    public void add(HeroNode heroNode) {
        //heda节点不能动所以需要一个辅助节点来移动
        HeroNode temp = head;

        while (temp.next != null) {
            //添加到最后
            //没找到就把这个节点往后移动
            temp = temp.next;
        }
        //退出循环temp一定指向的是最后一个节点
        temp.next = heroNode;

    }

    public void addByOrder(HeroNode heroNode) {
        //heda节点不能动所以需要一个辅助节点来移动
        HeroNode temp = head;
        //添加的编号是否存在
        boolean flag = false;
        while (temp.next != null) {
//            如果下一个节点的no大于要插入节点的no
            //说明这个节点要插入到这里
            if (temp.next.no > heroNode.no) {
                break;
            }
            if (temp.no == heroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //判断有没有相同no的节点
        if (flag) {
            System.out.println("要插入的No已经在列表中无法插入");
        } else {
            //插入劫点
            heroNode.next = temp.next;
            temp.next = heroNode;

        }
    }

    //修改节点信息
    //传入一个no相同的节点来修改
    public void updateNode(HeroNode heroNode) {
//        先遍历列表，找出相同no的节点
        HeroNode temp = this.head;
        //找到相同no的标志
        boolean flag = false;
        while (temp.next != null) {
            if (temp.no == heroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            //找到了相同的就替换
            temp.setName(heroNode.getName());
            temp.setNickname(heroNode.getNickname());
        } else {
            //没找到就提示
            System.out.println("没有找到no为" + heroNode.no + "的节点");
        }

    }


    //删除节点
    public void del(int no) {
        //遍历找到要删除节点的no
        HeroNode temp = this.head;
        //找没找到的标志
        boolean flag = false;
        while (temp.next != null) {
            if (temp.next.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //找到了就删除这个节点
        if (flag) {
            //找到了
            temp.next = temp.next.next;
        } else {
            //没找到
            System.out.println("链表中没有找到no为" + no + "这个节点");
        }

    }

    //显示列表
    public void list() {
        //判断空
        if (this.head.next == null) {
            System.out.println("列表为空不能显示");
            return;
        }
        //辅助节点
        HeroNode temp = this.head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    //有效节点个数
    public int getLength() {
        int length = 0;

        if (this.head.next == null) {
            System.out.println("数据为空");
            return 0;
        } else {
            HeroNode temp = head.next;
            while (temp != null) {
                length++;
                temp = temp.next;
            }
            return length;
        }
    }

    //输出倒数第index个节点
    public HeroNode findLastIndexNode(int index) {
        HeroNode temp = head.next;
        if (temp == null) {
            System.out.println("链表为空");
            return null;
        }
        if (index > this.getLength() || index <= 0) {
            System.out.println("索引值不规范");
            return null;
        }
        for (int i = 0; i < getLength() - index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    //反转链表
    public void revise() {
        if (head.next == null) {
            System.out.println("链表为空不能反转");
            return;
        }
        if (head.next.next == null) {
            System.out.println("链表只有一个节点,不用反转");
        }
        HeroNode temp = head.next;
        HeroNode next = null;
        //创建一个头节点
        HeroNode tempHead = new HeroNode(0, "", "");
        while (temp != null) {
            //因为后面要赋值所以在这里要保存一下
            next = temp.next;
            temp.next = tempHead.next;
            tempHead.next = temp;
            //这里就要用到上面保存的节点了，上面改变了temp的next所以需要在上面保存
            temp = next;
        }
        head.next = tempHead.next;
    }
}

class HeroNode {
    public int no;
    public String name;
    public String Nickname;
    public HeroNode next;//下一个节点

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return Nickname;
    }

    public void setNickname(String nickname) {
        Nickname = nickname;
    }

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        Nickname = nickname;

    }
    //显示方便重写tostring


    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", Nickname='" + Nickname + '\'' +
                "}";
    }
}
