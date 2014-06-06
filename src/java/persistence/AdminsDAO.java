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
public class AdminsDAO {

    public PersistenceManager pm;

    public AdminsDAO() {
        pm = PMF.get().getPersistenceManager();
    }

    /**
     * Ulozi polozku
     * @param polozka
     */
    public void save(Admins c) {
        
        try {
            pm.makePersistent(c);
        } catch (Exception e) {
           
        } finally {
            //pm.close();
        }
    }

    /**
     * Smazani kategorie
     * @param dodavatel
     */
    public void delete(Admins adm) {
        try {
            pm.deletePersistent(adm);
        } catch (Exception e) {

        } finally {
            //pm.close();
        }
    }

    /**
     * Ziska list vsech kategorii
     * @return List polozek
     */
    public List<Admins> getAll() {
        List<Admins> list = null;
        try {
            Query query = pm.newQuery(Admins.class);
            list = (List<Admins>) query.execute();
            return list;
        } catch (Exception e) {
            
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
    public Admins get(Key k) {
        Admins c;
        try {
            c = pm.getObjectById(Admins.class, k);
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
    public Admins get(String id) {
        Admins c;
        // musime vytvorit klic
        Key k = KeyFactory.createKey(Admins.class.getSimpleName(),Long.parseLong(id));
        
        try {
            c = pm.getObjectById(Admins.class, k);
        } finally {
            // pm.close();
        }
        return c;
    }

    /**
     * Zavre PersistentManagera
     */
    public void closePM(){
           pm.close();
    }

    /**
     * Vrati uzivatelskou roli uzivatele dle userName
     * @param userName
     * @return
     */
    public String getRole(String userName){

        String s = "";
        Query query = pm.newQuery(Admins.class);
        query.setFilter("user_name == user_name_param");
        query.declareParameters("String user_name_param");
        System.out.println("queryParameter: " + userName);

        try {
            List<Admins> results = (List<Admins>) query.execute(userName);
            if (results.iterator().hasNext()) {
                for (Admins e : results) {
                    return e.getRole();
                }
            } else {
                return "Host";
            }

        } finally {
            query.closeAll();
        }
        return s;
    }

}
