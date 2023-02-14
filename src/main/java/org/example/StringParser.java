package org.example;

import java.util.*;

public class StringParser {
    private String[] splitArrayFromUserString;
    private PersonDataValidator validator;
    private Map<Integer, String> parsedMapForRecord;
    private String msgValid ="Проверьте правильность ввода данных.";
    private String msg ="Введено больше данных, чем требуется";

    public StringParser(String stringFromUser) {
        this.splitArrayFromUserString = stringFromUser.replaceAll("\\s+", " ").split(" ");
        this.validator = new PersonDataValidator();
        this.parsedMapForRecord = new TreeMap<>();
    }

    public Map<Integer, String> getMapForRecord() {
        int exceptCountData = 6;
        if (countCheck(exceptCountData)) {
            parseString();
        } else {
            if (this.splitArrayFromUserString.length > exceptCountData) {
                throw new WrongAmountOfDataException(msg);
            } else {
                throw new WrongAmountOfDataException(msg);
            }
        }
        return this.parsedMapForRecord;
    }

    public String getFileNameRecord() {
        return parsedMapForRecord.get(1);
    }

    private boolean countCheck(int exceptCountData) {
        return this.splitArrayFromUserString.length == exceptCountData;
    }

    private void parseString() {
        List<String> listNames = new LinkedList<>();

        for (String valueForCheck : this.splitArrayFromUserString) {
            if (validator.sexValidator().isValid(valueForCheck)) {
                if (this.parsedMapForRecord.putIfAbsent(6, valueForCheck) != null) {
                    throw new IllegalArgumentException("Введен лишний пол! "+msgValid);
                }
            }
            else if (validator.phoneValidator().isValid(valueForCheck)) {
                if (this.parsedMapForRecord.putIfAbsent(5, valueForCheck) != null) {
                    throw new IllegalArgumentException("Введен лишний телефон! "+msgValid);
                }
            }
            else if (validator.dateValidator().isValid(valueForCheck)) {
                if (this.parsedMapForRecord.putIfAbsent(4, valueForCheck) != null) {
                    throw new IllegalArgumentException("Введен лишний день рождения! "+msgValid);
                }
            } else {
                listNames.add(capitalizeWord(valueForCheck));
            }
        }
        if (listNames.size() != 3) {
            validationEnteredData(parsedMapForRecord);
        } else {
            mapAcceptedNames(listNames);
        }
    }

    private void validationEnteredData(Map<Integer, String> dataForCheck) {
        StringBuilder sb = new StringBuilder("Неверно введены: ");
        int lengthSB = sb.length();
        if (!dataForCheck.containsKey(4)) {
            sb.append("Дата рождения");
        }
        if (!dataForCheck.containsKey(5)) {
            if (sb.length() != lengthSB) {
                sb.append(", ");
            }
            sb.append("Номер телефона");
        }
        if (!dataForCheck.containsKey(6)) {
            if (sb.length() != lengthSB) {
                sb.append(", ");
            }
            sb.append("Пол");
        }
        if (sb.length() != lengthSB) {
            throw new IllegalArgumentException(sb.toString());
        }
    }

    private void mapAcceptedNames(List<String> listNames) {
        String[] arrayTitles = {"Фамилия", "Имя", "Отчество"};
        while (true) {
            System.out.println("\nПодтвердите ввод Ф.И.О:");
            for (int i = 0; i < listNames.size(); i++) {
                System.out.printf("%s: %s%n", arrayTitles[i], listNames.get(i));
            }
            System.out.print("\n1. Подтвердить\n2. Изменить\nВыбор: ");
            int choice = OnlyCorrect.correctInt(1, 2);
            if (choice == 1) {
                this.parsedMapForRecord.put(1,listNames.get(0));
                this.parsedMapForRecord.put(2,listNames.get(1));
                this.parsedMapForRecord.put(3,listNames.get(2));
                break;
            } else {
                changeNameTitles(listNames);
            }
        }
    }

    private void changeNameTitles(List<String> listNames) {
        String title = "фамилию";
        int count = 1;
        for (int i = 0; i < listNames.size() - 1; i++) {
            System.out.println("\nВыберете " + title + ":");
            for (int j = i; j < listNames.size(); j++) {
                System.out.printf("%d. %s%n", count++, listNames.get(j));
            }
            System.out.print("Выбор: ");
            int choice = OnlyCorrect.correctInt(1, count - 1);
            String temp = listNames.get(choice + i - 1);
            listNames.set(choice + i - 1,listNames.get(i));
            listNames.set(i, temp);
            title = "имя";
            count = 1;
        }
    }

    private String capitalizeWord(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
}
