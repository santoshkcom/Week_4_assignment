package com.greatlearning.bankapp;


import java.util.Scanner;

/**
 * Transaction menu class
 */
public class MenuDisplay {
    BankingTransaction bankingTransaction = new BankingTransaction();
    int selectedOption;

    public void showMenu(int accountNumber) {
        System.out.println("!!!!! Welcome to Indian Bank !!!!!");
        System.out.println("----------------------------------------");
        System.out.println("Enter the operation that you want to perform");
        System.out.println("1. Deposit");
        System.out.println("2. Withdrawal");
        System.out.println("3. Transfer");
        System.out.println("0. Logout");
        System.out.println("----------------------------------------");
        Scanner scanner = new Scanner(System.in);
        selectedOption = scanner.nextInt();
        switch (selectedOption) {
            case 1:
                bankingTransaction.deposit(accountNumber);
                showMenu(accountNumber);
                break;
            case 2:
                bankingTransaction.withdrawal(accountNumber);
                showMenu(accountNumber);
                break;
            case 3:
                bankingTransaction.verifyOtpAndTransfer(accountNumber);
                showMenu(accountNumber);
                break;

            case 0:
                System.out.println("Exited successfully");
                System.exit(0);
                break;

            default:
                System.out.println("Invalid selection");
                System.exit(0);
                break;
        }

    }

}
