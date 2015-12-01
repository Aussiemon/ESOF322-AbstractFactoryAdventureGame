/**
 * @author Andrew Jenkins, Stephanie McLaren, David Schwehr,
 * Kay Spokas, Matt Rohrlach
 */

/**
 * @SummaryOfChanges
 *  
 */
package esof322.a4;

//Formerly AdventureGameModelFacade, because the AdventureGameView is the true facade interface

import java.io.Serializable;
import java.util.ArrayList;

public class AdventureGameModelFacade implements Serializable{

    private Room entrance;
    private Player thePlayer;
    private int difficulty;

    /**
     * Default constructor
     */
    AdventureGameModelFacade() { // we initialize
        thePlayer = new Player();
        Level0Room startRm = createLevel0Adventure();
        thePlayer.setRoom(startRm);
    }
    
    /**
     * Constructor for difficulty choosing
     */
    AdventureGameModelFacade(int inDifficulty) { // we initialize
        thePlayer = new Player();
        difficulty = inDifficulty;
        Room startRm;
        if(difficulty == 1){
            startRm = createLevel1Adventure();
        }
        else{
            startRm = createLevel0Adventure();
        }
        thePlayer.setRoom(startRm);
    }
    
    /**
     * "Easy" difficulty abstract factory method
     * @return level 0 adventure's starting point
     */
    public Level0Room createLevel0Adventure() {
        Level0Factory level0 = new Level0Factory();
        return level0.getEntrance();
    }
    
    /**
     * "Hard" difficulty abstract factory method
     * @return level 1 adventure's starting point
     */
    public Level1Room createLevel1Adventure() {
        Level1Factory level1 = new Level1Factory();
        return level1.getEntrance();
    }
    
    //Linked GUI to movement methods
    public void goUp() {
        thePlayer.go(4);
    }

    public void goDown() {
        thePlayer.go(5);
    }

    public void goNorth() {
        thePlayer.go(0);
    }

    public void goSouth() {
        thePlayer.go(1);
    }

    public void goEast() {
        thePlayer.go(2);
    }

    public void goWest() {
        thePlayer.go(3);
    }

    // You need to finish these getView and getItems methods.
    public String getView() {
        return thePlayer.look();
    }

    public String getItems() {
        return thePlayer.showMyThings();
    }

    /**
     * Gets the current number of items the player is holding.
     *
     * @return Number of items held
     */
    public int getNumberOfPlayerItems() {
        return thePlayer.numItemsCarried();
    }

    /**
     * Gets the list of items held by the player
     *
     * @return Item list
     */
    public Item[] getListOfPlayerItems() {
        return thePlayer.getPlayerItemList();
    }

    /**
     * Get the current status of the player
     *
     * @return Current Status
     */
    public String getStatus() {
        return thePlayer.currentStatus();
    }

    /**
     * Update the player's current status
     *
     * @param status Status to update with
     */
    public void setPlayerStatus(String status) {
        thePlayer.setCurrentStatus(status);
    }

    /**
     * Gives an item to the player
     *
     * @param item Item to give to the player
     */
    public void giveItemToPlayer(Item item) {
        thePlayer.pickUp(item);
    }

    /**
     * Drops an item from the player
     *
     * @param int Item to drop
     */
    public void dropItemFromPlayer(int item) {
        thePlayer.drop(item);
    }

    /**
     * Checks to see if the player has the item
     *
     * @return Whether the player has the item
     */
    public boolean checkIfPlayerHasItem(Item item) {
        return thePlayer.haveItem(item);
    }

    /**
     * Gets the current room player is in.
     *
     * @return Current room
     */
    public Room getCurrentRoom() {
        return thePlayer.getLoc();
    }
    
    public int getDifficulty(){
        return difficulty;
    }
}
