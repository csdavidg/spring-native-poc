package com.demo.springnativepoc.services;

import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

@Service

public class AlgorithmService {

    public String computeMaximumPalindromeFromAWird(String word) {

        char[] charsS = word.toCharArray();
        Map<Integer, Integer> amountChars = new LinkedHashMap<>();
        for (int num : charsS) {
            amountChars.merge(Character.getNumericValue(num), 1, Integer::sum);
        }

        if (amountChars.size() == 1) {
            return word;
        }

        Map<Integer, Integer> sortedMap = amountChars.entrySet()
                .stream()
                .filter(e -> e.getValue() > 1)
                .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
                .sorted(Map.Entry.<Integer, Integer>comparingByKey().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        Integer maxOne = amountChars.entrySet()
                .stream()
                .filter(e -> e.getValue() == 1)
                .max(Map.Entry.comparingByKey())
                .map(Map.Entry::getKey)
                .orElse(-1);

        Stack<Integer> integerStack = new Stack<>();
        Integer missing = null;
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<Integer, Integer> e : sortedMap.entrySet()) {
            int mid = e.getValue() / 2;
            int count = 0;
            while (count < mid) {
                sb.append(e.getKey());
                count++;
            }

            if (e.getValue() == 3) {
                missing = e.getKey();
                count++;
            }

            while (count < e.getValue()) {
                integerStack.add(e.getKey());
                count++;
            }

            if (e.getValue() == 1 && sb.length() > 0) {
                break;
            }
        }

        if(maxOne > -1) {
            integerStack.add(maxOne);
        }

        if (missing != null) {
            if (integerStack.size() == sb.length()) {
                sb.append(missing);
            } else {
                int max = Integer.max(missing, integerStack.pop());
                sb.append(max);
            }
        }

        while (!integerStack.isEmpty()) {
            sb.append(integerStack.pop());
        }

        return sb.toString();

    }
}
