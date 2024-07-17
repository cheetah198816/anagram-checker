package com.anagramchecker;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AnagramFetchTest {
    @Autowired
    AnagramCheck anagramCheck;
    @Autowired
    AnagramFetch anagramFetch;

    @Test
    void test_fetch_anagram_strings_for_a_given_string_without_spaces() {
        anagramCheck.anagramCheck("true", "rute");
        Set<String> anagrams = anagramFetch.anagramFetch("true");
        assertThat(anagrams).hasSize(1);
        assertThat(anagrams).contains("rute");
    }

    @Test
    void test_fetch_anagram_strings_transitive_for_a_given_string_without_spaces() {
        anagramCheck.anagramCheck("test", "tset");
        anagramCheck.anagramCheck("test", "ttes");
        Set<String> anagramsOne = anagramFetch.anagramFetch("test");
        assertThat(anagramsOne).hasSize(2);
        assertThat(anagramsOne).contains("tset");
        assertThat(anagramsOne).contains("ttes");
        Set<String> anagramsTwo = anagramFetch.anagramFetch("tset");
        assertThat(anagramsTwo).hasSize(2);
        assertThat(anagramsTwo).contains("test");
        assertThat(anagramsTwo).contains("ttes");
    }

    @Test
    void test_fetch_anagrams_when_no_anagrams_found() {
        Set<String> anagramsOne = anagramFetch.anagramFetch("tube");
        assertThat(anagramsOne).isEmpty();
    }
}
