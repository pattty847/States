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
public class User {
    private Home home;
    private String firstName, lastName;
    
    User(){}
    
    User(String firstName, String lastName, Home home) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.home = home;
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
        return "User [" + firstName + " " + lastName + "\n" + home.toString() + ']';
    }
    
    
}
