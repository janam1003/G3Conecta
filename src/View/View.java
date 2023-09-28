package view;

import java.util.List;
import classes.ConvocatoriaExamen;
import classes.Enunciado;
import classes.UnidadDidactica;

public interface View {
	public void mostrarConvocatorias(List<ConvocatoriaExamen> convocatorias);
	public void mostrarEnunciados(List<Enunciado> enunciados);
	public int menuListadosEnunciado();
	public int seguirPrograma();
	public int getUserId(String userMessage);
	public void terminarPrograma();
	public void mostrarUnidadDidacticaNoExiste();
	public Enunciado setDatosEnun();
	public String askConvocatoriaExamen();
	public void mostrarConvocatoriaExamenNoExiste();
	public long askIdEnunciado();
	public void cantFindPath();
	public void showIdEnunciado(long id);
	public void visualizeDocument(String path);
	public void showIdUD(Integer id);
	public UnidadDidactica setDatosUD();
	public ConvocatoriaExamen setDatosConvocatoria();
}
