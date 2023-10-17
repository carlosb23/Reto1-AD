package org.example;


import static org.example.DataProcessor.processFiles;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    /**
     * Main method that invokes the changesfile method to process data and generate output files.
     *
     * @param args Command-line arguments (not used in this program).
     */
    public static void main(String[] args) {

        processFiles("data.csv", "template.txt");
    }
}