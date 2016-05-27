package com.crooks;

import com.sun.org.apache.regexp.internal.CharacterArrayCharacterIterator;
import jdk.nashorn.internal.ir.debug.JSONWriter;
import jodd.json.JsonSerializer;

import javax.swing.text.html.parser.Parser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        HashMap<String, ArrayList<Country>> countryHash = new HashMap<String, ArrayList<Country>>();
        File f = new File("countries.txt");
        Scanner fileScanner = new Scanner(f);

        parseFile(countryHash, fileScanner);

        String userInput = userInput();


        exportFile(countryHash, userInput);
        exportJson(countryHash, userInput);

        //TODO Sanitize user input

    }

    public static String userInput(){
        System.out.println("Pick a Letter to Receive a List of countries that begin with said letter:\n");
        Scanner scanner = new Scanner(System.in);
        String userinput = scanner.nextLine();


        if (userinput.length() !=1) {
            System.out.println("Please enter a single character only.");
            userInput();
        }
        //TODO take character to unicode, find if it falls within the alphabet numeric range, return a message appropriately
        ArrayList<String> invalidChar = new ArrayList<>();
        /* Fix on return from Lunch!!!!!     if (userinput.contains()){
            System.out.println("holy shit what are you doing!");
        }*/


        return userinput;

    }

    public static void exportFile(HashMap<String, ArrayList<Country>> countryHash, String userInput) throws IOException {


        String fileName = userInput + "_countries.txt";
        ArrayList<Country> fileText = countryHash.get(userInput);
        File outPut = new File(fileName);
        FileWriter fw = new FileWriter(outPut);
        fw.write(fileText.toString());
        fw.close();
    }


    public static void exportJson(HashMap<String, ArrayList<Country>> countryHash, String userInput) throws IOException {

        String fileName = userInput + "_Json_countries.txt";
        JsonSerializer serializer = new JsonSerializer();
        ArrayList<Country> fileText = countryHash.get(userInput);
        String json =  serializer.include("*").serialize(fileText);
        File f = new File(fileName);
        FileWriter fw = new FileWriter(f);
        fw.write(json);
        fw.close();

    }

    public static void parseFile(HashMap<String, ArrayList<Country>> countryHash, Scanner fileScanner){
        while (fileScanner.hasNext()) {                             //while (there is more to read...)
            String line = fileScanner.nextLine();                   // read line from file and set it to a variable
            String[] columns = line.split("\\|");                    // Take the variable and split it's contents based on the delimiter "|" into a new array
            Country country = new Country(columns[0], columns[1]); // Using index numbers of the new array, create a new Country object
            String letter = String.valueOf(country.fullName.charAt(0));  // Set a variable to the first letter of the country

            if (!countryHash.containsKey(letter)) {
                countryHash.put(letter, new ArrayList<Country>());
            }
            countryHash.get(letter).add(country);
        }
    }

}
