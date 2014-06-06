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
public class ItemDAO {

    public PersistenceManager pm;

    public ItemDAO() {
        pm = PMF.get().getPersistenceManager();
    }

    /**
     * Ulozi polozku
     * @param polozka
     */
    public void save(Item c) {
        try {
            pm.makePersistent(c);
        } catch (Exception e) {
            System.out.println("ItemDAO: chyba save(): " + e);
        } finally {
            //pm.close();
        }
    }

    /**
     * Smazani polozky
     * @param dodavatel
     */
    public void delete(Item cat) {
        try {
            pm.deletePersistent(cat);
        } catch (Exception e) {
            System.out.println("ItemDAO: Chyba delete(): " + e);
        } finally {
            //pm.close();
        }
    }

    /**
     * Ziska list vsech polozek
     * @return List polozek
     */
    public List<Item> getAll() {
        List<Item> list = null;
        try {
            Query query = pm.newQuery(Item.class);
            list = (List<Item>) query.execute();
            return list;
        } catch (Exception e) {
            System.out.println("ItemDAO: chyba getAll(): " + e);
        } finally {
            // nesmime uzavirat, aby to slo vypsat
            // pm.close();
        }
        return null;
    }

    /**
     * Zjistime polozku dle klice
     * @param k
     * @return
     */
    public Item get(Key k) {
        Item c;
        try {
            c = pm.getObjectById(Item.class, k);
        } finally {
            // pm.close();
        }
        return c;
    }

    /**
     * Zjistime polozku jenom podle id
     * @param id
     * @return
     */
    public Item get(String id) {
        Item c;
        // musime vytvorit klic
        Key k = KeyFactory.createKey(Item.class.getSimpleName(),Long.parseLong(id));
        System.out.println("ItemDAO: key pro smazání je " + k);
        try {
            c = pm.getObjectById(Item.class, k);
        } finally {
            // pm.close();
        }
        return c;
    }
    //uzavreme persistance managera
    public void closePM(){
           pm.close();
    }
}
