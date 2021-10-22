package Vending;

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
            // Instantiate the UserIO implementation
            UserIO myIo = new UserIOConsoleImpl();
            // Instantiate the View and wire the UserIO implementation into it
            VendingView myView = new VendingView(myIo);
            // Instantiate the DAO
            VendingDao myDao = new VendingDaoFileImpl();
            // Instantiate the Service Layer and wire the DAO
            VendingServiceLayer myService = new VendingServiceLayerImpl(myDao);

            Change myChange = new Change();
            // Instantiate the Controller and wire the Service Layer into it
            VendingController controller = new VendingController(myService, myView, myChange);
            // Kick off the Controller
            controller.run();
        }
}
