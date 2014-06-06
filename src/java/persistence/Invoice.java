package persistence;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Persistent;

/**
 * Entita Invoice / Faktura
 * @author Bc. Vojtěch Svoboda <svobovo3@fel.cvut.cz>
 */
@PersistenceCapable
public class Invoice {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key id;

    /* FK */
    @Persistent
    int id_order;

    @Persistent
    String date_to_by_paid;

    /**
     * Default constructor
     */
    public Invoice() {
    }

    /**
     * Constructor
     * @param id_supplier
     * @param id_order
     * @param amount
     * @param date_to_by_paid
     */
    public Invoice(int id_order, String date_to_by_paid) {
        this.id_order = id_order;
        this.date_to_by_paid = date_to_by_paid;
    }

    public String getDate_to_by_paid() {
        return date_to_by_paid;
    }

    public void setDate_to_by_paid(String date_to_by_paid) {
        this.date_to_by_paid = date_to_by_paid;
    }

    public Key getId() {
        return id;
    }

    public void setId(Key id) {
        this.id = id;
    }

    public String getKeyWeb() {
        return KeyFactory.keyToString(this.id);
    }

    public int getId_order() {
        return id_order;
    }

    public void setId_order(int id_order) {
        this.id_order = id_order;
    }

}
