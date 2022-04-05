
package boggleassignment;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import Controller.ReadFromFile;
import Modal.Board;
import View.*;

/**
 *
 * @author Zac
 */
public class BoggleAssignment {
    
    //declaring variables
    public static ArrayList<String> diceData = new ArrayList<>();
    private static final String BOGGLE_FILE_NAME = "../boggleassignment/diceValues.txt";
    
    private final ArrayList<String> dictionaryData = new ArrayList<>();
    private static final String DICTIONARY_FILE_NAME = "../boggleassignment/dictionary.txt";

    public static void main(String[] args) {
        // TODO code application logic here
        
        //reads dices' data
        ReadFromFile dice = new ReadFromFile(BOGGLE_FILE_NAME);
        dice.populateArray();
        
        //reads dictionary's data
        ReadFromFile dictionary = new ReadFromFile(DICTIONARY_FILE_NAME);
        dictionary.populateArray();
        
        //instantiates and populates the board's data
        Board board = new Board(dice.getData(), dictionary.getData());
        board.populateDice();
        
        System.out.println("There are " + dictionary.getData().size() + " entries in the dictionary."); //prints number of words to console
        System.out.println("There are " + dice.getData().size() + " dice faces");
        
        diceData = board.shakeDice(); //assigns randomly picked letters on the randomly picked dice to diceData
        
        board.displayBoard(); //shows how the boggle board looks when all dice and sides of dice are randomized in console
        
        // Create a new instance of the Main Menu
        MainMenuView menuView = new MainMenuView();
 
        // Add Start Game Action
        menuView.startButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == menuView.startButton) {
                menuView.frame.dispose();
                //Create the view
                MainView viewController = new MainView(board, dictionary.getData());
            }
        }
        });
        
        // Add Rules Window Actions
        menuView.rulesButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == menuView.rulesButton) {
                //Create the view
                HelpView helpView = new HelpView();
            }
        }
        });
        
        
        
    
        
    }
    
}
