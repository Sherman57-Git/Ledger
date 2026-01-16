package com.example.demo;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Ledger {
    private final List<Transaction> Transactions;
    private final Scanner scanner = new Scanner(System.in);

    public Ledger(List<Transaction> Transactions) {
        this.Transactions = Transactions;
    }
    // building my ledger menu
    public void showMenu() {
        while (true) {
            System.out.println("\n= THY Ledger Menu =");
            System.out.println("1) Show All Transactions");
            System.out.println("2) Show Deposits");
            System.out.println("3) Show payments");
            System.out.println("4) Show Reports");
            System.out.println("0) Back - go back to the Ledger page");
            System.out.println("Which would you like to pick?: ");
            String options = scanner.nextLine().toUpperCase();

            switch (options) {
                case "1":
                    showAll();
                    break;
                case "2":
                    showDeposits();
                    break;
                case "3":
                    showPayments();
                    break;
                case "4":
                    showReports();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid option.");


            }

        }


    }


    public void showAll() {
        System.out.println("\n All transactions ");
        for (Transaction trans : Transactions) {
            System.out.println(trans);
        }
    }

    public void showDeposits() {
        System.out.println("\n Deposits ");
        for (Transaction trans : Transactions) {
            if (trans.getAmount() > 0) {
                System.out.println(trans);
            }
        }
    }

    public void showPayments() {
        System.out.println("\n Paymemts  ");
        for (Transaction trans : Transactions) {
            if (trans.getAmount() < 0) {
                System.out.println(trans);
            }
        }
    }

    //Report to methods
    public void showReports() {
        while (true) {
            System.out.println("\n= THY Reports =");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back - go back to the Ledger page");
            System.out.println("Which would you like to pick?: ");
            String options = scanner.nextLine();

            switch (options) {
                case "1":
                    monthToDate();
                    break;
                case "2":
                    previousMonth();
                    break;
                case "3":
                    yearToDate();
                    break;
                case "4":
                    previousYear();
                    break;
                case "5":
                    searchByVendor();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid option.");

            }
        }
    }private void monthToDate() {
        LocalDate now = LocalDate.now();
        double total = 0;

        System.out.println("\nTransactions for this month:");
        for (Transaction transaction : Transactions) {
            if (transaction.getDate().getYear() == now.getYear()&&
                    transaction.getDate().getMonth() == now.getMonth()) {
                System.out.println(transaction);
                total += transaction.getAmount();
            }
        }
        System.out.printf("Total: %.2f\n", total);
    }
    private void previousMonth() {
        LocalDate now = LocalDate.now();
        LocalDate lastMonth = now.minusMonths(1);
        double total = 0;
        System.out.println("\nTransactions for last month:");
        for (Transaction transaction : Transactions) {
            if (transaction.getDate().getYear() == lastMonth.getYear()&&
                    transaction.getDate().getMonth() == lastMonth.getMonth()) {
                System.out.println(transaction);
                total += transaction.getAmount();
            }
        }
        System.out.printf("Total: %.2f\n", total);


    }
    private void yearToDate(){
        LocalDate now = LocalDate.now();
        double total = 0;
        System.out.println("\nTransactions for this year:");
        for (Transaction transaction : Transactions) {
            if (transaction.getDate().getYear() == now.getYear()) {
                System.out.println(transaction);
                total += transaction.getAmount();
            }
        }
        System.out.printf("Total: %.2f\n" , total);


    }
    private void previousYear(){
        LocalDate now = LocalDate.now();
        int lastYear = now.getYear() - 1;
        double total = 0;
        System.out.println("\nTransactions for last year:");
        for (Transaction transaction : Transactions) {
            if (transaction.getDate().getYear() == lastYear) {
                System.out.println(transaction);
                total += transaction.getAmount();
            }
        }
        System.out.printf("Total: %.2f\n" , total);
    }
    private void searchByVendor(){
        System.out.println("Enter Name");
        String vendorSearch = scanner.nextLine().toLowerCase();
        Boolean whoops = false;
        double total = 0;
        for (Transaction transaction : Transactions) {
            if (transaction.getVendor().toLowerCase().contains(vendorSearch)) {
                total += transaction.getAmount();
                whoops = true;
            }
        }
        if (!whoops) {
            System.out.println("No transactions found: " + vendorSearch);
        } else {
            System.out.printf("Total for '%s' : %.2f\n" , vendorSearch, total);
        }
    }

}




