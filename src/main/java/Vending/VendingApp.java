package Vending;

import Vending.Dao.VendingAuditDAO;
import Vending.Dao.VendingAuditDaoFileImpl;
import Vending.Dao.VendingDao;
import Vending.Dao.VendingDaoFileImpl;
import Vending.Service.Change;
import Vending.Service.VendingServiceLayer;
import Vending.Service.VendingServiceLayerImpl;
import Vending.UI.UserIO;
import Vending.UI.UserIOConsoleImpl;
import Vending.UI.VendingView;
import Vending.controller.VendingController;

public class VendingApp {

        public static void main(String[] args) {
            UserIO myIo = new UserIOConsoleImpl();

            VendingView myView = new VendingView(myIo);

            VendingDao myDao = new VendingDaoFileImpl();

            VendingAuditDAO myAuditDao = new VendingAuditDaoFileImpl();

            VendingServiceLayer myService = new VendingServiceLayerImpl(myDao, myAuditDao);

            Change myChange = new Change();

            VendingController controller = new VendingController(myService, myView, myChange);

            controller.run();
        }
}
