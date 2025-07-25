package edu.ifpb;

import edu.ifpb.tree.Tree;

public class Test {
    public static void main(String[] args) {
        Tree tree = new Tree();

        tree.insert(10);
        tree.insert(2);
        tree.insert(4);
        tree.insert(23);
        tree.insert(12);
        tree.insert(1);

        System.out.println(tree);
        System.out.println(tree.getNodeSize(tree.search(10)));

    }
}
