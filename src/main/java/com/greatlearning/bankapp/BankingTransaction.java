package com.greatlearning.bankapp;

import com.greatlearning.bankapp.helpers.FileWriterHelper;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.greatlearning.bankapp.constants.GlobalConstants.TRANSFER;

/**
 * BankingTransaction class to perform various transactions
 */
public class BankingTransaction {
    private List<Customers> customers;

    public BankingTransaction() {
        customers = new ArrayList<>();
        customers.add(new Customers(12345, "pass1", 50000));
        customers.add(new Customers(12346, "pass2", 60000));
        customers.add(new Customers(12347, "pass3", 70000));
        customers.add(new Customers(12348, "pass4", 80000));
    }

    /**
     * verify the credential and display the transaction menu
     *
     * @throws GenericException
     */
    public void verifyCredential() throws GenericException {

        try {
            int result = isValidCredential();
            if (result != 0) {
                System.out.println(" ");
                MenuDisplay menuDisplay = new MenuDisplay();

                //display transaction menu
                menuDisplay.showMenu(result);
            } else {
                System.out.println("Invalid credentials");
                isValidCredential();
            }

        } catch (Exception e) {

            GenericException genericException = new GenericException("Error occurred while transaction");
            System.out.println(genericException.getMessage());
            throw genericException;

        }
    }

    /**
     * deposit the amount to a given account number
     *
     * @param accountNumber
     */
    public void deposit(int accountNumber) {
        Customers customer = getCustomers(accountNumber);
        customer.deposit();
    }

    /**
     * withdrawal of amount from a given account number
     *
     * @param accountNumber
     */
    public void withdrawal(int accountNumber) {
        Customers customer = getCustomers(accountNumber);
        customer.withdrawal();
    }

    /**
     * return customer object based on account number
     *
     * @param accountNumber
     * @return Customers object
     */
    private Customers getCustomers(int accountNumber) {
        return customers.stream().filter(x -> x.getBankAccountNo() == accountNumber).findAny().get();
    }

    /**
     * Verify the otp and initiate transfer from a given account
     *
     * @param accountNumber
     */
    public void verifyOtpAndTransfer(int accountNumber) {
        int otp;
        int amount;
        int accountNo;
        System.out.println("Enter the otp");
        System.out.println("8409");
        Scanner scanner = new Scanner(System.in);
        otp = scanner.nextInt();

        //verifying otp provided
        if (otp == 8409) {
            System.out.println("otp verification successful!");
            System.out.println("enter the amount and bank account number to which you want to transfer");
            amount = scanner.nextInt();
            accountNo = scanner.nextInt();
            Customers customer = getCustomers(accountNo);
            //transferring amount to the account provided
            customer.setBalance(customer.getBalance() + amount);
            System.out.println(MessageFormat.format("Amount {0} transferred successful to bankAccount " + accountNo, amount));

            //deducting the amount from the account
            customer = getCustomers(accountNumber);
            customer.setBalance(customer.getBalance() - amount);
            System.out.println(MessageFormat.format("Total Balance: {0}", customer.getBalance()));
            System.out.println(" ");

            //writing transaction to file
            FileWriterHelper writerHelper = new FileWriterHelper();
            writerHelper.writeToFile(TRANSFER);

        } else {
            System.out.println("otp verification unsuccessful!");
        }
    }

    /**
     * verify valid credential and return the account number if valid
     *
     * @return account number after successful validation
     */
    private int isValidCredential() {
        final String password;
        final int account;
        System.out.println("Welcome to the login page");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the bank account number:");
        account = scanner.nextInt();
        System.out.println("Enter the Password for the corresponding bank account number:");
        password = scanner.next();

        boolean isValid = customers.stream()
                .anyMatch(x -> x.getBankAccountNo() == account && x.getPassword().equals(password));
        return isValid ? account : 0;
    }
}
