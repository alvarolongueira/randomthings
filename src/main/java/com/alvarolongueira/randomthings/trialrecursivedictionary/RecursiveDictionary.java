package com.alvarolongueira.randomthings.trialrecursivedictionary;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RecursiveDictionary {

    /*
     * Complete the 'longestChain' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING_ARRAY words as parameter.
     */
    public static int longestChain(List<String> words) {

        int solution = 0;
        Map<Integer, List<String>> wordsByLengthMap = new HashMap<Integer, List<String>>();

        for (String word : words) {
            List<String> wordsWithLenght = wordsByLengthMap.get(word.length());
            if (wordsWithLenght == null) {
                wordsWithLenght = new ArrayList<String>();
            }

            wordsWithLenght.add(word);
            wordsByLengthMap.put(word.length(), wordsWithLenght);
        }

        Map<String, Integer> wordsWithValues = new HashMap<String, Integer>();

        for (int length : wordsByLengthMap.keySet().stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList())) {
            for (String currentWord : wordsByLengthMap.get(length)) {

                int value = removeLetterAndCheck(currentWord, wordsByLengthMap, wordsWithValues);
                if (solution < value) {
                    solution = value;
                }
                if (solution >= length) {
                    break;
                }

            }
            if (solution >= length) {
                break;
            }
        }
        return solution;
    }

    private static int removeLetterAndCheck(String word, Map<Integer, List<String>> wordsByLengthMap, Map<String, Integer> wordsWithValues) {
        Integer newValue = wordsWithValues.get(word);

        if (newValue != null) {
            return newValue;
        }

        int value = 1;

        for (int i = 0; i < word.length(); i++) {
            String newWord = word.substring(0, i) + word.substring(i + 1, word.length());
            List<String> wordsWithThisLenght = wordsByLengthMap.get(newWord.length());

            if ((wordsWithThisLenght != null) && wordsWithThisLenght.contains(newWord)) {

                newValue = wordsWithValues.get(newWord);
                if (newValue == null) {
                    newValue = removeLetterAndCheck(newWord, wordsByLengthMap, wordsWithValues) + 1;
                }
                if (newValue > value) {
                    value = newValue;
                }
            }
            if (value >= word.length()) {
                break;
            }
        }
        wordsWithValues.put(word, value);
        return value;
    }
}
