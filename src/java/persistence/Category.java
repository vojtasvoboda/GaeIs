package persistence;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * Entita Category / Kategorie pro polozky
 * @author Bc. Vojtěch Svoboda <svobovo3@fel.cvut.cz>
 */
@PersistenceCapable(detachable="true")
public class Category {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key klic;

    @Persistent
    String caption;

    /**
     * Default constructor
     */
    public Category() {
    }

    /**
     * Constructor
     * @param caption
     */
    public Category(String caption) {
        this.caption = caption;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Key getKey() {
        return klic;
    }

    public String getKeyWeb() {
        return KeyFactory.keyToString(this.klic);
    }

    /**
     * Ziska id z klice
     * @return Id
     */
    public long getId() {
        return klic.getId();
    }

    public String getIdString() {
        long kl = klic.getId();
        return String.valueOf(kl);
    }

    public void setKey(Key id) {
        this.klic = id;
    }
}
