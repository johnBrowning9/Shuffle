package com.ShuffleEdit;



import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This frame will be opened up when a person opts to register for an account.
 *
 * This class still needs listeners for each component in the swingGui
 */

public class registrationGUI extends JFrame {

    // registration panel Fields:
    private JPanel panel;
    private JLabel entryMessage;
    private JLabel userField;
    private JTextField userNameText;
    private JLabel newPasswordField;
    private JPasswordField passwordField1;
    private JLabel reEnterPasswordField;
    private JPasswordField passwordField2;
    private JComboBox songwriterOrProducerField;
    private JLabel industryRoleField;
    private JCheckBox popCheckbox;
    private JCheckBox countryCheckBox;
    private JCheckBox hipHopCheckBox;
    private JCheckBox rapCheckBox;
    private JCheckBox rockCheckBox;
    private JCheckBox alternativeCheckBox;
    private JCheckBox indieCheckBox;
    private JCheckBox soulCheckBox;
    private JLabel genreField;
    private JLabel instrumentsField;
    private JCheckBox vocalsCheckBox;
    private JCheckBox guitarCheckBox;
    private JCheckBox bassCheckBox;
    private JCheckBox drumsCheckBox;
    private JCheckBox stringsCheckBox;
    private JCheckBox electronicCheckBox;
    private JCheckBox percussionCheckBox;
    private JCheckBox windCheckBox;
    public JButton createButton;
    private JLabel currentCityLabel;
    private JTextField cityTextField;
    private JLabel doNotMatchLabel;

    //public static Database myDatabase;


    /**
     * ******************************************************************************************************
     * Constructor for the GUI
     *
     * @param title the name of the application
     */
    public registrationGUI(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel);
        this.pack();


    }


    /**
     * ****************************   Mutators   ******************************************************************
     * ************************************************************************************************************
     */


    /**
     * Creates a new User through the registration page
     */
    public boolean createNewUser(Database databaseItem) {
        boolean didItWork = false;
        doNotMatchLabel.setText(""); // this will refresh the error message each time button is pressed
        String password = "";
        String id = "";
        String currentCity = "";
        String[] favoriteGenres = new String[8];
        String[] instrumentsPlayed = new String[8];
        int arrayCount = 0;
        int errorCode = 0;
        // int instrumentArrayCount = 0; can be used if resetting arrayCount doesn't work.

        String passTest1 = String.valueOf(passwordField1.getPassword());
        String passTest2 = String.valueOf(passwordField2.getPassword());

        // Picking 3 genres
        while (arrayCount < 3) { // this needs to be fixed.
            if (popCheckbox.isSelected()) {

                favoriteGenres[arrayCount] = "pop";
                arrayCount++;
            }
            if (countryCheckBox.isSelected()) {
                favoriteGenres[arrayCount] = "country";
                arrayCount++;
            }
            if (rockCheckBox.isSelected()) {
                favoriteGenres[arrayCount] = "rock";
                arrayCount++;
            }
            if (alternativeCheckBox.isSelected()) {
                favoriteGenres[arrayCount] = "alternative";
                arrayCount++;
            }
            if (rapCheckBox.isSelected()) {
                favoriteGenres[arrayCount] = "rap";
                arrayCount++;
            }
            if (hipHopCheckBox.isSelected()) {
                favoriteGenres[arrayCount] = "hip-hop";
                arrayCount++;
            }
            if (indieCheckBox.isSelected()) {
                favoriteGenres[arrayCount] = "indie";
                arrayCount++;
            }
            if (soulCheckBox.isSelected()) {
                favoriteGenres[arrayCount] = "soul";
                arrayCount++;
            }
        }
        if(arrayCount>3) {
            errorCode = errorCode + 1; // error code used for checking to see if there are too many genre boxes checked.
        }
        arrayCount = 0; // resets array count

        while (arrayCount < 3) { // this needs to be fixed also. use a different type of loop.
            if (vocalsCheckBox.isSelected()) {
                instrumentsPlayed[arrayCount] = "vocals";
                arrayCount++;
            }
            if (guitarCheckBox.isSelected()) {
                instrumentsPlayed[arrayCount] = "guitar";
                arrayCount++;
            }
            if (bassCheckBox.isSelected()) {
                instrumentsPlayed[arrayCount] = "bass";
                arrayCount++;
            }
            if (drumsCheckBox.isSelected()) {
                instrumentsPlayed[arrayCount] = "drums";
                arrayCount++;
            }
            if (stringsCheckBox.isSelected()) {
                instrumentsPlayed[arrayCount] = "strings";
                arrayCount++;
            }
            if (electronicCheckBox.isSelected()) {
                instrumentsPlayed[arrayCount] = "electronic";
                arrayCount++;
            }
            if (percussionCheckBox.isSelected()) {
                instrumentsPlayed[arrayCount] = "percussion";
                arrayCount++;
            }
        }
        if(arrayCount>3) {
            errorCode = errorCode + 2; // error code used for checking to see if there are too many instrument boxes checked.
        }
        arrayCount = 0;

        String genres = "";
        while (arrayCount < 2) {
            genres = genres + favoriteGenres[arrayCount] + ", ";
            arrayCount++;
        }
        genres = genres + favoriteGenres[arrayCount]; // This makes the last genre in the string not have a comma
        arrayCount = 0; //reset the counter again

        String instruments = "";
        while (arrayCount < 2) {
            instruments = instruments + instrumentsPlayed[arrayCount] + ", ";
            arrayCount++;
        }
        instruments = instruments + instrumentsPlayed[arrayCount]; // This makes the last instrument in
        // the string not have a comma




        if (passTest1.equals(passTest2)) { // make sure the passwords match before creating account

            password = passTest1;
            id = userNameText.getText();
            currentCity = cityTextField.getText();

            if(errorCode==0) {

                if (String.valueOf(songwriterOrProducerField.getSelectedItem()).equals("Songwriter")) {
                    databaseItem.addSongwriter(genres, instruments, currentCity, id, password);
                }
                if (String.valueOf(songwriterOrProducerField.getSelectedItem()).equals("Producer")) {
                    databaseItem.addProducer(genres, instruments, currentCity, id, password);
                }
                didItWork = true;
            } else if(errorCode == 1) {
                doNotMatchLabel.setText("Choose 3 genres only please!");
            } else if(errorCode == 2) {
                doNotMatchLabel.setText("Choose 3 instruments only please!");
            } else if(errorCode == 3) {
                doNotMatchLabel.setText("Choose 3 instruments and 3 genres only please!");
            }


        } else {
            // set up an error message to be displayed here saying "passwords do not match"
            doNotMatchLabel.setText("Passwords do not match!"); //setText() occurs after the check for password equality.
        }
        return didItWork;
    }

    /**
     * Reset the registration page if brought up again.
     */
    public void resetRegistrationPage() {
        // re-setting Jcheckboxes
        popCheckbox.setSelected(false);
        countryCheckBox.setSelected(false);
        hipHopCheckBox.setSelected(false);
        rapCheckBox.setSelected(false);
        rockCheckBox.setSelected(false);
        alternativeCheckBox.setSelected(false);
        indieCheckBox.setSelected(false);
        soulCheckBox.setSelected(false);

        vocalsCheckBox.setSelected(false);
        guitarCheckBox.setSelected(false);
        bassCheckBox.setSelected(false);
        drumsCheckBox.setSelected(false);
        stringsCheckBox.setSelected(false);
        electronicCheckBox.setSelected(false);
        percussionCheckBox.setSelected(false);
        windCheckBox.setSelected(false);

        cityTextField.setText("");
        userNameText.setText("");

        passwordField1.setText("");
        passwordField2.setText("");


    }





} // end of Class