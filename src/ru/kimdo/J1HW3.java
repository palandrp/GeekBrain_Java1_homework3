package ru.kimdo;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Pavel Petrikovskiy
 * @version 14.05.17.
 */

public class J1HW3 {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int score = 0;

        while (true) {

            int number = random.nextInt(10);
            int guess = -1;
            boolean win = false;
//            int[] result = new int[3];
//            Arrays.fill(result, -1);
//            System.out.println(Arrays.toString(result));

            while (guess < 0 || guess > 9) {
                System.out.print("Make your choice! Input the number from 0 to 9: ");
                guess = scanner.nextInt();
            }
            int game = 0;
            do {
                if (guess == number){
                    System.out.println("You win!");
                    System.out.printf("It was %d!\n", number);
                    score++;
                    win = true;
                    break;
                } else if (guess > number) {
                    System.out.println("Number is less than your guess!");
                    System.out.print("Another guess: ");
                    guess = scanner.nextInt();
//                    for (int a : result){
//                        if (a == guess){
//                            System.out.print("You said it earlier! Another guess: ");
//                        }
//                    }
//                    result[game] = guess;
                    if (guess == number){
                        System.out.println("You win!");
                        System.out.printf("It was %d!\n", number);
                        score++;
                        win = true;
                        break;
                    }
                } else {
                    System.out.println("Number is higher than your guess!");
                    System.out.print("Another guess: ");
                    guess = scanner.nextInt();
//                    result[game] = guess;
                    if (guess == number){
                        System.out.println("You win!");
                        System.out.printf("It was %d!\n", number);
                        score++;
                        win = true;
                        break;
                    }
                }
                game++;
            } while (game != 2);
            if (!win) {
                score--;
                System.out.println("You lost! Overflow numbers of attempts!");
                System.out.printf("It was %d!\n", number);
            }

            if (score == 10) {
                System.out.println("YOU ABSOLUTELY WINNER!");
                System.out.println("GOODBYE!");
                break;
            }

            int play = -1;

            while (play < 0 || play > 1) {
                System.out.printf("Your score: %d\n", score);
                System.out.println("Play again? Yes: 1; No: 0");
                play = scanner.nextInt();
            }
            if (play == 0) {
                System.out.print("Goodbye!");
                break;
            }
        }
    }
}
