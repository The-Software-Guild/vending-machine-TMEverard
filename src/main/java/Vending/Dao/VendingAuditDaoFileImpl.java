package Vending.Dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class VendingAuditDaoFileImpl implements VendingAuditDAO {

        public static final String AUDIT_FILE = "auditVending.txt";

        public void writeAuditEntry(String entry) throws VendingPersistenceException {
            PrintWriter out;

            try {
                out = new PrintWriter(new FileWriter(AUDIT_FILE, true)); //In append mode so only adds
                // not overwrites
            } catch (IOException e) {
                throw new VendingPersistenceException("Could not persist audit information.", e);
            }

            LocalDateTime timestamp = LocalDateTime.now();
            out.println(timestamp.toString() + " : " + entry);
            out.flush();
        }

}
