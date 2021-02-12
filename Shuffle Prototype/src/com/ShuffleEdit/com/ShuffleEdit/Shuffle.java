package com.ShuffleEdit;



import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Shuffle implements ActionListener {

   // Class Initializations:
    loginGUI login;

    //registrationGUI register = new registrationGUI("register here") ;
    registrationGUI register = new registrationGUI("register here");

    userInterfaceGUI userInterface = new userInterfaceGUI();

    private Database myDatabase = new Database(); // this might need to be static

    User currentUser = null;


    /**
     * Constructor for Shuffle ********************************************************************
     */

    public Shuffle() {


        //myDatabase = new Database();


        login = new loginGUI("login");
        JFrame frame = login;
        frame.setVisible(true);


        // Connecting GUI buttons to this class // most of these fields are public from other classes
        login.registerForFreeButton.addActionListener(this);
        login.loginButton.addActionListener(this);
        register.createButton.addActionListener(this);
        userInterface.shuffleButton.addActionListener(this);

    }

  ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void main(String[] args) {
        new Shuffle();
    }

  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * ***************************** Button Events **************************************************************
     * **********************************************************************************************************
     */

    /**
     * Action events for registerForFreeButton, loginButton, and createButton. Conditionals look for which button is pressed to dish
     * out proper commands.
     *
     * @param e A button pressed
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login.registerForFreeButton) {

            // opens the register GUI frame

            register.setVisible(true);
            login.setVisible(false);

        }
        if (e.getSource() == login.loginButton) {
            // checks to see if the credentials are correct, if so, then opens the full interface



            User user = login.logIn(myDatabase);

            if(user!=null) {
                userInterface.setInterface(user);
                login.setVisible(false);
                userInterface.setVisible(true);
                currentUser = user;
            }

        }
        if (e.getSource() == register.createButton) {

            if(register.createNewUser(myDatabase)==true) { // adds a new user and checks to make sure it worked before proceeding.
                ; // passes in myDatabase and adds the new user to it in the regGUI
                register.resetRegistrationPage();
                register.setVisible(false);
                login.setVisible(true);
            }
        }
        if (e.getSource() == userInterface.shuffleButton) {

             userInterface.shuffleSearch(myDatabase, currentUser);
        }



    }
}
