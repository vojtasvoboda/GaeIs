package persistence;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import javax.jdo.Query;
import java.util.List;
import javax.jdo.PersistenceManager;

/**
 * DAO pro praci s kategoriemi
 * @author Bc. Vojtěch Svoboda <svobovo3@fel.cvut.cz>
 */
public class SupplierDAO {

    public PersistenceManager pm;

    public SupplierDAO() {
        pm = PMF.get().getPersistenceManager();
    }

    /**
     * Ulozi polozku
     * @param polozka
     */
    public void save(Supplier c) {
        try {
            pm.makePersistent(c);
            pm.flush();
            pm.refreshAll();
        } catch (Exception e) {
            System.out.println("SupplierDAO: chyba save(): " + e);
        } finally {
            //pm.close();
        }
    }

    /**
     * Smazani polozky
     * @param dodavatel
     */
    public void delete(Supplier cat) {
        try {
            pm.deletePersistent(cat);
        } catch (Exception e) {
            System.out.println("SupplierDAO: Chyba delete(): " + e);
        } finally {
           // pm.close();
        }
    }

    /**
     * Ziska list vsech kategorii
     * @return List polozek
     */
    public List<Supplier> getAll() {
        List<Supplier> list = null;
        try {
            Query query = pm.newQuery(Supplier.class);
            list = (List<Supplier>) query.execute();
            return list;
        } catch (Exception e) {
            System.out.println("SupplierDAO: chyba getAll(): " + e);
        } finally {
            // nesmime uzavirat, aby to slo vypsat
            //pm.close();
        }
        return null;
    }

    /**
     * Zjistime kategorii dle klice
     * @param k
     * @return
     */
    public Supplier get(Key k) {
        Supplier c;
        try {
            c = pm.getObjectById(Supplier.class, k);
        } finally {
            // pm.close();
        }
        return c;
    }

    /**
     * Zjistime kategorii jenom podle id
     * @param id
     * @return
     */
    public Supplier get(String id) {
        Supplier c;
        // musime vytvorit klic
        // Key k = KeyFactory.createKey(Supplier.class.getSimpleName(),id);
        Key k = KeyFactory.stringToKey(id);
        System.out.println("SupplierDAO: key pro smazání je " + k);
        try {
            c = pm.getObjectById(Supplier.class, k);
        } finally {
            // pm.close();
        }
        return c;
    }

    public void closePM(){
           pm.close();
    }
}
