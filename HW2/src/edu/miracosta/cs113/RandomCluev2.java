package edu.miracosta.cs113;

import java.util.Random;
import java.util.Scanner;
import model.Theory;
import model.AssistantJack;

public class RandomCluev2 {

    /**
     * Driver method for calculated guessing approach
     *
     * @param args not used for driver
     */
    public static void main(String[] args) {
        // DECLARATION + INITIALIZATION
        int answerSet, solution;
        int weapon = 1, location = 1, murder = 1;
        Theory answer;
        AssistantJack jack;
        Scanner keyboard = new Scanner(System.in);
        Random random = new Random();

        // INPUT
        System.out.print("Which theory would like you like to test? (1, 2, 3[random]): ");
        answerSet = keyboard.nextInt();
        keyboard.close();

        // PROCESSING
        jack = new AssistantJack(answerSet);

        do {
            solution = jack.checkAnswer(weapon, location, murder);
            if (solution == 1) weapon++;
            else if (solution == 2) location++;
            else if (solution == 3) murder++;
        } while (solution != 0);

        answer = new Theory(weapon, location, murder);

        // OUTPUT
        System.out.println("Total Checks = " + jack.getTimesAsked() + ", Solution " + answer);

        if (jack.getTimesAsked() > 20) {
            System.out.println("FAILED!! You're a horrible Detective...");
        } else {
            System.out.println("WOW! You might as well be called Batman!");
        }

    }

}