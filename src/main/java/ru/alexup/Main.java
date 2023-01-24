package ru.alexup;

import ru.alexup.db.DataBase;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DataBase dataBase = new DataBase();

        System.out.println("Enter operation's number:");
        System.out.println("1. Show products by person, person's_name\n" +
                "2. Find persons, purchased product, product's_title\n" +
                "3. Remove person, person's_name\n" +
                "4. Remove product, product's_title\n" +
                "5. Buy, person's_name, product's_title\n" +
                "To finish program type \"end\"");
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("end")) {
                break;
            }
            String[] split = input.split(", | ");
            int operation = Integer.parseInt(split[0]);
            switch (operation) {
                case 1:
                    dataBase.showProductsByClient(split[1]);
                    break;
                case 2:
                    dataBase.findClientsByProductTitle(split[1]);
                    break;
                case 3:
                    dataBase.removeClient(split[1]);
                    break;
                case 4:
                    dataBase.removeProduct(split[1]);
                    break;
                case 5:
                    dataBase.insertIntoGoodsClients(split[1], split[2]);
                    break;
            }
        }
        dataBase.closeFactory();
    }
}
