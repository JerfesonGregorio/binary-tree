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
        if(root.getRight() == null && root.getLeft() == null) return null;
        if(Objects.equals(value, root.getLeft().getValue()) || Objects.equals(value, root.getRight().getValue())) {
            return root;
        } else if(value > root.getValue()) {
            return search(value, root.getRight());
        } else {
            return search(value, root.getLeft());
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

    public Integer search(Integer value) {
        Node<Integer> node = search(value, root);

        if(node == null) {
            return null;
        }
        if(node.getLeft().getValue().equals(value)) {
            return node.getLeft().getValue();
        } else {
            return node.getRight().getValue();
        }
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

}
