package view;

import java.util.List;

import classes.ConvocatoriaExamen;
import classes.Enunciado;
import utils.Util;

/**
 *
 * @author Janam
 */
public class ViewTerminal implements View {

    public void mostrarConvocatorias(List<ConvocatoriaExamen> convocatorias) {
        System.out.println("\nLista de convocatorias:");
        for (ConvocatoriaExamen convocatoria : convocatorias) {
            System.out.println(convocatoria.toString());
        }
    }

	public void mostrarEnunciados(List<Enunciado> enunciados) {
        System.out.println("\nLista de enunciados:");
        for (Enunciado enunciado : enunciados) {
            System.out.println(enunciado.toString());
        }
    }

	public int menuListadosEnunciado() {
        System.out.println("\t1: Create UnidadDidactica.\n\t"
                + "2: Create Enunciado.\n\t"
                + "3: Create ConvocatoriaExamen.\n\t"
                + "4: Consult UnidadDidactica.\n\t"
                + "5: Consult Enunciados with specefic Unidad Didactica.\n\t"
                + "6: Consult ConvocatoriaExamen.\n\t"
                + "7: Consult Convocatorias with specefic Enunciado.\n\t"
				+ "8: Visualize document with specific Enunciado.\n\t"
				+ "9: Exit.");
        return Util.leerInt("Introduce un número del 1 al 9: ", 1, 9);
    }

	public int seguirPrograma() {
		return Util.leerInt("Write 0 to continue with the program or 1 to finish it", 0, 1);
	}

	public void terminarPrograma() {
		System.out.println("Program finished");
	}

	public int getUserId(String message) {
		return Util.leerInt(message);
	}

	public void mostrarUnidadDidacticaNoExiste() {
		System.out.println("This Unidad Didactica doesn't exist");
	}
}
