package com.anagramchecker;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Set;

@Component
public class AnagramStringStore {
    private final HashMap<String, Set<String>> anagramStore = new HashMap<>();

    public HashMap<String, Set<String>> getAnagramStore() {
        return anagramStore;
    }
}
