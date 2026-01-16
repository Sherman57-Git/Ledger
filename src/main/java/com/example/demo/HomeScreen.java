package com.example.demo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class HomeScreen {
    private static final Scanner scanner = new Scanner(System.in);
    private static final TransactionManager transactionManager = new TransactionManager();
    public static void main(String[] args) {
        while (true) {
            System.out.println("=   HELLO !   =");
            System.out.println("1) Add Deposit");
            System.out.println("2) Make payment(Debit)");
            System.out.println("3) Display ledger");
            System.out.println("0) Exit");
            System.out.println("Which would you like to pick?:  ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    addDeposit();
                    break;
                case "2":
                    makePayment();
                    break;
                case "3":
                    displayLedger();
                    break;
                case "0":
                    System.out.println("Thank you! Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid.");
            }
        }

    } //prints out positive payment
    private static void addDeposit() {
        System.out.println("Enter description: ");
        String description = scanner.nextLine();
        System.out.println("Enter vendor: ");
        String vendor = scanner.nextLine();
        System.out.println("Enter amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        Transaction newTrans = new Transaction(LocalDate.now(), LocalTime.now(),
                description, vendor, Math.abs(amount));
        transactionManager.addTransaction(newTrans);

        System.out.println("DEPOSIT ADDED !");

    } // prints out a negative payment
    private static void makePayment() {
        System.out.println("Enter description: ");
        String description = scanner.nextLine();
        System.out.println("Enter vendor: ");
        String vendor = scanner.nextLine();
        System.out.println("Enter amount: ");

        double amount;
        amount = Double.parseDouble(scanner.nextLine());

        Transaction newTrans = new Transaction(LocalDate.now(), LocalTime.now(),
                description, vendor, -Math.abs(amount));
        transactionManager.addTransaction(newTrans);

        System.out.println("PAYMENT SAVED !");

    } //Displaying Ledger menu
    private static void displayLedger() {
        List<Transaction> transactions = transactionManager.readTransactions();
        Ledger leger = new Ledger(transactions);
        leger.showMenu();
    }
}

