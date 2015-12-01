/**
 * @author Matt Rohrlach 
 */

/**@SummaryOfChanges
 * 
 */




package esof322.a4;

import java.util.Random;

/**
 * Adventure Game Program Code Copyright (c) 1999 James M. Bieman
 *
 * To compile: javac AdventureGame.java To run: java AdventureGame
 *
 * The main routine is AdventureGame.main
 *
 *
 */
public class Level1Wall extends Wall{
    String failure = "The wall morphs into a fearsome mouth!\n"
                    + "It swallows you whole.\n"
                    + "The world goes black...\n";
    
    @Override
    public void enter(Player p) {
        Random random = new Random();
        int randInt = random.nextInt(20);
        System.out.println(randInt);
        if (randInt > 4){
            p.setCurrentStatus(description);
            System.out.println("Ouch! That hurt.\n");
        }
        else{ //You get "wall-grabber'd"
            p.setCurrentStatus(failure);
            System.out.println("The wall morphs into a fearsome mouth!\n"
                    + "It swallows you whole.\n"
                    + "The world goes black...\n");
            p.setLoc(p.getStartLoc());
        }
    }   
}
