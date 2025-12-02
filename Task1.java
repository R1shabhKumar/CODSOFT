import java.util.Random;
import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        final int min = 1;
        final int max = 100;
        final int attempts = 7;
        int won = 0;
        int totalRounds = 0;

        System.out.println("Welcome to the Number Guessing Game!");

        boolean again = true;
        while(again){
            int toGuess = rand.nextInt(max-min+1)+min;
            int attemptsLeft = attempts;
            boolean guess = false;

            System.out.println("\nRound "+(totalRounds+1));
            System.out.println("Guess the number between "+min+" and "+max);
            System.out.println("You have "+attempts+" attempts");

            while(attemptsLeft>0 && !guess){
                System.out.print("Enter your guess:");
                int g = sc.nextInt();
                attemptsLeft--;

                if(g==toGuess){
                    System.out.println("Correct! You guessed the number.");
                    guess = true;
                    won++;
                }else if(g> toGuess){
                    System.out.println("Too high! Attempts left "+attemptsLeft);
                }else{
                    System.out.println("Too low! Attempts left "+attemptsLeft);
                }
            }
            if(!guess){
                System.out.println("Out of attempts! The number was: "+toGuess);
            }
            totalRounds++;
            System.out.println("Score: "+won+" wins out of "+totalRounds+" total rounds.");

            System.out.println("\nDo you want to play again? (yes/no): ");
            String response = sc.next().toLowerCase();
            again = response.equals("yes");
        }
        System.out.println("\nFinal Score: "+won+" wins out of "+totalRounds+" rounds.");
        System.out.println("Thanks for playing!");
        sc.close();
    }
}
