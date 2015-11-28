/**
 * @author Andrew Jenkins, Stephanie McLaren, David Schwehr,
 * Kay Spokas, Matt Rohrlach 
 */

/**@SummaryOfChanges
 *  Edited output for when player walks into wall
 *  Added method call to set player status when player walks into wall
 */




package esof322.a4;

/**
 * Adventure Game Program Code Copyright (c) 1999 James M. Bieman
 *
 * To compile: javac AdventureGame.java To run: java AdventureGame
 *
 * The main routine is AdventureGame.main
 *
 *
 */
public class Wall implements CaveSite {
    String description = "Ow! My face.";
    
    public void enter(Player p) {
        p.setCurrentStatus(description);
        System.out.println("Ouch! That hurts.\n");
    }   
}
