package com.ShuffleEdit;



import javax.swing.*;


public class loginGUI extends JFrame {

    // fields for loginGUI:
    public JButton loginButton;
    public JButton registerForFreeButton;
    private JLabel shuffleTitleLabel;
    private JLabel loginLabel;
    private JTextField loginTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordTextField;

    private JPanel loginPanel;
    private JLabel idErrorLabel;


    /**
     * *********************************************************************************************************
     * Constructor for the loginGUI
     *
     * @param title the name of the application
     */
    public loginGUI(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(loginPanel);
        this.pack();

    }

    /**
     * ********************************* Accessors ************************************************************
     * ********************************************************************************************************
     */

    /**
     * Logs in to the Shuffle platform if the credentials are correct. This method checks the creds then opens the
     * userInterface.
     */
    public User logIn(Database myDatabase) {
        idErrorLabel.setText(""); //resets error message
        String loginString = loginTextField.getText();
        String passwordString = passwordTextField.getText();
        User userInQuestion;

        if(myDatabase.allUsers.containsKey(loginString)) {
           userInQuestion = myDatabase.allUsers.get(loginString);

           if(userInQuestion.getPassword().equals(passwordString)) {
               //userInterfaceGUI ui = new userInterfaceGUI(userInQuestion); // pass in the user in question that has the
                                                                                // correct credentials.
               //ui.setVisible(true);
               return userInQuestion;

           } else {
               idErrorLabel.setText("Incorrect password for user!");

           }
        } else {

            idErrorLabel.setText("We could not find a matching ID!");
            userInQuestion = null;

        }
        return userInQuestion;
    }







}
