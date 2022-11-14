package com.epam.mjc.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CheckSolutions {

    public CheckSolutions () {}

    public boolean fileExists () {
        Path path = Paths.get("./" + "/src/main/resources/Profile.txt");

        boolean fileExists =
                Files.exists(path,
                        new LinkOption[]{ LinkOption.NOFOLLOW_LINKS});
        try {
            RandomAccessFile aFile = new RandomAccessFile("./" + "/src/main/resources/Profile.txt", "r");
            System.out.println(aFile.length());
            System.out.println(Files.size(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return fileExists;
    }

}
