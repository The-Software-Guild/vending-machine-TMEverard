package Vending.Dao;

import Vending.DTO.Item;

import java.util.List;

public interface VendingDao {

        Item getItem(String name) throws VendingPersistenceException;

        List<Item> getAllItems() throws VendingPersistenceException;

        void updateStock(String name, Item item) throws VendingPersistenceException;

}
