package view;

import java.util.ArrayList;
import java.util.List;

import classes.ConvocatoriaExamen;
import classes.Enunciado;
import classes.UnidadDidactica;
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

	@Override
	public Enunciado setDatosEnun() {
		Enunciado enunciado = new Enunciado();
		List <UnidadDidactica> unidadDidacticas = new ArrayList<>() ;

		enunciado.setDescripcion(Util.introducirCadena("Write the Enunciado's description"));
		enunciado.setRuta(Util.introducirCadena("Write the Enunciado's route"));
		enunciado.setNivel(Util.leerChar("Set your nivel type in A for Alta, M for Media"
		+ "B for Baja: ", 'A', 'M', 'B'));
		if (Util.leerInt("Write 1 if the Enunciado is available or 0 if not", 0, 1) == 1)
			enunciado.setDisponible(true);
		else
			enunciado.setDisponible(false);
		while (Util.leerInt("Write 1 if you want to add a Unidad Didactica or 0 if not", 0, 1) == 1) {
			UnidadDidactica unidadDidactica = new UnidadDidactica();
			unidadDidactica.setId(Util.leerInt("Write the Unidad Didactica's id you want"));
			unidadDidacticas.add(unidadDidactica);
		}
		enunciado.setUnidadDidacticas(unidadDidacticas);
		return enunciado;
	}

	@Override
	public Integer askConvocatoriaExamen() {
		return Util.leerInt("Write the Convocatoria Examen's id you want or -1 if you don't want to add it");
	}

	@Override
	public void mostrarConvocatoriaExamenNoExiste() {
		System.out.println("This Convocatoria Examen doesn't exist");
	}

	@Override
	public long askIdEnunciado() {
		double inputValue = Util.leerDouble("Write the enunciado's id you want to visualize");
		long id = (long) inputValue;
		return id;
	}

}
