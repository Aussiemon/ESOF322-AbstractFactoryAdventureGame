/**
 * @author Andrew Jenkins, Stephanie McLaren, David Schwehr,
 * Kay Spokas, Matt Rohrlach
 */

/**
 * @SummaryOfChanges
 *  Added createAdventure method to build cave rooms, key, treasure(gold),
 *      and treasure (Timmy the rat)
 *  Created player object, and called createAdventure in
 *      AdventureGameModelFacade()
 *  Connected movement methods to GUI
 *  Finished getView and getItems methods and connected them to GUI
 *  Added several methods to account for player status, number of items carried,
 *      adding items, removing items, and determining the room that is currently
 *      being visited. See lines (203-277) for more information
 */
package esof322.a4;

public class AdventureGameModelFacade {

    // some private fields to reference current location,
    // its description, what I'm carrying, etc.
    //
    // These methods and fields are left as exercises.
    private Room entrance;
    private Player thePlayer;

    public Room createAdventure() {
        // The outside: 
        Room outside = new Room();
        outside.setDesc(
                "You are standing at the edge of a desert cave. The stars\n"
                + "illuminate the sands around you but shed no light\n"
                + "past the mouth of the abyss at your feet. Something \n"
                + "compells you to descend, and will smack your face\n"
                + "if you do not. (outside).");

        // Room 1:
        Room r1 = new Room();
        r1.setDesc(
                "The cave exit shines brightly above you, but all else is\n"
                + " dark, and you are glad to have your torch. It reveals a narrow,\n"
                + "dank passage to the east (r1).");

        // Connect the outside to Room 1:
        outside.setSide(5, r1);
        r1.setSide(4, outside);
        entrance = outside;

        // Room 2:
        Room r2 = new Room();
        r2.setDesc(
                "The entire room is flooded. You wade into the chest-high\n"
                + "water and see a dim light to the west; however, you see\n"
                + "a dark hole to the east only about 18 inches high (r2).");

        // Room 3:
        Room r3 = new Room();
        r3.setDesc("The room is wide, and huge rocks block your path.\n"
                + "However, you expertly scale the rubble and find yourself\n"
                + "at the room's center. There is a rubble-filled corridor\n"
                + "to the west, another to the east, and a deep hole\n"
                + "that is completely flooded in the middle of the room (r3).");

        // Connect Rooms 1, 2, & 3:
        r1.setSide(2, r2);
        r2.setSide(3, r1);
        r2.setSide(2, r3);
        r3.setSide(3, r2);

        // Room 4:
        Room r4 = new Room();
        r4.setDesc("There are passages leading west and north. The sound of\n"
                + " dripping water echoes from a downward-slopping path (r4).");

        Treasure timmyTheRat = new Treasure();
        timmyTheRat.setDesc("An inexplicably adorable, yet lonely, rat. You named"
                + " him Timmy.");
        r4.addItem(timmyTheRat);

        // Room 5:
        Room r5 = new Room();
        r5.setDesc("You hold your breath as you negotiate pitch black water,\n"
                + "having left your torch in the room above. You feel a\n"
                + "current pulling you to the east (r5).");

        // Room 6:
        Room r6 = new Room();
        r6.setDesc("As your hands fumble in the darkness, an eel swims\n"
                + "past your face, and your lungs tighten as you hold in\n"
                + "a scream. The space is tiny, you can only go west\n"
                + "without smacking your face (r6).");

        // Room 7:
        Room r7 = new Room();
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

        // Room 8:
        Room r8 = new Room();
        r8.setDesc("A bat flies past, but you like bats, so you are\n"
                + "completely fine with that. A narrow passage runs to the\n"
                + "east, and a very, very creepy crawl space runs to the\n"
                + "west. Your keen nose tells you that the cave entrance is\n"
                + "toward the south (r8).");

        // Room 9:
        Room r9 = new Room();
        r9.setDesc("This room is entirely uninteresting and devoid of anything"
                + " useful. There is an opening to the east (r9).");

        // Room 10:
        Room r10 = new Room();
        r10.setDesc("It looks like someone has been here.\n"
                + "There is a pile of Baby Ruth candy wrappers on the floor;\n"
                + "at least they have good taste. Wait! There is something\n"
                + "else? A trap door creaks under your feet,\n"
                + " there is a large keyhole in the door (r10).");

        // Room 11:
        Room r11 = new Room();
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
        return entrance;

    }

    AdventureGameModelFacade() { // we initialize
        thePlayer = new Player();
        Room startRm = createAdventure();
        thePlayer.setRoom(startRm);
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

 // Surely you will need other methods to deal with
    // picking up and dropping things.
}
