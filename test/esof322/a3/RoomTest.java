/*
 * Test class for Room.java of ESOF322-AssignmentP2
 */
package esof322.a3;

import esof322.a4.Room;
import esof322.a4.Key;
import esof322.a4.Item;
import esof322.a4.Player;
import esof322.a4.Treasure;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Matthew Rohrlach
 */
public class RoomTest {
    
    public RoomTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addItem method, of class Room.
     */
    @Test
    public void testAddItem() {
        
        System.out.println("Testing: addItem() method");
        //Create objects
        Item rat = new Item();
        Treasure treasure = new Treasure();
        Key key = new Key();
        
        //Create room
        Room instance = new Room();
        
        //Add first item to empty room
        instance.addItem(rat);
        assertSame(rat,instance.getRoomContents()[0]);
        assertSame(1,instance.getRoomContents().length);
        
        //Add second item to room
        instance.addItem(key);
        assertSame(key,instance.getRoomContents()[1]);
        assertSame(rat,instance.getRoomContents()[0]);
        assertSame(2,instance.getRoomContents().length);
        
        //Add third item to room
        instance.addItem(treasure);
        assertSame(treasure,instance.getRoomContents()[2]);
        assertSame(key,instance.getRoomContents()[1]);
        assertSame(rat,instance.getRoomContents()[0]);
        assertSame(3,instance.getRoomContents().length);
    }

    /**
     * Test of removeItem method, of class Room.
     */
    @Test
    public void testRemoveItem() {
        System.out.println("Testing: removeItem() method");
        
        //Create objects
        Item rat = new Item();
        Treasure treasure = new Treasure();
        Key key = new Key();
        
        //Create room
        Room instance = new Room();
        
        //Add first item to empty room
        instance.addItem(rat);
        
        //Add second item to room
        instance.addItem(key);
        
        //Add third item to room
        instance.addItem(treasure);
        
        //Remove rat, assert location of key and treasure are in proper place
        instance.removeItem(rat);
        assertSame(key,instance.getRoomContents()[0]);
        assertSame(treasure,instance.getRoomContents()[1]);
        assertSame(2,instance.getRoomContents().length);
        
        //Remove treasure, assert location of key is in proper place
        instance.removeItem(treasure);
        assertSame(key,instance.getRoomContents()[0]);
        assertSame(1,instance.getRoomContents().length);
        
        //Remove key, assert that room is empty
        instance.removeItem(key);
        assertSame(0,instance.getRoomContents().length);
    }

    /**
     * Test of enter method, of class Room.
     */
    @Test
    public void testEnter() {
        System.out.println("Testing: enter() method");
        Player p = new Player();
        Room instance = new Room();
        instance.enter(p);
        assertSame(p.currentStatus(),"You successfully enter the room");
    }

    /**
     * Test of exit method, of class Room.
     */
    @Test
    public void testExit() {
        System.out.println("Testing: exit() method");
        
        //Run into wall
        int direction = 0;
        Player p = new Player();
        Room instance = new Room();
        instance.exit(direction, p);
        assertSame(p.currentStatus(),"Ow! My face.");
        
        //Run into room
        Room adjoining = new Room();
        instance.setSide(direction, adjoining);
        instance.exit(direction, p);
        assertSame(p.currentStatus(),"You successfully enter the room");
    }
}
