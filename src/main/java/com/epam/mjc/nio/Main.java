package com.epam.mjc.nio;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        System.out.println(new CheckSolutions().fileExists());

        File fileToRead = new File("./" + "/src/main/resources/Profile.txt");

        FileReader fileReader = new FileReader();
        fileReader.getDataFromFile(fileToRead);
    }
}
