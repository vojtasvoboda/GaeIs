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
public class OrderxDAO {

    public PersistenceManager pm;

    public OrderxDAO() {
        pm = PMF.get().getPersistenceManager();
    }

    /**
     * Ulozi polozku
     * @param polozka
     */
    public void save(Orderx c) {
        try {
            pm.makePersistent(c);
        } catch (Exception e) {
            System.out.println("OrderxDAO: chyba save(): " + e);
        } finally {
            // pm.close();
        }
    }

    /**
     * Smazani kategorie
     * @param dodavatel
     */
    public void delete(Orderx cat) {
        try {
            pm.deletePersistent(cat);
        } catch (Exception e) {
            System.out.println("OrderxDAO: Chyba delete(): " + e);
        } finally {
            // pm.close();
        }
    }

    /**
     * Ziska list vsech kategorii
     * @return List polozek
     */
    public List<Orderx> getAll() {
        List<Orderx> list = null;
        try {
            Query query = pm.newQuery(Orderx.class);
            list = (List<Orderx>) query.execute();
            return list;
        } catch (Exception e) {
            System.out.println("OrderxDAO: chyba getAll(): " + e);
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
    public Orderx get(Key k) {
        Orderx c;
        try {
            c = pm.getObjectById(Orderx.class, k);
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
    public Orderx get(String id) {
        Orderx c;
        // musime vytvorit klic
        Key k = KeyFactory.createKey(Orderx.class.getSimpleName(),Long.parseLong(id));
        System.out.println("OrderxDAO: key pro smazání je " + k);
        try {
            c = pm.getObjectById(Orderx.class, k);
        } finally {
            // pm.close();
        }
        return c;
    }
     public void closePM(){
           pm.close();
    }
}
