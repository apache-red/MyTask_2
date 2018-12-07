package com.redcompany.red;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static final String FILEPATH = "./src/com/redcompany/red/File.txt";
    public static List wordList = new ArrayList();

    public static void main(String[] args) {
        wordList = readFromFile();
        Map<String, Integer> mapList = findDuplicates(wordList);
        showListDuplicates(mapList);
    }


    public static List readFromFile() {
        try (BufferedReader reader = new BufferedReader(
                new FileReader((FILEPATH)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                wordList = parsingFile(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File was no found!");
        } catch (IOException e) {
            System.out.println("Problems with the file");
        }
        return wordList;
    }

    private static List parsingFile(String line) {
        String[] strArray = line.replaceAll("[^a-zA-Z+ ]", " ").toLowerCase().split("\\s+");
        for (int i = 0; i < strArray.length; i++) {
            wordList.add(strArray[i]);
        }
        return wordList;
    }

    public static Map<String, Integer> findDuplicates(List wordList) {
        Map<String, Integer> resultList = new HashMap<String, Integer>();
        int count = 0;
        String str = "";
        String find = "";
        for (int i = 0; i < wordList.size(); i++) {
            str = wordList.get(i).toString();
            for (int j = 0; j < wordList.size(); j++) {
                find = wordList.get(j).toString();
                if (find.equals(str)) {
                    count++;
                }
            }
            resultList.put(str, count);
            count = 0;
        }
        return resultList;
    }

    public static void showListDuplicates(Map<String, Integer> mapList) {
        int numberOfDisplayedResults = 20;
        mapList.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).
                limit(numberOfDisplayedResults)
                .forEach(System.out::println);
    }

}
