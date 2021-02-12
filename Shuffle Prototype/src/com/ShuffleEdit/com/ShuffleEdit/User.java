package com.ShuffleEdit;

import java.util.ArrayList;
import java.util.HashMap;

/**
 This class is for the User to create their profile.

 - Verified status -
 is for User's who have status in the music industry and have worked with big name people.
 (Shuffle admin's will determine if someone is verified or not, users have to apply for this status)
 (This could be a good way to have contests where a lower tier user can work with verified users if they win)

 - Good Sport Rating -
 This will be used to motivate people to be good sports and play nice. At the end of each session, each user will be
 asked how the other user behaved from 1 - 5 stars. This rating will be updated at the end of each session for users.
*/

public class User {
    // Fields:
    private String name;
    private String city;
    private String[] genres;
    private String[] instruments;
    private boolean isVerified;
    private double goodSportRating;
    private int numOfRatings;
    private String username;
    private String password;
    private double percentage = 0;
    



    private ArrayList<String> dataPoints;
    private HashMap<String, Integer> userDataPointsMap; // the integer is there to assign a value of importance to each
                                                    // data point. The numbers will add together when matched and used
                                                    // to create a percentage

    /**
     * Constructor for Users that are songwriters and producers. (Another class will separate songwriters from prod.)
     * @param favoriteGenres the users favorite genres
     * @param instrumentsPlayed the instruments a user can play
     *
     * @param currentCity the current city the user lives in
     * @param id the username of the user
     * @param pass the users password
     */
    public User(String favoriteGenres, String instrumentsPlayed,
               String currentCity, String id, String pass) {
        genres = favoriteGenres.split(",");
        instruments = instrumentsPlayed.split(",");

        city = currentCity;
        username = id;
        password = pass;

        goodSportRating = 0;
        numOfRatings = 0;


        isVerified = false;
        dataPoints = new ArrayList<>(); // Arrays have limitations, might need to use a different collection in the future.

        double networkAddress; // this will be where users are assigned network ID's etc.

        // This will be used to match up shufflers data points to a percentage
        userDataPointsMap = new HashMap<>();
        fillUserDataPointsMap();

    }

    /**
     * ************************     Accessors     ******************************************************
     * *************************************************************************************************
     */



    public String getCity()
    {
        return city;
    }

    public String[] getGenres()
    {
        return genres;
    }

    public String[] getInstruments()
    {
        return instruments;
    }
    public String getUsername()
    {
        return username;
    }
    public String getPassword()
    {
        return password;
    }
    public int getNumOfRatings() {return numOfRatings;}
    public double getRating() { return goodSportRating; }
    public boolean getVerifiedStatus() {return isVerified;}
    public HashMap<String, Integer> getUserDataPointsMap() {return userDataPointsMap;}
    public double getPercentage() {return percentage;}


    /**
    This Accessor will put all the data points into an array of searchable strings then concatenate into a single string
     */
    public String getDataPoints()
    {
        int count = 0;
        for(String genre : genres) {       // loops through the instruments array and adds each element to dataPoints
            dataPoints.add(genre);
            count++;
        }
        for(String instrument: instruments){  // loops through the instruments array and adds each element to dataPoints
            dataPoints.add(instrument);
            count++;
        }
        dataPoints.add(city);

        String dataPointsString = "";

        for(String data: dataPoints) {
            dataPointsString = dataPointsString + data;
        }

        return dataPointsString;
    }

    /**
     * This will fill the dataPointsMap where it is used to create the percentage of match between users
     */
    public void fillUserDataPointsMap() {

        for(String genre: genres) {
            userDataPointsMap.put(genre,1);
        }

        userDataPointsMap.put(getCity(),3); //this gets a 3 because being in the same city is a higher priority.
    }


/**
 * ********************************* Mutators *************************************************************
 * ********************************************************************************************************
 */

    /**
     * Changes verified status of user if application accepted
     */
    public void updateVerifiedStatus()
    {
        isVerified = true;
    }

    /**
     * Updates the goodSportRating for the user
     */
    public void updateRating(int rating)
    {
        numOfRatings++;
        goodSportRating = rating/numOfRatings;
    }

    /**
     * Spits out a percentage of match when searched for
     * @param numberOfMatches number of datapoint matches
     * @return a double percentage number
     */
    public double percentageMatch(int numberOfMatches) {
        int numberOfPossibleMatches = 4;

        percentage = ((double)numberOfMatches)/numberOfPossibleMatches;
        return percentage;
    }

    public double changePercentageMatch(double percent) {
        percentage = percent;
        return percentage;
    }














}
