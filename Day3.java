package day3;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day3 {

  public static void main(String[] args) {

    try {
      String fileName = "input.txt";
      Path path = Paths.get(fileName);
      Files.readAllBytes(path);
      List<String> allLines = Files.readAllLines(path, StandardCharsets.UTF_8);

      int result = 0;

      for(String line : allLines) {
        result += valueBasedOnASCII(sharedItem(line));
      }
      System.out.println(result);

      result = 0;
      for(int i = 0; i <= allLines.size() - 3; i += 3) {
        result += valueBasedOnASCII(findCommonInThree(allLines.get(i), allLines.get(i + 1), allLines.get(i + 2)));
      }
      System.out.println(result);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  private static int valueBasedOnASCII(Character c) {
    if(Character.isUpperCase(c)) {
      return  (int) c - 65 + 27;
    } else {
      return (int) c - 97 + 1;
    }
  }

  private static Character findCommonInThree(String s, String s1, String s2) {
    Set<Character> charsS = new HashSet<>();
    for(int i = 0; i < s.length(); i++) {
      charsS.add(s.charAt(i));
    }

    Set<Character> charsS1 = new HashSet<>();
    for(int i = 0; i < s1.length(); i++) {
      charsS1.add(s1.charAt(i));
    }

    Set<Character> charsS2 = new HashSet<>();
    for(int i = 0; i < s2.length(); i++) {
      charsS2.add(s2.charAt(i));
    }

    charsS.retainAll(charsS1);
    charsS.retainAll(charsS2);

    for (Character shared : charsS) {
      return shared;
    }

    return '0';
  }

  private static Character sharedItem(String line) {
    Set<Character> chars = new HashSet<>();

    for(int i = 0; i < line.length() / 2; i++) {
      chars.add(line.charAt(i));
    }

    for(int i = line.length() - 1; i >= line.length() / 2; i--) {
      if(chars.contains(line.charAt(i))) {
        return line.charAt(i);
      }
    }

    return '0';
  }
}
