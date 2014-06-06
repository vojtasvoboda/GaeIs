package persistence;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entita dodavatele
 * @author Bc. Vojtěch Svoboda <svobovo3@fel.cvut.cz>
 */
@PersistenceCapable
@XmlRootElement
public class Supplier {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key ID_SUPPLIER;

    @Persistent
    String ico;
    @Persistent
    String dic;
    @Persistent
    String address;
    @Persistent
    String name;
    @Persistent
    String contact;
    @Persistent
    String webkey;

    /**
     * Default constructor
     */
    public Supplier() {
    }

    @Override
    public String toString() {
        return "Supplier{" + "key=" + ID_SUPPLIER.toString() + ", name=" + name + '}';
    }

    /**
     * Constructor
     * @param key
     * @param ico
     * @param dic
     * @param address
     * @param name
     * @param contact
     */
    public Supplier(String ico, String dic, String address, String name, String contact) {
        //this.id = KeyFactory.createKey(Supplier.class.getSimpleName(), ico);
        this.ico = ico;
        this.dic = dic;
        this.address = address;
        this.name = name;
        this.contact = contact;
        this.webkey = "";
    }

    public final String getWebkey() {
        return KeyFactory.keyToString(ID_SUPPLIER);
    }

    public void setWebkey(String keyWeb) {
        this.webkey = keyWeb;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDic() {
        return dic;
    }

    public void setDic(String dic) {
        this.dic = dic;
    }

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    public Key getKey() {
        return ID_SUPPLIER;
    }

    public String getKeyWeb() {
        return KeyFactory.keyToString(ID_SUPPLIER);
    }

    public long getId() {
        return ID_SUPPLIER.getId();
    }

    public void setKey(Key key) {
        this.ID_SUPPLIER = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
