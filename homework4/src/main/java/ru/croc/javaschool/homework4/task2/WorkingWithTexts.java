package ru.croc.javaschool.homework4.task2;

import ru.croc.javaschool.homework4.general.MapUtil;

import java.util.Objects;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;


/**
 * Работа с текстом.
 *
 * @author Danila Fedortsov
 */
public class WorkingWithTexts {
    /**
     * Точка входа в программу и пример сценария.
     *
     * @param args args
     */
    public static void main(String[] args) {
        ArrayList<String> articles = new ArrayList<>(List.of(
                "Я поел;Артём Исаев;Я сегодня поел. Хорошо поел. Пойду теперь погуляю",
                "Игра в доту;Артём Исаев;Я сегодня играл в доту. Дота очень хорошая игра!",
                "Прогулка;Дарья Аладина;Вчера я ходила на прогулку и прогулка моя, откровенно говоря, не задалась."
        ));
        System.out.println(getRatingMap(articles));
    }

    /**
     * Принимает на вход список строк формата "Название статьи;Имя автора;Текст статьи".
     * Возвращает словарь, где ключ - автор статей, значение - частота использования в тексте слов из названия статей.
     * Словарь отсортирован по убываюнию ключа. null возможен только в случае если на вход был подан null. Если в одной
     * из строк формат был нарушен, то она пропускается. Аналогично, если вместо строки null.
     *
     * @param articles список строк
     * @return словарь, где ключ - имя автора, значение - частота
     */
    public static HashMap<String, Double> getRatingMap(ArrayList<String> articles) {
        if (Objects.isNull(articles)) {
            return null;
        }
        Map<String, Double> ratingMap = new HashMap<>();
        Map<String, ArrayList<Double>> results = new HashMap<>();
        for (String article : articles) {
            if (Objects.isNull(article)) {
                continue;
            }
            String[] articleList = article.split(";");
            if (articleList.length != 3) {
                continue;
            }
            double countWords = getCountWords(articleList[2], getWords(articleList[0]));
            double allWords = getWords(articleList[2]).size();

            results.putIfAbsent(articleList[1], new ArrayList<>());
            results.get(articleList[1]).add(allWords > 0 ? (100.0 * countWords / allWords) : 0.0);
        }

        for (String author : results.keySet()) {
            double sum = 0.0;
            for (Double per : results.get(author)) {
                sum += per;
            }
            ratingMap.put(author, sum / results.get(author).size());
        }
        return (HashMap<String, Double>) MapUtil.sortByValue(ratingMap);
    }

    /**
     * Считает количество вхождений слов из words в text.
     * В words ищутся уникальные слова и уже по ним производится подсчёт. Следует избегать передачи null в качестве
     * параметра.
     *
     * @param text  текст для подсчёта слов
     * @param words искомые слова
     * @return количество слов из words в text
     */
    private static int getCountWords(String text, ArrayList<String> words) {
        Set<String> newWords = new HashSet<>(words);
        ArrayList<String> wordsInText = getWords(text);
        int countWords = 0;

        for (String searchWord : newWords) {
            for (String word : wordsInText) {
                if (searchWord.equals(word)) {
                    countWords++;
                }
            }
        }

        return countWords;
    }

    /**
     * Принимает строку. Возвращает список слов в этой строке. Следует избегать передачи null в качестве параметра.
     *
     * @param text текст
     * @return список слов
     */
    private static ArrayList<String> getWords(String text) {
        return new ArrayList<>(List.of(text
                .replaceAll("\\.", " ")
                .replaceAll(",", " ")
                .replaceAll("!", " ")
                .replaceAll("\\?", " ")
                .replaceAll("\\s+", " ")
                .strip()
                .toLowerCase()
                .split(" ")));
    }
}
