package org.example;

import java.io.IOException;

public class Program {
    public static void main(String[] args) {
        while(true) {
            System.out.println("""
                    
                    Введите в произвольном порядке данные в формате:
                    ФИО - строки
                    дата_рождения - dd.mm.yyyy
                    номер_телефона - целое беззнаковое число без форматирования
                    пол - символ латиницей f или m

                    Ввод:""");
            StringParser parser = new StringParser(UserInformation.stringFromUser());
            try {
                DataFiles.add(parser.getMapForRecord(), parser.getFileNameRecord());
                if (!DataFiles.isEnd()) {
                    break;
                }
            } catch (WrongAmountOfDataException | IllegalArgumentException e) {
                System.err.println(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
