/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modal;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Zac
 */
public class Die {
    
    private final ArrayList<String> letters = new ArrayList<>();
    
    //declaring constant
    int NUMBER_OF_SIDES = 6; 
    
    //Declaration
    Random randomNumbers = new Random();
    int randomSide;
    String letterRolled;
    
    //adds the letters
    //    implemented in populateData()
    public void addLetter(String letter) {
        letters.add(letter);
    }
    
    //prints the dices' letters
    //    implemented in populateData()
    public void displayLetters() {
        for (String character: letters) {
            System.out.print(character + "  ");
        }
    }
    
    //picks a random letter from the die
    public String rollDie() {
       randomSide = randomNumbers.nextInt(6);
       letterRolled = letters.get(randomSide);
       
       return letterRolled; // Return the letter rolled from the die at random
    }
    
    public int getNumberOfSides() {
        return NUMBER_OF_SIDES;
    }
    
}
