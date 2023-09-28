package classes;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author Janam
 */
public class ConvocatoriaExamen implements Serializable{

    private String convocatoria;
    private String descripcion;
    private LocalDate fecha;
    private String curso;
	private Long id_Enunciado;

    public Long getId_Enunciado() {
		return id_Enunciado;
	}

	public void setId_Enunciado(Long id_Enunciado) {
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

}
