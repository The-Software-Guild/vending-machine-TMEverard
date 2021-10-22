package Vending.Dao;

public interface VendingAuditDAO {

        public void writeAuditEntry(String entry) throws VendingPersistenceException;

    }
