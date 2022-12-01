package main;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Day1 {

  public static void main(String[] args) {

    try {
      String fileName = "files/input.txt";
      Path path = Paths.get(fileName);
      Files.readAllBytes(path);
      List<String> allLines = Files.readAllLines(path, StandardCharsets.UTF_8);

      List<Long> sums = new ArrayList<>();
      long currentSum = 0;

      for(String line : allLines) {
        if(line.isEmpty() || line.isBlank()) {
          sums.add(currentSum);
          currentSum = 0;
        } else {
          currentSum += Long.parseLong(line);
        }
      }

      Optional<Long> max = sums.stream().max(Comparator.comparingLong(x -> x));

      if(max.isPresent()) {
        System.out.println(max);
      }

      // if speed was not important can be done in O(1)
      sums.sort(Comparator.comparingLong(x -> x));
      // unsafe if trere are lt 3 elements
      System.out.println(sums.get(sums.size() - 1) + sums.get(sums.size() - 2) + sums.get(sums.size() - 3));

    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }

  }
}
