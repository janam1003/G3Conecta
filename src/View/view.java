package View;

import java.util.List;

import Classes.ConvocatoriaExamen;
import Classes.Enunciado;

/**
 *
 * @author Janam
 */
public class view {


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

	public void mostrarUnidadDidacticaNoExiste() {
        System.out.println("\nLa unidad didáctica no existe.");
    }
	
}
