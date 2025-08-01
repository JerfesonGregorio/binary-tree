package edu.ifpb.tree;

import java.util.Objects;

public class Tree {

    private Node<Integer> root;

    public Tree() {
        this.root = null;
    }

    private void insert(Node<Integer> node, Node<Integer> root) {

        if(this.root == null) {
            this.root = node;
        } else if(root.getLeft() == null && root.getValue() > node.getValue()) {
            root.setLeft(node);
        } else if(root.getRight() == null && root.getValue() < node.getValue()) {
            root.setRight(node);
        } else if(Objects.equals(root.getValue(), node.getValue())) {
            System.out.println("Valor já existe");
        } else {
            if(root.getValue() > node.getValue()) {
                insert(node, root.getLeft());
            } else {
                insert(node, root.getRight());
            }
        }
    }

    private Node<Integer> search(Integer value, Node<Integer> root) {
        if (root == null) return null;

        if (Objects.equals(root.getValue(), value)) {
            return root;
        } else if (value < root.getValue()) {
            return search(value, root.getLeft());
        } else {
            return search(value, root.getRight());
        }
    }

    private boolean removeRec(Node<Integer> parent, Node<Integer> node, boolean isLeftChild) {
        if (node.getLeft() == null && node.getRight() == null) {
            if (isLeftChild) {
                parent.setLeft(null);
            } else {
                parent.setRight(null);
            }
        }
        else if (node.getLeft() == null) {
            if (isLeftChild) {
                parent.setLeft(node.getRight());
            }
            else {
                parent.setRight(node.getRight());
            }
        } else if (node.getRight() == null) {
            if (isLeftChild) {
                parent.setLeft(node.getLeft());
            }
            else {
                parent.setRight(node.getLeft());
            }
        }
        else {
            Node<Integer> successorParent = node;
            Node<Integer> successor = node.getRight();

            while (successor.getLeft() != null) {
                successorParent = successor;
                successor = successor.getLeft();
            }

            node.setValue(successor.getValue());

            if (successorParent == node) {
                successorParent.setRight(successor.getRight());
            } else {
                successorParent.setLeft(successor.getRight());
            }
        }
        return true;
    }

    public Node<Integer> search(Integer value) {
        return search(value, root);
    }

    public void insert(Integer value) {
        insert(new Node<>(value), this.root);
    }

    public Integer remove(Integer value) {
        if (root == null) return null;

        if (Objects.equals(root.getValue(), value)) {
            Node<Integer> pseudoRoot = new Node<>(0);
            pseudoRoot.setLeft(root);
            boolean removed = removeRec(pseudoRoot, root, true);
            root = pseudoRoot.getLeft();
            return removed ? value : null;
        }

        Node<Integer> parent = search(value, root);
        if (parent == null) return null;

        boolean removed = false;

        if (parent.getLeft() != null && Objects.equals(parent.getLeft().getValue(), value)) {
            removed = removeRec(parent, parent.getLeft(), true);
        } else if (parent.getRight() != null && Objects.equals(parent.getRight().getValue(), value)) {
            removed = removeRec(parent, parent.getRight(), false);
        }

        return removed ? value : null;
    }

    @Override
    public String toString() {
        if (root == null) {
            return "Tree is empty.";
        }
        StringBuilder sb = new StringBuilder();
        buildString(root, sb, "", true);
        return sb.toString();
    }

    private void buildString(Node<Integer> node, StringBuilder sb, String prefix, boolean isTail) {
        if (node == null) {
            return;
        }

        sb.append(prefix)
                .append(isTail ? "└── " : "├── ")
                .append(node.getValue())
                .append("\n");

        boolean hasLeft = node.getLeft() != null;
        boolean hasRight = node.getRight() != null;

        if (hasLeft || hasRight) {
            if (node.getRight() != null)
                buildString(node.getRight(), sb, prefix + (isTail ? "    " : "│   "), node.getLeft() == null);
            if (node.getLeft() != null)
                buildString(node.getLeft(), sb, prefix + (isTail ? "    " : "│   "), true);
        }
    }

    private void printPreOrder(Node<Integer> node) {

        if(node != null) {
            System.out.println(node.getValue());
            printPreOrder(node.getLeft());
            printPreOrder(node.getRight());
        }
    }

    private void printInOrder(Node<Integer> node) {
        if(node != null) {
            printPreOrder(node.getLeft());
            System.out.println(node.getValue());
            printPreOrder(node.getRight());
        }
    }

    private void printPostOrder(Node<Integer> node) {
        if(node != null) {
            printPreOrder(node.getLeft());
            printPreOrder(node.getRight());
            System.out.println(node.getValue());
        }
    }

    public void preOrder() {
        printPreOrder(root);
    }

    public void inOrder() {
        printInOrder(root);
    }

    public void postOrder() {
        printPostOrder(root);
    }

    private Node<Integer> rightRotation(Node<Integer> current) {

        Node<Integer> currentLeft = current.getLeft(); // null
        Node<Integer> currentChildRight = currentLeft.getRight(); //

        currentLeft.setRight(current);
        current.setLeft(currentChildRight);

        return currentLeft;
    }

    private Node<Integer> leftRotation(Node<Integer> current) {

        Node<Integer> currentRight = current.getRight(); // 7
        Node<Integer> currentChildLeft = currentRight.getLeft();  // null

        currentRight.setLeft(current); // 5 <-- 7
        current.setRight(currentChildLeft); // 10 --> null

        return currentRight;
    }

    public int getNodeSize(Node<Integer> node) {
        if (node == null) {
            return -1;
        }

        int leftHeight = getNodeSize(node.getLeft());
        int rightHeight = getNodeSize(node.getRight());

        return 1 + Math.max(leftHeight, rightHeight);
    }

    private int balanceFactor(Node<Integer> current) {
        return getNodeSize(current.getLeft()) - getNodeSize(current.getRight());
    }

    private Node<Integer> balance(Node<Integer> node) {
        int nodeBalanceFactor = balanceFactor(node);

        if (nodeBalanceFactor > 1) {
            if (balanceFactor(node.getLeft()) < 0) {
                node.setLeft(leftRotation(node.getLeft()));
            }
            return rightRotation(node);
        }

        // Direita pesada
        if (nodeBalanceFactor < -1) {
            if (balanceFactor(node.getRight()) > 0) {
                node.setRight(rightRotation(node.getRight()));
            }
            return leftRotation(node);
        }

        return node;
    }










}
