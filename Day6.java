package day6;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day6 {

  public static void main(String[] args) {

    try {
    String fileName = "input.txt";
    Path path = Paths.get(fileName);
    Files.readAllBytes(path);
    List<String> allLines = Files.readAllLines(path, StandardCharsets.UTF_8);

      String input = allLines.get(0);
      Set<Character> chars = new HashSet<>();

      // PART 1
      for(int i = 0; i < input.length() - 4; i++) {
        for(int j = 0; j < 4; j++) {
          if(!chars.add(input.charAt(i + j))) {
            break;
          }
        }

        if(chars.size() == 4) {
          System.out.println(i + 4);
          break;
        }

        chars = new HashSet<>();
      }

      // PART 2
      for(int i = 0; i < input.length() - 14; i++) {
        for(int j = 0; j < 14; j++) {
          if(!chars.add(input.charAt(i + j))) {
            break;
          }
        }

        if(chars.size() == 14) {
          System.out.println(i + 14);
          break;
        }

        chars = new HashSet<>();
      }

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
