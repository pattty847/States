/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package states;

import user.User;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author pattt
 */
public class StateList {

    //Text file's name
    private static final String statesFile = "statedata.txt";

    //JFrame to display an error message later in the searchStates method
    private static final JFrame frame = new JFrame("Search");

    //Instance of our User class
    private static User user;

    public static void loadData(State[] s) {
        //Opens the statedata.txt file
        File f = new File(statesFile);

        //Create two Strings and int to read the file into. 
        String state, capital;
        int population;
        try {
            //Scanner created to read the file
            Scanner readLines = new Scanner(f);

            //Loop to create 50 state objects
            for (int i = 0; i < 50; i++) {

                //Variables able are initialized with each line in the scanner
                state = readLines.nextLine();
                capital = readLines.nextLine();
                population = Integer.parseInt(readLines.nextLine());

                //Creates a new State object array with this information
                s[i] = new State(state, capital, population);
            } // End for
            readLines.close(); //Scanner closed
        } catch (FileNotFoundException ex) {
            Logger.getLogger(State.class.getName()).log(Level.SEVERE, null, ex);//Exception for FileNotFound
        }
    } //Close of file loading method

    public static void searchForState(State[] states) {
        Scanner input = new Scanner(System.in);
        System.out.println("Which state would you like to search for?");
        String searchState = input.nextLine().toLowerCase();
        boolean stateFound = false;

        // Enhanced forloop to search array of State objects
        for (State s : states) {
            if (s.getState().toLowerCase().equals(searchState) && !stateFound) {
                JOptionPane.showMessageDialog(frame, s.toString());
                stateFound = true;
                User.handleUserInput(states, user);
                //createVacation(s, states);
            } // End if
        } // End for
        if (!stateFound) {
            JOptionPane.showMessageDialog(frame, "There's no state named " + searchState, "Retry?", JOptionPane.INFORMATION_MESSAGE);
            searchForState(states);
        }
    }

    public static void searchForCapital(State[] states) {
        Scanner input = new Scanner(System.in);
        System.out.println("Which capital would you like to search for?");
        String searchCap = input.nextLine().toLowerCase();
        boolean capFound = false;
        // Enhanced forloop to search array of State objects
        for (State s : states) {
            // If the getCaptial() method matches the searchCap, the captail is printed
            if (s.getCapital().toLowerCase().equals(searchCap) && !capFound) {
                JOptionPane.showMessageDialog(frame, s.toString());

                // Sets the capFound bool to true so we know to stop checking the states.
                capFound = true;

                // Sets the found bool to true so we know to stop checking the states.
                searchForState(states);

                //createVacation(s, states);
            } // End if
        } // End for
        if (!capFound) {
            JOptionPane.showMessageDialog(frame, "There's no capital named " + searchCap, "Retry?", JOptionPane.INFORMATION_MESSAGE);
            searchForCapital(states);
        }
        // End lookup cap
    }

    // Bubble sort
    public static void sortStatesPopulation(State[] states) {
        // Create an index starting at 0 that counts to states.len
        for (int i = 0; i < states.length; i++) {
            // Create another index which will count until it reaches i @ (states.length - 1)
            for (int j = 0; j < i; j++) {
                // If the population of the i is greater than j+1
                if (states[j].getPopulation() > states[j + 1].getPopulation()) {
                    // Store the state at that point
                    State temp = states[j];
                    // Switch (j, j+1)
                    states[j] = states[j + 1];
                    // Make j+1 = what the larger state population is, moving it down the array
                    states[j + 1] = temp;
                }
            }
        }
        // Print the list of states according to population
        System.out.println("Bubble Sort States by Population.");
        for (State s : states) {
            System.out.println(s.toString());
        }
    }

    /*
    public static void createVacation(State firstLoc, State[] states) {
        Vacation trip;
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a number of states you would like to visit: ");
        int num = Integer.parseInt(scan.nextLine());
        State[] locations = new State[num];
        
        for(int i = 0; i < num; i++){
            System.out.println("Enter state number " + (1 + i));
            String input = scan.nextLine();
            
        }
    }
     */
    public static void displayStates(State[] states) {                          // Simple enhanced for loop method to display states
        for (State s : states) {
            System.out.println(s.toString());
        }
        searchForState(states);                                                    //Calls this method again to give the user another chance to do something.
    } // Close of display states loop
} //Close for StateList class file
