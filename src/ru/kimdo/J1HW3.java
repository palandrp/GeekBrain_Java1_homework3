package ru.kimdo;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Pavel Petrikovskiy
 * @version 14.05.17.
 */

public class J1HW3 {

    public static void main(String[] args){

        System.out.println("What game you choice?");
        System.out.println("If game 'Guess number' input '1'");
        System.out.println("If game 'Guess word' input '2'");
        Scanner sc = new Scanner(System.in);

        int choice;
        do {
            choice = sc.nextInt();
        } while (choice < 1 || choice > 2);

        switch (choice) {
            case 1:
                GuessNumber game_gn = new GuessNumber();
                game_gn.gameRun();
                break;
            case 2:
                GuessWord game_gw = new GuessWord();
                game_gw.gameRun();
        }
    }
}


class GuessNumber {

    void gameRun() {

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


class GuessWord {

    void gameRun() {

        String[] words = {"apple", "orange", "lemon", "banana", "apricot",
                "avocado", "broccoli", "carrot", "cherry", "garlic", "grape",
                "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive",
                "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin",
                "potato"};

        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        int word_num = random.nextInt(words.length);    // Загадываем слово;
        boolean lose;
        String answer;
        Pattern pattern = Pattern.compile("[A-Z]+");
        Matcher matcher;           // Используем паттерн для отлова верхнего регистра;
        boolean error;

        while (true) {
            System.out.print("Input your word: ");
            do {
                answer = scanner.nextLine();
                matcher = pattern.matcher(answer);
                error = matcher.find();
                if (error)
                    System.out.println("Type in lower case please!");
            } while (error);         // Пока есть верхний регистр цикл будет мучить юзера;

            lose = false;      // В начале игрового цикла флаг "проигрыша" снимается;
            int i = 0;
            do {
                try {
                    if (words[word_num].charAt(i) == answer.charAt(i)) {
                        System.out.print(answer.charAt(i));
                    } else {
                        System.out.print("#");
                        lose = true;     // Если есть хоть одна неточность, то установится флаг
                    }                    // "проигрыша" - повторения итерации игрового цикла;
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.print("#");            // Отлавливаем: когда ответ меньше, чем
                    lose = true;                      // загаданное слово, адрес ответа выходит за
                    break;                            // границы массива;
                }
                i++;
            } while (i < words[word_num].length());

            if (lose) {
                System.out.print("###############\n");
                System.out.print("\nMake another choice!\n");
            } else {
                System.out.print("\nYou win!");
                break;           // Если в конце цикла флаг не установлен, значит несовпадения
            }                    // отсутствуют, значит победа и выходим из игрового цикла;
        }
    }
}
//            Теперь напишу брутфорсер для этой игры,
//            надоело вводить букавки самому :)