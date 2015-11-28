/*
 * Test class for Player.java of ESOF322-AssignmentP2
 */
package esof322.a4;

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
public class PlayerTest {
    
    public PlayerTest() {
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
     * Test of go method, of class Player.
     */
    @Test
    public void testGo() {
        System.out.println("Testing: go() method");
        //Test new room
        Room rTest = new Room();
        int direction = 0;
        Player instance = new Player();
        instance.setLoc(rTest);
        instance.go(direction);
        assertSame(instance.currentStatus(),"Ow! My face.");
    }

    /**
     * Test of pickUp method, of class Player.
     */
    @Test
    public void testPickUp() {
        System.out.println("Testing: pickUp() method");
        
        //Create items
        Key key = new Key();
        key.setDesc("Key");
        Treasure treasure = new Treasure();
        treasure.setDesc("Treasure");
        Item rat = new Item();
        rat.setDesc("Rat");
        
        //Add objects to room
        Room rTest = new Room();
        rTest.addItem(rat);
        rTest.addItem(treasure);
        rTest.addItem(key);
        
        Player instance = new Player();
        instance.setLoc(rTest);
        
        //No objects
        instance.pickUp(key);
        assertSame(key.getDesc(),(instance.getPlayerItemList())[0].getDesc());
        assertSame(instance.numItemsCarried(),1);
        
        //One pre-existing object
        instance.pickUp(treasure);
        assertSame(treasure.getDesc(),(instance.getPlayerItemList())
                [1].getDesc());
        assertSame(instance.numItemsCarried(),2);
        
        //Two pre-existing objects
        instance.pickUp(rat);
        assertSame(key.getDesc(),(instance.getPlayerItemList())[0].getDesc());
        assertSame(treasure.getDesc(),(instance.getPlayerItemList())
                [1].getDesc());
        assertSame(instance.numItemsCarried(),2);
    }

    /**
     * Test of drop method, of class Player.
     */
    @Test
    public void testDrop() {
        //Create items
        Key key = new Key();
        key.setDesc("Key");
        Treasure treasure = new Treasure();
        treasure.setDesc("Treasure");
        Item rat = new Item();
        rat.setDesc("Rat");
        
        //Add objects to room
        Room rTest = new Room();
        rTest.addItem(rat);
        rTest.addItem(treasure);
        rTest.addItem(key);
        
        Player instance = new Player();
        instance.setLoc(rTest);
        
        //No objects
        instance.pickUp(key);
        
        //One pre-existing object
        instance.pickUp(treasure);
        
        //Two pre-existing objects, rat is not picked up
        instance.pickUp(rat);
        
        //Drop first item, twice
        assertSame(instance.numItemsCarried(),2);
        String itemDesc = instance.getPlayerItemList()[1].getDesc();
        instance.drop(1);
        assertSame(instance.getPlayerItemList()[0].getDesc(),itemDesc);
        assertSame(instance.numItemsCarried(),1);
        instance.drop(1);
        assertSame(instance.numItemsCarried(),0);
        instance.drop(1);
        instance.drop(2);
        assertSame(instance.numItemsCarried(),0);
        
        instance.pickUp(key);
        instance.pickUp(treasure);
        
        //Attempt to drop second item, twice
        assertSame(instance.numItemsCarried(),2);
        itemDesc = instance.getPlayerItemList()[0].getDesc();
        instance.drop(2);
        assertSame(instance.getPlayerItemList()[0].getDesc(),itemDesc);
        assertSame(instance.numItemsCarried(),1);
        instance.drop(2);
        assertSame(instance.numItemsCarried(),1);
        
        //Drop from non-existent third hand
        instance.drop(3);
        assertSame(instance.numItemsCarried(),1);
        
        //Drop from non-existent negative hand
        instance.drop(-1);
        assertSame(instance.numItemsCarried(),1);
        
        //It is impossible for final branch of switch to be hit. Coverage is 
        //as complete as possible.
    }
}
