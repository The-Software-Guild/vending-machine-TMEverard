package Vending.Service;

import Vending.Dao.VendingAuditDAO;
import Vending.Dao.VendingPersistenceException;

public class VendingAuditDaoStubImpl implements VendingAuditDAO {

    @Override
    public void writeAuditEntry(String entry) throws VendingPersistenceException {
        //do nothing . . .
    }
}