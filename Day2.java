package day2;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Day2 {

  public static void main(String[] argv) {

    try {
      String fileName = "input.txt";
      Path path = Paths.get(fileName);
      Files.readAllBytes(path);
      List<String> allLines = Files.readAllLines(path, StandardCharsets.UTF_8);
      int result = 0;

      for(String game : allLines) {
        char yourMove = game.charAt(2);
        char opponentMove = game.charAt(0);

        result += movePoints(yourMove) + gamePoints(yourMove, opponentMove);
      }

      System.out.println(result);

      result = 0;
      for(String game : allLines) {
        char opponentMove = game.charAt(0);
        char yourMove = calculateMove(opponentMove, game.charAt(2));

        result += movePoints(yourMove) + gamePoints(yourMove, opponentMove);
      }
      System.out.println(result);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  private static char calculateMove(char opponentMove, char outcome) {
    return switch (outcome) {
      case 'X' -> {
        if(opponentMove == 'A') {
          yield 'Z';
        } else if(opponentMove == 'B') {
          yield 'X';
        } else {
          yield 'Y';
        }
      }
      case 'Y' -> {
        if(opponentMove == 'A') {
          yield 'X';
        } else if(opponentMove == 'B') {
          yield 'Y';
        } else {
          yield 'Z';
        }
      }
      case 'Z' -> {
        if(opponentMove == 'A') {
          yield 'Y';
        } else if(opponentMove == 'B') {
          yield 'Z';
        } else {
          yield 'X';
        }
      }
      default -> '0';
    };
  }

  private static int gamePoints(char yourMove, char opponentMove) {
    return switch (yourMove) {
      case 'X' -> {
        if(opponentMove == 'A') {
          yield 3;
        } else if(opponentMove == 'B') {
          yield 0;
        } else {
          yield 6;
        }
      }
      case 'Y' -> {
        if(opponentMove == 'A') {
          yield 6;
        } else if(opponentMove == 'B') {
          yield 3;
        } else {
          yield 0;
        }
      }
      case 'Z' -> {
        if(opponentMove == 'A') {
          yield 0;
        } else if(opponentMove == 'B') {
          yield 6;
        } else {
          yield 3;
        }
      }
      default -> 0;
    };
  }

  private static int movePoints(char yourMove) {
    return switch (yourMove) {
      case 'X' -> 1;
      case 'Y' -> 2;
      case 'Z' -> 3;
      default -> 0;
    };
  }
}
