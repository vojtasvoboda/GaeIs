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
public class Admins {

    @PrimaryKey
    private Key id;

    @Persistent
    String real_name;

    @Persistent
    String user_name;

    @Persistent
    String role;

    /**
     * Default constructor
     */
    public Admins(String userName, String realName, String role) {
        this.user_name = userName;
        this.real_name = realName;
        this.role = role;
        this.id = KeyFactory.createKey(Admins.class.getSimpleName(), userName);
    }

    /**
     * Constructor
     * @param caption
     */
    public Key getKey() {
        return id;
    }

    public String getKeyWeb() {
        return KeyFactory.keyToString(this.id);
    }

    /**
     * Ziska id z klice
     * @return Id
     */
    public long getId() {
        return id.getId();
    }

    public String getIdString() {
        long kl = id.getId();
        return String.valueOf(kl);
    }

    public void setKey(Key id) {
        this.id = id;
    }

        public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

}
