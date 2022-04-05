/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modal;

import Modal.Die;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Zac
 */
public class Board {
    
    
    
    //declaring constants
    public static final int NUMBER_OF_DICE = 16;
    public static final int GRID = 4;
        Die dieClass = new Die();
    
    //declaring variables of type ArrayList
    private ArrayList<String> diceData;
    private ArrayList<String> dictionaryData;
    private ArrayList<Die> dieArray;
    private ArrayList<String> gameData = new ArrayList<>();
    private ArrayList<Integer> unusedDice = new ArrayList<>();
    
    Random randomNumbers = new Random();
    int randomDieIndex;

    public ArrayList<String> getGameData() {
        return gameData;
    }
    
    //class constructor; initializes above variables
    public Board(ArrayList<String> diceArg, ArrayList<String> dictionaryArg) {
        diceData = diceArg;
        dictionaryData = dictionaryArg;
        dieArray = new ArrayList<>();
    }

    //gives dice their letters using addLetter() and displays them using displayLetters()
    public void populateDice() {
        Die boggleDie;
        
        //outer loop runs 16 times for the 16 different dice
        for (int outerCounter = 0 ; outerCounter < NUMBER_OF_DICE ; outerCounter++) {
            boggleDie = new Die(); //reinitializes for each die
            
            System.out.print("Die " + outerCounter + ": "); //header for printing each die
            
            //inner loop runs 6 times for each side of the dice
            for (int innerCounter = 0 ; innerCounter < boggleDie.getNumberOfSides() ; innerCounter++) {
                String s = diceData.get(outerCounter * boggleDie.getNumberOfSides() + innerCounter); //placeholder variable so each letter is added in order
                boggleDie.addLetter(s);
                //System.out.print(boggleDie);
            }
            //out of inner loop but still in outer loop
            boggleDie.displayLetters(); //displays all letters on the die
            System.out.print("\n"); //prints newline so next die is on a separate line
            dieArray.add(boggleDie); //current die's information is added to diceArray
        }
    }
    
    //randomly chooses each of the 16 dice once
    public ArrayList shakeDice() {
        
        for (int i = 0 ; i < NUMBER_OF_DICE ; i++)
            unusedDice.add(i);
        
        int diePickedValue;
        
        for (int loopCount = 0 ; loopCount < NUMBER_OF_DICE ; loopCount++) {
            randomDieIndex = randomNumbers.nextInt(NUMBER_OF_DICE-loopCount); //picks an index from 0-15, 0-14, 0-13, etc.
            diePickedValue = unusedDice.get(randomDieIndex); //assigns value of a randomly picked index
            //System.out.println(unusedDice.get(randomDie));
            unusedDice.remove(randomDieIndex); //deletes element to not reuse a die; ArrayList is dynamic so values will shift accordingly to close the gap
            
            String s = dieArray.get(diePickedValue).rollDie(); //temp variable to store the letter
            gameData.add(s); //adds randomized letter to array gameData
            
            //System.out.println(gameData);
        }
            return gameData;
    }

    public void displayBoard() {
        int count = 0;
        
        System.out.println("Initiating Boggle Board!");
        
        for (String character: gameData) {
            System.out.print(character + " ");
            count++;
            
            if (count%4 == 0) {
                System.out.println();
            }
        }
    }
    
}
