package com.example.demo;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionManager {
    public final String fileName = ".idea/File/transactions.csv";
    // Reading Transactions from a file
    public List<Transaction> readTransactions(){
        ArrayList<Transaction> transactions = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String transactionString;
            while ((transactionString = bufferedReader.readLine()) != null) {
                String[] transArr = transactionString.split("\\|");
                Transaction trans = new Transaction(LocalDate.parse(transArr[0]), LocalTime.parse(transArr[1]), transArr[2], transArr[3], Double.parseDouble(transArr[4]));
                transactions.add(trans);
            }
        }  catch (IOException e) {
            System.out.println("ERROR. Cannot read transactions: " + e.getMessage());
        }
        return transactions;

    } //Adding new transaction to CSV file
    public void addTransaction(Transaction transaction) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            System.out.println("Writing to: " + new File(fileName).getAbsolutePath());
            writer.write(transaction.toCsv());
            writer.newLine();
        }
        catch (IOException e) {
            System.out.println("ERROR. Cannot write transaction: " + e.getMessage());
        }
    }


}






