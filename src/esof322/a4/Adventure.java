/**
 * @author Andrew Jenkins, Stephanie McLaren, David Schwehr,
 * Kay Spokas, Matt Rohrlach
 * 
 * @SummaryOfChanges
 *  Changed room descriptions to match AdventureGameModelFacade
 */



package esof322.a4;


/**  Adventure Game  Program Code
     Copyright (c) 1999 James M. Bieman

     To compile: javac AdventureGame.java
     To run:     java AdventureGame

     The main routine is AdventureGame.main
                
**/

/**  Adventure Game  Program Code
Copyright (c) 1999-2012 James M. Bieman
The Adventure game is based on the "Colossal Cave Adventure" originally
designed by Will Crowther and implemented by Will Crowther
and Don Wood in Fortran in 1975 and 1976.

This micro-version is a variant of the original cave system and is implemented in Java
with just a few rooms and with a much more limited vocabulary.

Updated August 2010, January 2012
- Code is put into package cs314.a2 to match current CS314 coding standards.
Updated January 2012
- Renamed as the "Adventure Game"

To compile: javac cs314.a2.AdventureGame.java
To run:     java cs314.a2.AdventureGame

The main routine is AdventureGame.main
			    
			    **/

/** class Adventure: Primary method, createCave, creates the cave system.
        It eventually be replaced with a more flexible mechanism
        to support input and output from devices other than
        an ASCII terminal.

		Room descriptions are followed by a room identifier,
		to ease debugging and testing.  These would be removed
		to help confuse the user, which is our ultimate aim.

		I haven't added all of the room descriptions.  They
		will be done later.
		
		In this version all I/O is through standard I/O;
		I/O is to and from the console. 

*/

public class Adventure {

  private Room entrance;
  
  public Room createAdventure(){
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
                + " dripping water echoes from a downward-slopping path.");

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
                + "without smacking your face (r7).");

        // Room 7:
        Room r7 = new Room();
        r7.setDesc("This room has nothing to offer. You got your clothes\n"
                + "soggy again for nothing(r7).\n");

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
                + " useful. There is an opening to the east. (r9).");

        // Room 10:
        Room r10 = new Room();
        r10.setDesc("It looks like someone has been here.\n"
                + "There is a pile of Baby Ruth candy wrappers on the floor;\n"
                + "at least they have good taste. Wait! There is something\n"
                + "else? A trap door creaks under your feet,\n"
                + " there is a large keyhole in the door(r10).");

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
}
