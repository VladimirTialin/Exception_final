package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class DataFiles {
    public static void add(Map<Integer, String> parsedData, String fileName) throws IOException{
        Path contactsPath = Paths.get("contacts/");
        if (!Files.exists(contactsPath)) {
            try {
                Files.createDirectory(contactsPath);
            } catch (Exception e) {
                System.out.println("Не удалось создать директорию contacts/");
                e.printStackTrace();
            }
        }
        File file = new File("contacts/" + fileName + ".txt");
        saveData(parsedData, file.getPath(), file.exists());
    }

    private static void saveData(Map<Integer, String> parsedData, String path, boolean move) throws IOException{
        try (FileWriter fw = new FileWriter(path, move)) {
            for (String value : parsedData.values()) {
                fw.append("<")
                        .append(value)
                        .append(">");
            }
            fw.append("\n");
        }
    }

    public static boolean isEnd() {
        System.out.print("\nДобавить еще одну запись?\n1. Да\n2. Нет\nВыбор: ");
        int choice = OnlyCorrect.correctInt(1, 2);
        return choice == 1;
    }
}