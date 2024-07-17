package com.anagramchecker;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AnagramCheckTest {
    @Autowired
    AnagramCheck anagramCheck;
    @Autowired
    AnagramStringStore anagramStringStore;

    @Test
    void checkIfTwoStringsAreAnagram() {
        boolean check = anagramCheck.anagramCheck("test", "tset");

        assertThat(check).isTrue();
        assertThat(anagramStringStore.getAnagramStore()).containsKey("test");
        assertThat(anagramStringStore.getAnagramStore()).containsKey("tset");
    }

    @Test
    void checkIfTwoStringsAreNotAnagram_String_Length_Is_Different() {
        boolean check = anagramCheck.anagramCheck("testf", "tsetog");

        assertThat(check).isFalse();
        assertThat(anagramStringStore.getAnagramStore()).doesNotContainKey("testf");
        assertThat(anagramStringStore.getAnagramStore()).doesNotContainKey("tsetog");

    }

    @Test
    void checkIfTwoStringsAreNotAnagram_String_Length_Is_Same_Characters_Are_Different() {
        boolean check = anagramCheck.anagramCheck("tests", "testq");

        assertThat(check).isFalse();
        assertThat(anagramStringStore.getAnagramStore()).doesNotContainKey("tests");
        assertThat(anagramStringStore.getAnagramStore()).doesNotContainKey("testq");
    }

    @Test
    void checkIfTwoStringsAreAnagram_Only_One_Argument_Passed() {
        assertThrows(IllegalArgumentException.class, () -> anagramCheck.anagramCheck("tests", null));
    }
}
