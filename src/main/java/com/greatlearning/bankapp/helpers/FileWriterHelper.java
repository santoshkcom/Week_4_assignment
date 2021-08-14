package com.greatlearning.bankapp.helpers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * FileWriterHelper to write transaction to file
 */
public class FileWriterHelper {
    public void writeToFile(String inputLine) {
        PrintWriter out = null;
        try {
            String fileName = "transactions.txt";
            File f = new File(fileName);

            if (f.exists() && !f.isDirectory()) {
                out = new PrintWriter(new FileOutputStream(fileName, true));
            } else {
                out = new PrintWriter(fileName);
            }
            out.println(inputLine);
        } catch (Exception e) {
            System.out.println("Error occurred while writing transaction to file:" + e.getMessage());
        } finally {
            if (out != null)
                out.close();
        }

    }
}
