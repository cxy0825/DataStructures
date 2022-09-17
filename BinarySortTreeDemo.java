public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int j : arr) {
            binarySortTree.add(new Node(j));
        }
        //遍历
        binarySortTree.infixOrder();
    }
}

//创建二叉排序树
class BinarySortTree {
    private Node root;

    //添加节点
    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            this.root.add(node);
        }

    }

    //中序遍历
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("数为空不能遍历");
        }
    }
}

//创建node节点
class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    //添加节点
    public void add(Node node) {
        if (node == null) {
            return;
        }
        //传入的node的value和当前子树根节点的关系
        if (node.value < this.value) {//添加的节点小于左子节点
            if (this.left == null) {
                this.left = node;
            } else {
                //如果左子节点不为空就递归向下查找
                this.left.add(node);
            }
        } else {//添加的节点大于等于当前节点
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);

        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    @Override
    public String toString() {
        return "Node {" +
                "value=" + value +
                '}';
    }
}