import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        final int DIFFICULTY_SETTING = 2;   // how hard the game is, higher value = less possible guesses, default 2
        String inputWord = generateWord(DIFFICULTY_SETTING); // asks user for word
        ArrayList<Character> blankWord = generateBlankWord(inputWord); // generates blank word for user to guess into
        ArrayList<Character> word = convertStringToCharArrayList(inputWord); // converts word into an ArrayList of chars
        ArrayList<Character> guessedLetters = new ArrayList<Character>(); // users already guessed letters

        ArrayList<Integer> indexesOfBlankWord = new ArrayList<Integer>();   // handles duplicate characters

        boolean playingGame = true; // handles winning the game
        char guess = 'a';   // users guess
        int lives = word.size() - DIFFICULTY_SETTING;   // determines difficulty, number of lives is based on the size of the word minus the difficulty, default 2
        int guesses = 0;    // fun info for winning game

        // main game loop
        while (playingGame) {
            printCurrentProgress(word, blankWord, guessedLetters);  // prints progress

            guess = askUserForGuess(); // gets the user's guess
            guesses++;  // increment guesses

            indexesOfBlankWord = getIndexesOfBlankWord(guess, word);    // gets the index to set the value of guess to

            if(indexesOfBlankWord.size() > 0){  // guessed letter successfully

                // handles duplicate letters
                for(int i = 0; i < indexesOfBlankWord.size(); i++){
                    blankWord.set(indexesOfBlankWord.get(i), guess);
                }
                
                if(blankWord.equals(word)){ // if your guess is equal to the word, you win
                    winGame(inputWord, guesses, lives);
                    break;
                }
                continue;
            }
            else{   // incorrect guess
                guessedLetters.add(guess);
                lives--;
                println("You lost a life! Only " + lives + " lives remaining!");

                if(lives <= 0){
                    loseGame(inputWord);
                    break;
                }
            }
        }
    }

    /** Asks user for word input (2 player game) */
    private static String generateWord(int DIFFICULTY_SETTING) {
        Scanner inp = new Scanner(System.in);
        String word;

        // input
        println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nEnter the word you'd like the other player to guess: ");
        word = inp.nextLine();

        while(word.length() <= DIFFICULTY_SETTING){
            println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nPlease enter a longer word");
            word = inp.nextLine();
        }
        println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

        return word;
    }

    /** Convert the user's word to an ArrayList */
    private static ArrayList<Character> convertStringToCharArrayList(String word) {
        ArrayList<Character> wordAsArrayList = new ArrayList<Character>();

        for (char c : word.toCharArray()) {
            wordAsArrayList.add(c);
        }

        return wordAsArrayList;
    }

    /** Generates the dashes to be output for the word */
    private static ArrayList<Character> generateBlankWord(String word) {
        ArrayList<Character> blankArrayList = new ArrayList<Character>();
        for (int i = 0; i < word.length(); i++) {
            blankArrayList.add('-');
        }

        return blankArrayList;
    }

    /** Asks user for a char to input */
    private static char askUserForGuess() {
        char guess = 'a';
        Scanner inp = new Scanner(System.in);
        println("Enter your guess: ");
        guess = inp.nextLine().charAt(0); // gets the next char input, only first char if string is input

        println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

        return guess;
    }

    private static ArrayList<Integer> getIndexesOfBlankWord(char guess, ArrayList<Character> word) {
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        for (int i = 0; i < word.size(); i++) {
            if (word.get(i) == guess) {
                indexes.add(i); // get all of the indexes of a letter in the word
            }
        }
        return indexes;
    }

    /** Outputs the users progress */
    private static void printCurrentProgress(ArrayList<Character> word, ArrayList<Character> blankWord, ArrayList<Character> guessedLetters) {
        blankWord.forEach(System.out::print);   // blank word to guess into

        println("\n\nYou have guessed the following letters:"); // incorrect word bank
        if(guessedLetters.size() < 1){  // if nothing is guessed, output is none
            println("None");
        }
        else{
            guessedLetters.forEach(System.out::println);
        }
    }

    /** Handle winning the game */
    private static void winGame(String inputWord, int guesses, int lives){
        println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nCongratulations, you win!");
        println("The word you guessed was: " + inputWord);
        println("It took you " + guesses + " guesses");
        println("You had " + lives + " lives remaining");
    }

    /** Handle losing the game */
    private static void loseGame(String inputWord){
        println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nOops, you ran out of lives :(");
        println("The word you tried to guess was: " + inputWord);
        println("Better luck next time!");
    }

    /** Makes printing easier */
    private static void println(Object o) {
        System.out.println(o);
    }
}