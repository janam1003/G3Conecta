package application;

//import controller.Controller;
import exceptions.ExceptionManager;
import	model.DAO;
import model.DAOFactory;
import model.DAOImplementationDB;
import model.DAOImplementationFich;
import classes.UnidadDidactica;
import classes.ConvocatoriaExamen;
import classes.Enunciado;
import controller.Controller;
import view.View;
import view.ViewFactory;

//import view.View;
//import view.ViewFactory;

/**
 *
 * @author Iñigo
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws ExceptionManager
     */
    public static void main(String[] args) throws ExceptionManager {
		/* DAO modelExamen = DAOFactory.getModel("Examen");
		 DAO modelUDyEnun = DAOFactory.getModel("UDyEnun");
		 View view = ViewFactory.getView();
		 Controller.startApplication(modelUDyEnun, modelExamen, view); */
                
                   DAOImplementationFich model = new  DAOImplementationFich();
                   ConvocatoriaExamen convocatoriaExamen= new ConvocatoriaExamen();
                   convocatoriaExamen.setDatos();
                   model.createConvocatoriaExamen(convocatoriaExamen);
                
                
                
	}
}

