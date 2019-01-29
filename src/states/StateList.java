/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package states;

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
    
    private static final String instructions = 
                "---------------------------------------------------\n" + 
                "|To list the all states in the USA type: 'list'   |\n" +
                "|To search by state type: 'lookup (state/cap)'    |\n" +
                "|To display all states by population type: 'sort' |\n" +
                "|To close the program type: 'close'               |\n" +
                "---------------------------------------------------\n";
    //Text file's name
    private static final String statesFile = "statedata.txt";
    
    //JFrame to display an error message later in the searchStates method
    private static final JFrame frame = new JFrame("Search");

    public static void loadData(State[] s){
        //Opens the statedata.txt file
        File f = new File(statesFile);
        
        //Create two Strings and int to read the file into. 
        String state, capital;                                                  
        int population;
        try {
            //Scanner created to read the file
            Scanner readLines = new Scanner(f);
            
            //Loop to create 50 state objects
            for(int i = 0; i < 50; i++){
                
                //Variables able are initialized with each line in the scanner
                state = readLines.nextLine();                                   
                capital = readLines.nextLine();                                 
                population = Integer.parseInt(readLines.nextLine());
                
                //Creates a new State object array with this information
                s[i] = new State(state, capital, population);
            } // End for
            readLines.close();                                                  //Scanner closed
        } catch (FileNotFoundException ex) {
            Logger.getLogger(State.class.getName()).log(Level.SEVERE, null, ex);//Exception for FileNotFound
        }
    } //Close of file loading method
    
    public static void searchState(State[] states) {
        // Loads and prints instruction variable
        System.out.print(instructions);
        Scanner input = new Scanner(System.in); // New input scanner
        String oper = input.nextLine(); // Variable for user's choice
        // Switch statement for operation execution by user
        switch(oper.toLowerCase()) {
            case "list": // Display states alphabetically
                displayStates(states);
            // End list    
            case "sort": // Display states by population (a < b)
                sortStatesPopulation(states);
            // End sort
            case "lookup state": // Search for state by name
                System.out.println("Which state would you like to search for?");
                String searchState = input.nextLine().toLowerCase();
                boolean stateFound = false;
                
                // Enhanced forloop to search array of State objects
                for(State s : states){
                    if(s.getState().toLowerCase().equals(searchState) && !stateFound){
                        JOptionPane.showMessageDialog(frame, s.toString());
                        stateFound = true;
                        searchState(states);
                        //createVacation(s, states);
                    } // End if
                } // End for
                if(!stateFound) {
                    JOptionPane.showMessageDialog(frame, "There's no state named " + searchState, "Retry?", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
            // End lookup state
            case "lookup cap":
                System.out.println("Which capital would you like to search for?");
                String searchCap = input.nextLine().toLowerCase();
                boolean capFound = false;
                // Enhanced forloop to search array of State objects
                for(State s : states){
                    // If the getCaptial() method matches the searchCap, the captail is printed
                    if(s.getCapital().toLowerCase().equals(searchCap) && !capFound){
                        JOptionPane.showMessageDialog(frame, s.toString());
                        
                        // Sets the capFound bool to true so we know to stop checking the states.
                        capFound = true;
                        
                        // Sets the found bool to true so we know to stop checking the states.
                        searchState(states);
                        
                        //createVacation(s, states);
                        
                    } // End if
                } // End for
                if(!capFound) {
                    JOptionPane.showMessageDialog(frame, "There's no capital named " + searchCap, "Retry?", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
            // End lookup cap
            case "create u":
                createUser(states);
                break;
            // End create user
            default:
                JOptionPane.showMessageDialog(frame, "Your entry was not found : " + oper, "Retry?", JOptionPane.INFORMATION_MESSAGE);        // If the state isn't found we print the error. 
                searchState(states);
        }// End switch
        
    } //Close of method which returns a state's information.
    
    private static void createUser(State[] states) {
        // Temp variables;
        State homeState = null; // Null homeState because we later set it after searching an array
        String firstName, lastName;
        boolean stateF = false;
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter your home state: ");
        String home = input.nextLine();
        
        for(State s : states) {
            if(home.toLowerCase().equals(s.getState().toLowerCase()) && !stateF) {
                stateF = true;
                homeState = s;
            }
        }
        System.out.println("To create an account enter your first name: ");
        firstName = input.nextLine();
                
        System.out.println("Enter your last name: ");
        lastName = input.nextLine();
        
        Home homeU = new Home(homeState);
        User user = new User(firstName, lastName, homeU);
        
        System.out.println(user.toString());
    }
    
    // Bubble sort
    public static void sortStatesPopulation(State[] states) {
        // Create an index starting at 0 that counts to states.len
        for(int i = 0; i < states.length; i++){
            // Create another index which will count until it reaches i @ (states.length - 1)
            for(int j = 0; j < i; j++) {
                // If the population of the i is greater than j+1
                if(states[j].getPopulation() > states[j+1].getPopulation()) {
                    // Store the state at that point
                    State temp = states[j];
                    // Switch (j, j+1)
                    states[j] = states[j+1];
                    // Make j+1 = what the larger state population is, moving it down the array
                    states[j+1] = temp;
                }
            }
        }
        // Print the list of states according to population
        System.out.println("Bubble Sort States by Population.");
        for(State s : states) {
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
        for(State s : states) {
            System.out.println(s.toString());
        }
        searchState(states);                                                    //Calls this method again to give the user another chance to do something.
    } // Close of display states loop
} //Close for StateList class file
