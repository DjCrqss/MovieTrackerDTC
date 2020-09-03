import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.Color;

/**
 * This class creates the main UI for the movie tracker
 * 
 * @author DJ
 * @version 2.1
 */
public class GUI
{
    // create MovieDatabase class as an object
    private MovieDatabase database1 = new MovieDatabase();

    /**
     * Constructor for objects of class GUI
     */
    public GUI()
    {
        // initialise instance variables
        
    }
    
    /**
     * Add a movie to the array
     */
    private void addMovie()
    {
        UI.clearGraphics();
        UI.println("\n------  Add Movie  ------");
        // ask user for parameters of movie
        String title = "";
        String director = "";
        String genre = "";
        boolean askAgain = true;
        while (askAgain == true){
            title =  UI.askString("Movie title: ").trim();
            director = UI.askString("Movie director: ").trim();
            genre = UI.askString("Movie genre: ").trim();
            if (!title.equals("") && !director.equals("") && !genre.equals("")){
                askAgain = false;
            } else{
                UI.println("One of your inputs are blank.\n");
            }
        }
        int rating = 0;
        askAgain = true;
        while (askAgain == true){
            // error checking for rating input belove 0 or above 5
            try{
                rating = UI.askInt("Your rating (0-5): ");
                if (rating < 0 || rating > 5){
                    throw new ArithmeticException();
                } else{
                    askAgain = false;
                }
            }
            catch(ArithmeticException ae){
                UI.println("Your rating (0-5):  (must be between 0-5): ");
            }
        }
        // add movie to HashMap as an object via method in MovieDatabase class
        database1.createMovie(capitaliseSentence(title), capitaliseSentence(genre), capitaliseSentence(director), rating);
        UI.println("Added " + title + " to the movie list.");
    }
    
    /**
     * Capitalise the first letter of each word in a string
     * @param String sentence to convert
     * @return String converted sentence
     */
    private String capitaliseSentence(String sentence)
    {
        // split into words
        String[] words = sentence.split(" ");
        // capitalize each word
        for (int i = 0; i < words.length; i++)
        {
            words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1).toLowerCase();
        }
        // rejoin back into a sentence
        sentence = String.join(" ", words);
        return sentence;
    }
    
    /**
     * Search movie and get ID
     */
    private void searchMovie()
    {
        UI.println("\n------ Search Movie ------");
        String titleQuery = UI.askString("Please enter Movie name: ");
        int movieNum = database1.returnMovieID(titleQuery);
        if (movieNum == -1){
            UI.println("There were no movies found with this name");
        }
        else{
            printInfo(movieNum);
        }
    }
    
    /**
     * Print info of movie
     * @param movieID   ID of the movie that is being searched
     * 
     */
    private void printInfo(int movieID)
    {   
        UI.clearGraphics();
        UI.println("Title: " + database1.returnMovieTitle(movieID));
        UI.println("Director: " + database1.returnMovieDirector(movieID));
        UI.println("Genre: " + database1.returnMovieGenre(movieID));
        UI.print("Rating: ");
        for (int i = 0; i < database1.returnMovieRating(movieID); i++){
            UI.print("★");
        }
        for (int i = 5; i > database1.returnMovieRating(movieID); i--){
            UI.print("☆");
        }
        UI.println();
        // Draw title for GUI
        UI.setFontSize(32);
        UI.drawString("Movie Information ", 50, 60);
        // Draw GUI
        UI.setColor(Color.darkGray);
        UI.fillRect(50, 90, 250, 250);
        UI.drawImage("movie_icon.png", 75, 115, 200, 200);
        UI.setFontSize(18);
        // Draw text
        UI.drawString("Movie    | " + database1.returnMovieTitle(movieID), 50, 365);
        UI.drawString("Director | " + database1.returnMovieDirector(movieID), 50, 390);
        UI.drawString("Genre    | " + database1.returnMovieGenre(movieID), 48, 415);
        UI.drawString("Rating   | ", 51, 440);
        // Draw circles for star rating
        for (int i = 0; i < database1.returnMovieRating(movieID); i++){
            UI.fillOval(130 + (22 * i), 425, 20, 20);
        }
        for (int i = 5; i > database1.returnMovieRating(movieID); i--){
            UI.drawOval(130 + (database1.returnMovieRating(movieID) * 22) + (22 * (5-i)), 425, 20, 20);
        }
        // Find movie to recommend    
            
        String[] moviesList = database1.getTopRecommended(movieID);
        if (moviesList[0] != "null"){
            UI.drawString("Recommended:", 50, 475);
        } else {
            UI.drawString("No movie to recommend", 50, 475);
        }
        for (int i = 0; i < moviesList.length; i++){
            if (moviesList[i] != "null"){
                UI.setColor(Color.lightGray);
                UI.fillRect(50, 485 + (55 * i), 250, 50);
                UI.drawImage("movie_icon.png", 65, 495 + (i * 55), 32, 30);
                UI.setColor(Color.darkGray);
                UI.drawString(moviesList[i], 110, 517 + (i * 55));
            }
        }
        
    }
    
    /**
     * Display list of all movies to GUI and print
     */
    private void displayMovies(){
        UI.clearGraphics();
        UI.println("\n------ All Movies ------");
        String[] moviesList = database1.getAllMovies();
        for (int i = 0; i < moviesList.length; i++){
             UI.println(moviesList[i]);
        }
        // Draw title for GUI
        UI.setFontSize(32);
        UI.drawString("All Movies", 50, 60);
        UI.setFontSize(18);
        // Draw movies on screen
        for (int i = 0; i < moviesList.length; i++){
            UI.setColor(Color.lightGray);
            UI.fillRect(50, 80 + (55 * i), 250, 50);
            UI.drawImage("movie_icon.png", 65, 90 + (i * 55), 32, 30);
            UI.setColor(Color.darkGray);
            UI.drawString(moviesList[i], 110, 112 + (i * 55));
        }
    }
    
    /**
     * Main routine
     * Setup GUI
     */
    //public static void main(String[] args){
    public void main(){
        GUI obj = new GUI();  // Make a GUI object
        
        UI.initialise();
        UI.setWindowSize(720, 620);
        UI.addButton("Add Movie", obj::addMovie);
        UI.addButton("Search Movie", obj::searchMovie);
        UI.addButton("Display Movies", obj::displayMovies);
        UI.addButton("Quit", UI::quit);
        UI.setDivider(0.5);     // must come after setting up buttons etc.
    }
 
    /**
     * Method to clear the GUI
     */
    private void clearScreen(){
        UI.clearGraphics();
    }
}

