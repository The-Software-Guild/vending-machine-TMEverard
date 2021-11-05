package Vending.Service;

import Vending.Dao.VendingDao;
import Vending.Dao.VendingPersistenceException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Change {
    private VendingDao dao;
    private VendingServiceLayer service;
    public Change() {
        }

    public BigDecimal changeGiven(BigDecimal funds, BigDecimal price) throws VendingPersistenceException, InsufficientFundsException {
        BigDecimal changeGiven;
        if (price.compareTo(funds) > 0) {
            throw new InsufficientFundsException("Insufficient funds!");
        } else {
        changeGiven = funds.subtract(price);
        }
        return changeGiven;
    }

    BigDecimal val1 = new BigDecimal("2");

    BigDecimal val2 = new BigDecimal("1");

    BigDecimal val3 = new BigDecimal("0.5");

    BigDecimal val4 = new BigDecimal("0.2");

    BigDecimal val5 = new BigDecimal("0.1");

    BigDecimal val6 = new BigDecimal("0.05");

    BigDecimal val7 = new BigDecimal("0.02");

    BigDecimal val8 = new BigDecimal("0.01");

    List<BigDecimal> denoms = List.of(new BigDecimal[]{val1, val2, val3, val4, val5, val6, val7, val8});

    public ArrayList coinsGiven(BigDecimal changeGiven){
        ArrayList<BigDecimal> change = new ArrayList<>();
        //Hard coded because unlikely new coins made
        BigDecimal value = changeGiven;
        int i = 0;
        System.out.println("Change total: " + changeGiven);
        for (Coins coin : Coins.values()) {
            BigDecimal number = value.divide(denoms.get(i), 0,RoundingMode.FLOOR);
            change.add(number);
            BigDecimal result = number.multiply(denoms.get(i));
            value = value.subtract(result);
            i++;
        }
        return change;
    };

}
