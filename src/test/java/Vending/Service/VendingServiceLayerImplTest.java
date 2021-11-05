package Vending.Service;

import Vending.DTO.Item;
import Vending.Dao.VendingAuditDAO;
import Vending.Dao.VendingDao;
import Vending.Dao.VendingPersistenceException;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;


public class VendingServiceLayerImplTest {

    private VendingServiceLayer service;

    public VendingServiceLayerImplTest() {
        VendingDao dao = new VendingDaoStubImpl();
        VendingAuditDAO auditDao = new VendingAuditDaoStubImpl();
        service = new VendingServiceLayerImpl(dao, auditDao);
    }

    @Test
    public void testGetAllStudents() throws Exception {
        // ARRANGE
        Item testClone = new Item("Test",new BigDecimal("3.5"),4);

        // ACT & ASSERT
        assertEquals( 1, service.getAllItems().size(),
                "Should only have one item.");
        assertTrue( service.getAllItems().contains(testClone),
                "The one item should be test.");
    }
    @Test
    public void testGetItem() throws Exception {
        // ARRANGE
        Item testClone = new Item("Test",new BigDecimal("3.5"),4);
        // ACT & ASSERT
        Item shouldBeTest = service.getItem("Test");
        assertNotNull(shouldBeTest, "Getting Test should be not null.");
        assertEquals( testClone, shouldBeTest, "item stored under test should be Test.");
    }
    @Test
    public void testOutOfStock() throws VendingPersistenceException, OutOfStockException {
        try {
            Item tester = new Item("Bounty", new BigDecimal("2.99"), 0);
            service.updateStock("Bounty", tester);
            fail("No exception thrown");
        }catch(OutOfStockException e){
                return;
        }catch(VendingPersistenceException e){
            fail("Wrong exception");
        }
        };


}