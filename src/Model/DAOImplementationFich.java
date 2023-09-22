package Model;

import Classes.ConvocatoriaExamen;
import Classes.Enunciado;
import Classes.UnidadDidactica;
import Exceptions.ExceptionManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Objects;
import utils.Util;

/**
 *
 * @author Janam
 */
public class DAOImplementationFich implements DAO {

    String ficheroCon = "convocatorias.dat";
    File fichconvocatoria = new File(ficheroCon);
    
    @Override
    public void createUnidadDidactica(UnidadDidactica unidadDidactica) throws ExceptionManager {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createEnunciado(Enunciado enunciado) throws ExceptionManager {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createConvocatoriaExamen(ConvocatoriaExamen convocatoriaExamen) throws ExceptionManager {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean ConsultUnidadDidactica(UnidadDidactica unidadDidactica) throws ExceptionManager {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean ConsultConvocatoriaExamen(ConvocatoriaExamen convocatoriaExamen) throws ExceptionManager {
		// TODO Auto-generated method stub
                boolean esta = false;
		int cuantos;
		if (fichconvocatoria.exists()) {
			cuantos = Util.calculoFichero(fichconvocatoria);
			try {
				FileInputStream fis = new FileInputStream(fichconvocatoria);
				ObjectInputStream ois = new ObjectInputStream(fis);
				for (int i = 0; i < cuantos; i++) {
					ConvocatoriaExamen convocatoriaExamen2 = (ConvocatoriaExamen) ois.readObject();
					if (Objects.equals(convocatoriaExamen.getId(), convocatoriaExamen2.getId())) {
						esta=true;
					}
				}
				ois.close();
			} catch (FileNotFoundException e) {
				System.out.println("File not found");
			} catch (IOException e) {
				System.out.println("Error IO");
			} catch (ClassNotFoundException e) {
				System.out.println("Error en el tipo de datos");
			}
		} else {
			System.out.println("File not found");
		}
                return esta;
	}

    @Override
    public void ConsultConvocatoriasUD(ConvocatoriaExamen convocatoriaExamen) throws ExceptionManager {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ConsultEnunciadosUD(Enunciado enunciado) throws ExceptionManager {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
