package ru.queue.utility;

import java.util.Random;

public class NameUtil {

    private static String[] firstNamesM = {"Зеленый", "Сладкий", "Северный", "Худой", "Огромный", "Крошечный", "Южный", "Длинный", "Космический", "Подземный", "Богатый", "Синий",
            "Мягкий", "Волшебный", "Строгий", "Дерзкий", "Ворчливый", "Добрый", "Волосатый", "Светлый", "Неземной"};
    private static String[] lastNamesM = {"Адвокат", "Бычок", "Пончик", "Космонавт", "Крот", "Комар", "Кедр", "Кит", "Круг", "Сыр", "Квартал", "Мишка", "Воробей", "Трамвай",
            "Хвостик", "Цветочек", "Глаз"};
    private static String[] firstNamesF = {"Зеленая", "Сладкая", "Северная", "Худая", "Огромная", "Крошечная", "Южная", "Длинная", "Космическая", "Подземная", "Богатая", "Синяя",
            "Мягкая", "Волшебная", "Строгая", "Дерзкая", "Ворчливая", "Добрая", "Волосатая", "Светлая", "Неземная"};
    private static String[] lastNamesF = {"Ласточка", "Кровать", "Совесть", "Пятка", "Свинка", "Лопата", "Ягода", "Москва", "Береза", "Ночь", "Лень", "Нога"};

    public static String generateName(){
        int gender = new Random().nextInt(2);
        switch (gender) {
            case 0: {
                return firstNamesM[new Random().nextInt(firstNamesM.length)] + " " + lastNamesM[new Random().nextInt(lastNamesM.length)];
            }
            default: {
                return firstNamesF[new Random().nextInt(firstNamesF.length)] + " " + lastNamesF[new Random().nextInt(lastNamesF.length)];
            }
        }

    }
}
