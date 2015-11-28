package esof322.a3;

/*
 * Driver of all test classes in Assignment 3
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Matthew Rohrlach
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    esof322.a3.Level0DoorTest.class,
    esof322.a3.PlayerTest.class,
    esof322.a3.RoomTest.class
})
public class AllDriver {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
