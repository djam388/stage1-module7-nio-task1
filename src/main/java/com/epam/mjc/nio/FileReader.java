package com.epam.mjc.nio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


public class FileReader {

    public Profile getDataFromFile(File file) {
        String name = "";
        String email = "";
        int age = 0;
        Long phone = 0L;
        RandomAccessFile aFile = null;
        try {
            aFile = new RandomAccessFile(file.getCanonicalPath(), "r");
            FileChannel channel = aFile.getChannel();
            long fileSize = channel.size();
            ByteBuffer buffer = ByteBuffer.allocate((int) fileSize);
            StringBuilder readContent = new StringBuilder();


            channel.read(buffer);
            buffer.flip();

            for (int i = 0; i < fileSize; i++) {
                readContent.append((char) buffer.get());
            }
            aFile.close();


            String[] contentLines = readContent.toString().split("\\r?\\n|\\r");

            for (int i = 0; i < contentLines.length; i++) {
                String[] lineValues = contentLines[i].split(": ");
                if (lineValues.length > 1)  {
                    if (lineValues[0].equals("Name")) {
                        name = lineValues[1];
                    }
                    else if (lineValues[0].equals("Age")) {
                        age = Integer.valueOf(lineValues[1]);
                    }
                    else if (lineValues[0].equals("Email")) {
                        email = lineValues[1];
                    }
                    else if (lineValues[0].equals("Phone")) {
                        phone = Long.valueOf(lineValues[1]);
                    }
                }
            }
            return new Profile(name, age, email, phone);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                aFile.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}
