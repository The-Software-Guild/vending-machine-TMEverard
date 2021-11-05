package Vending.Service;

import Vending.Dao.VendingPersistenceException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ChangeTest {
    public Change change;

    public ChangeTest() {
        change = new Change();
    }

    @Test
    public void testChangeGiven() throws VendingPersistenceException, InsufficientFundsException {
        BigDecimal funds = new BigDecimal("34.4");
        BigDecimal price = new BigDecimal("1.99");
        BigDecimal amount = change.changeGiven(funds, price);
        BigDecimal correctChange = new BigDecimal("32.41");
        assertEquals(amount, correctChange);
    }

    @Test
    public void testCoinsGiven() {
        BigDecimal changeToGive = new BigDecimal("5.73");
        ArrayList<BigDecimal> coinsGiven = change.coinsGiven(changeToGive);
        ArrayList<BigDecimal> correctCoins = new ArrayList<BigDecimal>();
        correctCoins.add(0, new BigDecimal(2));
        correctCoins.add(1, new BigDecimal(1));
        correctCoins.add(2, new BigDecimal(1));
        correctCoins.add(3, new BigDecimal(1));
        correctCoins.add(4, new BigDecimal(0));
        correctCoins.add(5, new BigDecimal(0));
        correctCoins.add(6, new BigDecimal(1));
        correctCoins.add(7, new BigDecimal(1));
        assertEquals(coinsGiven, correctCoins);
    }

    @Test
    public void testInsufficientFunds() throws VendingPersistenceException, InsufficientFundsException {
        BigDecimal testFunds = new BigDecimal("0.3");
        BigDecimal testPrice = new BigDecimal("50");
        try {
            BigDecimal money = change.changeGiven(testFunds, testPrice);
            fail("Doesn't throw needed insufficient funds exception");
        } catch (InsufficientFundsException e) {
            return;
        } catch (VendingPersistenceException e) {
            fail("Incorrect exception thrown");
        }
    }
}