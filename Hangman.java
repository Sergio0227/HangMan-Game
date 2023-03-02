import java.util.Arrays;
import java.util.Scanner;

public class Hangman {

    public static String[] words = { "ant", "baboon", "badger", "bat", "bear", "beaver", "camel",
            "cat", "clam", "cobra", "cougar", "coyote", "crow", "deer",
            "dog", "donkey", "duck", "eagle", "ferret", "fox", "frog", "goat",
            "goose", "hawk", "lion", "lizard", "llama", "mole", "monkey", "moose",
            "mouse", "mule", "newt", "otter", "owl", "panda", "parrot", "pigeon",
            "python", "rabbit", "ram", "rat", "raven", "rhino", "salmon", "seal",
            "shark", "sheep", "skunk", "sloth", "snake", "spider", "stork", "swan",
            "tiger", "toad", "trout", "turkey", "turtle", "weasel", "whale", "wolf",
            "wombat", "zebra" };

    public static String[] gallows = { "+---+\n" +
            "|   |\n" +
            "    |\n" +
            "    |\n" +
            "    |\n" +
            "    |\n" +
            "=========\n",

            "+---+\n" +
                    "|   |\n" +
                    "O   |\n" +
                    "    |\n" +
                    "    |\n" +
                    "    |\n" +
                    "=========\n",

            "+---+\n" +
                    "|   |\n" +
                    "O   |\n" +
                    "|   |\n" +
                    "    |\n" +
                    "    |\n" +
                    "=========\n",

            " +---+\n" +
                    " |   |\n" +
                    " O   |\n" +
                    "/|   |\n" +
                    "     |\n" +
                    "     |\n" +
                    " =========\n",

            " +---+\n" +
                    " |   |\n" +
                    " O   |\n" +
                    "/|\\  |\n" + // if you were wondering, the only way to print '\' is with a trailing escape
                                  // character, which also happens to be '\'
                    "     |\n" +
                    "     |\n" +
                    " =========\n",

            " +---+\n" +
                    " |   |\n" +
                    " O   |\n" +
                    "/|\\  |\n" +
                    "/    |\n" +
                    "     |\n" +
                    " =========\n",

            " +---+\n" +
                    " |   |\n" +
                    " O   |\n" +
                    "/|\\  |\n" +
                    "/ \\  |\n" +
                    "     |\n" +
                    " =========\n" };

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int numberOfMisses = 0;
        char[] misses = new char[6];
        String word = randomWord();
        char[] placeholders = new char[word.length()];
        for (int i = 0; i < placeholders.length; i++) {
            placeholders[i] = '_';
        }
        System.out.print(word);
        System.out.print(gallows[0]);
        for (int i = 0;; i++) {

            System.out.print("Word:\t");
            printPlaceholders(placeholders);
            System.out.print("Misses:\t");
            printMissedGuesses(misses);
            System.out.print("\nGuess:\t");
            char guess = scan.nextLine().charAt(0);
            if (!(checkGuess(word, guess))) {
                misses[numberOfMisses] = guess;
                numberOfMisses++;
                System.out.print(gallows[numberOfMisses]);
                if (numberOfMisses == 6) {
                    System.out.println("You lost");
                    System.exit(0);
                }
            } else {
                System.out.print(gallows[numberOfMisses]);
                updatePlaceholders(word, placeholders, guess);
            }

            if (Arrays.equals(placeholders, word.toCharArray())) {
                System.out.print("\nWord:   ");
                printPlaceholders(placeholders);
                System.out.println("\nGOOD WORK!");
                break;
            }

        }
        scan.close();
    }

    /**
     * Fontion that generates a random number (from 0 to the length of the words
     * array) and returns a random word
     * 
     * @return
     */
    public static String randomWord() {
        int wordNumber;
        String randomWord;

        wordNumber = (int) (Math.random() * words.length + 1);
        randomWord = words[wordNumber];

        return randomWord;
    }

    /**
     * Fonction to check if guess is correct
     * 
     * @param word
     * @param guess
     * @return
     */
    public static boolean checkGuess(String word, char guess) {

        for (int i = 0; i < word.length(); i++) {
            if (guess == word.charAt(i))
                return true;

        }

        return false;
    }

    /**
     * Fonction to update placeholders
     * 
     * @param word
     * @param placeholders
     * @param guess
     */
    public static void updatePlaceholders(String word, char[] placeholders, char guess) {
        for (int i = 0; i < word.length(); i++) {
            if (guess == word.charAt(i)) {
                placeholders[i] = guess;
            }
        }

    }

    /**
     * Fonction to print placeholders
     * 
     * @param placeholders
     */
    public static void printPlaceholders(char[] placeholders) {

        for (int i = 0; i < placeholders.length; i++) {
            System.out.print(" " + placeholders[i]);
        }
        System.out.print("\n");
    }

    public static void printMissedGuesses(char[] misses) {

        for (int i = 0; i < misses.length; i++) {
            System.out.print(misses[i]);
        }
    }

}
