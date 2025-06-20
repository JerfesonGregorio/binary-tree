package edu.ifpb;

import edu.ifpb.tree.Tree;

public class Main {
    public static void main(String[] args) {

        Tree tree = new Tree();

        tree.insert(10);
        tree.insert(3);
        tree.insert(11);
        tree.insert(5);
        tree.insert(2);
        tree.insert(4);
        tree.insert(6);

        System.out.println(tree);

        tree.remove(3);
        System.out.println(tree);


    }
}