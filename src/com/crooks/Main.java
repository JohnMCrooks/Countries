package com.crooks;

import javax.swing.text.html.parser.Parser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

      // ArrayList<Country> cArray = new ArrayList<Country>();

        HashMap<String, ArrayList<Country>> countryHash = new HashMap<String, ArrayList<Country>>();
        File f = new File("countries.txt");
        Scanner fileScanner = new Scanner(f);


        while (fileScanner.hasNext()) {            //while (there is more to read...)
            String line = fileScanner.nextLine(); // read line from file and set it to a variable
            String[] columns = line.split("\\|"); // Take the variable and split it's contents based on the delimiter "|" into a new array
            Country country = new Country(columns[0], columns[1]); // Using index numbers of the new array, create a new Country object
            String letter = String.valueOf(country.fullName.charAt(0));  // Setting a variable to the first letter of the country

            if (!countryHash.containsKey(letter)) {
                countryHash.put(letter, new ArrayList<Country>());
            }
            countryHash.get(letter).add(country);
        }

        //TODO Sanitize user input
        //TODO Add Json export
        //TODO Refactor main method

        System.out.println("Pick a Letter to Recieve a country List");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        String fileName = userInput + "_countries.txt";
        ArrayList<Country> fileText = countryHash.get(userInput);
        File outPut = new File(fileName);

        FileWriter fw = new FileWriter(outPut);
        fw.write(fileText.toString());
        fw.close();

    }

}
