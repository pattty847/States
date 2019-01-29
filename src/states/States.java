/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package states;

/**
 *
 * @author pattt
 */
public class States {
    public static void main(String args[]) 
    {   
        State[] states = new State[50];                                         //We first create an empty array of state objects 50 elements long
        StateList.loadData(states);                                             //Call the method in the StateList file to read the file
        StateList.searchState(states);                                          //Then give the user an oportunity to search the file
    }
}
