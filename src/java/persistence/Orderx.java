package persistence;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entita Order / Objednavka
 * Orderx kvuli tomu, ze Order je rezervovane slovo
 * @author Bc. Vojtěch Svoboda <svobovo3@fel.cvut.cz>
 */
@PersistenceCapable
@XmlRootElement
public class Orderx {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key id;

    /* FK supplier / dodavatel */
    @Persistent
    int id_supplier;

    @Persistent
    String date_ordered;
    @Persistent
    int amount;
    @Persistent
    String note;
    @Persistent
    String webkey;

    /**
     * Default constructor
     */
    public Orderx() {
    }

    /**
     * Constructor
     * @param id_supplier
     * @param date_ordered
     * @param amount
     * @param note
     */
    public Orderx(int id_supplier, String date_ordered, int amount, String note) {
        this.id_supplier = id_supplier;
        this.date_ordered = date_ordered;
        this.amount = amount;
        this.note = note;
        this.webkey = "";
    }

    public final String getWebkey() {
        return KeyFactory.keyToString(id);
    }

    public void setWebkey(String keyWeb) {
        this.webkey = keyWeb;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDate_ordered() {
        return date_ordered;
    }

    public void setDate_ordered(String date_ordered) {
        this.date_ordered = date_ordered;
    }

    public Key getIdKey() {
        return id;
    }

    public long getId() {
        return id.getId();
    }

    public String getIdString() {
        long k = id.getId();
        return String.valueOf(k);
    }

    public void setId(Key id) {
        this.id = id;
    }

    public String getKeyWeb() {
        return KeyFactory.keyToString(this.id);
    }

    public int getId_supplier() {
        return id_supplier;
    }

    public void setId_supplier(int id_supplier) {
        this.id_supplier = id_supplier;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
