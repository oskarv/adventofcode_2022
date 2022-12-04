package day4;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Day4 {

  public static void main(String[] args) {

    try {
      String fileName = "input.txt";
      Path path = Paths.get(fileName);
      Files.readAllBytes(path);
      List<String> allLines = Files.readAllLines(path, StandardCharsets.UTF_8);
      int count = 0;

      int countOverlapAll = 0;

      for(String line : allLines) {
        String[] ranges = line.split(",");
        String[] rangeOne = ranges[0].split("-");
        String[] rangeTwo = ranges[1].split("-");
        int firstStart = Integer.parseInt(rangeOne[0]);
        int firstEnd = Integer.parseInt(rangeOne[1]);
        int secondStart = Integer.parseInt(rangeTwo[0]);
        int secondEnd = Integer.parseInt(rangeTwo[1]);

        if(firstStart <= secondStart && firstEnd >= secondEnd) {
          count += 1;
        } else if(secondStart <= firstStart && secondEnd >= firstEnd) {
          count += 1;
        }

        if(firstStart <= secondStart && secondStart <= firstEnd) {
          countOverlapAll += 1;
        } else if (secondStart <= firstStart && firstStart <= secondEnd) {
          countOverlapAll += 1;
        }
      }

      System.out.println(count);
      System.out.println(countOverlapAll);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
