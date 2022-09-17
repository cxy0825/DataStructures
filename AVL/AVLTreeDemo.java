package AVL;

public class AVLTreeDemo {
    public static void main(String[] args) {
        int[] arr = {4, 3, 6, 5, 7, 8};
        //创建一个AVLTree
        AVLTree avlTree = new AVLTree();
        for (int i : arr) {
            avlTree.add(new Node(i));
        }
        avlTree.infixOrder();
        System.out.println("调整平衡时后：");
        System.out.println(avlTree.getRoot().height());
        System.out.println("左子树高度" + avlTree.getRoot().leftHeight());
        System.out.println("右子树高度" + avlTree.getRoot().rightHeight());
    }
}

//创建AVLTree
class AVLTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

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
        //添加完成后，如果（右子树-左子树的高度）>1，左旋
        if (this.rightHeight() - leftHeight() > 1) {
//            if (right != null && right.rightHeight() < right.leftHeight()) {
//                //先对右子树进行旋转
//            }
            leftRotate();
        }
        if (this.leftHeight() - leftHeight() >= 1) {
            rightRotate();
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

    //返回当前节点的高度,以该节点为根节点的树的高度
    public int height() {
        //本身节点也算一层所以后面要+1
        //+1是为了返回该节点的高度，不加1是返回子树的高度

        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    //返回左子树的高度
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    //返回该节点右子树的高度
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    //左旋转的方法
    private void leftRotate() {
        //创建新节点，值和当前根节点一样
        Node newNode = new Node(this.value);
        //把新的节点的左子树设置成当前节点的左子树
        newNode.left = this.left;
        //新的节点的右子树设置成当前节点的右子树的左子树
        newNode.right = this.right.left;
        //把当前节点的值替换成右子节点的值
        this.value = this.right.value;
        //把当前节点的右子树设置成右子树的右子树
        this.right = this.right.right;
        //把当前节点的左子树设置成新节点的右子树
        this.left = newNode;
    }

    //右旋的方法
    //当二叉树的左子树高度大于右子树的高度时
    private void rightRotate() {
        //创建一个节点
        Node newNode = new Node(this.value);
        //把新节点的有节点变成当前节点的有节点
        newNode.right = this.right;
        //把新节点的左节点变成当前节点的左节点的右节点
        newNode.left = this.left.right;
        //把当前节点的值替换成当前节点左子树的值
        this.value = this.left.value;
        //把当前节点的左子树设置当前节点的左子树的左子树
        this.left = this.left.left;
        //当前节点的右节点变成新节点
        this.right = newNode;
    }

    @Override
    public String toString() {
        return "Node {" +
                "value=" + value +
                '}';
    }

}
