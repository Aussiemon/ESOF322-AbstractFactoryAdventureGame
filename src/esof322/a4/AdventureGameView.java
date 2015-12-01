/**
 * @author Andrew Jenkins, Stephanie McLaren, David Schwehr, Kay Spokas, Matt
 * Rohrlach
 */
/**
 * @SummaryOfChanges
 *  Adjusted Main method adjusted size of GUI to account for screen size
 *  Adjusted button locations to fit under player status box
 *  Added JLabel and JText Area for player status messages
 *  Added displayCurrentInfo and grab methods to keep track of player
 *      inventory status
 *
 *
 */
package esof322.a4;

import javax.swing.*;

import BreezySwing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AdventureGameView extends GBFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

// Window objects --------------------------------------
    JLabel welcomeLabel
            = addLabel("Welcome to the Adventure Game "
                    + "(inspired by an old game called the Colossal Cave Adventure)."
                    + " Java implementation Copyright (c) 1999-2012 by James M. Bieman",
                    1, 1, 5, 1);

    // Row, Column, Width, Height
    JLabel viewLable = addLabel("Your View: ", 2, 1, 1, 1);
    JTextArea viewArea = addTextArea("Start", 3, 1, 4, 3);

    JLabel carryingLable = addLabel("You are carrying: ", 6, 1, 1, 1);
    JTextArea carryingArea = addTextArea("Nothing", 7, 1, 4, 3);

    JLabel statusLable = addLabel("Your status: ", 10, 1, 1, 1);
    JTextArea statusArea = addTextArea("Nothing", 11, 1, 4, 3);

    JLabel choiceLabel = addLabel("Choose a direction, pick-up, or drop an item", 15, 1, 5, 1);
    
    JButton restartButton = addButton("Restart", 2, 5, 1, 1);
    
    JButton saveButton = addButton("Save Game", 7, 5, 1, 1);
    JButton loadButton = addButton("Load Game", 8, 5, 1, 1);

    JButton difficultyButton = addButton("Choose Difficulty", 15, 5, 1, 1);
    JButton grabButton = addButton("Grab an item", 16, 5, 1, 1);
    JButton dropButton = addButton("Drop an item", 17, 5, 1, 1);

    JButton northButton = addButton("North", 16, 2, 1, 1);
    JButton southButton = addButton("South", 17, 2, 1, 1);
    JButton eastButton = addButton("East", 16, 3, 1, 1);
    JButton westButton = addButton("West", 16, 1, 1, 1);
    JButton upButton = addButton("Up", 15, 2, 1, 1);
    JButton downButton = addButton("Down", 18, 2, 1, 1);

    AdventureGameModelAbstractFactory model;
    int difficulty = Integer.parseInt((String) JOptionPane.showInputDialog(
                null,
                "Choose an initial difficulty:",
                "Customized Dialog",
                JOptionPane.PLAIN_MESSAGE,
                null,
                new String[]{"0", "1"},
                "0"));
    
    

    // Constructor-----------------------------------------------
    public AdventureGameView() {
        setTitle("Adventure Game");
        model = new AdventureGameModelAbstractFactory(difficulty);
        viewArea.setEditable(false);
        carryingArea.setEditable(false);
        displayCurrentInfo();
        
        System.out.println(System.getProperty("user.dir"));
    }

    // buttonClicked method--------------------------------------
    public void buttonClicked(JButton buttonObj) {
        String moveResult = "";
        if (buttonObj == upButton) {
            //changed junk
            model.goUp();
        } else if (buttonObj == downButton) {
            model.goDown();
        } else if (buttonObj == northButton) {
            model.goNorth();
        } else if (buttonObj == southButton) {
            model.goSouth();
        } else if (buttonObj == eastButton) {
            model.goEast();
        } else if (buttonObj == westButton) {
            model.goWest();
        } else if (buttonObj == saveButton) {
            saveGame();
        } else if (buttonObj == loadButton) {
            loadGame();
        } else if (buttonObj == difficultyButton) {
            difficulty = Integer.parseInt((String) JOptionPane.showInputDialog(
                null,
                "Choose a difficulty:",
                "Customized Dialog",
                JOptionPane.PLAIN_MESSAGE,
                null,
                new String[]{"0", "1"},
                "0"));
                
                model.setPlayerStatus("Difficulty set to " + difficulty + ".");
        } else if (buttonObj == restartButton){
            model = new AdventureGameModelAbstractFactory(difficulty);
            displayCurrentInfo();
        }  else if (buttonObj == grabButton) {
            grab();
        } else if (buttonObj == dropButton) {
            drop();
        }

        displayCurrentInfo();
    }

    // Private methods-------------------------------------------
    private void displayCurrentInfo() {
        viewArea.setText(model.getView());
        statusArea.setText(model.getStatus());
        carryingArea.setText(model.getItems());
    }

    // Left as an exercise. 
    private void grab() {
        if (model.getNumberOfPlayerItems() >= 2) {
            model.setPlayerStatus("You cannot carry more than two items.\n"
                    + "Drop the item you wish to leave behind."
                    + "\nThe items you are currently holding are\n"
                    + model.getItems());
            return;
        }
        Item[] roomContents = model.getCurrentRoom().getRoomContents();
        String s = (String) JOptionPane.showInputDialog(
                null,
                "Pick up which item?\nView Room Contents for\n"
                + "a list of items in the room",
                "Customized Dialog",
                JOptionPane.PLAIN_MESSAGE,
                null,
                new String[]{"1", "2", "3"},
                "1");

        int item = Integer.parseInt(s);

        if (item <= roomContents.length) {
            model.giveItemToPlayer(roomContents[item - 1]);
            model.getCurrentRoom().removeItem(roomContents[item - 1]);
            model.setPlayerStatus("Item successfully picked up.");
        } else {
            model.setPlayerStatus("Are you seeing things? That item is not"
                    + " there.");
        }
    }

    // Left as an exercise. 
    private void drop() {
        String s = (String) JOptionPane.showInputDialog(
                null,
                "Drop item 1 or item 2?\nView carried items status for a "
                + "list of current items held.",
                "Customized Dialog",
                JOptionPane.PLAIN_MESSAGE,
                null,
                new String[]{"1", "2"},
                "2");

        int item = Integer.parseInt(s);
        Item[] playerItems = model.getListOfPlayerItems();

        if (model.checkIfPlayerHasItem(playerItems[item - 1])) {
            model.dropItemFromPlayer(item);
            model.setPlayerStatus("Item successfully dropped.");
        } else {
            model.setPlayerStatus("You are not holding an item in that hand.");
        }
    }
    
    /**
     * Save current game model to file
     */
    public void saveGame(){
        String saveName = System.getProperty("user.dir")+ "\\save" 
                + JOptionPane.showInputDialog(null, 
                "Choose a save number.");
        
        ObjectOutputStream saving;
        try {
            saving = new ObjectOutputStream(new FileOutputStream(saveName));
            saving.writeObject(model);
            saving.flush();
            saving.close();
        } catch (IOException ex) {
            System.out.println("Error saving game. No write permission?");
        }
        model.setPlayerStatus("Saved game.");
    }
    
    /**
     * Load game model from user-specified file
     */
    public void loadGame(){
        String saveName = System.getProperty("user.dir")+ "\\save" 
                + JOptionPane.showInputDialog(null, 
                "Choose a save number to load.");
        
        ObjectInputStream loading;
        try {
            loading = new ObjectInputStream(new FileInputStream(saveName));
            model = (AdventureGameModelAbstractFactory) loading.readObject();
            loading.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Error loading save. Does not exist?");
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Error loading save. No read permission? Disk error?");
        }
        
        difficulty = model.getDifficulty();
    }

    /**
     * @param args
     * @throws IOException This Main Method creates the game on the GUI and on
     * the original terminal
     */
    public static void main(String[] args) throws IOException {
        JFrame view = new AdventureGameView();
        view.setSize(800, 800); /* was 400, 250  */

        view.setVisible(true);
        AdventureGame theGame = new AdventureGame();
        theGame.startQuest();

    }
}
