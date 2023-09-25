package Model;

import Classes.ConvocatoriaExamen;
import Classes.Enunciado;
import Classes.UnidadDidactica;
import Exceptions.ExceptionManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import Util.Util;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Objects;
import utility.MyObjectOutputStream;

/**
 *
 * @author Janam
 */
public class DAOImplementationFich implements DAO {
    
    
    File fichconvocatoria = new File("convocatorias.dat");

    @Override
    public void createUnidadDidactica(UnidadDidactica unidadDidactica) throws ExceptionManager {
        // It is only done in DataBase
    }

    @Override
    public void createEnunciado(Enunciado enunciado) throws ExceptionManager {
        // It is only done in DataBase
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

                newConvocatoriaExamen = ConsultConvocatoriaExamen(convocatoriaExamen);
                
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
    public boolean ConsultUnidadDidactica(UnidadDidactica unidadDidactica) throws ExceptionManager {
        throw new UnsupportedOperationException("Not supported yet.");
        // It is only done in DataBase
    }

    @Override
    public ConvocatoriaExamen ConsultConvocatoriaExamen(ConvocatoriaExamen convocatoriaExamen) throws ExceptionManager {

        ConvocatoriaExamen newConvocatoriaExamen = null;

        if (fichconvocatoria.exists()) {
            FileInputStream fis;
            ObjectInputStream ois;
            try {
                fis = new FileInputStream(fichconvocatoria);
                ois = new ObjectInputStream(fis);

                int count = Util.calculoFichero(fichconvocatoria);

                for (int i = 0; i < count; i++) {
                    newConvocatoriaExamen = new ConvocatoriaExamen();
                    newConvocatoriaExamen = (ConvocatoriaExamen) ois.readObject();

                    if (!Objects.equals(newConvocatoriaExamen.getId(), convocatoriaExamen.getId())) {
                    } else {
                        i = count;
                    }
                }

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
            return newConvocatoriaExamen;

        } else {
            ExceptionManager e = new ExceptionManager("Thhe file doesn't exist");
            throw e;
        }
    }

    @Override
    public List<ConvocatoriaExamen> ConsultConvocatoriasUD(UnidadDidactica unidadDidactica) throws ExceptionManager {

        ConvocatoriaExamen newConvocatoriaExamen = null;

        ArrayList<ConvocatoriaExamen> list = new ArrayList<>();

        if (fichconvocatoria.exists()) {

            try {

                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichconvocatoria))) {

                    int count = Util.calculoFichero(fichconvocatoria);

                    for (int i = 0; i < count; i++) {

                        newConvocatoriaExamen = new ConvocatoriaExamen();

                        newConvocatoriaExamen = (ConvocatoriaExamen) ois.readObject();

                        if (Objects.equals(newConvocatoriaExamen.getId_UnidadDidactica(), unidadDidactica.getId())) {

                            list.add(newConvocatoriaExamen);
                        }
                    }
                }

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

    @Override
    public List<Enunciado> ConsultEnunciadosUD(UnidadDidactica unidadDidactica) throws ExceptionManager {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
