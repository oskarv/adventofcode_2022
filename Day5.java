package day5;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Day5 {

  public static void main(String[] args) {  try {
    String fileName = "input.txt";
    Path path = Paths.get(fileName);
    Files.readAllBytes(path);
    List<String> allLines = Files.readAllLines(path, StandardCharsets.UTF_8);

    List<Stack<Character>> stacks = new ArrayList<>();

    createStacks(stacks, allLines);

    for(var stack : stacks) {
      System.out.print(stack.peek());
    }

  } catch (Exception e) {
    System.out.println(e.getMessage());
  }
 }

  private static void createStacks(List<Stack<Character>> stacks, List<String> allLines) {
    int numberOfStacks = 0;
    int moveIndex = 0;
    for(int i = 0; i < allLines.size(); i++) {
      if(allLines.get(i).startsWith(" 1")) {
        numberOfStacks = Integer.parseInt(allLines.get(i).substring(allLines.get(i).length() - 1));
        moveIndex = i + 2;
        break;
      }
    }

    for(int i = 0; i < numberOfStacks; i++) {
      stacks.add(new Stack<>());

      int currentIndex = i * 4 + 1;

      for(int j = moveIndex - 2; j >= 0; j--) {
        if(allLines.get(j).length() > currentIndex) {
          char c = allLines.get(j).charAt(currentIndex);
          if(c != ' ') {
            stacks.get(i).push(c);
          }
        }
      }
    }

    for(int i = moveIndex; i < allLines.size(); i++) {
      String[] split = allLines.get(i).split(" ");
      int move = Integer.parseInt(split[1]);
      int from = Integer.parseInt(split[3]);
      int to = Integer.parseInt(split[5]);

      // PART 1
      // for(int j = 0; j < move; j++) {
      //    stacks.get(to - 1).push(stacks.get(from - 1).pop());
      // }

      // PART 2
      List<Character> temp = new ArrayList<>();
      for(int j = 0; j < move; j++) {
        temp.add(stacks.get(from - 1).pop());
      }
      for(int j = move - 1; j >= 0; j--) {
        stacks.get(to - 1).push(temp.get(j));
      }
    }
  }

}
