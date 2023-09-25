package Classes;

import Util.Util;

/**
 *
 * @author Janam
 */
public class UnidadDidactica {

    private Integer id;
    private String titulo;
    private String descripcion;
    private String evaluacion;
    private String acronimo;

    public UnidadDidactica() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(String evaluacion) {
        this.evaluacion = evaluacion;
    }

    public String getAcronimo() {
        return acronimo;
    }

    public void setAcronimo(String acronimo) {
        this.acronimo = acronimo;
    }

    public void setDatos(int id) {

        this.id = id;
        
        titulo = Util.introducirCadena("Insert the titulo: ");
        
        descripcion = Util.introducirCadena("Insert the UnidadDidactica descripcion: ");
        
        evaluacion = Util.introducirCadena("Insert the evaluacion: ");
        
        acronimo = Util.introducirCadena("Insert the acronimo: ");

    }

    public String getDatos() {
        
        return "UnidadDidactica{" + "id=" + id + ", titulo=" 
                + titulo + ", descripcion=" + descripcion + ","
                + "evaluacion=" + evaluacion + ", acronimo=" + acronimo + '}';
    }

}
