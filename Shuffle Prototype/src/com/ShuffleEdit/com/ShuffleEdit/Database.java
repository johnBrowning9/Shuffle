package com.ShuffleEdit;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * This class will be where the Songwriters and Producers are stored
 */

public class Database {
    // Fields:
    public static HashMap<String, User> songwriters;
    public static HashMap<String, User> producers;
    public static HashMap<String, User> allUsers;
    public static ArrayList<User> arrayOfUsers;

    private static int numOfSongwriters;
    private static int numOfProducers;

    /**
     * Constructor for the database
     */

    public Database() {

    songwriters = new HashMap<>();
    producers = new HashMap<>();
    allUsers = new HashMap<>();
    arrayOfUsers = new ArrayList<>();
    numOfSongwriters=0;
    numOfProducers=0;
 }

    /**
     * **************************************** Accessors *****************************************************
     * ********************************************************************************************************
     */


    public ArrayList<User> getArrayOfUsers() {return arrayOfUsers;}














    /**
     * **************************************** Mutators *****************************************************
     * ********************************************************************************************************
     */

    /**
     * Add a new songwriter to the database.
     *
     */
    public void addSongwriter(String favoriteGenres, String instrumentsPlayed, String currentCity, String id, String pass)
    {
        User songwriter = new User(favoriteGenres, instrumentsPlayed, currentCity, id, pass);
            songwriters.put(songwriter.getDataPoints(),songwriter);
            numOfSongwriters++;
            System.out.println(songwriter.getDataPoints());
            allUsers.put(id, songwriter); //adds to allUsers HashMap
            arrayOfUsers.add(songwriter); //adds to arrayOfUsers ArrayList
    }

    /**
     * Add a new producer to the database.
     *
     */
    public void addProducer(String favoriteGenres, String instrumentsPlayed, String currentCity, String id, String pass)
    {
        User producer = new User(favoriteGenres, instrumentsPlayed, currentCity, id, pass);
        producers.put(producer.getDataPoints(),producer);
        numOfProducers++;
        System.out.println(producer.getDataPoints());
        allUsers.put(id, producer); // adds to allUsers HashMap
        arrayOfUsers.add(producer); //adds to arrayOfUsers ArrayList
    }

}
