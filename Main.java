import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        Hangman hangman = new Hangman();
        Scanner scan = new Scanner(System.in);
        hangman.readFromDictionary();
        System.out.println("Enter maximum number of wrong guesses");
        hangman.setMaxWrongGuesses(scan.nextInt());
        hangman.selectRandomSecretWord();
        System.out.println(String.valueOf(hangman.displayedWord));
        char letter;
        while(hangman.Leftguesses!=0) {
            if(hangman.win) {
                System.out.println("YOU WIN !");
                break;
            }
            System.out.println("Guess a letter..");
            do {
                letter = scan.next().charAt(0);
            } while (letter == '\u0000');// to check that a character isn't null
            System.out.println(hangman.guess(letter));
            System.out.println("Left wrong guesses : " + hangman.Leftguesses);
        }
    }
}
