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
        
        FileOutputStream fos = null;

        MyObjectOutputStream moos = null;

        ObjectOutputStream oos = null;

        ConvocatoriaExamen newConvocatoriaExamen = new ConvocatoriaExamen();

        convocatoriaExamen.setId(Util.calculoFichero(fichconvocatoria));

        try {

            if (fichconvocatoria.exists()) {

                fos = new FileOutputStream(fichconvocatoria, true);

                moos = new MyObjectOutputStream(fos);

                newConvocatoriaExamen = getConvocatoriaExamenData(convocatoriaExamen);

                if (newConvocatoriaExamen == null) {

                    moos.writeObject(convocatoriaExamen);

                } else {

                    ExceptionManager e = new ExceptionManager("The user exist");

                    throw e;
                }

            } else {

                fos = new FileOutputStream(fichconvocatoria);
                oos = new ObjectOutputStream(fos);
            }

            moos.close();

            oos.close();

            fos.close();

        } catch (FileNotFoundException ex) {

            ExceptionManager e = new ExceptionManager("The file doesn't exist");

            throw e;

        } catch (IOException ex) {

            ExceptionManager e = new ExceptionManager("Don't work");

            throw e;

        }
    }

    @Override
    public void ConsultUnidadDidactica(UnidadDidactica unidadDidactica) throws ExceptionManager {
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
