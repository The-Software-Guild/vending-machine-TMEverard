package Vending.Dao;


import Vending.DTO.Item;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class VendingDaoFileImpl implements VendingDao{

    public static String STOCK_FILE;

    public static final String DELIMITER = "::";

    public VendingDaoFileImpl(){
        STOCK_FILE = "VendingStock";
    }

    public VendingDaoFileImpl(String stockTextFile){
        STOCK_FILE = stockTextFile;
    }

    private Map<String, Item> items = new HashMap<>();

    @Override
    public List<Item> getAllItems()  throws VendingPersistenceException {
        loadStock();
        return new ArrayList(items.values());
    }

    @Override
    public Item getItem(String name)  throws VendingPersistenceException {
        loadStock();
        return items.get(name);
    }

    @Override
    public void updateStock(String name, Item item)  throws VendingPersistenceException {
        loadStock();
        items.get(name).setNumberOf(items.get(name).getNumberOf()-1);
        writeStock();
    }

    private Item unmarshallItem(String itemAsText) {
        String[] itemTokens = itemAsText.split(DELIMITER);
        String name = itemTokens[0];
        BigDecimal dec = new BigDecimal(itemTokens[1]);
        Item itemFromFile = new Item(name,dec,Integer.parseInt(itemTokens[2]));

        return itemFromFile;
    }

    private void loadStock() throws VendingPersistenceException {
        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(STOCK_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingPersistenceException(
                    "Could not load VendingStock.txt data", e);
        }
        String currentLine;
        Item currentItem;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentItem = unmarshallItem(currentLine);
            items.put(currentItem.getName(), currentItem);
        }
        scanner.close();
    }

    private String marshallItem(Item itemIn){
        String itemAsText = itemIn.getName() + DELIMITER;
        itemAsText += itemIn.getCost() + DELIMITER;
        itemAsText += itemIn.getNumberOf();
        return itemAsText;
    }

    private void writeStock() throws VendingPersistenceException {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(STOCK_FILE));
        } catch (IOException e) {
            throw new VendingPersistenceException(
                    "Could not save Vending Stock data to the file.", e);
        }
        String itemAsText;
        List<Item> itemList = this.getAllItems();
        for (Item currentItem : itemList) {
            itemAsText = marshallItem(currentItem);
            out.println(itemAsText);
            out.flush();
        }
        out.close();
    }
}
