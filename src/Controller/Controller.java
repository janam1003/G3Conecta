package Controller;

import java.util.List;

import javax.swing.text.View;

import Classes.ConvocatoriaExamen;
import Classes.Enunciado;
import Classes.UnidadDidactica;
import Exceptions.ExceptionManager;
import Model.DAO;
import utils.Util;

/**
 *
 * @author Janam
 */
public class Controller {

    private static void createUnidadDidactica(DAO model) throws ExceptionManager {
        UnidadDidactica unidadDidactica = new UnidadDidactica();
        unidadDidactica.setDatos(0);
        model.createUnidadDidactica(unidadDidactica);
    }

    private static void createEnunciado(DAO model) throws ExceptionManager {
        Enunciado enunciado = new Enunciado();
        enunciado.setDatos(0);
        model.createEnunciado(enunciado);
    }

    private static void createConvocatoriaExamen(DAO model) throws ExceptionManager {
        ConvocatoriaExamen convocatoriaExamen = new ConvocatoriaExamen();
        convocatoriaExamen.setDatos(0);
        model.createConvocatoriaExamen(convocatoriaExamen);
    }

    private static void ConsultUnidadDidactica(DAO model) throws ExceptionManager {
        // consultar unidad didactica
        UnidadDidactica unidadDidactica = new UnidadDidactica();
        unidadDidactica.setDatos(0);
        model.ConsultUnidadDidactica(unidadDidactica);

    }

    private static void ConsultEnunciadosUD(DAO model, View view) throws ExceptionManager {

        // consultar enunciados de una unidad didactica
        UnidadDidactica unidadDidactica = new UnidadDidactica();
        unidadDidactica.setDatos(0);
		if (model.ConsultUnidadDidactica(unidadDidactica) == true) {
        	List<Enunciado> enunciados = model.ConsultEnunciadosUD(unidadDidactica);
			view.mostrarEnunciados(enunciados);
		} else {
			view.mostrarUnidadDidacticaNoExiste();
		}
    }

    private static void ConsultConvocatoriaExamen(DAO model) throws ExceptionManager {

        // consultar convocatoria de examen
        ConvocatoriaExamen convocatoriaExamen = new ConvocatoriaExamen();
        convocatoriaExamen.setDatos(0);
        model.ConsultConvocatoriaExamen(convocatoriaExamen);

    }

    private static void ConsultConvocatoriasUD(DAO model, View view) throws ExceptionManager {

        // consultar convocatorias de una unidad didactica
        UnidadDidactica unidadDidactica = new UnidadDidactica();
        unidadDidactica.setDatos(0);

		if (model.ConsultUnidadDidactica(unidadDidactica) == true) {
			List<ConvocatoriaExamen> convocatorias = model.ConsultConvocatoriasUD(unidadDidactica);
			view.mostrarConvocatorias(convocatorias);
		} else {
			view.mostrarUnidadDidacticaNoExiste();
		}
    }

    // Visualizar el documento de texto asociado a un enunciado. 
    private static void VisualizarDocumentoEnunciado(Enunciado enunciado) throws ExceptionManager {

    }

    public void run(DAO model, View view) throws ExceptionManager {

        int opt;
        
        do {
            opt = menuListadosEnunciado();
            
            switch (opt) {
                case 1:
                    createUnidadDidactica(model);
                    break;

                case 2:
                    createEnunciado(model);
                    break;

                case 3:
                    createConvocatoriaExamen(model);
                    break;

                case 4:
                    ConsultUnidadDidactica(model);
                    break;

                case 5:
                    ConsultEnunciadosUD(model, view);
                    break;

                case 6:
                    ConsultConvocatoriaExamen(model);
                    break;

                case 7:
                    ConsultConvocatoriasUD(model, view);
                    break;


            }

        } while (opt != 7);
    }

    private static int menuListadosEnunciado() {

        System.out.println("1: Create UnidadDidactica.\n\t"
                + "2: Create Enunciado.\n\t"
                + "3: Create ConvocatoriaExamen.\n\t"
                + "4: Consult UnidadDidactica.\n\t"
                + "5: Consult EnunciadosUD.\n\t"
                + "6: Consult ConvocatoriaExamen.\n\t"
                + "7: Consult ConvocatoriasUD.\n\t");
        return Util.leerInt("Introduce un número del 1 al 7: ", 1, 7);
    }

}
