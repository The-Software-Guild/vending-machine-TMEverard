package Vending.Dao;

import Vending.DTO.Item;
import org.junit.jupiter.api.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

public class VendingDaoFileImplTest {
    public VendingDaoFileImplTest() {
    }

    VendingDao testDao;

    @BeforeEach
    public void setUp() throws Exception{
        String testFile = "testStock.txt";
        // Use the FileWriter to quickly blank the file
        new FileWriter(testFile);
        PrintWriter out;
            try {
                out = new PrintWriter(new FileWriter(testFile));
            } catch (IOException e) {
                throw new VendingPersistenceException(
                        "Could not save Vending Stock data to the file.", e);
            }
            out.println("Snickers::1.99::9");
            out.println("Bounty::1.49::0");
            out.println("Mars Bar::2.49::7");
            out.flush();
            out.close();
        testDao = new VendingDaoFileImpl(testFile);
    }
    @AfterEach
    public void cleanUp() throws Exception{
        String testFile = "testStock.txt";
         //Use the FileWriter to quickly blank the file
        new FileWriter(testFile);
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(testFile));
        } catch (IOException e) {
            throw new VendingPersistenceException(
                    "Could not save Vending Stock data to the file.", e);
        }
        out.println("Snickers::1.99::9");
        out.println("Bounty::1.49::0");
        out.println("Mars Bar::2.49::7");
        out.flush();
        out.close();
        testDao = new VendingDaoFileImpl(testFile);
    }

    @Test
    public void testGetItem() throws Exception {
        String name = "Mars Bar";
        BigDecimal cost = new BigDecimal("2.49");
        int numberOf = 7;
        Item setItem = new Item(name, cost, numberOf);

        Item retrievedItem = testDao.getItem(name);

        // Check the data is equal
        assertEquals(setItem.getName(),
                retrievedItem.getName(),
                "Checking name");
        assertEquals(setItem.getCost(),
                retrievedItem.getCost(),
                "Checking cost.");
        assertEquals(setItem.getNumberOf(),
                retrievedItem.getNumberOf(),
                "Checking Stock number.");
    }

    @Test
    public void testGetAllItems() throws Exception {
        // Create our first item
        BigDecimal cost = new BigDecimal("2.49");
        Item setItem1 = new Item("Mars Bar", cost, 7);

        // Create our second item
        BigDecimal cost2 = new BigDecimal("1.49");
        Item setItem2 = new Item("Bounty", cost2, 0);

        BigDecimal cost3 = new BigDecimal("1.49");
        Item setItem3 = new Item("Snickers", cost3, 9);

        // Retrieve the list of all students within the DAO
        List<Item> allItems = testDao.getAllItems();

        // First check the general contents of the list
        Assertions.assertNotNull(allItems, "The list of items must not null");
        assertEquals(3, allItems.size(),"List of items should have 3 items.");

        // Then the specifics
        assertTrue(testDao.getAllItems().contains(setItem1),
                "The list of students should include Mars Bar.");
        assertTrue(testDao.getAllItems().contains(setItem2),
                "The list of students should include Bounty.");
    }

    @Test
    public void testUpdateStock() throws VendingPersistenceException {
        Item toUpdate = testDao.getItem("Snickers");
        testDao.updateStock(toUpdate.getName(), toUpdate);
        Item updated = testDao.getItem("Snickers");
        assertEquals(toUpdate.getNumberOf()-1,updated.getNumberOf());
    }



}
