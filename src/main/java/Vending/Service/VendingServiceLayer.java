package Vending.Service;


import Vending.DTO.Item;
import Vending.Dao.VendingPersistenceException;

import java.util.List;

public interface VendingServiceLayer {

        List<Item> getAllItems() throws
                VendingPersistenceException;

        Item getItem(String name) throws VendingPersistenceException, VendingDataValidationException, OutOfStockException;

        void updateStock(String name, Item newVersion) throws
                VendingPersistenceException, OutOfStockException;
}
