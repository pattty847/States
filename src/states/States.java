/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package states;

import user.User;


/**
 *
 * @author pattt
 */
public class States{
    
    // Reference to our User class
    private static User user;
    
    public static void main(String args[]) 
    {   
        State[] states = new State[50];                                         //We first create an empty array of state objects 50 elements long
        StateList.loadData(states);                                             //Call the method in the StateList file to read the file
        //StateList.searchForState(states);                                          //Then give the user an oportunity to search the file
        User.handleUserInput(states, user);
    }
}
