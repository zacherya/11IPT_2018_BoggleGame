/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import com.apple.eawt.Application;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Zac
 */
public class HelpView extends JFrame {
    
    public HelpView() {
        super("Boggle - Help");
        // Create Frame
        JFrame frame = new JFrame("Boogle - Help");
        
        // Set Frame & Application Icon
        ImageIcon icon = new ImageIcon(getClass().getResource("../boggleassignment/app_icon.png"));
        frame.setIconImage(icon.getImage()); 
        
        Application application = Application.getApplication();
        application.setDockIconImage(icon.getImage());
        
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        
        // Set Background Color
        frame.getContentPane().setBackground(new Color(244, 152, 78)); 
        
        Dimension dimension = new Dimension(703,563);
        ImageIcon rulesImg = new ImageIcon(getClass().getResource("../boggleassignment/app_helpView.png"));
        
        JLabel rulesLabel = new JLabel(rulesImg);
        
        rulesLabel.setPreferredSize(dimension);
        rulesLabel.setSize(1002, 708);
        frame.add(rulesLabel);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }  
}
