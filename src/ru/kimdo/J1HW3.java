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
//        Ведется счёт между циклами игр:
        int score = 0;

        while (true) {

            int number = random.nextInt(10);
            int guess = -1;
            boolean win = false;
//            Этот массив для функционала проверки, что значение
//              еще не вводилось в игровом цикле:
            int[] result = new int[3];
            Arrays.fill(result, -1);
            int game = 0;            // Счетчик попыток угадывания;

            while (guess < 0 || guess > 9) {
                System.out.print("Make your choice! Input the number from 0 to 9: ");
                guess = scanner.nextInt();
                result[game] = guess;     // Отправляем попытку в память;
            }
//            System.out.println(Arrays.toString(result));       // Это отладочное сообщение;

            game = 1;
            do {
                if (guess == number){
                    System.out.println("You win!");
                    System.out.printf("It was %d!\n", number);
                    score++;               // Если выигрыш, то счет увеличивается;
                    win = true;
                    break;
                } else if (guess > number) {
                    System.out.println("Number is less than your guess!");
                    do {
                        System.out.print("Another guess: ");
                        guess = scanner.nextInt();
                    } while (guess < 0 || guess > 9);
                    int i = 0;
                    while (i < 3){
                        if (result[i] == guess){
                            System.out.println("You said it earlier!");
                            do {
                                System.out.print("Another guess: ");
                                guess = scanner.nextInt();
                            } while (guess < 0 || guess > 9);
                            i = 0;        // Пришлось поломать голову над тем, как заставить
                        } else i++;       // цикл, после неверного ввода проверять значения с начала;
                    }
                    result[game] = guess;
//                    System.out.println(Arrays.toString(result));    // Отладочное сообщение;
                    if (guess == number){
                        System.out.println("You win!");
                        System.out.printf("It was %d!\n", number);
                        score++;
                        win = true;
                        break;
                    }
                } else {
                    System.out.println("Number is higher than your guess!");
                    do {
                        System.out.print("Another guess: ");
                        guess = scanner.nextInt();
                    } while (guess < 0 || guess > 9);
                    int i = 0;
                    while (i < 3){
                        if (result[i] == guess){
                            System.out.println("You said it earlier!");
                            do {
                                System.out.print("Another guess: ");
                                guess = scanner.nextInt();
                            } while (guess < 0 || guess > 9);
                            i = 0;
                        } else i++;
                    }
                    result[game] = guess;
//                    System.out.println(Arrays.toString(result));     // Отладочное сообщение;
                    if (guess == number){
                        System.out.println("You win!");
                        System.out.printf("It was %d!\n", number);
                        score++;
                        win = true;
                        break;
                    }
                }
                game++;
            } while (game != 3);

            if (!win) {
                score--;        // Если проигрыш, то счет уменьшается (даже в минус);
                System.out.println("You lost! Overflow numbers of attempts!");
                System.out.printf("It was %d!\n", number);
            }

            if (score == 10) {      // В этой игре можно победить! :)))
                System.out.println("You are the absolute winner!");
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
