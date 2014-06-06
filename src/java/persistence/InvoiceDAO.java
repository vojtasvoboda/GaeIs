package persistence;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import javax.jdo.Query;
import java.util.List;
import javax.jdo.PersistenceManager;

/**
 * DAO pro praci s fakturami
 * @author Bc. Vojtěch Svoboda <svobovo3@fel.cvut.cz>
 */
public class InvoiceDAO {

    public PersistenceManager pm;

    public InvoiceDAO() {
        pm = PMF.get().getPersistenceManager();
    }

    /**
     * Ulozi polozku
     * @param polozka
     */
    public void save(Invoice c) {
        try {
            pm.makePersistent(c);
        } catch (Exception e) {
            System.out.println("InvoiceDAO: chyba save(): " + e);
        } finally {
           // pm.close();
        }
    }

    /**
     * Smazani kategorie
     * @param dodavatel
     */
    public void delete(Invoice cat) {
        try {
            pm.deletePersistent(cat);
        } catch (Exception e) {
            System.out.println("InvoiceDAO: Chyba delete(): " + e);
        } finally {
           // pm.close();
        }
    }

    /**
     * Ziska list vsech kategorii
     * @return List polozek
     */
    public List<Invoice> getAll() {
        List<Invoice> list = null;
        try {
            Query query = pm.newQuery(Invoice.class);
            list = (List<Invoice>) query.execute();
            return list;
        } catch (Exception e) {
            System.out.println("InvoiceDAO: chyba getAll(): " + e);
        } finally {
            // nesmime uzavirat, aby to slo vypsat
            // pm.close();
        }
        return null;
    }

    /**
     * Zjistime kategorii dle klice
     * @param k
     * @return
     */
    public Invoice get(Key k) {
        Invoice c;
        try {
            c = pm.getObjectById(Invoice.class, k);
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
    public Invoice get(String id) {
        Invoice c;
        // musime vytvorit klic
        // Key k = KeyFactory.createKey(Invoice.class.getSimpleName(),Long.parseLong(id));
        Key k = KeyFactory.stringToKey(id);
        System.out.println("InvoiceDAO: key pro smazání je " + k);
        try {
            c = pm.getObjectById(Invoice.class, k);
        } finally {
            // pm.close();
        }
        return c;
    }

     public void closePM(){
           pm.close();
    }
}
