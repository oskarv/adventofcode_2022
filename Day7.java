package day7;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.util.function.Predicate.not;

public class Day7 {
  private static final Dir root = Dir.newDir(null);
  private static Dir current = root;

  public static void main(String[] args) {
    try {
      String fileName = "input.txt";
      Path path = Paths.get(fileName);
      Files.readAllBytes(path);
      List<String> allLines = Files.readAllLines(path, StandardCharsets.UTF_8);
      allLines.stream().filter(not(String::isBlank)).skip(1).forEach(Day7::parse);
      System.out.println("root.size() = " + root.size());

      final long smallDirSize = root.listRecursive()
          .mapToLong(Dir::size)
          .filter(size -> size <= 100_000)
          .sum();
      System.out.println("smallDirSize = " + smallDirSize);

      final long sizeToFind = 30_000_000 - (70_000_000 - root.size());
      System.out.println("sizeToFind = " + sizeToFind);

      final long dirToRemoveSize = root.listRecursive()
          .mapToLong(Dir::size)
          .filter(size -> size >= sizeToFind)
          .min()
          .orElseThrow();
      System.out.println("dirToRemoveSize = " + dirToRemoveSize);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  private static void parse(final String line) {
    switch (line.charAt(0)) {
      case '$' -> {
        if(line.charAt(2) == 'c') {
          current = current.cd(line.substring(5));
        }
      }
      case 'd' -> current.addDir(line.substring(4));
      default -> current.addFile(line.substring(line.indexOf(' ') + 1), Long.parseLong(line.substring(0, line.indexOf(' '))));
    }
  }
}
