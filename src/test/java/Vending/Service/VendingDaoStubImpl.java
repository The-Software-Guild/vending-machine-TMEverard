package Vending.Service;

import Vending.DTO.Item;
import Vending.Dao.VendingDao;
import Vending.Dao.VendingPersistenceException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class VendingDaoStubImpl implements VendingDao {

    public Item onlyItem;

    public VendingDaoStubImpl() {
        onlyItem = new Item("Test",new BigDecimal("3.5"),4);
    }

    public VendingDaoStubImpl(Item testItem){

        this.onlyItem = testItem;
    }

    @Override
    public List<Item> getAllItems()
            throws VendingPersistenceException {
        List<Item> itemList = new ArrayList<>();
        itemList.add(onlyItem);
        return itemList;
    }

    @Override
    public Item getItem(String name)
            throws VendingPersistenceException {
        if (name.equals(onlyItem.getName())) {
            return onlyItem;
        } else {
            return null;
        }
    }

    @Override
    public void updateStock(String name, Item item){

        onlyItem.setNumberOf(onlyItem.getNumberOf()-1);
    }
}