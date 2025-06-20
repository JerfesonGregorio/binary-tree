package edu.ifpb.tree;

import java.util.Objects;

public class Tree {

    private Node<Integer> root;

    public Tree() {
        this.root = null;
    }

    public void insert(Integer value) {

    }

    private void insert(Node<Integer> node) {

        if(root == null) {
            this.root = node;
        } else {

            Node<Integer> current = root;




        }



    }

    private Node<Integer> search(Node<Integer> node, Node<Integer> root) {

        Node<Integer> n = null;

        if(Objects.equals(node.getValue(), root.getValue())) {
            n = root;
        } else if(node.getValue() > root.getValue()) {
            search(node, root.getRight());
        } else {
            search(node, root.getLeft());
        }

        return n;
    }

    public Node<Integer> remove(Node<Integer> value) {
        return null;
    }
}
