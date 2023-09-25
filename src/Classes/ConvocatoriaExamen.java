package classes;

import java.io.Serializable;
import java.time.LocalDate;
import utils.Util;

/**
 *
 * @author Janam
 */
public class ConvocatoriaExamen implements Serializable {

    private String convocatoria;
    private String descripcion;
    private LocalDate fecha;
    private String curso;
    private Integer id_Enunciado;

    public Integer getId_Enunciado() {
		return id_Enunciado;
	}

	public void setId_Enunciado(Integer id_Enunciado) {
		this.id_Enunciado = id_Enunciado;
	}

	public ConvocatoriaExamen() {
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

    public void setDatos() {
        convocatoria = Util.introducirCadena("Insert the Convocatoria: ");
        descripcion = Util.introducirCadena("Insert the ConvocatoriaExamen descripcion: ");
        fecha = LocalDate.now(); //we set the creation date to now.
        curso = Util.introducirCadena("Insert the curso: ");

    }

    @Override
    public String toString() {
        return "ConvocatoriaExamen{" + "convocatoria=" + convocatoria + ", descripcion=" + descripcion + ", fecha=" + fecha + ", curso=" + curso + ", id_Enunciado=" + id_Enunciado + '}';
    }



}
