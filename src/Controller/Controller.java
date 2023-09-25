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
                    ConsultUnidadDidactica(modelUDyEnun);
                    break;

                case 5:
                    ConsultEnunciadosUD(modelUDyEnun, view);
                    break;

                // case 6:
                //     ConsultConvocatoriaExamen(modelExamen, view);
                //     break;

                case 7:
                    ConsultConvocatoriasEnun(modelUDyEnun, view);
                    break;

				case 8:
                    VisualizeDocument(modelUDyEnun, view);
                    break;
            }
			choosenMethod = view.seguirPrograma();

        } while (choosenMethod != 1);
		view.terminarPrograma();
	}

    private static void VisualizeDocument(DAO modelUDyEnun, View view) {
		Enunciado enunciado = new Enunciado();
		enunciado.setId(view.askIdEnunciado());

		

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
	}

    private static void createConvocatoriaExamen(DAO model) throws ExceptionManager {
        ConvocatoriaExamen convocatoriaExamen = new ConvocatoriaExamen();
        convocatoriaExamen.setDatos(0);
        model.createConvocatoriaExamen(convocatoriaExamen);
    }

    private static void ConsultUnidadDidactica(DAO model) throws ExceptionManager {
        // consultar unidad didactica
        UnidadDidactica unidadDidactica = new UnidadDidactica();
        //unidadDidactica.setDatos(0);
        model.ConsultUnidadDidactica(unidadDidactica);

    }

	// private static ConvocatoriaExamen ConsultConvocatoriaExamen(DAO model, View view) throws ExceptionManager {
    //     // consultar convocatoria de examen
    //     ConvocatoriaExamen convocatoriaExamen = new ConvocatoriaExamen();
    //     //convocatoriaExamen.setDatos(0);
    //     model.ConsultConvocatoriaExamen(convocatoriaExamen);

    // }

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
		enunciado.setId(view.getUserId("Write the Enunciado's id you want"));
		List<ConvocatoriaExamen> convocatorias = model.ConsultConvocatoriasEnun(enunciado);
		view.mostrarConvocatorias(convocatorias);
    }

    // Visualizar el documento de texto asociado a un enunciado. 
    private static void VisualizarDocumentoEnunciado(DAO model, View view) throws ExceptionManager {
		//view.AskEnunciado();
    }

    // public void run() throws ExceptionManager {

    //     int opt;
	// 	DAO modelDB = new getDAO("DB");
	// 	DAO modelFich = new getDAO("DB");
        
    //     do {
    //         opt = menuListadosEnunciado();
            
    //         switch (opt) {
    //             case 1:
    //                 createUnidadDidactica(model);
    //                 break;

    //             case 2:
    //                 createEnunciado(model);
    //                 break;

    //             case 3:
    //                 createConvocatoriaExamen(model);
    //                 break;

    //             case 4:
    //                 ConsultUnidadDidactica(model);
    //                 break;

    //             case 5:
    //                 ConsultEnunciadosUD(model, View);
    //                 break;

    //             case 6:
    //                 ConsultConvocatoriaExamen(model);
    //                 break;

    //             case 7:
    //                 ConsultConvocatoriasUD(model, View);
    //                 break;


    //         }

    //     } while (opt != 7);
    // }

}
