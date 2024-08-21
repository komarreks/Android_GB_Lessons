package ru.streamapi;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        List<Integer> intList = new ArrayList<>();

        int countElements = new Random().nextInt(21);

        for (int i = 0; i < countElements; i++) {
            intList.add(new Random().nextInt(51));
        }

        System.out.println("Список чисел:" + intList);

        OptionalDouble optional = intList.stream()
                .filter(e -> e % 2 == 0)
                .mapToInt(e -> e).average();

        double avg = optional.isPresent() ? optional.getAsDouble() : 0;

        System.out.println("Среднее арифметическое четных чисел: " + avg);
    }
}
