package controller;


import java.util.List;

import utils.Util;
import classes.ConvocatoriaExamen;
import classes.Enunciado;
import classes.UnidadDidactica;
import exceptions.ExceptionManager;
import model.DAO;
import model.DAOFactory;
import model.DAOImplementationDB;
import model.DAOImplementationFich;
import view.View;
/**
 *
 * @author Janam
 */
public class Controller {


	public static void startApplication(DAO modelUDyEnun, DAO modelExamen, View view) throws ExceptionManager
	{
		Integer choosenMethod;

		do {
            choosenMethod = view.menuListadosEnunciado();
            
            switch (choosenMethod) {
                case 1:
                    createUnidadDidactica(modelUDyEnun);
                    break;

                case 2:
                    createEnunciado(modelUDyEnun, modelExamen, view);
                    break;

                case 3:
                    createConvocatoriaExamen(modelUDyEnun);
                    break;

                case 4:
                    ConsultEnunciadosUD(modelUDyEnun, view);
                    break;

                case 5:
                    ConsultConvocatoriasEnun(modelUDyEnun, view);
                    break;

				case 6:
                    VisualizeDocument(modelUDyEnun, view);
                    break;
            }
			choosenMethod = view.seguirPrograma();

        } while (choosenMethod != 1);
		view.terminarPrograma();
	}

    private static void VisualizeDocument(DAO model, View view) throws ExceptionManager {
		Enunciado enunciado = new Enunciado();
		enunciado.setId(view.askIdEnunciado());
		String path = model.getPathEnun(enunciado);
		if (path == null)
			view.cantFindPath();
		else
			view.visualizeDocument(path);	
	}

	private static void createUnidadDidactica(DAO model) throws ExceptionManager {
        UnidadDidactica unidadDidactica = new UnidadDidactica();
        unidadDidactica.setDatos(0);
        model.createUnidadDidactica(unidadDidactica);
    }

    private static void createEnunciado(DAO modelUDyEnun, DAO modelExamen, View view) throws ExceptionManager {
        Enunciado enunciado = new Enunciado();
		List <UnidadDidactica> unidadDidacticas = null;
		ConvocatoriaExamen convocatoriaExamen = new ConvocatoriaExamen();
        
		enunciado = view.setDatosEnun();
        unidadDidacticas = enunciado.getUnidadDidacticas();
		for (UnidadDidactica unidadDidactica : unidadDidacticas)
		{
			if (modelUDyEnun.ConsultUnidadDidactica(unidadDidactica) == false)
			{
				view.mostrarUnidadDidacticaNoExiste();
				return;
			}
		}
		Integer id_convocatoria = view.askConvocatoriaExamen();
		if (id_convocatoria != -1)
		{
			convocatoriaExamen.setId(id_convocatoria);
			if (modelExamen.ConsultConvocatoriaExamen(convocatoriaExamen) == false)
			{
				view.mostrarConvocatoriaExamenNoExiste();
				return;
			}
		}
		enunciado.setId(modelUDyEnun.createEnunciado(enunciado));
		if (id_convocatoria != -1)
			modelExamen.updateIdUEnunciadoExamen(enunciado, convocatoriaExamen);
		view.showIdEnunciado(enunciado.getId());
	}

    private static void createConvocatoriaExamen(DAO model) throws ExceptionManager {
        ConvocatoriaExamen convocatoriaExamen = new ConvocatoriaExamen();
        convocatoriaExamen.setDatos(0);
        model.createConvocatoriaExamen(convocatoriaExamen);
    }

    private static void ConsultEnunciadosUD(DAO model, View view) throws ExceptionManager {

        // consultar enunciados de una unidad didactica
        UnidadDidactica unidadDidactica = new UnidadDidactica();
        unidadDidactica.setId(view.getUserId("Write the Unidad Didactica's id you want"));
		if (model.ConsultUnidadDidactica(unidadDidactica) == true) {
        	List<Enunciado> enunciados = model.ConsultEnunciadosUD(unidadDidactica);
			view.mostrarEnunciados(enunciados);
		} else {
			view.mostrarUnidadDidacticaNoExiste();
		}
    }

    private static void ConsultConvocatoriasEnun(DAO model, View view) throws ExceptionManager {

        // consultar convocatorias de un enunciado
        Enunciado enunciado = new Enunciado();
		enunciado.setId(view.askIdEnunciado());
		List<ConvocatoriaExamen> convocatorias = model.ConsultConvocatoriasEnun(enunciado);
		view.mostrarConvocatorias(convocatorias);
    }

}
