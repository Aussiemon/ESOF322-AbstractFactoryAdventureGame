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

public class AdventureGameModelAbstractFactory implements Serializable{

    private Room entrance;
    private Player thePlayer;
    private ArrayList<Room> roomList;
    private int difficulty;

    /**
     * Default constructor
     */
    AdventureGameModelAbstractFactory() { // we initialize
        thePlayer = new Player();
        Level0Room startRm = createLevel0Adventure();
        thePlayer.setRoom(startRm);
    }
    
    /**
     * Constructor for difficulty choosing
     */
    AdventureGameModelAbstractFactory(int inDifficulty) { // we initialize
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
     */
    public Level0Room createLevel0Adventure() {
        // The outside: 
        Level0Room outside = new Level0Room();
        outside.setDesc(
                "You are standing at the edge of a desert cave. The stars\n"
                + "illuminate the sands around you but shed no light\n"
                + "past the mouth of the abyss at your feet. Something \n"
                + "compels you to descend, and will smack your face\n"
                + "if you do not. (outside).");

        // Level0Room 1:
        Level0Room r1 = new Level0Room();
        r1.setDesc(
                "The cave exit shines brightly above you, but all else is\n"
                + " dark, and you are glad to have your torch. It reveals a narrow,\n"
                + "dank passage to the east (r1).");

        // Connect the outside to Level0Room 1:
        outside.setSide(5, r1);
        r1.setSide(4, outside);
        entrance = outside;

        // Level0Room 2:
        Level0Room r2 = new Level0Room();
        r2.setDesc(
                "The entire room is flooded. You wade into the chest-high\n"
                + "water and see a dim light to the west; however, you see\n"
                + "a dark hole to the east only about 18 inches high (r2).");

        // Level0Room 3:
        Level0Room r3 = new Level0Room();
        r3.setDesc("The room is wide, and huge rocks block your path.\n"
                + "However, you expertly scale the rubble and find yourself\n"
                + "at the room's center. There is a rubble-filled corridor\n"
                + "to the west, another to the east, and a deep hole\n"
                + "that is completely flooded in the middle of the room (r3).");

        // Connect Level0Rooms 1, 2, & 3:
        r1.setSide(2, r2);
        r2.setSide(3, r1);
        r2.setSide(2, r3);
        r3.setSide(3, r2);

        // Level0Room 4:
        Level0Room r4 = new Level0Room();
        r4.setDesc("There are passages leading west and north. The sound of\n"
                + " dripping water echoes from a downward-slopping path (r4).");

        Item timmyTheRat = new Item();
        timmyTheRat.setDesc("An inexplicably adorable, yet lonely, rat. You named"
                + " him Timmy.");
        r4.addItem(timmyTheRat);

        // Level0Room 5:
        Level0Room r5 = new Level0Room();
        r5.setDesc("You hold your breath as you negotiate pitch black water,\n"
                + "having left your torch in the room above. You feel a\n"
                + "current pulling you to the east (r5).");

        // Level0Room 6:
        Level0Room r6 = new Level0Room();
        r6.setDesc("As your hands fumble in the darkness, an eel swims\n"
                + "past your face, and your lungs tighten as you hold in\n"
                + "a scream. The space is tiny, you can only go west\n"
                + "without smacking your face (r6).");

        // Level0Room 7:
        Level0Room r7 = new Level0Room();
        r7.setDesc("This room has nothing to offer. You got your clothes\n"
                + "soggy again for nothing (r7).\n");

        // Connect rooms 3, 4, 5, 6, & 7.
        r3.setSide(2, r4);
        r3.setSide(5, r5);
        r4.setSide(3, r3);
        r4.setSide(5, r7);
        r5.setSide(4, r3);
        r5.setSide(2, r6);
        r6.setSide(3, r5);
        r7.setSide(4, r4);

        // Level0Room 8:
        Level0Room r8 = new Level0Room();
        r8.setDesc("A bat flies past, but you like bats, so you are\n"
                + "completely fine with that. A narrow passage runs to the\n"
                + "east, and a very, very creepy crawl space runs to the\n"
                + "west. Your keen nose tells you that the cave entrance is\n"
                + "toward the south (r8).");

        // Level0Room 9:
        Level0Room r9 = new Level0Room();
        r9.setDesc("This room is entirely uninteresting and devoid of anything"
                + " useful. There is an opening to the east (r9).");

        // Level0Room 10:
        Level0Room r10 = new Level0Room();
        r10.setDesc("It looks like someone has been here.\n"
                + "There is a pile of Baby Ruth candy wrappers on the floor;\n"
                + "at least they have good taste. Wait! There is something\n"
                + "else? A trap door creaks under your feet,\n"
                + " there is a large keyhole in the door (r10).");

        // Level0Room 11:
        Level0Room r11 = new Level0Room();
        r11.setDesc("This room is very dark. You can just barely see (r11).");
        Treasure theTreasure = new Treasure();
        theTreasure.setDesc("A bag filled with gold bars.");
        r11.addItem(theTreasure);

        // Lets connect them:
        r4.setSide(0, r8);
        r8.setSide(1, r4);
        r8.setSide(3, r9);
        r8.setSide(2, r10);
        r9.setSide(2, r8);
        r10.setSide(3, r8);

        // Create a key and put it in r6:
        Key theKey = new Key();
        theKey.setDesc("A shiny gold key.");
        r6.addItem(theKey);

        // We add a door between r10 and r11: 
        Level0Door theDoor = new Level0Door(r10, r11, theKey);
        r10.setSide(5, theDoor);
        r11.setSide(4, theDoor);

        // Now return the entrance:
        entrance = outside;
        return (Level0Room) entrance;

    }
    
    /**
     * "Hard" difficulty abstract factory method
     */
    public Level1Room createLevel1Adventure() {
        // The outside: 
        Level1Room outside = new Level1Room();
        outside.setDesc(
                "You are standing at the edge of a desert cave. The stars\n"
                + "illuminate the sands around you but shed no light\n"
                + "past the mouth of the abyss at your feet. Something \n"
                + "compels you to descend, and will smack your face\n"
                + "if you do not. (outside).");

        // Level1Room 1:
        Level1Room r1 = new Level1Room();
        r1.setDesc(
                "The cave exit shines brightly above you, but all else is\n"
                + " dark, and you are glad to have your torch. It reveals a narrow,\n"
                + "dank passage to the east (r1).");

        // Connect the outside to Level1Room 1:
        outside.setSide(5, r1);
        r1.setSide(4, outside);
        entrance = outside;

        // Level1Room 2:
        Level1Room r2 = new Level1Room();
        r2.setDesc(
                "The entire room is flooded. You wade into the chest-high\n"
                + "water and see a dim light to the west; however, you see\n"
                + "a dark hole to the east only about 18 inches high (r2).");

        // Level1Room 3:
        Level1Room r3 = new Level1Room();
        r3.setDesc("The room is wide, and huge rocks block your path.\n"
                + "However, you expertly scale the rubble and find yourself\n"
                + "at the room's center. There is a rubble-filled corridor\n"
                + "to the west, another to the east, and a deep hole\n"
                + "that is completely flooded in the middle of the room (r3).");

        // Connect Level1Rooms 1, 2, & 3:
        r1.setSide(2, r2);
        r2.setSide(3, r1);
        r2.setSide(2, r3);
        r3.setSide(3, r2);

        // Level1Room 4:
        Level1Room r4 = new Level1Room();
        r4.setDesc("There are passages leading west and north. The sound of\n"
                + " dripping water echoes from a downward-slopping path (r4).");

        Item timmyTheRat = new Item();
        timmyTheRat.setDesc("An inexplicably adorable, yet lonely, rat. You named"
                + " him Timmy.");
        r4.addItem(timmyTheRat);

        // Level1Room 5:
        Level1Room r5 = new Level1Room();
        r5.setDesc("You hold your breath as you negotiate pitch black water,\n"
                + "having left your torch in the room above. You feel a\n"
                + "current pulling you to the east (r5).");

        // Level1Room 6:
        Level1Room r6 = new Level1Room();
        r6.setDesc("As your hands fumble in the darkness, an eel swims\n"
                + "past your face, and your lungs tighten as you hold in\n"
                + "a scream. The space is tiny, you can only go west\n"
                + "without smacking your face (r6).");

        // Level1Room 7:
        Level1Room r7 = new Level1Room();
        r7.setDesc("This room has nothing to offer. You got your clothes\n"
                + "soggy again for nothing (r7).\n");

        // Connect rooms 3, 4, 5, 6, & 7.
        r3.setSide(2, r4);
        r3.setSide(5, r5);
        r4.setSide(3, r3);
        r4.setSide(5, r7);
        r5.setSide(4, r3);
        r5.setSide(2, r6);
        r6.setSide(3, r5);
        r7.setSide(4, r4);

        // Level1Room 8:
        Level1Room r8 = new Level1Room();
        r8.setDesc("A bat flies past, but you like bats, so you are\n"
                + "completely fine with that. A narrow passage runs to the\n"
                + "east, and a very, very creepy crawl space runs to the\n"
                + "west. Your keen nose tells you that the cave entrance is\n"
                + "toward the south (r8).");

        // Level1Room 9:
        Level1Room r9 = new Level1Room();
        r9.setDesc("This room is entirely uninteresting and devoid of anything"
                + " useful. There is an opening to the east (r9).");

        // Level1Room 10:
        Level1Room r10 = new Level1Room();
        r10.setDesc("It looks like someone has been here.\n"
                + "There is a pile of Baby Ruth candy wrappers on the floor;\n"
                + "at least they have good taste. Wait! There is something\n"
                + "else? A trap door creaks under your feet,\n"
                + " there is a large keyhole in the door (r10).");

        // Level1Room 11:
        Level1Room r11 = new Level1Room();
        r11.setDesc("This room is very dark. You can just barely see (r11).");
        Treasure theTreasure = new Treasure();
        theTreasure.setDesc("A bag filled with gold bars.");
        r11.addItem(theTreasure);

        // Lets connect them:
        r4.setSide(0, r8);
        r8.setSide(1, r4);
        r8.setSide(3, r9);
        r8.setSide(2, r10);
        r9.setSide(2, r8);
        r10.setSide(3, r8);

        // Create a key and put it in r6:
        Key theKey = new Key();
        theKey.setDesc("A shiny gold key.");
        r6.addItem(theKey);

        // We add a door between r10 and r11: 
        Level1Door theDoor = new Level1Door(r10, r11, theKey);
        r10.setSide(5, theDoor);
        r11.setSide(4, theDoor);

        // Now return the entrance:
        entrance = outside;
        return (Level1Room) entrance;

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
    public Level0Room getCurrentRoom() {
        return (Level0Room) thePlayer.getLoc();
    }

    public ArrayList<Room> getRoomList(){
        return roomList;
    }
    
    public int getDifficulty(){
        return difficulty;
    }
}
