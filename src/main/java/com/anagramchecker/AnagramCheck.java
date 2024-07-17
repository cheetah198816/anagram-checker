package com.anagramchecker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@ShellComponent
public class AnagramCheck {
    @Autowired
    private AnagramStringStore anagramStringStore;
    @ShellMethod(key = "anagram-check")
    public boolean anagramCheck(
            @ShellOption String textOne,
            @ShellOption String textTwo
    ) {
        if (textOne == null || textTwo == null) {
            throw new IllegalArgumentException("Required two arguments received only one");
        }
        boolean check = checkIfStringsAreAnagram(textOne, textTwo);
        HashMap<String, Set<String>> anagramStore = anagramStringStore.getAnagramStore();
        if(check) {
            if(!anagramStore.containsKey(textOne)) {
                anagramStore.put(textOne, new HashSet<>());
            }
            if (!anagramStore.containsKey(textTwo)) {
                anagramStore.put(textTwo, new HashSet<>());
            }
            anagramStore.get(textOne).add(textTwo);
            anagramStore.get(textTwo).add(textOne);
        }
        return check;
    }

    private boolean checkIfStringsAreAnagram(String textOne, String textTwo) {
        if (textOne.length() != textTwo.length()) {
            return false;
        }
        char[] textOneArray = textOne.toCharArray();
        char[] textTwoArray = textTwo.toCharArray();
        Arrays.sort(textOneArray);
        Arrays.sort(textTwoArray);
        return Arrays.equals(textOneArray, textTwoArray);
    }
}
