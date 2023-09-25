package view;

import java.util.List;
import classes.ConvocatoriaExamen;
import classes.Enunciado;

public interface View {
	public void mostrarConvocatorias(List<ConvocatoriaExamen> convocatorias);
	public void mostrarEnunciados(List<Enunciado> enunciados);
	public int menuListadosEnunciado();
	public int seguirPrograma();
	public int getUserId(String userMessage);
	public void terminarPrograma();
	public void mostrarUnidadDidacticaNoExiste();
	public Enunciado setDatosEnun();
	public Integer askConvocatoriaExamen();
	public void mostrarConvocatoriaExamenNoExiste();
	public long askIdEnunciado();
}
