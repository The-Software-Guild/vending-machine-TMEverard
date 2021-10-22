package Vending.Service;

import Vending.DTO.Item;
import Vending.Dao.VendingDao;
import Vending.Dao.VendingPersistenceException;

import java.util.List;

public class VendingServiceLayerImpl implements VendingServiceLayer{
    VendingDao dao;
    public VendingServiceLayerImpl(VendingDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Item> getAllItems() throws
        VendingPersistenceException {
            return dao.getAllItems();
            //Pass-through
    };

    @Override
    public Item getItem(String name) throws
        VendingPersistenceException {
            return dao.getItem(name);
            //Pass through
    };

    @Override
    public void updateStock(String name, Item item) throws VendingPersistenceException {
            dao.updateStock(name, item);
            //Pass through
    };

}
