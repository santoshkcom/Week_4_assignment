package com.greatlearning.bankapp;

/**
 * Banking main APP
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        BankingTransaction bankingTransaction = new BankingTransaction();
        try {
            //verify credential and initiate display menu
            bankingTransaction.verifyCredential();
        } catch (GenericException e) {
            GenericException genericException = new GenericException("Error occurred while transaction");
            System.out.println(genericException.getMessage());
        }
    }
}
