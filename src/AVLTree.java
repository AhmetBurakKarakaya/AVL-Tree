import java.util.ArrayList;

public class AVLTree {

    public class Node {

        Node left, right;
        int height;
        String data;

        private Node(String data) {
            this.data = data;
            this.height = 1;
            this.left = null;
            this.right = null;
        }
    }

    private Node root = null;

    //insert
    public void insert(String data) {
        root = insert(root, data);
    }

    private Node insert(Node node, String data) {

        if (node == null) {
            System.out.println("Eleman Eklendi");
            return (new Node(data));
        }

        if (data.toLowerCase().compareTo(node.data.toLowerCase()) < 0) {
            node.left = insert(node.left, data);
        } else if (data.toLowerCase().compareTo(node.data.toLowerCase()) > 0) {
            node.right = insert(node.right, data);
        } else {
            System.out.println("Eleman Listede Mevcut!");
        }

        //eklemeden sonra yukseklık guncelle
        node.height = Math.max(height(node.left), height(node.right)) + 1;

        //balance faktor hesapla
        int balance = getBalance(node);

        //Duzeltme gerekli mi kontrol
        // Left Left Case
        if (balance > 1 && data.toLowerCase().compareTo(node.left.data.toLowerCase()) < 0) {
            System.out.println("LL Case");
            return rightRotate(node);
        }
        // Right Right Case
        if (balance < -1 && data.toLowerCase().compareTo(node.right.data.toLowerCase()) > 0) {
            System.out.println("RR Case");
            return leftRotate(node);
        }
        // Left Right Case
        if (balance > 1 && data.toLowerCase().compareTo(node.left.data) > 0) {
            System.out.println("LR Case");
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // Right Left Case
        if (balance < -1 && data.toLowerCase().compareTo(node.right.data) > 0) {
            System.out.println("RL Case");
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    //ratation
    private Node rightRotate(Node node) {
        Node rotateNode = node.left;
        Node T2 = rotateNode.right;

        // rotate
        rotateNode.right = node;
        node.left = T2;

        //  yukseklıkleri guncelle
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        rotateNode.height = Math.max(height(rotateNode.left), height(rotateNode.right)) + 1;

        // yeni root dugum
        return rotateNode;
    }

    private Node leftRotate(Node node) {
        Node rotateNode = node.right;
        Node T2 = rotateNode.left;

        //rotate
        rotateNode.left = node;
        node.right = T2;

        //  yukseklıkleri guncelle
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        rotateNode.height = Math.max(height(rotateNode.left), height(rotateNode.right)) + 1;

        //yeni root dugum
        return rotateNode;
    }

    //delete
    public void deleteNode(String data) {
        root = deleteNode(root, data);
    }

    private Node deleteNode(Node node, String data) {

        if (node == null) {
            return node;
        }

        //data < node.data
        if (data.toLowerCase().compareTo(node.data.toLowerCase()) < 0) {
            node.left = deleteNode(node.left, data);
        } //data > node.data
        else if (data.toLowerCase().compareTo(node.data.toLowerCase()) > 0) {
            node.right = deleteNode(node.right, data);
        } //node.data == data
        else {
            //bir cocuk veya yaprak dugumse
            if ((node.left == null) || (node.right == null)) {

                Node temp;
                if (node.left != null) {
                    temp = node.left;
                } else {
                    temp = node.right;
                }

                // yaprak dugum
                if (temp == null) {
                    //direk sil
                    temp = node;
                    node = null;
                } else // bir cocuk
                {
                    node = temp; //cocuk kendisine esitle 
                }
                temp = null;
            } else {
                // iki cocuk varsa
                Node temp = minValueNode(node.right);

                // successor datasını node.data ile degistir
                node.data = temp.data;

                // Successor sil
                node.right = deleteNode(node.right, temp.data);
            }
        }

        //Dengesizlik oldu mu kontrol 
        if (node == null) {
            return node;
        }

        // yukseklık guncelle
        node.height = Math.max(height(node.left), height(node.right)) + 1;

        // balance faktor hesapla
        int balance = getBalance(node);

        //durumları kontrol et(4 durum -> LL Case, RR Case, RL Case, LR Case)
        // Left Left Case
        if (balance > 1 && getBalance(node.left) >= 0) {
            System.out.println("LL Case");
            return rightRotate(node);
        }

        // Left Right Case
        if (balance > 1 && getBalance(node.left) < 0) {
            System.out.println("LR Case");
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Right Case
        if (balance < -1 && getBalance(node.right) <= 0) {
            System.out.println("RR Case");
            return leftRotate(node);
        }

        // Right Left Case
        if (balance < -1 && getBalance(node.right) > 0) {
            System.out.println("RL Case");
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    //ataNodes
    public void ataNodes(String data) {

        if (this.root.data.equals(data)) {
            System.out.print("Root Node");
        } else {
            root = ataNodes(root, data);
        }
    }

    private Node ataNodes(Node node, String data) {
        
        ArrayList<Node> ancestorNodes = new ArrayList<>();       
        if (node == null) {
            return node;
        }
  
        if (data.toLowerCase().compareTo(node.data.toLowerCase()) < 0) {
            if (node.left.data.toLowerCase().equals(data.toLowerCase())) {
                return node;
            }
            ancestorNodes.add(node);
            node.left = ataNodes(node.left, data);
        } else if (data.toLowerCase().compareTo(node.data.toLowerCase()) > 0) {
            if (node.right.data.toLowerCase().equals(data.toLowerCase())) {
                return node;
            }
            ancestorNodes.add(node);
            node.right = ataNodes(node.right, data);
        }
        
        for(int i = ancestorNodes.size()-1; i>= 0; i--){
            System.out.print(ancestorNodes.get(i).data + " ");
        }
         
        return node;

    }

    //findNode
    public int findNode(String data) {
        Node node = find(root, data);
        if (node == null) {
            return 0;
        }
        return 1;
    }

    private Node find(Node node, String data) {

        while (node != null) {
            if (node.data.toLowerCase().equals(data.toLowerCase())) {
                break;
            }
            node = node.data.toLowerCase().compareTo(data.toLowerCase()) < 0 ? node.right : node.left;
        }
        return node;
    }

    //successor
    private Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    private int getBalance(Node node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    //traverse
    public void preOrder() {
        if (root == null) {
            System.out.println("Liste Bos!");
        } else {
            preOrder(root);
            System.out.println("");
        }

    }

    private void preOrder(Node root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    public void postOrder() {
        if (this.root == null) {
            System.out.println("Liste Bos !");
        } else {
            postOrder(this.root);
            System.out.println();
        }
    }

    private void postOrder(Node node) {

        if (node != null) {
            preOrder(node.left);
            preOrder(node.right);
            System.out.print(node.data + " ");
        }
    }

    public void inOrder() {
        if (this.root == null) {
            System.out.println("Liste Bos !");
        } else {
            inOrder(this.root);
            System.out.println();
        }

    }

    private void inOrder(Node node) {

        if (node != null) {
            preOrder(node.left);
            System.out.print(node.data + " ");
            preOrder(node.right);
        }
    }
}
