package Classes;

import java.util.List;
import utils.Util;

/**
 *
 * @author Janam
 */
public class Enunciado {

    private Integer id;
    private boolean disponible;
    private String descripcion;
    private String ruta;
    private DificultadType nivel;
    private List<UnidadDidactica> unidadDidacticas;

    public Enunciado() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public DificultadType getNivel() {
        return nivel;
    }

    public void setNivel(DificultadType nivel) {
        this.nivel = nivel;
    }

    public List<UnidadDidactica> getUnidadDidacticas() {
        return unidadDidacticas;
    }

    public void setUnidadDidacticas(List<UnidadDidactica> unidadDidacticas) {
        this.unidadDidacticas = unidadDidacticas;
    }

    public void setDatos(int id) {

        this.descripcion = Util.introducirCadena("Insert the description: ");

        char x = Util.leerChar("Set your nivel type in A for Alta, M for Media"
                + " B for Baja: ", 'A', 'M', 'B');

        switch (x) {
            case 'A':
                nivel = DificultadType.ALTA;
                break;
            case 'M':
                nivel = DificultadType.MEDIA;
                break;
            case 'B':
                nivel = DificultadType.BAJA;
                break;
        }

        this.ruta = Util.introducirCadena("Ruta: ");

        this.disponible = Util.esBoolean("Is it available? ( Enter 'true' or 'false') | ( 'si' or 'no': ");

    }

    public String getDatos() {
        return "Enunciado{" + "id=" + id + ", disponible=" + disponible + ", descripcion=" + descripcion + ", ruta=" + ruta + ", nivel=" + nivel + ", unidadDidacticas=" + unidadDidacticas + '}';
    }

}
