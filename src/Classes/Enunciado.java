package classes;

import java.util.List;
import utils.Util;

/**
 *
 * @author Janam
 */
public class Enunciado {

    private long id;
    private boolean disponible;
    private String descripcion;
    private String ruta;
    private DificultadType nivel;
    private List<UnidadDidactica> unidadDidacticas;

	public Enunciado() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public void setNivel(char x) {
        switch (x) {

            case 'A':
                this.nivel = DificultadType.ALTA;
                break;

            case 'M':
                this.nivel = DificultadType.MEDIA;
                break;

            case 'B':
                this.nivel = DificultadType.BAJA;
                break;

        }
        this.nivel = nivel;
    }

    public List<UnidadDidactica> getUnidadDidacticas() {
        return unidadDidacticas;
    }

    public void setUnidadDidacticas(List<UnidadDidactica> unidadDidacticas) {
        this.unidadDidacticas = unidadDidacticas;
    }

}
