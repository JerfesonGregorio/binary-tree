package edu.ifpb;

import edu.ifpb.tree.Tree;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

        Tree tree = new Tree();

        Scanner scanner = new Scanner(System.in);
        String regex = "^-?\\d+$";
        boolean active = true;

        while(active) {

            Menu.show();
            System.out.print("Escolha uma opção: ");
            String input = scanner.next().toLowerCase();

            switch (input) {
                case "1":
                    System.out.print("Insira um valor: ");
                    String number = scanner.next();
                        if(Pattern.matches(regex, number)) {
                            Integer value = Integer.parseInt(number);
                            tree.insert(value);
                            System.out.println("Valor " + value + " inserido com sucesso!\n");
                        } else {
                            System.out.println("Falha ao inserir valor: Insira um número válido.");
                        }
                        break;
                case "2":
                    tree.preOrder();
                    break;
                case "3":
                    tree.inOrder();
                    break;
                case "4":
                    tree.postOrder();
                    break;
                case "5":
                    System.out.println(tree);
                    break;
                case "0":
                    active = false;
            }
        }
    }
}