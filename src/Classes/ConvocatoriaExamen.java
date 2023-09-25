package Classes;

import java.time.LocalDate;
import Util.Util;

/**
 *
 * @author Janam
 */
public class ConvocatoriaExamen {

    private Integer id;
    private String convocatoria;
    private String descripcion;
    private LocalDate fecha;
    private String curso;
    private Integer id_UnidadDidactica;

    public ConvocatoriaExamen() {
    }

    public Integer getId_UnidadDidactica() {
        return id_UnidadDidactica;
    }

    public void setId_UnidadDidactica(Integer id_UnidadDidactica) {
        this.id_UnidadDidactica = id_UnidadDidactica;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConvocatoria() {
        return convocatoria;
    }

    public void setConvocatoria(String convocatoria) {
        this.convocatoria = convocatoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public void setDatos(int id) {
        
         this.id = id;

        convocatoria = Util.introducirCadena("Insert the Convocatoria: ");
        
        descripcion = Util.introducirCadena("Insert the ConvocatoriaExamen descripcion: ");
        
        fecha = LocalDate.now(); //we set the creation date to now.
        
        curso = Util.introducirCadena("Insert the curso: ");

    }

    public String getDatos() {
        
        return "ConvocatoriaExamen{" + "id=" + id + ", convocatoria=" 
                
                + convocatoria + ", descripcion=" + descripcion + ", "
                
                + "fecha=" + fecha + ", curso=" + curso + ", "
                
                + "id_UnidadDidactica=" + id_UnidadDidactica + '}';
    }

}
