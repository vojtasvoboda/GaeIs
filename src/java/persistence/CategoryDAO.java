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
public class CategoryDAO {

    public PersistenceManager pm;

    public CategoryDAO() {
        pm = PMF.get().getPersistenceManager();
    }

    /**
     * Ulozi polozku
     * @param polozka
     */
    public void save(Category c) {
        System.out.println("CategoryDAO: pm je " + pm.toString());
        try {
            pm.makePersistent(c);
        } catch (Exception e) {
            System.out.println("CategoryDAO: chyba save(): " + e);
        } finally {
            //pm.close();
        }
    }

    /**
     * Smazani kategorie
     * @param dodavatel
     */
    public void delete(Category cat) {
        try {
            pm.deletePersistent(cat);
        } catch (Exception e) {
            System.out.println("CategoryDAO: Chyba delete(): " + e);
        } finally {
            //pm.close();
        }
    }

    /**
     * Ziska list vsech kategorii
     * @return List polozek
     */
    public List<Category> getAll() {
        List<Category> list = null;
        try {
            Query query = pm.newQuery(Category.class);
            list = (List<Category>) query.execute();
            return list;
        } catch (Exception e) {
            System.out.println("CategoryDAO: chyba getAll(): " + e);
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
    public Category get(Key k) {
        Category c;
        try {
            c = pm.getObjectById(Category.class, k);
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
    public Category get(String id) {
        Category c;
        // musime vytvorit klic
        Key k = KeyFactory.createKey(Category.class.getSimpleName(),Long.parseLong(id));
        System.out.println("CategoryDAO: key pro smazání je " + k);
        try {
            c = pm.getObjectById(Category.class, k);
        } finally {
            // pm.close();
        }
        return c;
    }

     public void closePM(){
           pm.close();
    }
}
