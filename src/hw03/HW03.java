package hw03;

import java.util.*;

public class HW03 {
    private static final String[] words =
            {"Доминиканская","Республика",
              "это","государство",
              "в","Карибском","бассейне",
              "расположенное","на","остров",
              "Гаити","Западную","часть",
              "остров","занимает","Республика","Гаити",
              "на","самом","деле","Доминикана","это","отдельная",
              "государство","с","полным","названием",
              "Доминиканская","Республика"};

    public static void main(String[] args) {
/*.
1. Создать массив с набором слов (10-20 слов,
должны встречаться повторяющиеся).
Найти и вывести список уникальных слов, из которых
состоит массив (дубликаты не считаем).
Посчитать сколько раз встречается каждое слово.*/
       wordsCounter(words);
/*
2. Написать простой класс ТелефонныйСправочник,
который хранит в себе список фамилий и телефонных
номеров. В этот телефонный справочник с помощью
метода add() можно добавлять записи.
С помощью метода get() искать номер телефона
по фамилии.
Следует учесть, что под одной фамилией может быть
несколько телефонов (в случае однофамильцев),
тогда при запросе такой фамилии должны выводиться
все телефоны. */
        System.out.println("\n-----> demo Phone Book");
        demoPhoneBook();
    }

    private static void demoPhoneBook() {
        //Create PB
        System.out.println("Creating the Phone Book...");
        PhoneBook PB = new PhoneBook();
        //populate PB
        System.out.println("Adding subscribers...");
        System.out.println(PB.add("12345", "Bender"));
        System.out.println(PB.add("76798", "Balaganov"));
        System.out.println(PB.add("45678", "Balaganov"));
        System.out.println(PB.add("334455", "Balaganov"));
        System.out.println(PB.add("765436","Panikovskiy"));
        System.out.println(PB.add("4353535","Kozlevich"));
        //search in PB
        System.out.print("\nSearching in the Phone Book..");
        searchInPhoneBook(PB, "Nemo");
        searchInPhoneBook(PB, "Bender");
        searchInPhoneBook(PB, "Balaganov");
    }

    private static void searchInPhoneBook(PhoneBook PB, String surname) {
        System.out.printf("\nSearch subscriber '%s'",surname);
        List<String> phones= PB.get(surname);
        if (!phones.isEmpty()) {
            System.out.println("\nThe subscriber has the following phone number(s): ");
            for (String phone: phones
                 ) {
                System.out.println(phone);
            }
        } else {
            System.out.println("\nThe subscriber isn't found!");
        }
    }

    private static void wordsCounter(String[] words) {

        System.out.printf("Total words in array: %d%n",
                words.length);
        System.out.println("The list of unique words: ");

        // array to set
        Set<String> set = arrayToSet(words);
        // print set
        printSet(set);
        System.out.println("\nFrequency token:");
        // count token
        Map<String, Integer> hm = getTokenFrequencyMap(words);
        // print tokens
        printTokens(hm);
    }

    private static Map<String, Integer> getTokenFrequencyMap(String[] words) {
        int curValue;
        Map<String, Integer> hm = new HashMap<>();
        for (String word: words
             ) {
            if (hm.containsKey(word)) {
                curValue=hm.get(word).intValue()+1;
            } else {
                curValue=1;
            }
            hm.put(word,curValue);
        }
        return hm;
    }

    private static void printTokens(Map<String, Integer> hm) {
        hm.forEach((k, v) -> {
           System.out.println(k + ": \t" + v);
        });
    }

    private static Set<String> arrayToSet(String[] words) {
        Set<String> set = new HashSet<>();
        for (String word: words
             ) {
            set.add(word);

        }
        return set;
    }

    private static void printSet(Set<String> set) {
        for (String s: set
             ) {
            System.out.println(s);
        }
    }
}
