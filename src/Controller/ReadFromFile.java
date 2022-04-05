/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Zac
 */
public class ReadFromFile {
    //declaring variables
    private Scanner scanner;
    private final String fileName;
    private final ArrayList<String> fileData;

    //getter for variable fileData
    public ArrayList<String> getData() {
        return fileData;
    }
   
    //constructor assigns and instantiates variables
    public ReadFromFile(String file) {
        fileName = file;
        fileData = new ArrayList<>();
    }
    
    public void populateArray() {
        
        try {
            URL url = getClass().getResource("/boggleassignment/" + fileName);
            File newFile = new File(url.toURI());
            
            scanner = new Scanner(newFile); //instantiates scanner

            //traverses the data and adds it to array fileData
            while(scanner.hasNext())
                getData().add(scanner.next());
        }

        //deals with errors if something goes wrong
        catch (IOException | URISyntaxException ex) {
            System.out.println(ex.toString());
            ex.printStackTrace();
        }
        
        finally {
            if (scanner != null)
            scanner.close();
       }

    }
    
    
}
