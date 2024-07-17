# anagram-checker

This application is CLI based application 
- to check if two strings are anagrams of each other.
- to fetch anagrams for a given string.

### Prerequisites

Before starting, make sure you have at least those components on your workstation:

- [Java Development Kit (JDK)](https://www.azul.com/downloads/?package=jdk#download-openjdk) >= 17

## Build the project

```shell
$ mvn install
```

## Run the project locally

```shell
$ mvn spring-boot:run
```

## Run the anagram-check command locally in Spring Shell

```shell
shell:> anagram-check one two
```

## Run the anagram-fetch command locally in Spring Shell

```shell
shell:> anagram-check one
```