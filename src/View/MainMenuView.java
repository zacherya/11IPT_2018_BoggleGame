/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import com.apple.eawt.Application;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Zac
 */
public class MainMenuView extends JFrame {
    
    public JButton startButton;
    public JButton rulesButton;
    public JFrame frame = new JFrame("Main Menu");
    public JPanel btnGrid = new JPanel(new GridLayout(1,2));
    
    public MainMenuView() {
        super("Boggle");
        
        // Set Up Frame
        // Set Frame & Application Icon
        ImageIcon icon = new ImageIcon(getClass().getResource("../boggleassignment/app_icon.png"));
        frame.setIconImage(icon.getImage()); 
        
        Application application = Application.getApplication();
        application.setDockIconImage(icon.getImage());
        
        frame.setLayout(new GridLayout(2,1));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set Background Color
        frame.getContentPane().setBackground(new Color(52, 52, 52)); 
        
        // Create Boggle Logo
        ImageIcon logoImg = new ImageIcon(getClass().getResource("../boggleassignment/app_logo.png"));
        JLabel logo = new JLabel(logoImg);
        frame.add(logo);
           
        
        frame.add(btnGrid);
        
        // Create Rules Button
        rulesButton = new JButton("?\nRules");
        rulesButton.setBackground(new Color(52, 52, 52));
        rulesButton.setOpaque(true);
        rulesButton.setFont(new Font("Arial", Font.PLAIN, 30));
        btnGrid.add(rulesButton);
        
        // Create Start Button
        startButton = new JButton("►\nPlay");
        startButton.setBackground(new Color(52, 52, 52));
        startButton.setOpaque(true);
        startButton.setFont(new Font("Arial", Font.PLAIN, 30));
        btnGrid.add(startButton);
        
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        
    }
    
}
