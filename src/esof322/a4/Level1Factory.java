/*
 * Level 1 Abstract Factory
 */
package esof322.a4;

/**
 *
 * @author Matthew Rohrlach
 */
public class Level1Factory {
    private Room entrance;
    
    public Level1Factory(){
        // The outside: 
        Level1Room outside = new Level1Room();
        outside.setDesc(
                "DIFFICULTY 1: You are standing in the foyer of a desert tower.\n"
                + "The stars illuminate the sands around you but shed no light\n"
                + "past the mouth of the abyss at your feet. Something \n"
                + "compels you to descend (outside).");

        // Level1Room 1:
        Level1Room r1 = new Level1Room();
        r1.setDesc(
                "The cave exit shines brightly above you, but all else is\n"
                + " dark, and you are glad to have your torch. It reveals a narrow,\n"
                + "dark passage to the east (r1).");

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
        
    }
    
    public Level1Room getEntrance(){
        return (Level1Room) entrance;
    }
}
