package View;

import Classes.ConvocatoriaExamen;
import Classes.Enunciado;
import Classes.UnidadDidactica;
import Exceptions.ExceptionManager;
import Model.DAO;
import Util.Util;
import java.util.List;

/**
 *
 * @author Janam
 */
public class view {

    public void mostrarCreateUnidadDidactica(UnidadDidactica unidadDidactica) {
        System.out.println("\nUnidad didáctica creada: ");
        System.out.println(unidadDidactica.toString());
    }

    public void mostrarCreateEnunciado(Enunciado enunciado) {
        System.out.println("\nEnunciado creado: ");
        System.out.println(enunciado.toString());
    }

    public void mostrarCreateConvocatoriaExamen(ConvocatoriaExamen convocatoriaExamen) {
        System.out.println("\nConvocatoriaExamen creado: ");
        System.out.println(convocatoriaExamen.toString());
    }

    public void mostrarConvocatorias(List<ConvocatoriaExamen> convocatorias) {
        System.out.println("\nLista de convocatorias: ");
        convocatorias.forEach((convocatoria) -> {
            System.out.println(convocatoria.toString());
        });
    }

    public void mostrarEnunciados(List<Enunciado> enunciados) {
        System.out.println("\nLista de enunciados: ");
        enunciados.forEach((enunciado) -> {
            System.out.println(enunciado.toString());
        });
    }

    public void mostrarUnidadDidacticaNoExiste() {
        System.out.println("\nLa unidad didáctica no existe.");
    }

    public void run(DAO model) throws ExceptionManager {
    }

}
