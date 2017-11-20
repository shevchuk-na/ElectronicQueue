package ru.queue.utility;

import java.util.Random;

public class NameUtil {

    private static String[] firstNames = {"Зеленый", "Сладкий", "Северный", "Худой", "Огромный", "Крошечный", "Южный", "Длинный", "Космический", "Подземный", "Богатый", "Синий"};
    private static String[] lastNames = {"Адвокат", "Бычок", "Пончик", "Космонавт", "Крот", "Комар", "Кедр", "Кит", "Круг", "Сыр", "Квартал", "Водитель"};

    public static String generateName(){
        return firstNames[new Random().nextInt(firstNames.length)] + " " + lastNames[new Random().nextInt(lastNames.length)];
    }
}
