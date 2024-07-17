package com.anagramchecker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.HashSet;
import java.util.Set;
@ShellComponent
public class AnagramFetch {
    @Autowired
    private AnagramStringStore anagramStringStore;
    @ShellMethod(key = "anagram-fetch")
    public Set<String> anagramFetch(
            @ShellOption String text
    ) {
        Set<String> totalAnagrams = new HashSet<>();
        if (anagramStringStore.getAnagramStore().get(text) != null) {
            fetchAnagramsForGivenString(text, anagramStringStore.getAnagramStore().get(text), totalAnagrams);
        }
        totalAnagrams.remove(text);
        return totalAnagrams;
    }
    private void fetchAnagramsForGivenString(String text, Set<String> anagrams, Set<String> visited) {
        if(visited.contains(text)) {
            return;
        }
        visited.add(text);
        for(String s : anagrams) {
            fetchAnagramsForGivenString(s, anagramStringStore.getAnagramStore().get(s), visited);
        }
    }
}

