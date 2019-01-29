/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import states.Home;
import states.State;
import states.StateList;
import static states.StateList.displayStates;
import static states.StateList.searchForState;
import static states.StateList.sortStatesPopulation;

/**
 *
 * @author pattt
 */
public class User {
    private Home home;
    private String firstName, lastName;
    private static JFrame frame;
    
    private static final String instructions = 
                "|-------------------------------------------------|\n" + 
                "|To list the all states in the USA type: 'list'   |\n" +
                "|To search by state type: 'lookup (state/cap)'    |\n" +
                "|To display all states by population type: 'sort' |\n" +
                "|To create an account type 'create user'          |\n" +
                "|To close the program type: 'close'               |\n" +
                ">";
    
    User(){}
    
    public User(String firstName, String lastName, Home home) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.home = home;
    }
    
    /*
        Method for handling the program command execution by user
    cmds: 
        list - displays states alphabetically
        sort pop - sorts state's population least to greatest
        lookup state - search a state by its name and display its information
        lookup capital - search for a capital and display its information
        create user - (enter home state) + (first name) + (last name)
    */
    public static void handleUserInput(State[] states, User user) {
        System.out.println(instructions);
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
                StateList.searchForState(states);
                
            case "lookup cap":
                StateList.searchForCapital(states);
                
            case "create user":
                createUser(states);
                break;
            // End create user
            default:
                JOptionPane.showMessageDialog(frame, "Your command was not found : " + oper, "Retry?", JOptionPane.INFORMATION_MESSAGE);        // If the state isn't found we print the error. 
                searchForState(states);
        }// End switch
    }

    public static void createUser(State[] states) {
        // Temp variables;
        State homeState = null; // Set a placeholder for the users Home State
        String firstName, lastName; // Placeholder for name
        boolean stateF = false; // Was their entered state real?
        Scanner input = new Scanner(System.in);
        
        while(!stateF) {
            System.out.println("Enter your home state: ");
            String inHome = input.nextLine();
        
            for(State s : states) {
                if(inHome.toLowerCase().equals(s.getState().toLowerCase()) && !stateF) {
                    stateF = true;
                    homeState = s;
                }
            }
            if(stateF) {
                System.out.println("To create an account enter your first name: ");
                firstName = input.nextLine();
                
                System.out.println("Enter your last name: ");
                lastName = input.nextLine();
        
                Home home = new Home(homeState);
                User user = new User(firstName, lastName, home);
        
                System.out.println(user.toString());
            }else{
                System.out.println("State not found, try again.");
            }
        }
    }
    
    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "User: [" + firstName + " " + lastName + "]\n" + home.toString();
    }
    
    
}
