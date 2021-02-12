package com.ShuffleEdit;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.Vector;

public class userInterfaceGUI extends JFrame{
    private JPanel uiPanel;
    private JTabbedPane tabbedPane1;
    public JButton shuffleButton;
    private JPanel myProfileTab;
    private JPanel mySessionsTab;
    private JTree sessionTree;
    private JSlider userStatusSlider;
    private JLabel bronzeLabel;
    private JLabel silverLabel;
    private JLabel goldLabel;
    private JLabel platinumLabel;
    private JLabel userNameLabel;

    // search results panel
    private JPanel searchResultsPanel;
    private JList searchResultsList;


    /**
     * ********************** Constructor ********************************************************
     */
    public userInterfaceGUI() {

        super("User Interface");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(uiPanel);
        this.pack();

        searchResultsList.setVisible(false);
        searchResultsPanel.setVisible(false);

    }

    /**
     * ********************** ACCESSORS **************************************************************
     * **********************************************************************************************
     */

    /**
     * SHUFFLE button is pressed. This performs the search for similar songwriters/producers
     * @return a SearchResults object
     */
    public void shuffleSearch(Database database, User person) { // need to create a percentage of match that factors in
                                                            // all both users data points and how many match.

        HashMap<String, Integer> personsDataPoints = person.getUserDataPointsMap();
        ArrayList<String> personsDataPointsArray = new ArrayList<>(personsDataPoints.size());
        HashMap<User, Double> hmForSortMethod = new HashMap<>();
        Vector<User> listOfSortedPeopleResults;

        // adds the data points from the HashMap to an ArrayList
        for(String dataPoint: personsDataPoints.keySet()) {
            personsDataPointsArray.add(dataPoint);
        }

        ////////////////////////////// Adding the arrayOfUsers to a Vector to be used for JList /////////////////////

        Vector<User> listOfSearchablePeople = new Vector<>();

                ArrayList<User> arrayOfUsers = database.getArrayOfUsers();
                for(User user: arrayOfUsers) {
                    listOfSearchablePeople.add(user); // adding the users to a vector
                }
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // search the arrayOfUsers list for similarities and then create the percentage match.
        int arrayCount = 0;
        for(User personSearched: listOfSearchablePeople) {

            int numberOfMatches = 0;
            HashMap<String, Integer> personSearchedDataPoints = personSearched.getUserDataPointsMap();
            for(String data:personsDataPointsArray) { // this looks for matches from other users to the data points
                if(personSearchedDataPoints.keySet().contains(data)) {
                  numberOfMatches++;
                    // the values of the city field are getting lost, I need to get that cities value i.e. 3 and add to
                        // numberOfMatches
                }

            }

           double percentage = personSearched.percentageMatch(numberOfMatches);


            hmForSortMethod.put(personSearched,percentage); // puts users into a hashmap that contains a user with its
                                                            // associated match percentage.

            arrayCount++; // not currently being used, but could be.

        }



        listOfSortedPeopleResults = sortByValues(hmForSortMethod); // sorts the hashmap in descending order to a Vector

        Vector<String> userViewOfResults = new Vector<>();
        for(User user: listOfSortedPeopleResults) { // puts the sorted Vector into a new String Vector for use by JList search results
            userViewOfResults.add(user.getUsername() + " " + user.getPercentage());
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////// Below is used for implementation of the search into the interface //////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

        searchResultsList.setListData(userViewOfResults); // add the userViewOfResults vector to the JList
        searchResultsList.setVisible(true);
        searchResultsPanel.setVisible(true);
        // use a JScrollPane to add a scroll bar to the List like this JScrollPane scrollPane = new JScrollPane(myList);
        //JScrollPane scrollPane = new JScrollPane(searchResultsList);
        //scrollPane.createVerticalScrollBar();

    }


    /**
     * ********************* MUTATORS *****************************************************************
     * ************************************************************************************************
     */
    public void setInterface(User person) {

        String username = person.getUsername();

        String city = person.getCity();
        String[] genres = person.getGenres();
        String[] instruments = person.getInstruments();
        String dataPoints = person.getDataPoints();
        int numOfRatings = person.getNumOfRatings();
        double goodSportRating = person.getRating();
        boolean verifiedStatus = person.getVerifiedStatus();

        userNameLabel.setText(username);
    }

    /**
     * This will sort a hashmap by its values for you.
     * @param map A hashmap of your choosing
     * @return a sorted Vector
     */
    private Vector sortByValues(HashMap map) {

        List list = new LinkedList(map.entrySet());
        Vector<Object> listOfSortedPeopleResults = new Vector<>();
        // Defined Custom Comparator here
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) { // this is reversing the return ints so that the list is descending
                if(((Comparable) ((Map.Entry) (o1)).getValue())
                        .compareTo(((Map.Entry) (o2)).getValue()) > 0)
                {
                    return -1;
                }
                else if(((Comparable) ((Map.Entry) (o1)).getValue())
                        .compareTo(((Map.Entry) (o2)).getValue()) < 0)
                {
                    return 1;
                }
                else
                {
                    return 0;
                }
            }
        });

        // Here I am copying the sorted list in HashMap
        // using LinkedHashMap to preserve the insertion order
        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
            listOfSortedPeopleResults.add(entry.getKey());
        }
        return listOfSortedPeopleResults;

    } // end of sortByValues method








}
