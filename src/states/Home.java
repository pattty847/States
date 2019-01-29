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
public class Home {
    
    private final State homeState;

    Home(State home){
        homeState = home;
    }

    public State getHomeState() {
        return homeState;
    }

    @Override
    public String toString() {
        return "Home [" + homeState.toString() + "]";
    }
    
    
}
