/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import static boggleassignment.BoggleAssignment.diceData;
import Modal.Board;
import View.HelpView;
import boggleassignment.BoggleAssignment;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;

/**
 *
 * @author Zac
 */
public class MainView {
    //declaring variables
    private JFrame frame;
    private JPanel bottomPanel, buttonPanel, rightPanel, topPanel, actionMenuPanel;
    private JButton[][] buttonGrid;
    private JScrollPane scrollPane;
    private JTextPane textPane;
    private JLabel currentWordLabel, scoreLabel, timeLeftLabel, blankLabel;
    private JButton submitWordButton, shakeDiceButton, cancelWordButton, endGameButton;
    private static Timer countdown;
    
    private ArrayList<String> dictionaryWords = new ArrayList<>();
    private ArrayList<String> foundWords = new ArrayList<>();
    boolean[] randomWords;
    public boolean[] duplicates;
    
    private static int minutes = 3;
    private static int seconds = 0;
    private int score = 0;
    private int duplicateWords = 0;
    
    private StyleDocument document;
    
    private static final int MIN_INDEX = 0;
    private static final int MAX_INDEX = 3;
    private int wordLength = 0;
    
    private int button0 = 0, button1 = 0, button2 = 0, button3 = 0, button4 = 0, button5 = 0, button6 = 0, button7 = 0, button8 = 0, button9 = 0, button10 = 0, button11 = 0, button12 = 0, button13 = 0, button14 = 0, button15 = 0;
    private int count = 0;
    
    TimerActionListener timerListener;
    private Board board;
    
    //custom constructor
    public MainView(Board passedBoard, ArrayList<String> passedDictionary) {
        //set passed components
        board = passedBoard;
        dictionaryWords = passedDictionary;
        timerListener = new TimerActionListener();
        initComponents(); //initiate gameboard
    }
    
    
    //allows strikethroughs for the textPane, not allowed by default
    public class StyleDocument extends DefaultStyledDocument {
        private Style primaryStyle;
        
        public StyleDocument()
        {
            super();
            primaryStyle = this.addStyle("Primary", null);
        }
        
        public Style getAttrStyle()
        {
            return primaryStyle;
        }
        
        @Override
        public void insertString (int offs, String str, AttributeSet a) throws BadLocationException
        {
            super.insertString(offs, str, primaryStyle);
        }   
    }
    
    public class SubmitWordActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
           
            
            int tempRow = -1;
            int tempCol = -1;
           
            //any buttons that may have been clicked won't automatically be disabled for the next word
            button0 = 0;
            button1 = 0;
            button2 = 0;
            button3 = 0;
            button4 = 0;
            button5 = 0;
            button6 = 0;
            button7 = 0;
            button8 = 0;
            button9 = 0;
            button10 = 0;
            button11 = 0;
            button12 = 0;
            button13 = 0;
            button14 = 0;
            button15 = 0;
            
            count = 0;
           
            //re-enables all buttons
            for (int row = 0 ; row <= MAX_INDEX ; row++)
            {
                for (int col = 0 ; col <= MAX_INDEX ; col++)
                {
                    buttonGrid[row][col].setEnabled(true);
                  
                    if (ae.getSource().equals(buttonGrid[row][col]))
                    {
                        tempRow = row;
                        tempCol = col;
                    }
                }
            }
           
            //adds word to textPane if valid
            if (dictionaryWords.contains(currentWordLabel.getText().toLowerCase()) == true) {
                if(currentWordLabel.getText().length() > 2) {
                    
                
                StyleConstants.setStrikeThrough(document.getAttrStyle(), false); //if played >1 time, any possible lines of strikethroughs are removed from the textarea
                String s = currentWordLabel.getText();
                wordLength = s.length();
               
            //3-4 letters: 1pt
            //5 letters: 2pts
            //6 letters: 3pts
            //7 letters: 5pts
            //8 letters: 11pts
                if ((wordLength > 2) && (wordLength < 5))
                    score++;
                
                else if (wordLength == 5)
                    score += 2;
                
                else if (wordLength == 6)
                    score += 3;
           
                else if (wordLength == 7)
                    score += 5;
                
                else if (wordLength > 7)
                    score += 11;
           
               
               updateTextArea(currentWordLabel.getText()); //adds word to textPane
               foundWords.add(currentWordLabel.getText()); //adds word to array
               scoreLabel.setText(String.valueOf(score)); //adds score to label
               currentWordLabel.setText(""); //clears current word label
                } else { //invalid word submitted, word too short
                    JOptionPane.showMessageDialog(null, "Not a valid word. Remember you word has to be more than 2 letters long");
                    currentWordLabel.setText("");
                }
            } else {//invalid word submitted, word doesn't exist
               JOptionPane.showMessageDialog(null, currentWordLabel.getText()+" isn't a valid word!");
               currentWordLabel.setText("");
            }
        }
    }
    
    public class CancelWordActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
           
            
            int tempRow = -1;
            int tempCol = -1;
           
            //any buttons that may have been clicked won't automatically be disabled for the next word
            button0 = 0;
            button1 = 0;
            button2 = 0;
            button3 = 0;
            button4 = 0;
            button5 = 0;
            button6 = 0;
            button7 = 0;
            button8 = 0;
            button9 = 0;
            button10 = 0;
            button11 = 0;
            button12 = 0;
            button13 = 0;
            button14 = 0;
            button15 = 0;
            
            count = 0;
           
            //re-enables all buttons
            for (int row = 0 ; row <= MAX_INDEX ; row++)
            {
                for (int col = 0 ; col <= MAX_INDEX ; col++)
                {
                    buttonGrid[row][col].setEnabled(true);
                  
                    if (ae.getSource().equals(buttonGrid[row][col]))
                    {
                        tempRow = row;
                        tempCol = col;
                    }
                }
            }
           
            //resets the textbox
            currentWordLabel.setText("");
        }
    }
    
    //disables letters out of reach & already-clicked letters
    public class ButtonGridActionListener implements ActionListener {
        int tempRow = -1;
        int tempCol = -1;
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            
            //disable board
            for (int row = 0 ; row <= MAX_INDEX ; row++)
            {
                for (int col = 0 ; col <= MAX_INDEX ; col++)
                {
                    buttonGrid[row][col].setEnabled(false);
                    
                    if (ae.getSource().equals(buttonGrid[row][col]))
                    {
                        tempRow = row;
                        tempCol = col;
                    }
                }
            }
            
            //first letter is added to current word label
            if (count == 0)
            {
                String temp = ae.getActionCommand();
                currentWordLabel.setText(temp);
            }
            
            //subsequent letters are added to current word label
            else
            {
                String temp2;
                temp2 = ae.getActionCommand();
                currentWordLabel.setText(currentWordLabel.getText() + temp2);
            }
            
            count++; //track how many letters there are for above if/else statements
            
            //keeps track of already-clicked buttons
            if ((tempRow == 0) && (tempCol == 0))
               button0 = 1;
           
            else if ((tempRow == 0) && (tempCol == 1))
               button1 = 1;
                      
            else if ((tempRow == 0) && (tempCol == 2))
               button2 = 1;
                      
            else if ((tempRow == 0) && (tempCol == 3))
               button3 = 1;
                      
            else if ((tempRow == 1) && (tempCol == 0))
               button4 = 1;
                      
            else if ((tempRow == 1) && (tempCol == 1))
               button5 = 1;
                      
            else if ((tempRow == 1) && (tempCol == 2))
               button6 = 1;
                      
            else if ((tempRow == 1) && (tempCol == 3))
               button7 = 1;
                      
            else if ((tempRow == 2) && (tempCol == 0))
               button8 = 1;
                      
            else if ((tempRow == 2) && (tempCol == 1))
               button9 = 1;
                      
            else if ((tempRow == 2) && (tempCol == 2))
               button10 = 1;
                      
            else if ((tempRow == 2) && (tempCol == 3))
               button11 = 1;
                      
            else if ((tempRow == 3) && (tempCol == 0))
               button12 = 1;
                      
            else if ((tempRow == 3) && (tempCol == 1))
               button13 = 1;
                      
            else if ((tempRow == 3) && (tempCol == 2))
               button14 = 1;
                      
            else if ((tempRow == 3) && (tempCol == 3))
               button15 = 1;
            
            //enables three buttons above clicked letter
            if (tempRow-1 >= MIN_INDEX)
            {
                buttonGrid[tempRow-1][tempCol].setEnabled(true);
                if (tempCol-1 >= MIN_INDEX)
                    buttonGrid[tempRow-1][tempCol-1].setEnabled(true);
                
                if(tempCol+1 <= MAX_INDEX)
                    buttonGrid[tempRow-1][tempCol+1].setEnabled(true);
            }
            
            //enables three buttons below clicked letter
            if (tempRow+1 <= MAX_INDEX)
            {
                buttonGrid[tempRow+1][tempCol].setEnabled(true);
                if (tempCol-1 >= MIN_INDEX)
                    buttonGrid[tempRow+1][tempCol-1].setEnabled(true);
                if(tempCol+1 <= MAX_INDEX)
                    buttonGrid[tempRow+1][tempCol+1].setEnabled(true);       
            }
            
            //enables button left of clicked letter
            if (tempCol-1 >= MIN_INDEX)
                buttonGrid[tempRow][tempCol-1].setEnabled(true);
            
            //enables button right of clicked letter
            if (tempCol+1 <= MAX_INDEX)
                buttonGrid[tempRow][tempCol+1].setEnabled(true);
         
            //disables already-clicked buttons
            if (button0 == 1)
                buttonGrid[0][0].setEnabled(false);
            
            if (button1 == 1)
                buttonGrid[0][1].setEnabled(false);
            
            if (button2 == 1)
                buttonGrid[0][2].setEnabled(false);
            
            if (button3 == 1)
                buttonGrid[0][3].setEnabled(false);
            
            if (button4 == 1)
                buttonGrid[1][0].setEnabled(false);
            
            if (button5 == 1)
                buttonGrid[1][1].setEnabled(false);
            
            if (button6 == 1)
                buttonGrid[1][2].setEnabled(false);
            
            if (button7 == 1)
                buttonGrid[1][3].setEnabled(false);
            
            if (button8 == 1)
                buttonGrid[2][0].setEnabled(false);
            
            if (button9 == 1)
                buttonGrid[2][1].setEnabled(false);
            
            if (button10 == 1)
                buttonGrid[2][2].setEnabled(false);
            
            if (button11 == 1)
                buttonGrid[2][3].setEnabled(false);
            
            if (button12 == 1)
                buttonGrid[3][0].setEnabled(false);
            
            if (button13 == 1)
                buttonGrid[3][1].setEnabled(false);
            
            if (button14 == 1)
                buttonGrid[3][2].setEnabled(false);
            
            if (button15 == 1)
                buttonGrid[3][3].setEnabled(false);
        }
    }
    
    //popup panel when exiting game
    public class ExitActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit this Boggle Game?\nYou will be returned to the main menu and existing game data will be lost!.", "Exit?", JOptionPane.YES_NO_OPTION);
            
            if (response == JOptionPane.YES_OPTION) { //closes pane if user clicks "Yes"
                //System.exit(0);
                
                //clears text, score, and current word    
                textPane.setText("");
                scoreLabel.setText("0");
                currentWordLabel.setText("");
                score = 0;
            
                //resets board
                diceData.clear();
                diceData = board.shakeDice();
            
                //resets timer
                timeLeftLabel.setText("3:00");
                countdown.stop();
                minutes = 3;
                seconds = 0;
                
                frame.dispose();
                
                String[] args = {};
                BoggleAssignment.main(args);
            }
        }
    }
    
    //resets game
    public class MenuActionListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            //clears text, score, and current word    
            textPane.setText("");
            scoreLabel.setText("0");
            currentWordLabel.setText("");
            score = 0;
            
            //resets frame
            frame.revalidate();
            frame.repaint();
            
            //resets board
            diceData.clear();
            diceData = board.shakeDice();
            //board.displayBoard();
            
            int counter = 0;
            for (int r = 0 ; r < 4 ; r++) {
                for (int c = 0 ; c < 4 ; c++) {
                    buttonGrid[r][c].setText(diceData.get(counter));
                    counter++;
                }
            }
            
            //re-enable buttons
            int tempRow = -1;
            int tempCol = -1;
            for (int row = 0 ; row <= MAX_INDEX ; row++)
            {
                for (int col = 0 ; col <= MAX_INDEX ; col++)
                {
                    buttonGrid[row][col].setEnabled(true);
                   
                    if (ae.getSource().equals(buttonGrid[row][col]))
                    {
                        tempRow = row;
                        tempCol = col;
                    }
                }
            }
            
            //resets timer
            timeLeftLabel.setText("3:00");
            countdown.stop();
            minutes = 3;
            seconds = 0;
            countdown.start();
        }
    }
    
    //makes the timer function
    public class TimerActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            //checks all cases regarding number of seconds/minutes left
            if (seconds == 0 && minutes == 0) {
                countdown.stop();
                compareScores();
            } else if (seconds == 0) {
                    seconds = 59;
                    minutes--;
            } else {
                seconds--;
            }
            
            //displays seconds/minutes left
            if (seconds < 10) {
                String zeroSecond = "0" + String.valueOf(seconds);
                timeLeftLabel.setText(String.valueOf(minutes) + ":" + zeroSecond);
            } else {
                timeLeftLabel.setText(String.valueOf(minutes) + ":" + String.valueOf(seconds));
            }
        }
    }
    
    //initializes and sets all of the pane's (JFrame's) components
    public void initComponents() {
        //sets up the frame
        frame = new JFrame();
        frame.setTitle("Boggle - Play");
        frame.setPreferredSize(new Dimension(606, 539));
        frame.setMinimumSize(new Dimension(606, 539));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.getContentPane().setBackground(new Color(244, 152, 78)); 
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        document = new StyleDocument();
        
        setupCurrentPanel(); //initialize the JPanel for the current word
        setupTopPanel(); //initialize the JPanel for actions,time,score
        setupWordPanel(); //initialize the JPanel for the word entry
        setupBogglePanel(); //initialize the JPanel for the Boggle dice
        
        //adds panels to the frame
        frame.add(topPanel, BorderLayout.PAGE_START);
        frame.add(bottomPanel, BorderLayout.PAGE_END);
        frame.add(buttonPanel, BorderLayout.LINE_START);
        frame.add(rightPanel, BorderLayout.CENTER);   
        
         //sets up timer
        countdown = new Timer(1000, new TimerActionListener());
        countdown.start();
    }
    
    //creates and adjusts panel for the current word
    private void setupCurrentPanel(){
        //initializing variables
        bottomPanel = new JPanel();
        buttonPanel = new JPanel(new GridLayout());
        rightPanel = new JPanel();
        actionMenuPanel = new JPanel(new GridLayout(2,1));
        textPane = new JTextPane();
        currentWordLabel = new JLabel();
        scoreLabel = new JLabel("0");
        timeLeftLabel = new JLabel("3:00");
        
        submitWordButton = new JButton("Submit Word");
        cancelWordButton = new JButton("Cancel Word");
        
        shakeDiceButton = new JButton("Shake Dice (New Game)");
        shakeDiceButton.addActionListener(new MenuActionListener()); //registers action listener
        endGameButton = new JButton("Exit Game");
        endGameButton.addActionListener(new ExitActionListener());
        
        //panel for bottom section
        bottomPanel.setBounds(40,100,80,30);
        bottomPanel.setBorder(BorderFactory.createTitledBorder(""));
        bottomPanel.setBackground(new Color(70, 130, 180)); 
        
        //label for the current word
        bottomPanel.add(currentWordLabel, ComponentOrientation.LEFT_TO_RIGHT);
        TitledBorder currentWordBorder = BorderFactory.createTitledBorder("Current Word");
        currentWordBorder.setTitleColor(Color.white);
        currentWordLabel.setBorder(currentWordBorder);
        currentWordLabel.setPreferredSize(new Dimension(270, 60));
        currentWordLabel.setBackground(new Color(255, 255, 255)); 
        currentWordLabel.setForeground(Color.white);
        currentWordLabel.setFont(new Font("SansSerif", Font.PLAIN, 28));
        
        //button to submit word
        bottomPanel.add(submitWordButton, ComponentOrientation.LEFT_TO_RIGHT); 
        submitWordButton.setPreferredSize(new Dimension(185, 40));
        submitWordButton.addActionListener(new SubmitWordActionListener());
        
        //button to cancel word
        bottomPanel.add(cancelWordButton, ComponentOrientation.LEFT_TO_RIGHT); 
        cancelWordButton.setPreferredSize(new Dimension(120, 40));
        cancelWordButton.addActionListener(new CancelWordActionListener());
        
        
    }
    
    //creates and adjusts panel for the current word
    private void setupTopPanel(){
        //initializing variables
        topPanel = new JPanel();
        
        //panel for bottom section
        topPanel.setBounds(40,100,80,30);
        topPanel.setBorder(BorderFactory.createTitledBorder(""));
        topPanel.setBackground(new Color(70, 130, 180)); 
        
        //panel for menu items
        topPanel.add(actionMenuPanel);
        actionMenuPanel.setLayout(new GridLayout(2,1));
        actionMenuPanel.setPreferredSize(new Dimension(200, 90));
        actionMenuPanel.setMinimumSize(new Dimension(200, 90));
        actionMenuPanel.setMaximumSize(new Dimension(200, 90));
        TitledBorder menuBtnBorder = BorderFactory.createTitledBorder("Game Actions");
        menuBtnBorder.setTitleColor(Color.white);
        menuBtnBorder.setBorder(BorderFactory.createLineBorder(Color.white));
        actionMenuPanel.setBorder(menuBtnBorder);
        actionMenuPanel.setBackground(new Color(70, 130, 180));
        
        //button for shaking dice
        shakeDiceButton.setPreferredSize(new Dimension(40, 20));
        endGameButton.setPreferredSize(new Dimension(40, 20));
        actionMenuPanel.add(shakeDiceButton);
        actionMenuPanel.add(endGameButton);
        
        //label for remaining time
        topPanel.add(timeLeftLabel);
        timeLeftLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timeLeftLabel.setPreferredSize(new Dimension(200, 90));
        timeLeftLabel.setMinimumSize(new Dimension(200, 90));
        timeLeftLabel.setMaximumSize(new Dimension(200, 90));
        TitledBorder timeLeftBorder = BorderFactory.createTitledBorder("Time Left");
        timeLeftBorder.setTitleColor(Color.white);
        timeLeftLabel.setBorder(timeLeftBorder);
        timeLeftLabel.setBackground(new Color(255, 255, 255));
        timeLeftLabel.setForeground(Color.white);
        timeLeftLabel.setFont(new Font("Monospaced", Font.PLAIN, 48));
        
        //label for player's score
        topPanel.add(scoreLabel, ComponentOrientation.LEFT_TO_RIGHT);
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scoreLabel.setPreferredSize(new Dimension(150, 90));
        scoreLabel.setMinimumSize(new Dimension(150, 90));
        scoreLabel.setMaximumSize(new Dimension(150, 90));
        TitledBorder scoreBorder = BorderFactory.createTitledBorder("Score");
        scoreBorder.setTitleColor(Color.white);
        scoreLabel.setBorder(scoreBorder);
        scoreLabel.setBackground(new Color(255, 255, 255));
        scoreLabel.setForeground(Color.white);
        scoreLabel.setFont(new Font("Monospaced", Font.PLAIN, 48));
    }
    
    //creates and adjusts grid for letters
    private void setupBogglePanel() {
        //panel for letter grid
        buttonGrid = new JButton[4][4];
        //buttonPanel.setBorder(BorderFactory.createTitledBorder("Boggle Board"));
        buttonPanel.setBackground(new Color(244, 152, 78)); 
        
        //instantiates and adds letter buttons
        buttonPanel.setLayout(new GridLayout(4,4));
        int counter = 0;
        for (int row = 0 ; row < 4 ; row++) {
            for (int column = 0 ; column < 4 ; column++) {
                buttonGrid[row][column] = new JButton();
                buttonGrid[row][column].setText(diceData.get(counter));
                buttonGrid[row][column].setPreferredSize(new Dimension(88, 80));
                buttonPanel.add(buttonGrid[row][column]);
                buttonGrid[row][column].addActionListener(new ButtonGridActionListener()); 
                buttonGrid[row][column].setFont(new Font("Dialog", Font.BOLD, 28));
                counter++;
            }
        }
    }
    
    private void updateTextArea(String data) {
        if("".equals(textPane.getText())) {
            textPane.setText(data);
        } else {
            textPane.setText(textPane.getText() + "\n" + data);
        }
        
        textPane.setCaretPosition(textPane.getDocument().getLength());
    }
    
    //creates and adjusts right-side panel
    private void setupWordPanel() {
         //panel for right section
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));
        
        //adds text pane
        rightPanel.add(textPane);
        rightPanel.setBorder(BorderFactory.createTitledBorder("Word List"));
        rightPanel.setBackground(new Color(244, 152, 78)); 
        //textPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        textPane.setFont(new Font("Dialog", Font.BOLD, 16));
        textPane.setEditable(false);
        
        //adds scroll bar to text pane
        scrollPane = new JScrollPane(textPane);
        rightPanel.add(scrollPane);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        
    }
  
    private void compareScores()
    {
        JOptionPane.showMessageDialog(null, "Game Over!\n Press Ok to see your compared score to the computer.");
        duplicates = new boolean[foundWords.size()];
        
        //traverses word list
        for (int wordPosition = 0 ; wordPosition < foundWords.size()-1 ; wordPosition++)
        {
            if (duplicates[wordPosition])
                continue;
            
            //compares "wordPosition" to all words to the right of it
            for (int comparedPosition = wordPosition+1 ; comparedPosition < foundWords.size() ; comparedPosition++)
            {
                if ((foundWords.get(wordPosition)).equals(foundWords.get(comparedPosition))) //checks if outer counter's position is the same word as the inner counter's position
                {
                    
                    if (!duplicates[comparedPosition]) //incase word was already counted as a duplicate
                    {
                        duplicates[comparedPosition] = true; //mark as a duplicate
                        duplicateWords++; //counts duplicate words
                        
                        String s2 = foundWords.get(comparedPosition);
                        wordLength = s2.length(); //to calculate modified score

                        //3-4 letters: 1pt
                        //5 letters: 2pts
                        //6 letters: 3pts
                        //7 letters: 5pts
                        //8 letters: 11pts
                        if ((wordLength > 2) && (wordLength < 5))
                            score--;

                        else if (wordLength == 5)
                            score -= 2;

                        else if (wordLength == 6)
                            score -= 3;

                        else if (wordLength == 7)
                            score -= 5;

                        else if (wordLength > 7)
                            score -= 11;      
                    }
                }
            }
        }                

        Random random = new Random();
        
        int totalWords = foundWords.size();
        totalWords -= duplicateWords;
        
        if (foundWords.isEmpty()) 
        {
            JOptionPane.showMessageDialog(null, "Oh no, you found 0 words! Better luck next time.");
            return; //so computer comparison doesn't occur
        }
            
        int numComputerWords = random.nextInt(totalWords); //assigns number of found computer words randomly
        
        randomWords = new boolean[foundWords.size()]; //instantiates boolean array to keep track of computer's found words
   
        JOptionPane.showMessageDialog(null, "The computer found " + numComputerWords + " out of the " + totalWords + " unique word(s) you found.\n\nThe words the computer found have been striked out. Press Ok to continue!");
        
        for (int loop = 0 ; loop < numComputerWords ; loop++) //for computer to choose found words
            randomWordSelect();
        
        //compared to computer
        for (int loop2 = 0 ; loop2 < foundWords.size() ; loop2++) {
            if (randomWords[loop2] == true) {
                System.out.println("Word " + loop2 + " of the player was found by the computer.");
                
                duplicates[loop2] = true; //adds to array with the duplicated words
                        
                String s3 = foundWords.get(loop2);
                wordLength = s3.length(); //to update score
               
                //3-4 letters: 1pt
                //5 letters: 2pts
                //6 letters: 3pts
                //7 letters: 5pts
                //8 letters: 11pts
                if ((wordLength > 2) && (wordLength < 5))
                    score--;
                
                else if (wordLength == 5)
                    score -= 2;
                
                else if (wordLength == 6)
                    score -= 3;
           
                else if (wordLength == 7)
                    score -= 5;
                
                else if (wordLength > 7)
                    score -= 11;
            }
        }
            
        //strikes through duplicated and computer-found words
        for (int wordPosition = 0 ; wordPosition < foundWords.size() ; wordPosition++) 
        {
            if (duplicates[wordPosition])
                StyleConstants.setStrikeThrough(document.getAttrStyle(), true);
        
            else
                StyleConstants.setStrikeThrough(document.getAttrStyle(), false);
            
            try
            {
                document.insertString(document.getLength(), foundWords.get(wordPosition) + "\n", null);
            }           
            
            catch (BadLocationException ex)
            {
                Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        textPane.setDocument(document);

        scoreLabel.setText(String.valueOf(score));
    }
    
    
    //randomly assigns computer's found words
    private void randomWordSelect()
    {
        Random random = new Random();
        int randWord = random.nextInt(foundWords.size());
        
        if (duplicates[randWord] || (randomWords[randWord] == true)) //if a duplicated word or an already-picked word -> repick
            randomWordSelect();

        else
            randomWords[randWord] = true; //assign as picked word
    }
    
}
