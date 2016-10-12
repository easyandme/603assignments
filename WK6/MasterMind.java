/**
 * Assignment 3 - CS603
 * Created by Max (Jialu) Xu on 10/11/2016.
 */

/* This program is used to create a simple game called 'MasterMind'. In this game, the user is asked to enter
 * four letters in a certain range to guess the randomly generated secret code.
 */

import java.util.Scanner;

public class MasterMind {

    public static void main (String[] args) {

        Scanner kb = new Scanner(System.in);

        final int    GUESSLIMIT = 10;
        final char   START      = 'a';
        final char   END        = 'f';
        char[] secretCode = SecretCode.generateCode();
        char[] inputArray  = new char[secretCode.length];       // 4
        int[]  matchResult = new int[2];                         // initialize a match result array containing 2 values: exact and partial match
        int    guessCount  = 0;                                   // count from 0
        String guess;                                              // store the original user input to string

        System.out.println("You have a maximum of 10 chances to correctly guess a sequence" +
                "of four letters ranging from a to f. \n\nEnter four letters, separated by a space.");

        do {
            guessCount += 1;
            System.out.println("\nGuess " + guessCount + ": ");
            guess = kb.nextLine();

            for (int i = 0; i < inputArray.length; i++) {
                inputArray[i] = Character.toLowerCase(guess.charAt(2 * i));
                // i times 2 to get the indexes of the letters, then convert them to lower case
            }

            if (!checkArrayRange(inputArray, START, END)) {
                System.out.println("*** Enter only letters between 'a' and 'f' ***");
                guessCount -= 1;   // out-of-range input doesn't count
            } else {
                // call the matchArray method which returns exact and partial match number
                matchResult = matchArray(inputArray, secretCode);
                if (matchResult[0] == secretCode.length) {
                    // matchResult[0] is the exact match number. If it equals to the secretCode.length, the guess is correct.
                    System.out.println("\nYou won!");
                } else {
                    System.out.println("exact (correct letter and position): " + matchResult[0]);
                    System.out.println("partial (correct letter, incorrect position): " + matchResult[1]);
                }
            }

        } while (matchResult[0] != secretCode.length && guessCount < GUESSLIMIT);

        if (guessCount == GUESSLIMIT && matchResult[0] != secretCode.length) {
            System.out.print("\nYou lost. The correct code was: ");
            printArray(secretCode);
        }

        kb.close();
    }

    // match two arrays and return exact and partial match numbers, so a return type of array is appropriate
    public static int[] matchArray(char[] array1, char[] array2) {
        int exact        = 0;
        int partialMatch = 0;
        boolean[] charUsedinArray1 = new boolean[array1.length];
        boolean[] charUsedinArray2 = new boolean[array2.length];
        /** I created these two boolean arrays to determine whether a certain character has been used for match.
         *  It feels tricky that these boolean values (which are initialized to false) are acting like attributes
         *  of the entries in the arrays we pass in, though they are not.
         */

        // a simple loop to find the exact matches and set those to 'used'
        for (int i = 0; i < array1.length; i++) {
            if (array1[i] == array2[i]) {
                exact += 1;
                charUsedinArray1[i] = true;
                charUsedinArray2[i] = true;
            }
        }

        // another simple loop to find the partial matches and set those to 'used'
        for (int i = 0; i < array1.length; i++) {
            for (int j = 0; j < array2.length; j++) {
                // If the character has already been used for match, it'll not be compared again.
                // Thus, duplicates are prevented.
                if (!charUsedinArray1[i] && !charUsedinArray2[j] && array1[i] == array2[j]) {
                    partialMatch += 1;
                    charUsedinArray1[i] = true;
                    charUsedinArray2[j] = true;
                    break;
                    // Once a match is found, break the inner loop so that it's always one to one
                }
            }
        }
        return new int[] {exact, partialMatch};  // In this case we can create the new array when we return it
    }

    // if the input character is out of range, return false
    public static boolean checkArrayRange(char[] myArray, char start, char end) {
        boolean inRange = true;
        // yet another simple loop to check, since the char data type is based on Unicode specification
        for (int i = 0; i < myArray.length; i++) {
            if (myArray[i] < start || myArray[i] > end) {
                inRange = false;
                break;
            }
        }
        return inRange;
    }

    // this method is for adding spaces when printing the secret code.
    public static void printArray(char[] myArray) {
        for (int i = 0; i < myArray.length; i++) {
            System.out.print(Character.toString(myArray[i]) + " ");
            // not sure if Character.toString() is redundant here
        }
    }

}
