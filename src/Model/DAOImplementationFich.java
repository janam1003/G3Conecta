package model;

import utils.MyObjectOutputStream;
import classes.ConvocatoriaExamen;
import classes.Enunciado;
import classes.UnidadDidactica;
import exceptions.ExceptionManager;
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
 * The DAOImplementationFich class is an implementation of the DAO (Data Access
 * Object) interface for file-based operations. It provides methods for reading
 * from and writing to files, specifically for handling ConvocatoriaExamen
 * objects.
 *
 * 2dam
 */
public class DAOImplementationFich implements DAO {

    String ficheroCon = "convocatorias.dat";
    File fichconvocatoria = new File(ficheroCon);

    @Override
    public void createUnidadDidactica(UnidadDidactica unidadDidactica) throws ExceptionManager {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long createEnunciado(Enunciado enunciado) throws ExceptionManager {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Creates a new ConvocatoriaExamen record by serializing the given
     * ConvocatoriaExamen object and writing it to a file.
     *
     * <p>
     * If the file already exists, the new ConvocatoriaExamen object is appended
     * to the existing data in the file. If the file does not exist, a new file
     * is created for storing ConvocatoriaExamen objects.
     * </p>
     *
     * @param convocatoriaExamen The ConvocatoriaExamen object to be created and
     * stored in the file.
     * @throws ExceptionManager If an exception occurs during the file write
     * operation.
     */
    @Override
    public void createConvocatoriaExamen(ConvocatoriaExamen convocatoriaExamen) throws ExceptionManager {

        if (fichconvocatoria.exists()) {
            try {
                MyObjectOutputStream oos = new MyObjectOutputStream(new FileOutputStream(fichconvocatoria, true));
                oos.writeObject(convocatoriaExamen);
                oos.close();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else {
            try {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichconvocatoria));
                oos.writeObject(convocatoriaExamen);
                oos.close();

            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * Updates the 'id_Enunciado' field of 'ConvocatoriaExamen' objects that
     * match the given 'convocatoria' with the provided 'enunciado' object's ID.
     *
     * @param enunciado The 'Enunciado' object containing the ID to update
     * 'ConvocatoriaExamen' objects.
     * @param convocatoriaExamen The 'ConvocatoriaExamen' object to be updated
     * based on 'convocatoria' matching.
     */
    public void updateIdUEnunciadoExamen(Enunciado enunciado, ConvocatoriaExamen convocatoriaExamen) {
        long id = enunciado.getId();
        String convocatoria = convocatoriaExamen.getConvocatoria();
        File fichAux = new File("auxiliar.dat");
        int cuantos;
        int borrar = 0;
        if (fichconvocatoria.exists()) {
            cuantos = Util.calculoFichero(fichconvocatoria);
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichconvocatoria));
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichAux));
                for (int i = 0; i < cuantos; i++) {
                    ConvocatoriaExamen conv = (ConvocatoriaExamen) ois.readObject();
                    if (conv.getConvocatoria().equalsIgnoreCase(convocatoria)) {
                        conv.setId_Enunciado(id);
                    }
                    oos.writeObject(conv);
                }

                oos.close();
                ois.close();

            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("Fatal error");
            } catch (ClassNotFoundException e) {

            }
            if (fichconvocatoria.delete() == false) {
                System.out.println("No ha sido posible borrar el fichero");
            }
            if (fichAux.renameTo(fichconvocatoria) == false) {
                System.out.println("No a sido posible renombrar el fichero");
            }

        } else {
            System.out.println("File not found");
        }
    }

    @Override
    public boolean ConsultUnidadDidactica(UnidadDidactica unidadDidactica) throws ExceptionManager {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Checks if a specific ConvocatoriaExamen object exists in the file by its
     * ID.
     *
     * <p>
     * This method reads ConvocatoriaExamen objects from the file and compares
     * their IDs with the given ConvocatoriaExamen's ID to determine if it
     * exists in the file.
     * </p>
     *
     * @param convocatoriaExamen The ConvocatoriaExamen object to check for
     * existence by ID.
     * @return True if the ConvocatoriaExamen with the same ID exists in the
     * file; false otherwise.
     * @throws ExceptionManager If an exception occurs during the file read
     * operation.
     */
    @Override
    public boolean ConsultConvocatoriaExamen(ConvocatoriaExamen convocatoriaExamen) throws ExceptionManager {
        int cuantos;
        boolean returnExist = false;
        if (fichconvocatoria.exists()) {
            cuantos = Util.calculoFichero(fichconvocatoria);
            try {
                FileInputStream fis = new FileInputStream(fichconvocatoria);
                ObjectInputStream ois = new ObjectInputStream(fis);
                for (int i = 0; i < cuantos; i++) {
                    ConvocatoriaExamen convocatoriaExamen2 = (ConvocatoriaExamen) ois.readObject();
                    if (Objects.equals(convocatoriaExamen.getConvocatoria(), convocatoriaExamen2.getConvocatoria())) {
                        returnExist = true;
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
        return returnExist;
    }

    /**
     * Retrieves a list of ConvocatoriaExamen objects associated with a specific
     * Enunciado from the file.
     *
     * <p>
     * This method reads ConvocatoriaExamen objects from the file and adds them
     * to a list if they are associated with the given Enunciado based on their
     * IDs.
     * </p>
     *
     * @param enunciado The Enunciado object to specify the search criteria.
     * @return A list of ConvocatoriaExamen objects associated with the
     * specified Enunciado.
     * @throws ExceptionManager If an exception occurs during the file read
     * operation or if the file doesn't exist.
     */
    @Override
    public List<ConvocatoriaExamen> ConsultConvocatoriasEnun(Enunciado enunciado) throws ExceptionManager {

        ConvocatoriaExamen newConvocatoriaExamen = null;
        ArrayList<ConvocatoriaExamen> list = new ArrayList<ConvocatoriaExamen>();

        if (fichconvocatoria.exists()) {

            try {

                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichconvocatoria));

                int count = Util.calculoFichero(fichconvocatoria);

                for (int i = 0; i < count; i++) {

                    newConvocatoriaExamen = new ConvocatoriaExamen();
                    newConvocatoriaExamen = (ConvocatoriaExamen) ois.readObject();

                    if (newConvocatoriaExamen.getId_Enunciado() == enunciado.getId()) {
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

                ExceptionManager e = new ExceptionManager("Doesn't work");

                throw e;
            }

        } else {

            ExceptionManager e = new ExceptionManager("Thhe file doesn't exist");

            throw e;
        }
    }

    @Override
    public List<Enunciado> ConsultEnunciadosUD(UnidadDidactica unidadDidactica) throws ExceptionManager {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ConsultEnunciadosUD'");
    }

    @Override
    public String getPathEnun(Enunciado enunciado) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPathEnun'");
    }

}
