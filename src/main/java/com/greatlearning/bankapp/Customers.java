package com.greatlearning.bankapp;

import com.greatlearning.bankapp.helpers.FileWriterHelper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.text.MessageFormat;
import java.util.Scanner;

import static com.greatlearning.bankapp.constants.GlobalConstants.DEPOSIT;
import static com.greatlearning.bankapp.constants.GlobalConstants.WITHDRAWAL;

/**
 * Customers to store customer information
 * using lombok for getter, setter and ArgsConstructor
 */
@AllArgsConstructor
public class Customers {
    @Getter
    @Setter
    private int bankAccountNo;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private double balance;

    /**
     * deposit the amount to account
     */
    void deposit() {
        Scanner scanner = new Scanner(System.in);
        FileWriterHelper writerHelper = new FileWriterHelper();
        double amount;
        System.out.println("Enter the amount you want to deposit");
        amount = scanner.nextDouble();
        if (amount <= 0) {
            System.out.println("Amount to be deposited should be positive");
        } else {
            balance = balance + amount;
            System.out.println(MessageFormat.format("Amount {0} deposited successfully", amount));
            System.out.println(MessageFormat.format("Total Balance: {0}", balance));
            System.out.println(" ");
            //writing transaction to file
            writerHelper.writeToFile(DEPOSIT);
        }

    }

    /**
     * withdraw the amount from account
     */
    void withdrawal() {
        FileWriterHelper writerHelper = new FileWriterHelper();
        double amount;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the amount you want to withdrawal");
        amount = scanner.nextLong();
        if (balance >= amount) {
            balance = balance - amount;
            System.out.println(MessageFormat.format("Amount {0} withdrawal successfully", amount));
            System.out.println(MessageFormat.format("Total Balance: {0}", balance));
            System.out.println(" ");
            //writing transaction to file
            writerHelper.writeToFile(WITHDRAWAL);
        } else {
            System.out.println("Less Balance! Transaction declined!");
        }
    }

}
