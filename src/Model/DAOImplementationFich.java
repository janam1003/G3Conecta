package Model;


import Classes.ConvocatoriaExamen;
import Classes.Enunciado;
import Classes.UnidadDidactica;
import Exceptions.ExceptionManager;
import Utils.MyObjectOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
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
    public void ConsultUnidadDidactica(UnidadDidactica unidadDidactica) throws ExceptionManager {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ConsultConvocatoriaExamen(ConvocatoriaExamen convocatoriaExamen) throws ExceptionManager {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List <ConvocatoriaExamen> ConsultConvocatoriasUD(UnidadDidactica unidadDidactica) throws ExceptionManager {
		
    	ConvocatoriaExamen newConvocatoriaExamen = null;
		ArrayList<ConvocatoriaExamen> list = new ArrayList<ConvocatoriaExamen>();

        if (fichconvocatoria.exists()) {

            try {

                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichconvocatoria));

                int count = Util.calculoFichero(fichconvocatoria);

                for (int i = 0; i < count; i++) {

                    newConvocatoriaExamen = new ConvocatoriaExamen();
                    newConvocatoriaExamen = (ConvocatoriaExamen) ois.readObject();

                    if (newConvocatoriaExamen.getId_UnidadDidactica() == unidadDidactica.getId()) {
							list.add(newConvocatoriaExamen);
                    }
                }
				ois.close();
				return list;

            } catch (FileNotFoundException ex) {

                ExceptionManager e = new ExceptionManager("The file doesn't exist");

                throw e;

            } catch (ClassNotFoundException ex) {

                ExceptionManager e = new ExceptionManager("Class not found");

                throw e;

            } catch (IOException ex) {

                ExceptionManager e = new ExceptionManager("Don't work");

                throw e;
            }

        } else {

            ExceptionManager e = new ExceptionManager("Thhe file doesn't exist");

            throw e;
        }
    }

   // @Override
   // public List <Enunciado> ConsultEnunciadosUD(UnidadDidactica unidadDidactica) throws ExceptionManager {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  //  }

}
